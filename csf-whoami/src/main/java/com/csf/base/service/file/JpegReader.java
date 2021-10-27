package com.csf.base.service.file;

import java.awt.color.ColorSpace;
import java.awt.color.ICC_ColorSpace;
import java.awt.color.ICC_Profile;
import java.awt.image.BufferedImage;
import java.awt.image.ColorConvertOp;
import java.awt.image.Raster;
import java.awt.image.WritableRaster;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
 
import javax.imageio.IIOException;
import javax.imageio.ImageIO;
import javax.imageio.ImageReader;
import javax.imageio.stream.ImageInputStream;
 
import org.apache.sanselan.ImageReadException;
import org.apache.sanselan.Sanselan;
import org.apache.sanselan.common.byteSources.ByteSource;
import org.apache.sanselan.common.byteSources.ByteSourceFile;
import org.apache.sanselan.formats.jpeg.JpegImageParser;
import org.apache.sanselan.formats.jpeg.segments.UnknownSegment;
 
public class JpegReader {
	
    public static final int COLOR_TYPE_RGB = 1;
    public static final int COLOR_TYPE_CMYK = 2;
    public static final int COLOR_TYPE_YCCK = 3;
 
    private int colorType = COLOR_TYPE_RGB;
    private boolean hasAdobeMarker = false;
 
    public BufferedImage readImage(File file) throws IOException, ImageReadException {
        colorType = COLOR_TYPE_RGB;
        hasAdobeMarker = false;
 
        ImageInputStream stream = ImageIO.createImageInputStream(file);
        Iterator<ImageReader> iter = ImageIO.getImageReaders(stream);
        while (iter.hasNext()) {
            ImageReader reader = iter.next();
            reader.setInput(stream);
 
            BufferedImage image;
            ICC_Profile profile = null;
            try {
                image = reader.read(0);
                reader.reset(); //대량 변환의 경우, 메모리 누수 막아중
            } catch (IIOException e) {
                colorType = COLOR_TYPE_CMYK;
                checkAdobeMarker(file);
                profile = Sanselan.getICCProfile(file);
                WritableRaster raster = (WritableRaster) reader.readRaster(0, null);
                reader.reset(); //대량 변환의 경우, 메모리 누수 막아중
                
                if (colorType == COLOR_TYPE_YCCK){
                    convertYcckToCmyk(raster);
                }
                 
                if (hasAdobeMarker){
                    convertInvertedColors(raster);
                }
                image = convertCmykToRgb(raster, profile);
                image.flush();
            } finally {
                stream.close(); //원본에는 close해주는 부분이 없어서 추가해 줬음, 위치상으로 애매한데 리턴 전이라 그냥 닫아줌
            }
 
            return image;
        }
 
        return null;
    }
 
    public void checkAdobeMarker(File file) throws IOException, ImageReadException {
        JpegImageParser parser = new JpegImageParser();
        ByteSource byteSource = new ByteSourceFile(file);
        @SuppressWarnings("rawtypes")
        ArrayList segments = parser.readSegments(byteSource, new int[] { 0xffee }, true);
        if (segments != null && segments.size() >= 1) {
            UnknownSegment app14Segment = (UnknownSegment) segments.get(0);
            byte[] data = app14Segment.bytes;
            if (data.length >= 12 && data[0] == 'A' && data[1] == 'd' && data[2] == 'o' && data[3] == 'b' && data[4] == 'e')
            {
                hasAdobeMarker = true;
                int transform = app14Segment.bytes[11] & 0xff;
                if (transform == 2)
                    colorType = COLOR_TYPE_YCCK;
            }
        }
    }
 
    public static void convertYcckToCmyk(WritableRaster raster) {
        int height = raster.getHeight();
        int width = raster.getWidth();
        int stride = width * 4;
        int[] pixelRow = new int[stride];
        for (int h = 0; h < height; h++) {
            raster.getPixels(0, h, width, 1, pixelRow);
 
            for (int x = 0; x < stride; x += 4) {
                int y = pixelRow[x];
                int cb = pixelRow[x + 1];
                int cr = pixelRow[x + 2];
 
                int c = (int) (y + 1.402 * cr - 178.956);
                int m = (int) (y - 0.34414 * cb - 0.71414 * cr + 135.95984);
                y = (int) (y + 1.772 * cb - 226.316);
 
                if (c < 0) c = 0; else if (c > 255) c = 255;
                if (m < 0) m = 0; else if (m > 255) m = 255;
                if (y < 0) y = 0; else if (y > 255) y = 255;
 
                pixelRow[x] = 255 - c;
                pixelRow[x + 1] = 255 - m;
                pixelRow[x + 2] = 255 - y;
            }
 
            raster.setPixels(0, h, width, 1, pixelRow);
        }
    }
 
    public static void convertInvertedColors(WritableRaster raster) {
        int height = raster.getHeight();
        int width = raster.getWidth();
        int stride = width * 4;
        int[] pixelRow = new int[stride];
        for (int h = 0; h < height; h++) {
            raster.getPixels(0, h, width, 1, pixelRow);
            for (int x = 0; x < stride; x++)
                pixelRow[x] = 255 - pixelRow[x];
            raster.setPixels(0, h, width, 1, pixelRow);
        }
    }
 
    public static BufferedImage convertCmykToRgb(Raster cmykRaster, ICC_Profile cmykProfile) throws IOException {
 
        if (cmykProfile == null){
            cmykProfile = ICC_Profile.getInstance(JpegReader.class.getResourceAsStream("ISOcoated_v2_300_eci.icc"));
        }
 
        ICC_ColorSpace cmykCS = new ICC_ColorSpace(cmykProfile);
        BufferedImage rgbImage = new BufferedImage(cmykRaster.getWidth(), cmykRaster.getHeight(), BufferedImage.TYPE_INT_RGB);
        WritableRaster rgbRaster = rgbImage.getRaster();
        ColorSpace rgbCS = rgbImage.getColorModel().getColorSpace();
        ColorConvertOp cmykToRgb = new ColorConvertOp(cmykCS, rgbCS, null);
        cmykToRgb.filter(cmykRaster, rgbRaster);
        return rgbImage;
    }
 
    static void intToBigEndian(int value, byte[] array, int index) {
        array[index]   = (byte) (value >> 24);
        array[index+1] = (byte) (value >> 16);
        array[index+2] = (byte) (value >>  8);
        array[index+3] = (byte) (value);
    }
     
}
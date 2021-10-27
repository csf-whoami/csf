package com.csf.base.service.file;

import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import org.apache.commons.io.FilenameUtils;
import org.apache.sanselan.ImageReadException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import com.csf.base.exception.CustomException;
import com.csf.base.exception.ErrorCode;
import com.csf.base.exception.HttpStatus;
import com.csf.base.service.common.CommonServiceSupport;

import net.coobird.thumbnailator.Thumbnails;
import net.coobird.thumbnailator.Thumbnails.Builder;

@Component
public class ThumbnailatorMakeStrategy extends CommonServiceSupport implements ThumbnailMakeStrategy{

	Logger log = LoggerFactory.getLogger(this.getClass());
	public static final String THUMB_FOLDER_NAME = "thumb";
	private static final String IMAGE_PATTERN = "([^\\s]+(\\.(?i)(jpg|png|gif|bmp|tif))$)";

	@Override
	public void make(File orgFile, ImageInfo imageInfo) throws Exception {
		if (orgFile.exists()) {
			if (orgFile.getName().matches(IMAGE_PATTERN)) {
				String fileExt = StringUtils.getFilenameExtension(orgFile.getName());

				File thumbnailDir = new File(orgFile.getParent(), ThumbnailatorMakeStrategy.THUMB_FOLDER_NAME+"/");
				if (!thumbnailDir.exists()) thumbnailDir.mkdirs();

				File thumbnail = new File(orgFile.getParent(), ThumbnailatorMakeStrategy.THUMB_FOLDER_NAME+"/"+orgFile.getName());

				if (imageInfo.getScale() > 0 && orgFile.getName().indexOf(".tif")==-1) {
					try {
						Thumbnails.of(orgFile).scale(imageInfo.getScale()).outputFormat(fileExt).toFile(thumbnail);
					} catch (IllegalArgumentException ile) {
						throw new CustomException(ErrorCode.ILLEGAL_ARGUMENT.getCode(), ile.getMessage(), HttpStatus.BAD_REQUEST);
					} catch (IllegalStateException ise) {
						throw new CustomException(ErrorCode.ILLEGAL_STATE.getCode(), ise.getMessage(), HttpStatus.BAD_REQUEST);
					} catch (IOException ioe) {
						throw new CustomException(ErrorCode.IO.getCode(), ioe.getMessage(), HttpStatus.BAD_REQUEST);
					} catch (Exception e) {
						throw new CustomException(ErrorCode.OTHER.getCode(), e.getMessage(), HttpStatus.BAD_REQUEST);
					}
				}
				else {
					// crop
					try {
						crop(orgFile, thumbnail, imageInfo.getWidth(), imageInfo.getHeight());
					} catch (IllegalArgumentException ile) {
						throw new CustomException(ErrorCode.ILLEGAL_ARGUMENT.getCode(), ile.getMessage(), HttpStatus.BAD_REQUEST);
					} catch (IllegalStateException ise) {
						throw new CustomException(ErrorCode.ILLEGAL_STATE.getCode(), ise.getMessage(), HttpStatus.BAD_REQUEST);
					} catch (IOException ioe) {
						throw new CustomException(ErrorCode.IO.getCode(), ioe.getMessage(), HttpStatus.BAD_REQUEST);
					} catch (Exception e) {
						throw new CustomException(ErrorCode.OTHER.getCode(), e.getMessage(), HttpStatus.BAD_REQUEST);
					}

					// 썸네일 작업
					//makeThumbImg(orgFile.getPath(), thumbnailDir.getPath(), orgFile.getName(), imageInfo.getWidth(), imageInfo.getHeight(), 100, true, false);
				}
				log.debug("thumbnail is created : {}", thumbnail.getAbsolutePath());
			}
		}
	}

	protected void crop(File orgFile, File outFile, int width, int height) throws  Exception {
		String fileExt = StringUtils.getFilenameExtension(orgFile.getName());
		BufferedImage image = null;
		JpegReader reader = new JpegReader();

		if (fileExt.equals("tif")){ //tif 이미지일 경우 jpg로 저장한 후 썸네일을 위해 다시 읽어 들임
			BufferedImage tif = ImageIO.read(orgFile);
			String orgStreFileNm = FilenameUtils.getName(orgFile.toString());
			orgFile = new File(orgFile.toString().substring(0, orgFile.toString().lastIndexOf("."))+".jpg");
			ImageIO.write( tif, "jpg", orgFile );
			fileExt = "jpg";
			image = ImageIO.read(orgFile); // 다시 읽어 들임
			outFile = new File(outFile.toString().substring(0, outFile.toString().lastIndexOf("."))+".jpg");
		} else {
			try { // CMYK인경우  javax.imageio.IIOException: Unsupported Image Type 오류 발생
				image = ImageIO.read(orgFile);
			} catch (Exception e) {
				try { //CMYK인 경우 RGB 변환 후 같은 이름으로 저장 후 다시 읽어 들임
					image = reader.readImage(orgFile);
					ImageIO.write(image, "jpg", orgFile);//동일한 명으로 이미지를 변경한다. 복사본을 남기려면  별도의 파일명으로 변경
					image.flush(); //메모리 누수 대처

					image = ImageIO.read(orgFile); // 다시 읽어 들임
				} catch (ImageReadException e1) {
					e1.printStackTrace();
				}
			}
		}



		Builder<BufferedImage> builder = null;
		if (image!=null){
/*			int imageWidth = image.getWidth();
			int imageHeitht = image.getHeight();
			if ((float)height / width != (float)imageWidth / imageHeitht) {
				if (imageWidth > imageHeitht) {
					image = Thumbnails.of(orgFile).height(height).asBufferedImage();
				} else {
					image = Thumbnails.of(orgFile).width(width).asBufferedImage();
				}
				builder = Thumbnails.of(image).sourceRegion(Positions.CENTER, width, height).size(width, height);
			} else {
				builder = Thumbnails.of(image).size(width, height);
			}*/

			//Thumbnails.of(image).crop(Positions.CENTER).size(600, 600).toFile(orgFile);

			/**
			 * 요구사항이 있어 고정 사이즈로 변경
			 * 원본은 600*600 으로 변경
			 * 썸네일은 작가이미지를 고려하여 500*300으로 썸네일
			 */
			Thumbnails.of(image).size(1000, 1000).toFile(orgFile);
			Thumbnails.of(image).size(500, 300).toFile(outFile);

			//builder.outputFormat(fileExt).toFile(outFile);
			image.flush();
		}
	}

	BufferedImage CreateBufferedImage(Image clsImage )
	{
		BufferedImage clsBI = new BufferedImage( clsImage.getWidth( null ), clsImage.getHeight( null ), BufferedImage.TYPE_INT_RGB );
		Graphics2D cls2D = clsBI.createGraphics( );
		cls2D.drawImage( clsImage, 0, 0, null );
		cls2D.dispose( );
		return clsBI;
	}
}

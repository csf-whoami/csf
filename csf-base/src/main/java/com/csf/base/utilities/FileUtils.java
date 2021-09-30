package com.csf.base.utilities;

import java.io.BufferedInputStream;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.MalformedURLException;
import java.net.URL;

import javax.servlet.http.HttpServletResponse;

import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;

//import org.apache.commons.io.FileUtils;

public class FileUtils {

	public InputStreamResource downloadFileContent(String downloadName, HttpServletResponse httpServletResponse) {

//		StringBuilder filePath = new StringBuilder();
//		filePath.append(propertyService.getString("File.Download.folder"));
//		filePath.append(id);
//		filePath.append("/");
//		filePath.append(downloadName);
//
//		HttpHeaders responseHeader = new HttpHeaders();
//		try {
//			
//			String demo = "/Volumes/WORKING/PROJECTS/29/2020년 서울특별시 베란다형 태양광 미니발전소 보급업체 제품정보.xls";
//			File file = new File(demo);
//			byte[] dataDownload = FileUtils.readFileToByteArray(file);
//			responseHeader.setContentType(MediaType.APPLICATION_OCTET_STREAM);
//			responseHeader.set("Content-disposition", "attachment; filename=\"" + objNotice.getFileName() + "\"");
//			responseHeader.setContentLength(dataDownload.length);
//			InputStream inputStreamDownload = new BufferedInputStream(new ByteArrayInputStream(dataDownload));
//			InputStreamResource inputStreamResource = new InputStreamResource(inputStreamDownload);
//			return inputStreamResource;
//		} catch (Exception ex) {
			return null;
//		}
	}

    public InputStreamResource downloadFromURL(String imageFile, HttpHeaders responseHeader) throws MalformedURLException, IOException {
        InputStream in = new URL(imageFile).openStream();
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        // Download image
        byte[] buf = new byte[1024];
        int n = 0;
        while (-1 != (n = in.read(buf))) {
            out.write(buf, 0, n);
        }
        byte[] data = out.toByteArray();

        String fileName = imageFile.substring(imageFile.lastIndexOf("/") + 1);
        responseHeader.setContentType(MediaType.APPLICATION_OCTET_STREAM);
        responseHeader.set("Content-disposition", "attachment; filename=\"" + fileName + "\"");
        responseHeader.setContentLength(data.length);
        InputStream inputStream = new BufferedInputStream(new ByteArrayInputStream(data));
        InputStreamResource inputStreamResource = new InputStreamResource(inputStream);

        return inputStreamResource;
    }

    public static void downloadFile(HttpServletResponse response, String filePath, String fileName) throws IOException {
        OutputStream out = response.getOutputStream();
        response.setContentType("application/pdf");
        response.addHeader("Content-Disposition", "attachment; filename=" + fileName);
        FileInputStream in = new FileInputStream(filePath);
        byte[] buffer = new byte[4096];
        int length;
        while ((length = in.read(buffer)) > 0) {
            out.write(buffer, 0, length);
        }
        in.close();
        out.flush();
    }
}

package com.csf.cdn.controller;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartException;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/upload")
public class CDNController {

	@Value("${spring.storage}")
	private String uploadFolder;

	@ExceptionHandler(MultipartException.class)
	@GetMapping(value = "test")
	public String testMethod() {
		return "test method ok";
	}

	@ExceptionHandler(MultipartException.class)
	@PostMapping(value = "saveFile", consumes = { MediaType.APPLICATION_OCTET_STREAM_VALUE,
			MediaType.MULTIPART_FORM_DATA_VALUE })
	public void uploadEditorFile(@RequestParam(value = "UploadFiles") MultipartFile uploadData) {
		try {
			byte[] bytes = uploadData.getBytes();
			String uploadPath = getUploadFolder();
			Path path = Paths.get(uploadPath + "/" + uploadData.getOriginalFilename());
			File dir = new File(uploadPath);
			if (!dir.exists()) {
				dir.mkdirs();
			}

			Files.write(path, bytes);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create folder with named are current date.
	 * 
	 * @return
	 */
	private String getUploadFolder() {
		Date current = new Date();
		SimpleDateFormat dateFormat = new SimpleDateFormat("YYYYMMdd");
		String time = dateFormat.format(current);
		if ("/".equals(uploadFolder.substring(uploadFolder.length() - 1))) {
			return uploadFolder + time;
		}
		return uploadFolder + "/" + time;
	}
}

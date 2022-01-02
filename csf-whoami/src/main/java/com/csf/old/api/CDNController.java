package com.csf.old.api;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import io.swagger.annotations.Api;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/upload")
@Api
public class CDNController {

	@Value("${server.local-url}")
	private String serverUrl;
//	@Value("${spring.image.upload-dir}")
//	private String uploadFolder;
	private String uploadFolder = System.getProperty("user.dir") + "/images/";
//	@Value("${spring.datasource.url}")
//	private String url;
//	@Value("${spring.datasource.username}")
//	private String username;

//	@ApiOperation(value = "Phương thức Upload file lên hệ thống.")
//	@ExceptionHandler(MultipartException.class)
//	@PostMapping(value = "saveFile", consumes = { MediaType.APPLICATION_OCTET_STREAM_VALUE,
//			MediaType.MULTIPART_FORM_DATA_VALUE })
//	public void uploadEditorFile(@RequestParam(value = "UploadFiles") MultipartFile uploadData) {
//		try {
//			byte[] bytes = uploadData.getBytes();
//			String uploadPath = getUploadFolder();
//			Path path = Paths.get(uploadPath + "/" + uploadData.getOriginalFilename());
//			File dir = new File(uploadPath);
//			if (!dir.exists()) {
//				dir.mkdirs();
//			}
//
//			Files.write(path, bytes);
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
//	}

	/**
	 * Create folder with named are current date.
	 * 
	 * @return
	 */
//	private String getUploadFolder() {
//		Date current = new Date();
//		SimpleDateFormat dateFormat = new SimpleDateFormat("YYYYMMdd");
//		String time = dateFormat.format(current);
//		if ("/".equals(uploadFolder.substring(uploadFolder.length() -1))) {
//			return uploadFolder + time;
//		}
//		return uploadFolder + "/" + time;
//	}

	/**
	 * Upload image / File to File storage cloud.
	 * 
	 * Box, Mediafire, Google drive.....
	 * @author mba0051
	 */
//	@PostMapping
//	public void storageFile() {
//		
//	}

	// TODO: Image upload.
	/***
	 * Upload image to Server.
	 * 
	 * @author Tuan Dang
	 * @param file
	 * @return
	 */
	@PostMapping("/image")
    @ResponseBody
    public ResponseEntity<?> handleFileUpload(@RequestParam("file") MultipartFile file) {
		Path path = null;
		try {
			byte[] bytes = file.getBytes();
			String uploadPath = getImageFolderName();

			path = Paths.get(uploadPath + "/" + file.getOriginalFilename());
			File dir = new File(uploadPath);
			if (!dir.exists()) {
				dir.mkdirs();
			}

			Files.write(path, bytes);
		} catch (IOException e) {
			e.printStackTrace();
		}
		String imagePath = serverUrl + "/upload/image/" + file.getOriginalFilename();
		return ResponseEntity.ok(imagePath);
    }

	private String getImageFolderName() {
//		Date current = new Date();
//		SimpleDateFormat dateFormat = new SimpleDateFormat("YYYYMMdd");
//		return dateFormat.format(current);
		String uploadPath = "";
		if ("/".equals(uploadFolder.substring(uploadFolder.length() -1))) {
			uploadPath = uploadFolder;
		}
		return uploadPath; 
	}

	@GetMapping("/image/{name}")
    @ResponseBody
    public ResponseEntity<?> serveFile(@PathVariable("name") String fileName) {
		// TODO: Search file.
		File searchedFile = searchFileByName(fileName);
		if (searchedFile != null) {
			try {
				HttpHeaders headers = new HttpHeaders();
				headers.add(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + searchedFile.getName() + "\"");
				headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);

	            Resource resource = new UrlResource(searchedFile.toPath().toUri());
	            return ResponseEntity.ok().headers(headers).body(resource);
			} catch (Exception e) {
	            e.printStackTrace();
	            return ResponseEntity.badRequest().build();
	        }
		}
        return ResponseEntity.badRequest().build();
    }

	/***
	 * Search file by name.
	 * It will return null if not found.
	 * 
	 * @param fileName
	 * @return
	 */
	private File searchFileByName(String name) {
		// Search file.
		File dir = new File(getImageFolderName());
		int last = 0;
		String fileName = "";
		for (File file : dir.listFiles()) {
			fileName = file.getName();
			last = fileName.lastIndexOf(".");
			if (last > 0 && fileName.substring(0, last).equalsIgnoreCase(name)) {
				return file;
			}
		}
		return null;
	}
}

/**
 * 
 */
package com.csf.cdn.controller;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.google.api.client.googleapis.auth.oauth2.GoogleCredential;
import com.google.api.client.http.FileContent;
import com.google.api.client.http.HttpResponse;
import com.google.api.client.http.HttpTransport;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.jackson2.JacksonFactory;
import com.google.api.services.drive.Drive;
import com.google.api.services.drive.Drive.Files;
import com.google.api.services.drive.Drive.Files.Get;
import com.google.api.services.drive.DriveScopes;
import com.google.api.services.drive.model.File;
import com.google.api.services.drive.model.FileList;
import com.google.api.services.drive.model.Permission;

@RestController
@RequestMapping("drive")
public class GoogleDriveController {

	private static final Logger LOGGER = LoggerFactory.getLogger(GoogleDriveController.class);

	@Value("${google.mail-services.service_account_email}")
	private String serviceAccountEmail;

	@Value("${google.mail-services.application_name}")
	private String applicationName;

	@Value("${google.mail-services.service_account_key}")
	private String serviceAccountKey;

	@Value("${google.mail-services.folder_id}")
	private String folderID;

	public Drive getDriveService() {
		Drive service = null;
		try {

			URL resource = GoogleDriveController.class.getResource("/" + this.serviceAccountKey);
			java.io.File key = Paths.get(resource.toURI()).toFile();
			HttpTransport httpTransport = new NetHttpTransport();
			JacksonFactory jsonFactory = new JacksonFactory();

			GoogleCredential credential = new GoogleCredential.Builder().setTransport(httpTransport)
					.setJsonFactory(jsonFactory).setServiceAccountId(serviceAccountEmail)
					.setServiceAccountScopes(Collections.singleton(DriveScopes.DRIVE))
					.setServiceAccountPrivateKeyFromP12File(key).build();

			service = new Drive.Builder(httpTransport, jsonFactory, credential).setApplicationName(applicationName)
					.setHttpRequestInitializer(credential).build();

		} catch (Exception e) {
			LOGGER.error(e.getMessage());
		}

		return service;
	}

	/**
	 * Upload file to Google Drive - Success.
	 * 
	 * @author mba0051
	 * @param fileName
	 * @param filePath
	 * @return
	 */
	@PostMapping("/upload")
	public File upLoadFile(@RequestParam("filename") String fileName, @RequestParam("path") String filePath) {
		File file = new File();
		try {
			java.io.File fileUpload = new java.io.File(filePath);
			com.google.api.services.drive.model.File fileMetadata = new com.google.api.services.drive.model.File();
//			fileMetadata.setMimeType("text/plain");
			fileMetadata.setName(fileName);
			fileMetadata.setParents(Collections.singletonList(folderID));

			com.google.api.client.http.FileContent fileContent = new FileContent("*/*", fileUpload);
			file = getDriveService().files().create(fileMetadata, fileContent)
					.setFields("id,webContentLink,webViewLink").execute();

			Permission newPermission = new Permission();
			newPermission.setEmailAddress("csf.whoami@gmail.com");
			newPermission.setType("user");
			newPermission.setRole("writer");
			getDriveService().permissions().create(file.getId(), newPermission).execute();
			
		} catch (Exception e) {
			LOGGER.error(e.getMessage());
		}
		return file;
	}

	@GetMapping("/list-files")
	public List<String> getFiles(@RequestParam("filename") String fileDownloadName,
			@RequestParam("file-id") String fileID) throws IOException {

		List<String> filesResult = new ArrayList<>();

		// Build a new authorized API client service.
		Drive service = getDriveService();

		// TODO: Download file to local.
		// Pass fileID to get method.
		Get fileGet = service.files().get(fileID);
		HttpResponse httpTest = fileGet.executeMedia();
		InputStream stream = httpTest.getContent();
		FileOutputStream outputTest = new FileOutputStream("/Volumes/SETUP/" + fileDownloadName);
		try {
			int l;
			byte[] tmp = new byte[2048];
			while ((l = stream.read(tmp)) != -1) {
				outputTest.write(tmp, 0, l);
			}
		} finally {
			outputTest.close();
			stream.close();
		}

		// TODO : List all files.
		// Print the names and IDs for up to 10 files.
		FileList result = service.files().list().execute();
		List<File> files = result.getFiles();
		if (files == null || files.size() == 0) {
			System.out.println("No files found.");
		} else {

			for (File file : files) {

				String fname = file.getName();
				String ex = fname.substring(fname.lastIndexOf(".") + 1);
				filesResult.add(fname);

				try {
					Files f = service.files();
					HttpResponse httpResponse = null;
					if (ex.equalsIgnoreCase("xlsx")) {
						httpResponse = f
								.export(file.getId(),
										"application/vnd.openxmlformats-officedocument.spreadsheetml.sheet")
								.executeMedia();

					} else if (ex.equalsIgnoreCase("docx")) {
						httpResponse = f
								.export(file.getId(),
										"application/vnd.openxmlformats-officedocument.wordprocessingml.document")
								.executeMedia();
					} else if (ex.equalsIgnoreCase("pptx")) {
						httpResponse = f
								.export(file.getId(),
										"application/vnd.openxmlformats-officedocument.presentationml.presentation")
								.executeMedia();

					} else if (ex.equalsIgnoreCase("pdf") || ex.equalsIgnoreCase("jpg") || ex.equalsIgnoreCase("png")) {

						Get get = f.get(file.getId());
						httpResponse = get.executeMedia();

					}
					if (null != httpResponse) {
						InputStream instream = httpResponse.getContent();
						FileOutputStream output = new FileOutputStream(file.getName());
						try {
							int l;
							byte[] tmp = new byte[2048];
							while ((l = instream.read(tmp)) != -1) {
								output.write(tmp, 0, l);
							}
						} finally {
							output.close();
							instream.close();
						}
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
		return filesResult;
	}
}

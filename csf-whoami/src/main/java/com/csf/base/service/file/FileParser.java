package com.csf.base.service.file;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Scanner;

import org.apache.commons.collections.MapUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.multipart.MultipartFile;

import com.csf.base.core.ZValue;
import com.csf.base.service.file.ThumbnailMakeStrategy.ImageInfo;

public class FileParser {

	private static Logger log = LoggerFactory.getLogger(FileParser.class);

	public static final String FILE_CN_PREFIX = "fileCn_";
	public static final String FILE_CN_EN_PREFIX = "fileCnEn_";

	private ThumbnailMakeStrategy thumbnailMakeStrategy = new ThumbnailatorMakeStrategy();

	public List<ZValue> parseFileInf(Map<String, MultipartFile> files, String fileStreCours, String folderPath,
			String prefix, Map<String, String> fileCnMap, Map<String, String> fileCnEnMap,
			Map<String, String> openYnMap, ImageInfo imageInfo) throws Exception {
		File saveFolder = new File(fileStreCours);
		if (!saveFolder.exists()) {
			saveFolder.mkdirs();
		}

		Iterator<Entry<String, MultipartFile>> itr = files.entrySet().iterator();
		MultipartFile file;
		String filePath = "";
		List<ZValue> resultList = new ArrayList<ZValue>();
		ZValue fvo;

		int fileSn = 0;
		while (itr.hasNext()) {
			Entry<String, MultipartFile> entry = itr.next();
			file = entry.getValue();
			String orignlFileNm = file.getOriginalFilename();

			// --------------------------------------
			// 원 파일명이 없는 경우 처리
			// (첨부가 되지 않은 input file type)
			// --------------------------------------
			if ("".equals(orignlFileNm)) {
				continue;
			}
			//// ------------------------------------

			int index = orignlFileNm.lastIndexOf(".");
			// String fileName = orginFileName.substring(0, index);
			String fileExtsnNm = orignlFileNm.substring(index + 1);
			String streFileNm = prefix + getTimeStamp() + String.valueOf(fileSn) + "." + fileExtsnNm;
			long fileSize = file.getSize();
			String fileFieldNm = file.getName();
			
			String fileCn = null;
			if (MapUtils.isNotEmpty(fileCnMap)) {
				fileCn = fileCnMap.get("fileCn_" + fileFieldNm);
			}
			String fileCnEn = null;
			if (MapUtils.isNotEmpty(fileCnEnMap)) {
				fileCnEn = fileCnEnMap.get("fileCnEn_" + fileFieldNm);
			}
			String openYn = null;
			if (MapUtils.isNotEmpty(openYnMap)) {
				openYn = openYnMap.get("openYn_" + fileFieldNm);
			}

			System.out.println("#fileFieldNm:"+fileFieldNm);
			System.out.println("#fileCn_:"+"fileCn_" + fileFieldNm);
			System.out.println("#fileCnEn_:"+"fileCnEn_" + fileFieldNm);
			System.out.println("#openYn_:"+"openYn_" + fileFieldNm);
			System.out.println("#fileCn:"+fileCn);
			System.out.println("#fileCnEn:"+fileCnEn);
			System.out.println("#openYn:"+openYn);
			
			File newFile = null;
			if (!"".equals(orignlFileNm)) {
				filePath = fileStreCours + File.separator + streFileNm;
				newFile = new File(filePath);
				file.transferTo(newFile);

				if (imageInfo != null) {
					thumbnailMakeStrategy.make(newFile, imageInfo);
				}
			}

			if (fileExtsnNm.equals("tif")){
				orignlFileNm = orignlFileNm.substring(0, orignlFileNm.length()-3) + "jpg";
				streFileNm = streFileNm.substring(0, streFileNm.length()-3) + "jpg";
				fileExtsnNm = "jpg";
			}

			fvo = new ZValue();
			fvo.put("fileExtsnNm", fileExtsnNm);
			fvo.put("fileStreCours", folderPath);
			fvo.put("fileSize", fileSize);
			fvo.put("orignlFileNm", orignlFileNm);
			fvo.put("streFileNm", streFileNm);
			fvo.put("fileSn", fileSn);
			fvo.put("fileFieldNm", file.getName());
			if (fileCn != null) {
				fvo.put("fileCn", fileCn);
			}
			if (fileCn != null) {
				fvo.put("fileCnEn", fileCnEn);
			}
			if (openYn != null) {
				fvo.put("openYn", openYn);
			}
			resultList.add(fvo);
			fileSn++;

		}
		return resultList;
	}

	public static String getTimeStamp() {
		String rtnStr = null;
		// 문자열로 변환하기 위한 패턴 설정(년도-월-일 시:분:초:초(자정이후 초))
		String pattern = "yyyyMMddhhmmssSSS";
		try {
			SimpleDateFormat sdfCurrent = new SimpleDateFormat(pattern, Locale.KOREA);
			Timestamp ts = new Timestamp(System.currentTimeMillis());
			rtnStr = sdfCurrent.format(ts.getTime());
		} catch (IllegalArgumentException iie){
			log.debug("IllegalArgumentException: " + iie.getMessage());
		} catch (Exception e) {
			log.debug("IGNORED: " + e.getMessage());
		}

		return rtnStr;
	}

    /**
     * 해당 파일 읽어서 스트링으로 변환하기
     * @param filePath
     * @return
     * @throws FileNotFoundException
     */
    public static String getFileToString(String filePath){
    	StringBuffer content=null;
    	FileInputStream fis=null;
    	try {
	    	content = new StringBuffer();
	    	fis = new FileInputStream(filePath);

        	@SuppressWarnings("resource")
    		Scanner s = new Scanner(fis,"utf-8");
        	while (s.hasNext()) {
        		content.append(s.nextLine()).append("\n");
        	}
		} catch (FileNotFoundException fne) {
			log.error("FileNotFoundException: " + fne.getMessage());
		} catch (Exception e) {
			log.error("Exception: " + e.getMessage());
		} finally {
			if (fis!=null){ try { fis.close();} catch (IOException e) {	e.printStackTrace();}}
		}

    	return content.toString();
    }

	public ThumbnailMakeStrategy getThumbnailMakeStrategy() {
		return thumbnailMakeStrategy;
	}

	public void setThumbnailMakeStrategy(ThumbnailMakeStrategy thumbnailMakeStrategy) {
		this.thumbnailMakeStrategy = thumbnailMakeStrategy;
	}

}

package com.csf.base.service.file;

import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.web.multipart.MultipartFile;

import com.csf.base.core.ZValue;

public interface IFileMngService {

	public static final String ROOT_IMG_URL = "/files";
	public static final String PREFIX_FILE_NORMAL_NAME = "normalFile";
	public static final String PREFIX_FILE_IMG_NAME = "imgFile";
	public static final String IMG_ALLOW_FILE_EXT = "jpg,jpeg,png,gif,bmp";
	public static final long  ATCH_LIMIT_SIZE = 30000000L; //100M(100000000L)

    public ZValue getFile(String atchFileId, int fileSn) throws Exception;

    public List<ZValue> getFiles(String atchFileId) throws Exception;

    public List<ZValue> getFiles(List<String> atchFileIdList) throws Exception;

    public String upload(Map<String, MultipartFile> files, ZValue param) throws Exception;

    public String upload(Map<String, MultipartFile> files, ZValue param, Set<String> delFileFieldNames) throws Exception;

    public void deleteAll(ZValue param, boolean physicalFileDelete) throws Exception;

    public void deleteOne(ZValue param, boolean physicalFileDelete) throws Exception;

    public void setFirstProperties(List<ZValue> resultList, Map<String, List<ZValue>> fileMap);

    public Map<String, List<ZValue>> getFileMap(ZValue param, List<ZValue> resultList) throws Exception;

    public void setImgUrl(List<ZValue> list) throws Exception;

    public void setImgUrl(ZValue val) throws Exception;

    public void updateFileCn(ZValue fVO) throws Exception;
}

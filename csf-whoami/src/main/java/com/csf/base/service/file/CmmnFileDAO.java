package com.csf.base.service.file;

import org.springframework.stereotype.Repository;

import com.csf.base.core.ZValue;
import com.csf.base.dao.SqlDAO;

@Repository
public class CmmnFileDAO extends SqlDAO<ZValue> {

	public ZValue findCmmnFileDetail(String atchFileId, int fileSn) throws Exception {
		ZValue param = new ZValue();
		param.put("atchFileId", atchFileId);
		param.put("fileSn", fileSn);
		return findOne("findOneCommnFileDetail", param);
	}

	public void deleteCmmnFileDetail(String atchFileId, int fileSn) throws Exception {
		ZValue param = new ZValue();
		param.put("atchFileId", atchFileId);
		param.put("fileSn", fileSn);
		deleteOne("deleteCmmnFileDetail", param);
	}
}

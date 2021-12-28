package com.csf.base.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;

import com.csf.base.ParameterContext;
import com.csf.base.handler.ListHandler;
import com.csf.base.paging.IPageInfo;
import com.csf.base.service.WebService;
import com.csf.base.service.common.CommonServiceSupport;

public class AbstractWebService extends CommonServiceSupport implements WebService {

    Logger log = LoggerFactory.getLogger(this.getClass());

    @Autowired
    protected ListHandler listHandler;
//    protected IExcelUploadService<ZValue> excelUploadService;
    protected IPageInfo pageInfo;
    protected MessageSource messageSource;

    /**
     * Lấy thông tin câu hỏi và hiển thị ra cho người dùng.
     */
    @Override
    public void index(ParameterContext paramCtx) {
        // TODO Auto-generated method stub
    	System.out.println("This class: " + this.getClass() + " - method : index");
    }

    /**
     * Thực hiện test và trả về kết quả cho người dùng.
     */
    @Override
    public void execute(ParameterContext paramCtx) {
        // TODO Auto-generated method stub
    	System.out.println("This class: " + this.getClass() + " - method : execute");
    }
}

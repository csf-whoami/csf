package com.csf;

import java.util.HashMap;
import java.util.Map;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.csf.base.common.CommonServiceFactory;
import com.csf.base.common.DefaultCommonServiceFactory;
import com.csf.base.common.DefaultModelAndViewResolver;
import com.csf.base.mvr.ModelAndViewResolver;

@SpringBootApplication
public class CSFApplication {

	public static void main(String[] args) {
		SpringApplication.run(CSFApplication.class, args);
	}

	@Bean
	public CommonServiceFactory defaultCommonServiceFactory() {
		DefaultCommonServiceFactory commonServiceFactory = new DefaultCommonServiceFactory();
		Map<String, String> mvrNameMap = new HashMap<>();
		mvrNameMap.put("default", "defaultModelAndViewResolver");
		commonServiceFactory.setMvrNameMap(mvrNameMap);
		return commonServiceFactory;
	}
	@Bean
	public ModelAndViewResolver defaultModelAndViewResolver() {
		DefaultModelAndViewResolver modelAndViewResolver = new DefaultModelAndViewResolver();
		Map<String, String> includePageMap = new HashMap<>();
		includePageMap.put("/*/bos/bbs/*/list.do*", "/bos/bbs/[bbsAttrbCd]/List.jsp");
		includePageMap.put("/*/bos/bbs/*/view.do*", "/bos/bbs/[bbsAttrbCd]/Read.jsp");
		includePageMap.put("/*/bos/bbs/*/replyView.do*", "/bos/bbs/[bbsAttrbCd]/ReplyRead.jsp");
		includePageMap.put("/*/bos/bbs/*/forInsert.do*", "/bos/bbs/[bbsAttrbCd]/Reg.jsp");
		includePageMap.put("/*/bos/bbs/*/forInsertRe.do*", "/bos/bbs/[bbsAttrbCd]/RegRe.jsp");
		includePageMap.put("/*/bos/bbs/*/forUpdate.do*", "/bos/bbs/[bbsAttrbCd]/Reg.jsp");
		includePageMap.put("/*/bos/bbs/*/forUpdateRe.do*", "/bos/bbs/[bbsAttrbCd]/RegRe.jsp");
		modelAndViewResolver.setIncludePageMap(includePageMap);
		return modelAndViewResolver;
	}
}

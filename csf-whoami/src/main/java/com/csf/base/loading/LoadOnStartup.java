package com.csf.base.loading;

import javax.annotation.PostConstruct;

import org.springframework.stereotype.Component;

@Component
public class LoadOnStartup implements ILoadOnStartup {

	@PostConstruct
	public void init() {
		SampleLoadingModule.getInstance();
	}

	@Override
	public void destroy() {
	}
}

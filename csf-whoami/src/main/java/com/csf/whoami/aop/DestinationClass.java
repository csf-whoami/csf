package com.csf.whoami.aop;

import java.util.Date;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class DestinationClass {

    private Long id;
    private String name;
    private Date deletedAt;
	@Property(name = "testProp")
    private Long myProperties;
	private byte test;
}

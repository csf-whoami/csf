package com.csf.aop;

import java.util.Date;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class SourceClass {

	private Long id;
	private String name;
	private int age;
	private Date createdAt;
	private Date deletedAt;
	private Long testProp;
}

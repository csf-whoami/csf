package com.csf.whoami.database.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter @Setter
public class TemplateVO {

	private String id;
	private String defaultValue;
	private String isActive;
	private String activeDate;
}

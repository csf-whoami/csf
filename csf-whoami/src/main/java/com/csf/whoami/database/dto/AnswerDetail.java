/**
 * 
 */
package com.csf.whoami.database.dto;

import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;

import lombok.Getter;
import lombok.Setter;

@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
//@XmlRootElement(name = "AnsOption")
//@XmlAccessorType(XmlAccessType.FIELD)
@Getter @Setter
public class AnswerDetail {

//	@XmlValue
	private String answerOption;
//	@XmlAttribute(name = "mark")
	private String answerMark;
}

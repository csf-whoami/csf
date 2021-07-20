/**
 * 
 */
package com.csf.whoami.database.dto;

import java.util.List;

import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;

import lombok.Getter;
import lombok.Setter;

@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
//@XmlRootElement(name = "AnswerOptions")
//@XmlAccessorType(XmlAccessType.FIELD)
@Getter @Setter
public class AnswerOptions {

//    @XmlElement(name = "type")
    private String questionType;
//    @XmlElement(name = "Options")
    private List<AnswerDetail> options;
}

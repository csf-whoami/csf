/**
 * 
 */
package com.csf.base.domain.question;

import java.util.List;

import com.csf.base.domain.AnswerDetail;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
public class QuestionSelectOptionDomain extends QuestionDomain {

	private String isMultiChoice;
	private List<AnswerDetail> ansOptions;
	private List<String> correctAnswer;
	private String isRandom;
}

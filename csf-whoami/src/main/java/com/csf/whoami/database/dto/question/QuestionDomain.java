/**
 * 
 */
package com.csf.whoami.database.dto.question;

import com.csf.base.constant.CommonConstants;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * @author tuan
 *
 */
@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, include = JsonTypeInfo.As.PROPERTY, property = "question_type")
@JsonSubTypes({ @JsonSubTypes.Type(value = QuestionSelectOptionDomain.class, name = CommonConstants.SelectOption), // --OK
		@JsonSubTypes.Type(value = QuestionTextDomain.class, name = CommonConstants.TextType),
		@JsonSubTypes.Type(value = QuestionPictureChoiceDomain.class, name = CommonConstants.PictureChoiceType), // Re-test.
		@JsonSubTypes.Type(value = QuestionYesNoDomain.class, name = CommonConstants.YesNoType), // --OK
		@JsonSubTypes.Type(value = QuestionUploadDomain.class, name = CommonConstants.UploadType) })
@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
@Getter @Setter
@NoArgsConstructor
public class QuestionDomain {

	private String questionId;
	private String questionType;
	private String questionContent;
	private String groupId;

	/**
	 * @param questionId
	 * @param questionType
	 * @param questionContent
	 * @param groupId
	 */
	// TODO
//	public QuestionDomain(Long questionId, String questionType, String questionContent, String groupId) {
//		super();
//		this.questionId = String.valueOf(questionId);
//		this.questionType = questionType;
//		this.questionContent = questionContent;
//		this.groupId = groupId;
//	}
}

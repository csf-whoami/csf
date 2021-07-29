/**
 * 
 */
package com.csf.base.domain.question;

import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;

/**
 * @author tuan
 *
 */
@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
public class QuestionUploadDomain extends QuestionDomain {
}

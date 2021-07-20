/**
 * 
 */
package com.csf.whoami.database.dto;

import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;

import lombok.Getter;
import lombok.Setter;

@Setter @Getter
@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
public class InviteGroupDomain {

	private String userId;
	private String groupId;
	private String joinType; // GroupRequireTypeConstant
}

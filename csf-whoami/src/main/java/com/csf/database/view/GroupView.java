package com.csf.database.view;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter @Setter
public class GroupView {

	private Long id;
    private String groupName;
    private String groupType;
    private String isPublish;
    private String isClosed;
    private String isPrivate;
    private String isLock;
    private String groupDescription;
    private String groupUrl;
    private Date activedAt;
}

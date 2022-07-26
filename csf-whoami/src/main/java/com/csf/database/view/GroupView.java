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
    private String name;
    private String typeCode;
    private String isPublish;
    private String isClosed;
    private String isPrivate;
    private String isLock;
    private String description;
    private String url;
    private Date activedAt;
}

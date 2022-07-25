package com.csf.database.vo;

import java.util.Date;

public interface GroupVO {

	Long 	getId();

	String 	getDisplayNameVn();

	String 	getDisplayNameEn();

	String 	getType();

	Long 	getOwnerId();

	String 	getIsPublish();

	String 	getIsClosed();

	String 	getIsPrivate();

	String 	getIsLock();

	Date 	getActivedAt();
}

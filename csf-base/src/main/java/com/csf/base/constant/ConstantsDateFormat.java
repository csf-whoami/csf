/**
 * 
 */
package com.csf.base.constant;

import java.util.Date;

/**
 * @author tuan
 *
 */
public class ConstantsDateFormat {

	public static final long DAY_MILISECONDS		= 24 * 60 * 60 * 1000;

	public static final String YYYYMMDDThhmmssZ 	= "YYYY-MM-DDThh:mm:ssZ";
	public static final String YYYYMMDDThhmmssZNew 	= "yyyy-MM-dd'T'HH:mm:ss'Z'";
	public static final String YYYYMMDDhhmmss 		= "yyyy-MM-dd HH:mm:ss";
	public static final String YYYYMMDDhhmm 		= "yyyy-MM-dd HH:mm";
	public static final String yyyyMMddHi24mmss		= "yyyy-MM-dd HH:mm:ss";
	public static final String yyyyMMdd				= "yyyy-MM-dd";
	public static final String TIMESTAMP_PATTERN	= "yyyy-MM-dd'T'HH:mm:ss'Z'";
	public static final String CSV_FORMAT_DATE		= "yyyyMMddHHmmssSSS";

	public static final String TIME_DEFAULT_ZERO 	= "00:00:00:000";
	public static final String DATE_FORMAT = "DD/MM/YYYY";

    public static final Date MIN_DATE = new Date(Long.MIN_VALUE);
    public static final Date MAX_DATE = new Date(Long.MAX_VALUE);
}

package com.csf.base.loading;

public class BeanUtil {

//	private static Logger log = new Logger(BeanUtil.class);

//	private static final boolean isWindows = (System.getProperty("os.name").toUpperCase().indexOf("WINDOWS") != -1);

	public final static String KEY_SEPARATOR = ",";

	public final static String ITEM_SEPARATOR = "_";

	public static String nvl(Object val) {
		if(val == null) {
			return "";
		}
		return val.toString();
	}

	public static String NVL(Object val) {
		if(val == null || val.equals("")) {
			return " ";
		} else{
			return val.toString();
		}
	}
}

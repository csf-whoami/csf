/**
 * 
 */
package com.csf.base.constant;

import java.util.Date;

/**
 * @author tuan
 *
 */
public class CommonConstants {

    public final static String SelectOption = "SelectOption";
    public final static String PictureChoiceType = "PictureChoiceType";
    public final static String TextType = "TextType";
    public final static String MultiLineTextType = "MultiLineTextType";
    public final static String YesNoType = "YesNoType";
    public final static String UploadType = "UploadType";

    public static final String EMAIL_REGEX = "^[_\\w\\d-]+(\\.[_\\w\\d-]+)*@[\\w\\d-]+(\\.[\\w\\d-]+)*(\\.[\\w]{2,})$";

    public static final String PHONE_REGEX = "^[\\d]*$";

    public static final String DEVICE_CODE_REGEX = "([a-zA-Z0-9]){3}-([0-9]){4}-([0-9]){4}";
    
    public static final String BIRTHDAY_REGEX = "\\d{4}-\\d{2}-\\d{2}";

    public static final int DEFAULT_PAGING_SIZE = 10;

    public static final int DEFAULT_PAGING_PAGE = 0;

    public static final String DATE_FORMAT = "DD/MM/YYYY";

    public static final String FEASIBILITY_REQUEST_PRODUCT_GROUP = "DUCT";

    public static final String RESOURCE_SERVER = "/opt/tomcat/latest/webapps/images";

    public static final String RESOURCE_ORIGIN_PRODUCT = "/originProduct";

    public static final String RESOURCE_ORIGIN_ADS = "/ads";

    public static final String RESOURCE_ORIGIN_PRODUCT_LANDING = "/landing";

    public static final String RESOURCE_CATEGORY= "/category";

    public static final String RESOURCE_IMAGES= "/images";

    public static final Integer MAX_NUMBER_OF_RESULT = 9999;

    public static final String HOST_REGEX = "(http(s)?:\\/\\/)([a-z0-9\\w]+\\.*)+(:)*([0-9]*)*";

    public static final String DOT = ".";
}

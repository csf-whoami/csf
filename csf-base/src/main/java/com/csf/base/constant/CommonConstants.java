/**
 * 
 */
package com.csf.base.constant;

import static java.util.Arrays.asList;

import java.util.Date;
import java.util.List;

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

    public static final List<String> FIBRES = asList("4", "12", "24", "28", "72", "96", "120", "144", "192", "288", "432", "576", "customised");

    public static final String HOTLINE_REQUEST_PRODUCT_GROUP = "DUCT HOTL";

    public static final String DATE_FORMAT = "DD/MM/YYYY";

    public static final String FEASIBILITY_REQUEST_PRODUCT_GROUP = "DUCT";

    public static final String RESOURCE_SERVER = "/opt/tomcat/latest/webapps/images";

    public static final String RESOURCE_ORIGIN_PRODUCT = "/originProduct";

    public static final String RESOURCE_ORIGIN_ADS = "/ads";

    public static final String RESOURCE_ORIGIN_PRODUCT_LANDING = "/landing";

    public static final String RESOURCE_CATEGORY= "/category";

    public static final String RESOURCE_IMAGES= "/images";

    public static final Integer MAX_NUMBER_OF_RESULT = 9999;

    public static final String NO_PROJECT_FOUND = "28";

    public static final String NO_REQUEST_FOUND = "12";

    public static final String MEMBER_TYPE_ADMIN = "ADMIN";

    public static final String MEMBER_TYPE_STORE_OWNER = "STORE_OWNER";

    public static final String ROLE_ADMIN = "ROLE_ADMIN";

    public static final String ROLE_STORE = "ROLE_STORE";
    
    public static final String TIMEZONE = "GMT+9";

    public static final String HOST_REGEX = "(http(s)?:\\/\\/)([a-z0-9\\w]+\\.*)+(:)*([0-9]*)*";

    public static final Date MIN_DATE = new Date(Long.MIN_VALUE);

    public static final Date MAX_DATE = new Date(Long.MAX_VALUE);

    public static final String DATAS = "datas";
    public static final String SEARCH = "search";
    public static final String PAGE_NO = "pageNumber";
    public static final String TOTAL_COUNT = "totalCount";
    public static final String ROLES = "roles";
    public static final String GROUPS = "groups";
}

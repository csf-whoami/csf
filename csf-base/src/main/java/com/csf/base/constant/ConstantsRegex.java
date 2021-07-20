package com.csf.base.constant;

public class ConstantsRegex {

    /** Pattern regex constant. */
    public static final String PASSWORD_PATTERN         = "(?=.*\\d)(?=.*[A-Za-z])[\\w]{4,32}";
    public static final String POSTCODE_PATTERN         = "^(\\s*$)|([0-9]{7}$)";
    public static final String NUMBER_PATTERN           = "^[0-9]+$";
    public static final String NUMBER_PATTERN_OR_NULL   = "^[0-9]*$";
    public static final String PHONE_PATTERN            = "^(\\s|[0-9-])*$";
    public static final String BIT_PATTERN              = "^(1|0)$";
    public static final String BIT_OR_NULL_PATTERN      = "^(|1|0|\\s)$";
    public static final String EMAIL_PATTERN            = "^[a-zA-Z0-9.!#$%&\\'*+/=?^_`{|}~-]+@[a-zA-Z0-9-]+(?:\\.[a-zA-Z0-9-]+)*$";
    public static final String ALPHANUMERIC_PATTERN     = "^[a-zA-Z0-9]+$";
    public static final String NUMBER__PATTERN          = "^[0-9]+$";
    public static final String DATE_PATTERN             = "^\\d{4}-(0\\d|10|11|12)-(0\\d|1\\d|2\\d|30|31)$";

    public static final String EMAIL_REGEX = "^[_\\w\\d-]+(\\.[_\\w\\d-]+)*@[\\w\\d-]+(\\.[\\w\\d-]+)*(\\.[\\w]{2,})$";
    public static final String PHONE_REGEX = "^[\\d]*$";
    public static final String DEVICE_CODE_REGEX = "([a-zA-Z0-9]){3}-([0-9]){4}-([0-9]){4}";
    public static final String BIRTHDAY_REGEX = "\\d{4}-\\d{2}-\\d{2}";

    public static final String HOST_REGEX = "(http(s)?:\\/\\/)([a-z0-9\\w]+\\.*)+(:)*([0-9]*)*";

}

package com.csf.base.constant;

public class ConstantsHTTP {

    // SERVER ERROR
    public static final String SUCCESS                  = "200";
    public static final String INTERNAL_SERVER_ERROR    = "500";
    public static final String EMPTY_PAGE               = "404";
    public static final String NOT_YET_OPENED           = "9999";
    public static final String COMMON_ERROR             = "9000";

    // JOIN ERROR
    public static final String FAIL_PHONE_ALREADY       = "9201";
    public static final String FAIL_USER_ALREADY        = "9202";
    public static final String FAIL_TOKEN_INIVALID      = "9203";
    public static final String FAIL_TOKEN_EXPIRE        = "9204";

    // LOGIN ERROR
    public static final String FAIL_NO_USER             = "9211";

    //MERCHANT ERROR
    public static final String EXISTING_REG_NO          = "9301";

    //CHARGE POINT
    public static final String OVER_INCENTIVE_LIMIT     = "9401";
    public static final String UNKNOWN_ACCOUNT          = "9402";
    public static final String FAIL_PAYMENT             = "9403";
    public static final String UN_AUTH_ACCOUNT          = "9405";
    public static final String WRONG_ACCOUNT            = "9406";
    public static final String HAVA_VIRTUAL_ACCOUNT     = "9407";
    public static final String NOT_VIRTUAL_ACCOUNT      = "9408";

    //CARD
    public static final String CARD_NOT_EXIST           = "9501";
    public static final String CARD_ALREADY_USE         = "9502";
    public static final String USER_USING_SAME_CARD     = "9503";
    public static final String CARD_NOT_REGISTER        = "9504";
    public static final String CARD_SUCCESS_UPDATE      = "9511";
    public static final String CARD_SUCCESS_REGISTER    = "9512";
    public static final String FAIL_LOST                = "9513";

    //EMPLOYEE
    public static final String ALREADY_EMPLOYEE         = "9601";
    public static final String MERCHANT_USER            = "9602";
    public static final String CAN_NOT_FOUND_USER       = "9603";

    //EXCHANGE RATE
    public static final String UPDATE_ONCE_A_DAY       = "9701";

    //PAYMENT
    public static final String NOT_ENOUGH_POINT         = "9801";
    public static final String PAY_OWN_SHOP             = "9802";
    public static final String ALREADY_CANCELED         = "9803";
    public static final String AUTOGRAPH_REQUIRED       = "9804";
    public static final String PASSED_CANCEL_LIMIT_TERM = "9805";
    public static final String ZERO_PAYMENT             = "9806";
    public static final String NOT_VALID_CARD           = "9807";
    public static final String NOT_VALID_MERCHANT       = "9808";
    public static final String NOT_VALID_USER           = "9809";
    public static final String AMOUNT_LARGER_THAN_POINT = "9810";
    public static final String OVERLAP                  = "9811";

    // FAIL
    public static final String FAIL = "9901";
}

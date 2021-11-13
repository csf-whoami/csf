package com.csf.base.constant;

public class ConstantsURL {
    private ConstantsURL() {}

    /** Multiple languages. */
    public static final String MESSAGE_PATH         = "ApplicationMessage";

    public static final String HOME                 = "/";
    public static final String ADM_SITE             = "adm";


    /** URL maun group. */
    public static final String MAIN                 = "main";
    public static final String PAGE_MAIN            = "main";

    /** URL login flow. */
    public static final String LOGIN                = "login";
    public static final String SEARCH_GROUP         = "/groups";
    public static final String REGISTER_GROUP       = "/groups/register";
    public static final String CONFIRM_EMAIL        = "/groups/confirm";
    public static final String CONFIRM_PINCODE      = "/groups/pin-code/{id}";
    public static final String AUTHENTICATE         = "/authenticate/{id}";
    public static final String PAGE_SEARCH_GROUP    = "login/seach-group";
    public static final String PAGE_GROUP_CONFIRM   = "login/group-confirm";
    public static final String PAGE_GROUP_REGISTER  = "login/group-register";
    public static final String PAGE_EMAIL_CONFIRM   = "login/email-confirm";
    public static final String PAGE_REGISTER_SUCCESS= "login/register-success";
    public static final String PAGE_GROUP_INFO      = "login/group-info";
    public static final String PAGE_AUTHENTICATE    = "login/authenticate";

    public static final String DASHBOARD_HOME       = "me";
    public static final String DASHBOARD_PAGE       = ADM_SITE + "/index";
    /** Dashboard menu page URL */
    public static final String URI_DASHBOARD_MENU   = "/menu";
    public static final String URI_DASHBOARD_START  = "/start";
    /** Dashboard layout page */
    public static final String PAGE_MENU = "layout/menu";
    public static final String PAGE_HOME = "layout/start";

    /** Master path. */
    public static final String URI_USER             = DASHBOARD_HOME + "/users";
    public static final String URI_ROLE             = DASHBOARD_HOME + "/roles";
    public static final String URI_CHANNEL          = DASHBOARD_HOME + "/channels";
    public static final String URI_GROUP            = DASHBOARD_HOME + "/groups";
    public static final String URI_QUESTION         = DASHBOARD_HOME + "/questions";

    /** Template URLs. */
    public static final String URI_MANAGEMENT   = "/management";
    public static final String URI_REGISTER     = "/detail";
    public static final String URI_DETAIL       = "/detail/{id}";
    public static final String URI_DELETE       = "/management/delete/{id}";
    public static final String URI_DELETE_LIST  = "/management/delete";

    /** Template page. */
    public static final String PAGE_MANAGEMENT = "dashboard/{0}-management/{0}-list";
    public static final String PAGE_DETAIL = "dashboard/{0}-management/{0}-detail";
    public static final String PAGE_REDIRECT = "redirect:/{0}{1}?p={2}";

    public static final String URI_ADMIN = "/admin";
    public static final String URI_PASSWORD_RESET = "/resetPassword/{id}";
    public static final String URI_ADMIN_LOGIN = "/admin/login";
    public static final String URI_USER_LOGIN = "login";

    public static final String URI_QNA = "/qna";
    public static final String URI_THEME = "/theme";

    /** Authentication URLs. */
    public static final String URI_AUTHEN           = "/auth";
    public static final String URI_LOGIN            = "/login";
    public static final String URI_SIGNUP           = "/signup";
    public static final String URI_MAIL_CONFIRM     = "/confirm-group";

    /** Page URL. */
    public static final String PAGE_START = "index";
}

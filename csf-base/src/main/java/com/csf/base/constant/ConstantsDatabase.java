/**
 * 
 */
package com.csf.base.constant;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class ConstantsDatabase {

    public static final String SORT_ASC = "ASC";
    public static final String SORT_DESC = "DESC";

    /**
     * Constant for User roles.
     * 
     * @author tuan
     *
     */
    public enum UserRolesConstant {

        USER("USER"), ADMIN("ADMIN"), EDITOR("EDITOR");

        String value;

        UserRolesConstant(String value) {
            this.value = value;
        };
    }

    /**
     * Question type mapping with constants.
     * 
     * @author mba0051
     */
    public enum QuestionTypeConstant {

        SELECT_TYPE(CommonConstants.SelectOption), PICTURE_CHOICE(CommonConstants.PictureChoiceType),
        SINGLE_TEXT(CommonConstants.TextType), MULTILINE_TEXT(CommonConstants.MultiLineTextType),
        YES_NO(CommonConstants.YesNoType), UPLOAD(CommonConstants.UploadType);

        String value;

        /**
         * @return the value
         */
        public String getValue() {
            return value;
        }

        /**
         * @param value the value to set
         */
        public void setValue(String value) {
            this.value = value;
        }

        QuestionTypeConstant(String value) {
            this.value = value;
        };
    }

    /**
     * Group conditions required.
     * 
     * @author mba0051
     *
     */
    public enum GroupRequireTypeConstant {

        Direct("Invite direct"), WaitAccept("Waiting accept"), RequireAuthen("pass to quiz test"),
        RequireExp("Keep time in group");

        String value;

        /**
         * @return the value
         */
        public String getValue() {
            return value;
        }

        /**
         * @param value the value to set
         */
        public void setValue(String value) {
            this.value = value;
        }

        GroupRequireTypeConstant(String value) {
            this.value = value;
        };
    }

    /**
     * Notification status.
     * 
     * @author mba0051
     *
     */
    public enum NotifyStatusConstant {

        NEW("1"), READED("2"), ACCEPT("3"), DENY("0"), OVER_EXPIRE("4");

        String value;

        /**
         * @return the value
         */
        public String getValue() {
            return value;
        }

        /**
         * @param value the value to set
         */
        public void setValue(String value) {
            this.value = value;
        }

        NotifyStatusConstant(String value) {
            this.value = value;
        };
    }

    /**
     * Notifications type.
     * 
     * @author mba0051
     *
     */
    public enum NotificationsTypeConstant {

        GROUP("1"), QUIZ("2"), CHANNEL("3"), KICK_OUT("4"), REPORT("5");

        String value;

        /**
         * @return the value
         */
        public String getValue() {
            return value;
        }

        /**
         * @param value the value to set
         */
        public void setValue(String value) {
            this.value = value;
        }

        NotificationsTypeConstant(String value) {
            this.value = value;
        };
    }

    /**
     * Constant for YES NO consider.
     * 
     * @author tuan
     *
     */
    public enum YesNoConstant {

        YES("1"), NO("0");
        @Getter @Setter
        String value;

        YesNoConstant(String value) {
            this.value = value;
        };
    }

    public enum RoleName {

        CMS_OBSERVER("CMS_OBSERVER", "Observer"), CMS_OPERATOR("CMS_OPERATOR", "Operator"),
        SYS_ADMIN("SYS_ADMIN", "Admin"), CMS_OWNER("CMS_OWNER", "Owner");

        @Getter @Setter
        private String code;
        @Getter @Setter
        private String name;

        RoleName(String code, String name) {
            this.code = code;
            this.name = name;
        }
    }

    /**
     * The enum Is lock.
     */
    public enum IsLock {

        LOCK("1"),

        UNLOCK("0");

        /**
         * The Value.
         */
        String value;

        /**
         * Gets value.
         *
         * @return the value
         */
        public String getValue() {
            return value;
        }

        /**
         * Sets value.
         *
         * @param value the value to set
         */
        public void setValue(String value) {
            this.value = value;
        }

        IsLock(String value) {
            this.value = value;
        }
    }

    public enum IsFistTimeLogin {
        TRUE(1), FALSE(0);

        Integer value;

        /**
         * @return the value
         */
        public Integer getValue() {
            return value;
        }

        /**
         * @param value the value to set
         */
        public void setValue(Integer value) {
            this.value = value;
        }

        IsFistTimeLogin(Integer value) {
            this.value = value;
        };
    }
}

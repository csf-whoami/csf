/**
 * 
 */
package com.csf.security.constant;

/**
 * @author tuan
 *
 */
public class CommonConstants {

	public final static String SelectOption = "SelectOption";
	public final static String TextType = "TextType";
	public final static String PictureChoiceType = "PictureChoiceType";
	public final static String YesNoType = "YesNoType";
	public final static String UploadType = "UploadType";
	public final static String MultiLine = "Area Text";

	/**
	 * Constant for User roles.
	 * 
	 * @author tuan
	 *
	 */
	public enum UserRolesConstant {

		USER("USER"), ADMIN("ADMIN"), EDITOR("EDITOR");

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

		UserRolesConstant(String value) {
			this.value = value;
		};
	}

	public enum QuestionTypeConstant {

		SingleChoice("SingleChoice"),
		MultiChoice("MultiChoice"),
		SingleText("SingleText"),
		MultiText("MultiText"),
		Upload("Upload");

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
}

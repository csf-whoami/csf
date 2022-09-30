/**
 * 
 */
package com.csf.common.constant;

/**
 * @author mba0019
 *
 */
public class AuthConstant {
	public static final String SCOPE_READ = "read";

	public enum Scope {
		READ("read");

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

		Scope(String value) {
			this.value = value;
		};
	}

	public enum LoginType {

		/**
		 * Playlist media status.
		 */
		SYSTEM("0", "System"), INTERNAL("1", "Internal login"),
		ANDROID("2", "Android"), IOS("3", "Ios"),
		GOOGLE("4", "Login by Google"), FACEBOOK("5", "Login by Facebook");

		private String code;
		private String value;

		private LoginType(String code, String value) {
			this.code = code;
			this.value = value;
		}

		/**
		 * @return the code
		 */
		public String getCode() {
			return code;
		}

		/**
		 * @param code the code to set
		 */
		public void setCode(String code) {
			this.code = code;
		}

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
	}
}

/**
 * 
 */
package com.csf.common.utilities;

import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
//import java.text.DecimalFormat;
import java.text.Normalizer;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;
import java.util.UUID;
import java.util.regex.Pattern;

/**
 * @author mba0051
 *
 */
public final class StringUtils {

	public static final String HOUR_MINUTE_PATTERN = "^(\\d{2}):([0-9]|[0-5][0-9]))$";

	public static String generateUUID() {
		return UUID.randomUUID().toString();
	}

	@SuppressWarnings("unused")
	private static String generateUUIDWithSHA256() throws NoSuchAlgorithmException, UnsupportedEncodingException {
		MessageDigest salt = MessageDigest.getInstance("SHA-256");
		salt.update(UUID.randomUUID().toString().getBytes("UTF-8"));
//		String digest = bytesToHex(salt.digest());
		return null;
	}

	/**
	 * alphabeUpCaseNumber.
	 */
	private static String alphabeUpCaseNumber = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
	private static String mask = "0123456789_aAÃ¡Ã?Ã Ã€áº£áº¢áº¡áº Ã£ÃƒÃ¢Ã‚áº¥áº¤áº§áº¦áº©áº¨áº­áº¬áº«áºªÄƒÄ‚áº¯áº®áº±áº°áº³áº²áº·áº¶áºµáº´bBcCdDÄ‘Ä?eEÃ©Ã‰Ã¨Ãˆáº»áººáº¹áº¸áº½áº¼ÃªÃŠáº¿áº¾á»?á»€á»ƒá»‚á»‡á»†á»…á»„fFgGhHiIÃ­Ã?Ã¬ÃŒá»‰á»ˆá»‹IHÄ©Ä¨jJkKlLmMnNoOÃ³Ã“Ã²Ã’á»?á»Žá»?á»ŒÃµÃ•Ã´Ã”á»‘á»?á»“á»’á»•á»”á»™á»˜á»—á»–Æ¡Æ á»›á»šá»?á»œá»Ÿá»žá»£á»¢á»¡á» pPqQrRsStTuUÃºÃšÃ¹Ã™á»§á»¦á»¥á»¤Å©Å¨Æ°Æ¯á»©á»¨á»«á»ªá»­á»¬á»±á»°á»¯á»®vVwWxXyYÃ½Ã?á»³á»²á»·á»¶á»µá»´á»¹á»¸zZ";
	private static String maskEN = "0123456789_aAbBcCdDeEfFgGhHiIjJkKlLmMnNoOpPqQrRsStTuUvVwWxXyYzZ";
	private static final String c[] = { "<", ">" };
	private static final String expansion[] = { "&lt;", "&gt;" };

	/**
	 * Html encode string.
	 *
	 * @param s the s
	 * @return string
	 */
	public static String HTMLEncode(String s) {
		String sTemp = s;
		for (int j = 0; j < c.length; j++) {
			sTemp = sTemp.replace(c[j], expansion[j]);
		}
		return sTemp;
	}

	/**
	 * Html decode string.
	 *
	 * @param s the s
	 * @return string
	 */
	public static String HTMLDecode(String s) {
		String mine = s;
		for (int i = 0; i < c.length; i++) {
			mine.replaceAll(expansion[i], (c[i] + ""));
		}
		return mine;
	}

	/**
	 * Compare string boolean.
	 *
	 * @param str1 the str 1
	 * @param str2 the str 2
	 * @return boolean
	 */
	public static boolean compareString(String str1, String str2) {
		String str1Temp = str1;
		String str2Temp = str2;
		if (str1Temp == null) {
			str1Temp = "";
		}
		if (str2Temp == null) {
			str2Temp = "";
		}

		if (str1Temp.equals(str2Temp)) {
			return true;
		}
		return false;
	}

	/**
	 * Convert from long to string string.
	 *
	 * @param lng the lng
	 * @return string
	 */
	public static String convertFromLongToString(Long lng) {
		return String.valueOf(lng);
	}

	/**
	 * Convert from long to string string [ ].
	 *
	 * @param arrLong the arr long
	 * @return string [ ]
	 * @throws Exception the exception
	 */
	public static String[] convertFromLongToString(Long[] arrLong) throws Exception {
		String[] arrResult = new String[arrLong.length];
		for (int i = 0; i < arrLong.length; i++) {
			arrResult[i] = convertFromLongToString(arrLong[i]);
		}
		return arrResult;
	}

	/**
	 * Convert from string to long long [ ].
	 *
	 * @param arrStr the arr str
	 * @return long [ ]
	 * @throws Exception the exception
	 */
	public static long[] convertFromStringToLong(String[] arrStr) throws Exception {
		long[] arrResult = new long[arrStr.length];
		for (int i = 0; i < arrStr.length; i++) {
			arrResult[i] = Long.parseLong(arrStr[i]);
		}
		return arrResult;
	}

	/*
	 * @todo: convert from String value to Long value
	 */

	/**
	 * Convert from string to long long.
	 *
	 * @param value the value
	 * @return long
	 * @throws Exception the exception
	 */
	public static long convertFromStringToLong(String value) throws Exception {
		return Long.parseLong(value);
	}

	/**
	 * Convert from string to long or null long.
	 *
	 * @param value the value
	 * @return the long
	 */
	public static Long convertFromStringToLongOrNull(String value) {
		try {
			return Long.parseLong(value);
		} catch (NumberFormatException e) {
			return null;
		}
	}

	/*
	 * Check String that containt only AlphabeUpCase and Number Return True if
	 * String was valid, false if String was not valid
	 */

	/**
	 * Check alphabe up case number boolean.
	 *
	 * @param value the value
	 * @return boolean
	 */
	public static boolean checkAlphabeUpCaseNumber(String value) {
		boolean result = true;
		for (int i = 0; i < value.length(); i++) {
			String temp = value.substring(i, i + 1);
			if (alphabeUpCaseNumber.indexOf(temp) == -1) {
				result = false;
				return result;
			}
		}
		return result;
	}

	/**
	 * Is valid string boolean.
	 *
	 * @param temp the temp
	 * @return boolean
	 */
	public static boolean isValidString(Object temp) {
		return temp != null && !temp.toString().trim().equals("");
	}

	/**
	 * Is invalid string boolean.
	 *
	 * @param temp the temp
	 * @return boolean
	 */
	public static boolean isInvalidString(Object temp) {
		return !isValidString(temp);
	}

	/**
	 * Is valid length boolean.
	 *
	 * @param str the str
	 * @param min the min
	 * @param max the max
	 * @return boolean
	 */
	public static boolean isValidLength(String str, int min, int max) {
		if (isInvalidString(str)) {
			return false;
		}
		int len = str.length();
		return len >= min && len <= max;
	}

	/**
	 * Is not valid length boolean.
	 *
	 * @param str the str
	 * @param min the min
	 * @param max the max
	 * @return boolean
	 */
	public static boolean isNotValidLength(String str, int min, int max) {
		return !isValidLength(str, min, max);
	}

	/**
	 * Mask vn boolean.
	 *
	 * @param str the str
	 * @return boolean
	 */
	public static boolean maskVN(String str) {
		for (int i = 0; i < str.length(); i++) {
			if (mask.indexOf(str.charAt(i)) < 0) {
				return false;
			}
		}
		return true;
	}

	/**
	 * Mask en boolean.
	 *
	 * @param str the str
	 * @return boolean
	 */
	public static boolean maskEN(String str) {
		for (int i = 0; i < str.length(); i++) {
			if (maskEN.indexOf(str.charAt(i)) < 0) {
				return false;
			}
		}
		if (str.toLowerCase().charAt(0) < 'a' || str.toLowerCase().charAt(0) > 'z') {
			return false;
		}
		return true;
	}

	/**
	 * Is integer boolean.
	 *
	 * @param str the str
	 * @return boolean
	 */
	public static boolean isInteger(String str) {
		if (str == null || !str.matches("[0-9]+$")) {
			return false;
		}
		return true;
	}

	/**
	 * Format string string.
	 *
	 * @param str the str
	 * @return string
	 */
	public static String formatString(String str) {
		return " '" + str.trim().toLowerCase() + "'";
	}

	/**
	 * Format like string.
	 *
	 * @param str the str
	 * @return string
	 */
	public static String formatLike(String str) {
		return "%" + str.trim().toLowerCase().replaceAll("_", "\\\\_") + "%";
	}

	/**
	 * Format order string.
	 *
	 * @param str       the str
	 * @param direction the direction
	 * @return string
	 */
	public static String formatOrder(String str, String direction) {
		return " NLSSORT(" + str + ",'NLS_SORT=vietnamese') " + direction;
	}

	/**
	 * Format function string.
	 *
	 * @param function the function
	 * @param str      the str
	 * @return string
	 */
	public static String formatFunction(String function, String str) {
		return " " + function + "(" + str + ") ";
	}

	/**
	 * Format constant string.
	 *
	 * @param str the str
	 * @return string
	 */
	public static String formatConstant(String str) {
		String str1 = "";
		int index = 0;
		String alphabe = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
		for (int i = 1; i < str.length(); i++) {
			if (alphabe.indexOf(str.charAt(i)) > 0) {
				str1 = str1 + str.substring(index, i).toUpperCase() + "_";
				index = i;
			}
		}
		str1 = str1 + str.substring(index, str.length()).toUpperCase() + "_";
		return str1;
	}

	/**
	 * Is long boolean.
	 *
	 * @param str the str
	 * @return boolean
	 */
	public static boolean isLong(String str) {
		try {
			Long.valueOf(str);
			return true;
		} catch (Exception ex) {
			return false;
		}
	}

	/**
	 * Check String can convert to double type
	 *
	 * @param str the str
	 * @return boolean boolean
	 */
	public static boolean isDouble(String str) {
		try {
			Double.valueOf(str);
			return true;
		} catch (Exception ex) {
			return false;
		}
	}

	/**
	 * Is boolean boolean.
	 *
	 * @param str the str
	 * @return boolean
	 */
	public static boolean isBoolean(String str) {
		try {
			Boolean.valueOf(str);
			return true;
		} catch (Exception ex) {
			return false;
		}
	}

	/**
	 * Contain special characteristic boolean.
	 *
	 * @param str the str
	 * @return boolean
	 */
	public static boolean containSpecialCharacteristic(String str) {
		if (str == null) {
			return false;
		}
		List<String> lstSpecialCharacteristic = new ArrayList<String>();
		lstSpecialCharacteristic.add("!");
		lstSpecialCharacteristic.add("@");
		lstSpecialCharacteristic.add("#");
		lstSpecialCharacteristic.add("%");
		lstSpecialCharacteristic.add("^");
		lstSpecialCharacteristic.add("&");
		lstSpecialCharacteristic.add("*");
		lstSpecialCharacteristic.add("(");
		lstSpecialCharacteristic.add(")");
		lstSpecialCharacteristic.add(" ");
		for (int i = 0; i < lstSpecialCharacteristic.size(); i++) {
			if (str.contains(lstSpecialCharacteristic.get(i).toString())) {
				return true;
			}
		}
		return false;
	}

	/**
	 * Upper first char string.
	 *
	 * @param input the input
	 * @return string
	 */
	public static String upperFirstChar(String input) {
		if (isNullOrEmpty(input)) {
			return input;
		}
		return input.substring(0, 1).toUpperCase() + input.substring(1);
	}

	/**
	 * Is null or empty boolean.
	 *
	 * @param obj1 the obj 1
	 * @return boolean
	 */
	public static boolean isNullOrEmpty(String obj1) {
		return (obj1 == null || "".equals(obj1.trim()));
	}

	/**
	 * Is long null or empty boolean.
	 *
	 * @param obj1 the obj 1
	 * @return boolean
	 */
	public static boolean isLongNullOrEmpty(String obj1) {
		return (obj1 == null || "0L".equals(obj1));
	}

	/**
	 * Is double null or empty boolean.
	 *
	 * @param obj1 the obj 1
	 * @return boolean
	 */
	public static boolean isDoubleNullOrEmpty(String obj1) {
		return (obj1 == null || "0D".equals(obj1));
	}

	//

	/**
	 * Is string null or empty boolean.
	 *
	 * @param obj1 the obj 1
	 * @return boolean
	 */
	public static boolean isStringNullOrEmpty(Object obj1) {
		return obj1 == null || obj1.toString().trim().equals("");
	}

	/**
	 * Convert string to boolean or null boolean.
	 *
	 * @param input the input
	 * @return boolean
	 */
	public static Boolean stringToBooleanOrNull(String input) {
		try {
			return Boolean.valueOf(input.trim());
		} catch (Exception e) {
			return null;
		}
	}

	/**
	 * Convert string to long or null long.
	 *
	 * @param input the input
	 * @return long
	 */
	public static Long stringToLongOrNull(String input) {
		try {
			return Long.valueOf(input.trim());
		} catch (Exception e) {
			return null;
		}
	}

	/**
	 * Convert string to integer or null integer.
	 *
	 * @param input the input
	 * @return integer
	 */
	public static Integer stringToIntegerOrNull(String input) {
		try {
			return Integer.valueOf(input.trim());
		} catch (Exception e) {
			return null;
		}
	}

	/**
	 * Convert string to float or null float.
	 *
	 * @param input the input
	 * @return float
	 */
	public static Float stringToFloatOrNull(String input) {
		try {
			return Float.valueOf(input.trim());
		} catch (Exception e) {
			return null;
		}
	}

	/**
	 * Convert object to string string.
	 *
	 * @param input the input
	 * @return string
	 */
	public static String convertObjectToString(Object input) {
		return input == null ? null : input.toString().trim();
	}

	/**
	 * Convert date to string string.
	 *
	 * @param input the input
	 * @return string
	 */
	public static String convertDateToString(Date input) {
		SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
		String date = dateFormat.format(input).toString();
		return date;
	}

	/**
	 * Convert string to boolean boolean.
	 *
	 * @param input the input
	 * @return boolean
	 * @throws Exception the exception
	 * @author at -ducnguyen3
	 */
	public static Boolean stringToBoolean(String input) throws Exception {
		if (input != null) {
			if (input.equals(Boolean.TRUE.toString()))
				return true;
			if (input.equals(Boolean.FALSE.toString()))
				return false;
		}
		throw new Exception();
	}

	/**
	 * Is string ascii boolean.
	 *
	 * @param str the str
	 * @return boolean
	 * @author at -ducnguyen3
	 */
	public static boolean isStringAscii(String str) {
		for (char ch : str.toCharArray()) {
			if (!isAscii(ch)) {
				return false;
			}
		}
		return true;
	}

	/**
	 * Is ascii boolean.
	 *
	 * @param ch the ch
	 * @return boolean
	 * @author at -ducnguyen3
	 */
	public static boolean isAscii(char ch) {
		return ch < 128;
	}

	/**
	 * Convert string to double or null double.
	 *
	 * @param amountNumber the amount number
	 * @return double
	 * @author at -ducnguyen3
	 */
	public static Double stringToDoubleOrNull(String amountNumber) {
		try {
			return Double.valueOf(amountNumber);
		} catch (Exception e) {
			return null;
		}
	}

	/**
	 * Convert string to big decimal or null big decimal.
	 *
	 * @param number the number
	 * @return big decimal
	 * @author at -ducnguyen3
	 */
	public static BigDecimal stringToBigDecimalOrNull(String number) {
		try {
			return new BigDecimal(number);
		} catch (Exception e) {
			return null;
		}
	}

	/**
	 * Convert to boolean boolean.
	 *
	 * @param value the value
	 * @return boolean
	 * @author at -ducnguyen3
	 */
	public static Boolean convertToBoolean(String value) {
		boolean returnValue = false;
		if ("1".equalsIgnoreCase(value) || "yes".equalsIgnoreCase(value) || "true".equalsIgnoreCase(value)
				|| "on".equalsIgnoreCase(value))
			returnValue = true;
		return returnValue;
	}

	/**
	 * Is valid email boolean.
	 *
	 * @param email the email
	 * @return boolean
	 * @author at -ducnguyen3
	 */
	public static boolean isValidEmail(String email) {
		String emailRegex = "^[a-zA-Z0-9_+&*-]+(?:\\." + "[a-zA-Z0-9_+&*-]+)*@" + "(?:[a-zA-Z0-9-]+\\.)+[a-z"
				+ "A-Z]{2,7}$";

		Pattern pat = Pattern.compile(emailRegex);

		if (email == null) {
			return false;
		}
		return pat.matcher(email).matches();
	}

	/**
	 * Contains only numbers boolean.
	 *
	 * @param str the str
	 * @return boolean
	 * @author at -ducnguyen3
	 */
	public static boolean containsOnlyNumbers(String str) {
		String regex = "[0-9]+";
		boolean b = str.matches(regex);
		return b;
	}

	/**
	 * Convert double to string or null string.
	 *
	 * @param value the value
	 * @return string
	 * @author at -ducnguyen3
	 */
//	public static String convertDoubleToStringOrNull(Double value) {
//		return value == null ? null : new DecimalFormat("#").format(new Double(value.toString()));
//	}

	/**
	 * Generate file name string.
	 *
	 * @return string
	 * @author at -ducnguyen3
	 */
	public static String generateFileName() {
		Random rand = new Random();
		int randomNum = rand.nextInt((10000 - 1) + 1) + 1;
		String dateString = new SimpleDateFormat("yyyyMMddHHmm").format(new Date());
		return "image-upload-" + dateString + randomNum + ".jpg";
	}

	/**
	 * Build list id sql string.
	 *
	 * @param ids the ids
	 * @return string
	 * @author at -ducnguyen3
	 */
	public static String buildListIdSQL(List<String> ids) {
		StringBuilder eCondition = new StringBuilder();

		List<String> idsNotNull = new ArrayList<String>();
		if (ids != null && ids.size() > 0) {
			StringBuilder idConditions = new StringBuilder("");
			Long staffId;
			for (String id : ids) {
				staffId = StringUtils.stringToLongOrNull(id);
				if (null != staffId)
					idsNotNull.add(id);
			}

			idConditions.append(String.join(",", idsNotNull));
			if (idConditions.length() > 0) {
				idConditions.insert(0, " s.id in (");
				idConditions.append(")");

				eCondition.append(idConditions);
			}
		}
		return eCondition.toString();
	}

	/**
	 * Remove invalid address chars string.
	 *
	 * @param inputAddress the input address
	 * @return string
	 * @author at -ducnguyen3
	 */
	public static String removeInvalidAddressChars(String inputAddress) {
		if (!isValidString(inputAddress)) {
			return inputAddress;
		}

		inputAddress = inputAddress.replaceAll("(^[,\\s]+)|([,\\s]+$)", "");
		inputAddress = inputAddress.replaceAll("[,\\s]+[,\\s]+", ",").replaceAll(",", ", ");

		return inputAddress;
	}

	/**
	 * Check time boolean.
	 *
	 * @param value the value
	 * @return boolean
	 * @author at -ducnguyen3
	 */
	public static Boolean checkTime(String value) {
		Boolean check = value.matches("^(([0-1]\\d)|(2[0-3])):[0-5]\\d$");
		return check;
	}

	/**
	 * Convert from int to string string.
	 *
	 * @param no the no
	 * @return string
	 * @author at -ducnguyen3
	 */
	public static String convertFromIntToString(Integer no) {
		return String.valueOf(no);
	}

	/**
	 * Convert object to long long.
	 *
	 * @param input the input
	 * @return long
	 * @author at -ducnguyen3
	 */
	public static Long convertObjectToLong(Object input) {
		try {
			return Long.valueOf(input.toString());
		} catch (Exception e) {
			return null;
		}
	}

	/**
	 * Contains only char boolean.
	 *
	 * @param str the str
	 * @return boolean
	 * @author at -ducnguyen3
	 */
	public static boolean containsOnlyChar(String str) {
		String regex = "[a-zA-Z]+";
		boolean b = true;
		if (!isNullOrEmpty(str)) {
			b = str.matches(regex);
		}
		return b;
	}

	/**
	 * Trim string.
	 *
	 * @param value the value
	 * @return string
	 * @author at -ducnguyen3
	 */
	public static String trim(String value) {
		if (null == value || "".equals(value)) {
			return value;
		}
		return value.trim();
	}

	/**
	 * Sets default string if null.
	 *
	 * @param value the value
	 * @return default string if null
	 * @author at -ducnguyen3
	 */
	public static String setDefaultStringIfNull(String value) {
		return null == value ? "" : value;
	}

	/**
	 * Is contain zen ka ku character boolean.
	 *
	 * @param str the str
	 * @return the boolean
	 */
	public static Boolean isContainZenKaKuCharacter(String str) {
		return str.matches(".*[^\\x01-\\x7E\\xA1-\\xDF].*");
	}

	/**
	 * Is contain invalid character boolean.
	 *
	 * @param str the str
	 * @return the boolean
	 */
	public static Boolean isContainInvalidCharacter(String str) {
		return str.matches(".*[<>!\"#$%&'()*+,/:;=?@\\[\\\\\\]^`{|}~].*");
	}

	/**
	 * Is contain uppercase character boolean.
	 *
	 * @param str the str
	 * @return the boolean
	 */
	public static boolean isContainUppercaseCharacter(String str) {
		return str.matches(".*[A-Z].*");
	}

	/**
	 * Is contain lowercase character boolean.
	 *
	 * @param str the str
	 * @return the boolean
	 */
	public static boolean isContainLowercaseCharacter(String str) {
		return str.matches(".*[a-z].*");
	}

	/**
	 * Is contain number boolean.
	 *
	 * @param str the str
	 * @return the boolean
	 */
	public static boolean isContainNumber(String str) {
		return str.matches(".*[0-9].*");
	}

	/**
	 * Is contain symbol boolean.
	 *
	 * @param str the str
	 * @return the boolean
	 */
	public static boolean isContainSymbol(String str) {
		return str.matches(".*[\"#$%&'()*+\\-.,/:;=?@\\[\\\\\\]^_`{|}~].*");
	}

	/**
	 * contains contains Medical pattern
	 *
	 * @param str the str
	 * @return boolean boolean
	 */
	public static boolean containsMedicalPattern(String str) {
		String regex = "^[a-zA-Z0-9_-]{0,10}$";
		boolean b = str.matches(regex);
		return b;
	}

	/**
	 * Convert time to 2 characters string.
	 *
	 * @param time the time
	 * @return the string
	 */
	public static String convertTimeTo2Characters(Integer time) {
		return String.format("%02d", time);
	}

	/**
	 * Check string value are valid pattern
	 * 
	 * @param valueCheck String need to validate.
	 * @return boolean match status.
	 */
	public static boolean validateStringFormat(String valueCheck, String pattern) {
		Pattern patternCheck = Pattern.compile(pattern);
		return patternCheck.matcher(valueCheck).matches();
	}

	/**
	 * Change pattern to Date
	 * 
	 * @param input
	 * @param sourcePattern
	 * @param targetPattern
	 * @return String with new pattern.
	 */
	public static String changeDatePattern(String dateInput, String sourcePattern, String targetPattern) {
		return convertDateToStringFormatPattern(DateTimeUtils.stringToDateOrNull(dateInput, sourcePattern),
				targetPattern);
	}

	public static String convertDateToStringFormatPattern(Date input, String Pattern) {
		SimpleDateFormat dateFormat = new SimpleDateFormat(Pattern);
		return input == null ? null : dateFormat.format(input).toString();
	}

	/**
	 * Convert Vietnam content text to acsii character. Keep string format.
	 * 
	 * @param inputContent
	 * @return
	 */
	public static String convertVietNamTextToACSII(String inputContent) {
		String nfdNormalizedString = Normalizer.normalize(inputContent, Normalizer.Form.NFD); 
        Pattern pattern = Pattern.compile("\\p{InCombiningDiacriticalMarks}+");
        return pattern.matcher(nfdNormalizedString).replaceAll("");
	}
}

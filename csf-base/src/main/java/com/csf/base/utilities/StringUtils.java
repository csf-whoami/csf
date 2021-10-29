package com.csf.base.utilities;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.TimeZone;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * The type String utils.
 */
public class StringUtils {

    public static final String MONEY_REGEX = "^\\d{1,3}(\\,\\d{3})*(|\\.\\d{2})$";
    public static final String MONEY_NUMBER_REGEX = "^\\d+$";
    public static final String YEAR_NUMBER_REGEX = "^\\d{4}$";
    public static final String NUMBER_REGEX = "^\\d+$";

    /**
     * convert Object To String
     *
     * @param input the input
     * @return string
     */
    public static String convertObjectToString(Object input) {
        return input == null ? null : input.toString();
    }

    /**
     * Convert object to string or empty string.
     *
     * @param input the input
     * @return the string
     */
    public static String convertObjectToStringOrEmpty(Object input) {
        return input == null ? "" : input.toString();
    }

    /**
     * Convert from string to long or null long.
     *
     * @param value the value
     * @return the long
     */
    public static Long toLongOrNull(String value) {
        try {
            return Long.parseLong(value);
        } catch (NumberFormatException e) {
            return null;
        }
    }

    /**
     * Convert from string to long or null long.
     *
     * @param value the value
     * @return Integer value
     */
    public static Integer toIntegerOrNull(String value) {
        try {
            return Integer.parseInt(value);
        } catch (NumberFormatException e) {
            return null;
        }
    }

    /**
     * Convert from string to float or null float.
     * 
     * @param value the value
     * @return Float value
     */
    public static Float toFloatOrNull(String value) {
        try {
            return Float.parseFloat(value);
        } catch (NumberFormatException e) {
            return null;
        }
    }

    /**
     * convert String To Date Or Null
     * 
     * @param date
     * @param pattern
     * @return
     */
    public static Date toDateOrNull(String date, String pattern) {
        SimpleDateFormat dateFormat = new SimpleDateFormat(pattern);
        try {
            return dateFormat.parse(date);
        } catch (Exception e) {
            return null;
        }
    }

    public static String fromLong(Long value) {
        if (value == null) {
            return null;
        }
        return String.valueOf(value);
    }

    public static String fromInteger(Integer value) {
        if (value == null) {
            return null;
        }
        return String.valueOf(value);
    }

    public static String fromFloat(Float value) {
        if (value == null) {
            return null;
        }
        return String.valueOf(value);
    }

    /**
     * Is null or empty boolean.
     *
     * @param checkStr the string need to check.
     * @return boolean
     */
    public static boolean isNullOrEmpty(String checkStr) {
        return (checkStr == null || "".equals(checkStr.trim()));
    }

    public static String integerToMoney(Integer price, String unit) {
        if (price == null) {
            return "";
        }
        String output = "";
        DecimalFormat formatter = new DecimalFormat("#,###");
        output = formatter.format(price) + unit;
        return output;
    }

    /**
     * Check input string is number pattern.
     * 
     * @param input
     * @return
     */
    public static boolean isNumber(String input) {
        if (isNullOrEmpty(input)) {
            return false;
        }
        Pattern checkRegex = Pattern.compile(NUMBER_REGEX);
        Matcher regexMatcher = checkRegex.matcher(input);
        return regexMatcher.matches();
    }

    /**
     * Check input string is number pattern.
     * 
     * @param input
     * @return
     */
    public static boolean isNumber(String input, String pattern) {
        if (isNullOrEmpty(input)) {
            return false;
        }
        Pattern checkRegex = Pattern.compile(pattern);
        Matcher regexMatcher = checkRegex.matcher(input);
        return regexMatcher.matches();
    }

    /**
     * Build select query string.
     *
     * @param attribute the attribute
     * @param table     the table
     * @param condition the condition
     * @return string string
     */
    public static String buildSelectQuery(List<String> attribute, List<String> table, List<String> condition) {
        StringBuilder selectQuery = new StringBuilder();
        selectQuery.append(" SELECT ");
        selectQuery.append(String.join(", ", attribute));
        selectQuery.append(" FROM ");
        selectQuery.append(String.join(" ", table));
        selectQuery.append(" WHERE ");
        selectQuery.append(String.join(" AND ", condition));
        return selectQuery.toString();
    }

    /**
     * Change datetime to other timezone.
     * @param date
     * @param timezone
     * @param dateTimeFormat
     * @return
     */
    public static String changeTimeZone(Date date, String timezone, String dateTimeFormat) {
        try {
            Calendar cal = Calendar.getInstance();
            long milliDiff = cal.get(Calendar.ZONE_OFFSET);
            // Got local offset, now loop through available timezone id(s).
            String[] ids = TimeZone.getAvailableIDs();
            String name = null;
            for (String id : ids) {
                TimeZone tz = TimeZone.getTimeZone(id);
                if (tz.getRawOffset() == milliDiff) {
                    // Found a match.
                    name = id;
                    break;
                }
            }

            String dateInString = DateTimeUtils.convertDateToString(date, dateTimeFormat);
            LocalDateTime ldt = LocalDateTime.parse(dateInString, DateTimeFormatter.ofPattern(dateTimeFormat));

            ZoneId currentZoneId = ZoneId.of(name);
            ZonedDateTime zonedDateTime = ldt.atZone(currentZoneId);
            ZoneId seoulZoneId = ZoneId.of(timezone);
            ZonedDateTime changeDateTime = zonedDateTime.withZoneSameInstant(seoulZoneId);
            DateTimeFormatter format = DateTimeFormatter.ofPattern(dateTimeFormat);

            return format.format(changeDateTime);
        } catch (Exception ex) {
            return null;
        }
    }

    /**
     * 지정된 length만큼의 문자열에 "..."을 뒤에 붙여 반환.
     *
     * @param s
     *            java.lang.String
     * @param size
     *            int length
     * @return java.lang.String
     */
    public static String head(String s, int size) {
        if (s == null)
            return "";
        String value = null;
        if (s.length() > size)
            value = s.substring(0, size) + "...";
        else
            value = s;
        return value;
    }

    public static String convertHangul(String money) {
        String[] han1 = { "", "일", "이", "삼", "사", "오", "육", "칠", "팔", "구" };
        String[] han2 = { "", "십", "백", "천" };
        String[] han3 = { "", "만", "억", "조", "경" };
        StringBuffer result = new StringBuffer();
        int len = money.length();

        for (int i = len - 1; i >= 0; i--) {
            result.append(han1[parseInt(money.substring(len - i - 1, len - i))]);
            if (parseInt(money.substring(len - i - 1, len - i)) > 0) {
                result.append(han2[i % 4]);
            }
            if (i % 4 == 0) {
                result.append(han3[i / 4]);
            }
        }
        return result.toString();
    }

    /**
     * 지정된 length만큼의 문자열에 "..."을 앞에 붙여 반환.
     *
     * @param s
     *            java.lang.String
     * @param size
     *            int length
     * @return java.lang.String
     */
    public static String tail(String s, int size) {
        if (s == null)
            return "";
        String value = null;
        if (s.length() > size)
            value = "..." + s.substring(s.length() - size);
        else
            value = s;
        return value;
    }

    /**
     * 문자열을 15자 만큼만 보여주고 그 길이에 초과되는 문자열일 경우 "..."를 덧붙여 보여준다.
     *
     * @return String the translated string.
     * @param input
     *            String 변환할 문자열
     */
    public static String fixLength(String input) {
        return fixLength(input, 15, "...");
    }

    /**
     * 문자열을 일정길이 만큼만 보여주고 그 길이에 초과되는 문자열일 경우 "..."를 덧붙여 보여준다.
     *
     * @return String the translated string.
     * @param input
     *            String 변환할 문자열
     * @param limit
     *            int 문자열의 제한 길이
     */
    public static String fixLength(String input, int limit) {
        return fixLength(input, limit, "...");
    }

    /**
     * 문자열을 일정길이 만큼만 보여주고 그 길이에 초과되는 문자열일 경우 특정문자를 덧붙여 보여준다.
     *
     * @return String the translated string.
     * @param input
     *            String 변환할 문자열
     * @param limit
     *            int 문자열의 제한 길이
     * @param postfix
     *            String 덧붙일 문자열
     */
    public static String fixLength(String input, int limit, String postfix) {

        String buffer = "";
        char[] charArray = input.toCharArray();
        if (limit >= charArray.length)
            return input;

        for (int j = 0; j < limit; j++)
            buffer += charArray[j];

        buffer += postfix;
        return buffer;
    }

    /**
     * 문자열에서 특정 문자열을 치환한다. 예를 들어, 123456-7890123라는 문자열 str을 1234567890123 형식으로
     * 바꾸고 싶다면, replaceStr( str, "-", "") 로 호출한다.
     *
     * @param source
     *            String 변환할 문자열
     * @param keyStr
     *            String 치환 대상 문자열
     * @param toStr
     *            String 치환될 문자열
     * @return String the translated string.
     */
    public static String replaceStr(String source, String keyStr, String toStr) {
        if (source == null)
            return null;
        int startIndex = 0;
        int curIndex = 0;
        StringBuffer result = new StringBuffer();

        while ((curIndex = source.indexOf(keyStr, startIndex)) >= 0) {
            result.append(source.substring(startIndex, curIndex)).append(toStr);
            startIndex = curIndex + keyStr.length();
        }

        if (startIndex <= source.length())
            result.append(source.substring(startIndex, source.length()));

        return result.toString();

    }

    /**
     * 문자열에서 특정 문자열을 치환한다. 문자열 배열의 차례대로 치환하되 더 이상 배열 값이 없으면 space 1칸으로 치환한다.
     *
     * @param source
     *            String 변환할 문자열
     * @param keyStr
     *            String 치환 대상 문자열
     * @param toStr
     *            String[] 치환될 문자열 배열
     * @return String the translated string.
     */
    public static String replaceStr(String source, String keyStr, String[] toStr) {
        if (source == null)
            return null;
        int startIndex = 0;
        int curIndex = 0;
        int i = 0;
        StringBuffer result = new StringBuffer();
        String specialString = null;

        while ((curIndex = source.indexOf(keyStr, startIndex)) >= 0) {
            if (i < toStr.length)
                specialString = toStr[i++];
            else
                specialString = " ";
            result.append(source.substring(startIndex, curIndex)).append(specialString);
            startIndex = curIndex + keyStr.length();
        }

        if (startIndex <= source.length())
            result.append(source.substring(startIndex, source.length()));

        return result.toString();
    }

    /**
     * 배열을 구분자를 넣어 String 으로 치환한다.
     *
     * @param ArrayList<String>
     *            toStr 배열
     * @param String
     *            keyStr 구분자
     * @return String the translated string.
     */
    public static String ArrayReplaceString(ArrayList<String> toStr, String keyStr) {

        String private_String = "";

        for (int i = 0; i < toStr.size(); i++) {
            if (private_String.equals("")) {
                private_String = toStr.get(i);
            } else {
                private_String = private_String + keyStr + toStr.get(i);
            }
        }

        return private_String;
    }

    /**
     * 문자열을 특정 형식으로 출력한다. 단, source가 null이라면 빈 문자열(empty string)을 리턴한다. 형식: #은
     * 문자열을 나타낸다. # 외의 문자는 그대로 출력된다. 예를 들어, 1234567890123라는 문자열 str을
     * 123456-7890123 형식으로 바꾸고 싶다면, printStr( str, "######-#######") 로 호출한다.
     *
     * @param source
     *            String 변환할 문자열
     * @param format
     *            String 형식
     * @return the translated string.
     */
    public static String printStr(String source, String format) {
        if (source == null)
            return "";

        StringBuffer buf = new StringBuffer();
        char[] f = format.toCharArray();
        char[] s = source.toCharArray();

        int len = f.length;
        int h = 0;
        for (int i = 0; i < len; i++) {
            if (h >= s.length)
                break;
            if (f[i] == '#')
                buf.append(s[h++]);
            else
                buf.append(f[i]);
        }
        return buf.toString();
    }

    /**
     * 문자열에 0을 추가해서 리턴한다.
     *
     * @param source
     *            String 변환할 문자열
     * @param resultLen
     *            결과 길이
     * @param isFront
     *            0이 앞에 붙는지 뒤에 붙는지
     * @return the translated string.
     */
    public static String fillZero(String source, int resultLen, boolean isFront) {
        if (source == null)
            return "";
        source = source.trim();

        char[] s = source.toCharArray();
        if (resultLen <= s.length)
            return source;

        char[] result = new char[resultLen - s.length];
        for (int i = 0; i < (resultLen - s.length); i++) {
            result[i] = '0';
        }

        if (isFront)
            return (new String(result) + source);
        else
            return source + (new String(result));
    }

    /**
     * 문자열에 포함된 특정한 형태의 String을 다른 String으로 변환한다. 단, source가 null이라면 빈 문자열(empty
     * string)을 리턴한다. 예를 들어, abc======라는 문자열에서 ===부분을 =로 바꾸고 싶다면
     * printStr("abc======", "===", "=")와 같은 형식으로 사용한다.
     *
     * @param source
     *            String 변환할 문자열
     * @param from
     *            String 형식
     * @param to
     *            String 형식
     * @return the translated string.
     */
    public static String replaceString(String source, String from, String to) {
        if (source == null || to == null || from == null)
            return "";

        int index;
        if (to.indexOf(from) == -1) {
            while ((index = source.indexOf(from)) != -1) {
                source = source.substring(0, index) + to + source.substring(index + from.length(), source.length());
            }

            return source;
        } else {
            StringBuffer sb = new StringBuffer();
            int beforeIndex = 0;
            while ((index = source.indexOf(from, beforeIndex)) != -1) {
                sb.append(source.substring(beforeIndex, index) + to);
                beforeIndex = index + from.length();
            }
            return sb.toString();
        }
    }

//    public static String translateNewLine(String source) {
//        String result = "";
//        result = StringUtility.replaceStr(source, "\r\n", "<br />");
//        result = StringUtility.replaceStr(source, "\n", "<br />");
//        return result;
//    }

//    public static ArrayList<Object> translateNewLines(ArrayList<Object> source) {
//        String cont = null;
//        for (int i = 0; i < source.size(); i++) {
//            cont = (String) source.get(i);
//            cont = StringUtility.replaceStr(cont, "\n", "<br />");
//            source.set(i, cont);
//            cont = null;
//        }
//        return source;
//    }

    /**
     * Java에서 OutOfIndexException발생방지를위한 substring
     *
     * @param str
     *            String
     * @param startIndex
     *            int
     * @param endIndex
     *            int
     * @return String
     */
    public static String substring(String str, int startIndex, int endIndex) {
        if (str == null)
            return "";

        String returnString = "";
        int lastIndex = str.length();

        if (lastIndex < startIndex)
            return "";

        if ((lastIndex + 1) < endIndex) {
            returnString = str.substring(startIndex, lastIndex);
        } else {
            returnString = str.substring(startIndex, endIndex);
        }

        return returnString;

    }

    /**
     * 대상문자열(strTarget)에서 지정문자열(strSearch)이 검색된 횟수를, 지정문자열이 없으면 0 을 반환한다.
     *
     * @param strTarget
     *            String
     * @param strSearch
     *            String
     * @return int
     */
    public static int search(String strTarget, String strSearch) {
        int result = 0;
        String strCheck = new String(strTarget);
        for (int i = 0; i < strTarget.length();) {
            int loc = strCheck.indexOf(strSearch);
            if (loc == -1) {
                break;
            } else {
                result++;
                i = loc + strSearch.length();
                strCheck = strCheck.substring(i);
            }
        }
        return result;
    }

    /**
     * Remove special white space from both ends of this string.
     * <p>
     * All characters that have codes less than or equal to
     * <code>'&#92;u0020'</code> (the space character) are considered to be
     * white space.
     * <p>
     * java.lang.String의 trim()과 차이점은 일반적인 white space만 짜르는 것이 아니라 위에서와 같은 특수한
     * blank도 짤라 준다.<br>
     *
     * @param source
     *            String
     * @return String
     */
    public static String trim(String source) {
        int position = 0;
        char[] val = source.toCharArray();
        int count = val.length;
        int len = count;

        while ((position < len) && ((val[position] <= ' ') || (val[position] == '　')))
            position++;
        while ((position < len) && ((val[len - 1] <= ' ') || (val[len - 1] == '　')))
            len--;

        return ((position > 0) || (len < count)) ? source.substring(position, len) : source;
    }

    /**
     * 원하는 문자만큼 잘라서 리턴해준다.
     *
     * @param source
     *            String
     * @param postfix
     *            String
     * @param limit
     *            int
     * @return String
     */
    public static String limitString(String source, String postfix, int limit) {
        String buffer = "";
        char[] charArray = source.toCharArray();
        if (limit >= charArray.length)
            return source;

        for (int j = 0; j < limit; j++)
            buffer += charArray[j];

        buffer += postfix;
        return buffer;
    }

    /**
     * 원하는 문자만큼 잘라서 리턴해준다.
     *
     * @param str
     *            String
     * @param szAddValue
     *            String
     * @param limit
     *            int
     * @return String
     */
    public static String limitStringForKor(String str, String szAddValue, int limit) {
        if (limit < 4)
            return str;
        int strlen = 0;
        StringBuffer strBuf = new StringBuffer();
        int j = 0;
        do {
            if (j >= str.length())
                break;
            char c = str.charAt(j);
            if (c > '\uAC00' && c < '\uD7A3')
                strlen += 2;
            else
                strlen++;
            strBuf.append(c);
            if (strlen > limit - 3) {
                strBuf.append(szAddValue);
                break;
            }
            j++;
        } while (true);
        return strBuf.toString();
    }

    /**
     * 문자열을 받아서 null이면 공백 문자열로 리턴
     *
     * @param str
     *            String
     * @return String
     */
    public static String isNull(String str) {
        if ((str == null) || (str.trim().equals("")) || (str.trim().equals("null")))
            return "";
        else
            return str;
    }

    /**
     * 문자열을 받아서 null이면 공백 그렇지 않으면 앞뒤 공백을 제거한 문자열을 리턴
     *
     * @param str
     *            String
     * @return String
     */
    public static String isNullToTrim(String str) {
        return isNull(str).trim();
    }

    /**
     * 문자열을 받아서 널이면 &nbsp;로 리턴
     *
     * @param p_null
     *            String
     * @return String
     */
    public static String isNullToNbsp(String p_null) {
        String v_returnStr = p_null;
        if (p_null == null || p_null.trim().equals("") || p_null.trim().equals("null")) {
            v_returnStr = "&nbsp;";
        }
        return v_returnStr;
    }

    /**
     * 문자열이 공백이거나 널이면 숫자로 문자"0"을 리턴
     *
     * @param p_str
     *            String
     * @return String
     */
    public static String isNullToZero(String p_str) {
        if (p_str == null || p_str.trim().equals("") || p_str.trim().equals("null"))
            return "0";
        else
            return p_str;
    }

    /**
     * 문자열이 널이면 대체할 문자열을 리턴
     *
     * @param str
     *            String
     * @param NVLString
     *            String
     * @return String
     */
    public static String nvl(String str, String NVLString) {
        if ((str == null) || (str.trim().equals("")) || (str.trim().equals("null")))
            return NVLString;
        else
            return str;
    }

    /**
     * 문자열이 널이면 대체할 정수를 리턴
     *
     * @param str
     *            String
     * @param NVLInt
     *            int
     * @return int
     */
    public static int nvl(String str, int NVLInt) {
        if ((str == null) || (str.trim().equals("")) || (str.trim().equals("null")))
            return NVLInt;
        else
            return parseInt(str);
    }

    /**
     * 문자열이 널이면 대체할 double를 리턴
     *
     * @param str
     *            String
     * @param NVLDouble
     *            double
     * @return double
     */
    public static double NVL(String str, double NVLDouble) {
        if ((str == null) || (str.trim().equals("")) || (str.trim().equals("null")))
            return NVLDouble;
        else
            return parseDouble(str);
    }

    /**
     * 문자열을 받아서 널이면 &nbsp;로 리턴
     *
     * @param s
     *            String
     * @return String
     */
    public static String nullToNbsp(String s) {
        String v_returnStr = s;
        if (s == null || s.trim().equals("") || s.trim().equals("null")) {
            v_returnStr = "&nbsp;";
        }
        return v_returnStr;
    }

    /**
     * 문자열이 공백이거나 널이면 숫자로 문자"0"을 리턴
     *
     * @param s
     *            String
     * @return String
     */
    public static String nullToZero(String s) {
        if (s == null || s.trim().equals("") || s.trim().equals("null"))
            return "0";
        else
            return s;
    }

    /**
     * 문자열을 정수로 변환
     *
     * @param str
     *            String
     * @return int
     */
    public static int parseInt(String str) {

        if (str == null || str.equals("")) {
            return 0;
        } else {
            try {
                return Integer.parseInt(str);
            } catch (NumberFormatException e) {
                return 0;
            }
        }
    }

    /**
     * 문자열을 정수로 변환
     *
     * @param str
     *            String
     * @return int
     */
    public static int parseInt(String str, int initValue) {

        if (str == null || str.equals("")) {
            return initValue;
        } else {
            try {
                return Integer.parseInt(str);
            } catch (NumberFormatException e) {
                return initValue;
            }
        }
    }

    /**
     * 문자열을 long형으로 변환
     *
     * @param str
     *            String
     * @return long
     */
    public static long parseLong(String str) {

        if (str == null || str.equals("")) {
            return 0L;
        } else {
            try {
                return Long.parseLong(str);
            } catch (NumberFormatException e) {
                return 0L;
            }
        }
    }

    /**
     * 문자열을 float형으로 변환
     *
     * @param str
     *            String
     * @return float
     */
    public static float parseFloat(String str) {

        if (str == null || str.equals("")) {
            return 0f;
        } else {
            try {
                return Float.parseFloat(str);
            } catch (NumberFormatException e) {
                return 0f;
            }
        }
    }

    /**
     * 문자열을 double형으로 변환
     *
     * @param str
     *            String
     * @return double
     */
    public static double parseDouble(String str) {

        if (str == null || str.equals("")) {
            return 0;
        } else {
            try {
                return Double.parseDouble(str);
            } catch (NumberFormatException e) {
                return 0;
            }
        }
    }

    /**
     * 대상문자열(strTarget)에서 구분문자열(strDelim)을 기준으로 문자열을 분리하여 각 분리된 문자열을 배열에 할당하여
     * 반환한다.
     *
     * @param strTarget
     *            분리 대상 문자열
     * @param strDelim
     *            구분시킬 문자열로서 결과 문자열에는 포함되지 않는다.
     * @return 분리된 문자열을 순서대로 배열에 격납하여 반환한다.
     */
    public static String[] split(String strTarget, String strDelim) {
        // StringTokenizer는 구분자가 연속으로 중첩되어 있을 경우 공백 문자열을 반환하지 않음.
        // 따라서 아래와 같이 작성함.
        int index = 0;
        String[] resultStrArray = new String[search(strTarget, strDelim) + 1];
        String strCheck = new String(strTarget);
        while (strCheck.length() != 0) {
            int begin = strCheck.indexOf(strDelim);
            if (begin == -1) {
                resultStrArray[index] = strCheck;
                break;
            } else {
                int end = begin + strDelim.length();
                resultStrArray[index++] = strCheck.substring(0, begin);

                strCheck = strCheck.substring(end);
                if (strCheck.length() == 0) {
                    resultStrArray[index] = strCheck;
                    break;
                }
            }
        }
        return resultStrArray;
    }

    /**
     * 대상문자열(strTarget)에서 구분문자열(strDelim)을 기준으로 문자열을 분리하여 각 분리된 문자열을 배열에 할당하여
     * 반환한다.
     *
     * @param strTarget
     *            분리 대상 문자열
     * @param strDelim
     *            구분시킬 문자열로서 결과 문자열에는 포함되지 않는다.
     * @param bContainNull
     *            구분되어진 문자열중 공백문자열의 포함여부. true : 포함, false : 포함하지 않음.
     * @return 분리된 문자열을 순서대로 배열에 격납하여 반환한다.
     */
    public static String[] split(String strTarget, String strDelim, boolean bContainNull) {
        // StringTokenizer는 구분자가 연속으로 중첩되어 있을 경우 공백 문자열을 반환하지 않음.
        // 따라서 아래와 같이 작성함.
        int index = 0;
        String[] resultStrArray = new String[search(strTarget, strDelim) + 1];
        String strCheck = new String(strTarget);
        while (strCheck.length() != 0) {
            int begin = strCheck.indexOf(strDelim);
            if (begin == -1) {
                resultStrArray[index] = strCheck;
                break;
            } else {
                int end = begin + strDelim.length();
                if (bContainNull) {
                    resultStrArray[index++] = strCheck.substring(0, begin);
                }
                strCheck = strCheck.substring(end);
                if (strCheck.length() == 0 && bContainNull) {
                    resultStrArray[index] = strCheck;
                    break;
                }
            }
        }
        return resultStrArray;
    }

    /**
     * Count the occurrences of the substring in string s
     *
     * @param s
     *            string to search in. Returns 0 if this is null
     * @param sub
     *            string to search for. Return 0 if this is null.
     */
    public static int countOccurrencesOf(String s, String sub) {
        if (s == null || sub == null || "".equals(sub))
            return 0;
        int count = 0, pos = 0, idx = 0;
        while ((idx = s.indexOf(sub, pos)) != -1) {
            ++count;
            pos = idx + sub.length();
            // System.out.println("Idx=" + idx + " Pos=" + pos);
        }
        return count;
    }

    /**
     * Replaces all occurences of a substring within a string with another
     * string.
     *
     * @param inString
     *            String to examine
     * @param oldPattern
     *            String to replace
     * @param newPattern
     *            String to insert
     * @return a String with the replacements
     */
    public static String replace(String inString, String oldPattern, String newPattern) {
        // Pick up error conditions
        if (inString == null)
            return "";
        if (oldPattern == null || newPattern == null)
            return inString;

        StringBuffer sbuf = new StringBuffer(); // Output StringBuffer we'll
                                                // build up
        int pos = 0; // Our position in the old string
        int index = inString.indexOf(oldPattern); // The index of an occurrence
                                                    // we've found, or -1
        int patLen = oldPattern.length();
        while (index >= 0) {
            sbuf.append(inString.substring(pos, index));
            sbuf.append(newPattern);
            pos = index + patLen;
            index = inString.indexOf(oldPattern, pos);
        }
        sbuf.append(inString.substring(pos)); // Remember to append any
                                                // characters to the right of a
                                                // match
        return sbuf.toString();
    } // replace

    /**
     * pattern과 일치하는 문자열을 뺀 문자열을 반환
     *
     * @param inString
     *            String to examine
     * @param pattern
     *            pattern to delete all occurrences of
     * @return String
     */
    public static String delete(String inString, String pattern) {
        return replace(inString, pattern, "");
    }

    /**
     * chars와 일치하지 않는 문자열을 뺀 문자열을 반환
     *
     * @param inString
     *            String to examine
     * @param chars
     *            characters to delete e.g. az\n will delete as, zs and new
     *            lines
     * @return String
     */
    public static String deleteAny(String inString, String chars) {
        if (inString == null || chars == null)
            return inString;
        StringBuffer out = new StringBuffer();
        for (int i = 0; i < inString.length(); i++) {
            char c = inString.charAt(i);
            if (chars.indexOf(c) == -1) {
                out.append(c);
            }
        }
        return out.toString();
    }

    /**
     * Take a String which is a delimited list and convert it to a String array
     *
     * @param s
     *            String
     * @param delimiter
     *            delimiter. This will not be returned
     * @return an array of the tokens in the list
     */
    public static String[] delimitedListToStringArray(String s, String delimiter) {
        if (s == null)
            return new String[0];
        if (delimiter == null)
            return new String[] { s };

        List<Object> l = new LinkedList<>();
        int pos = 0;
        int delpos = 0;
        while ((delpos = s.indexOf(delimiter, pos)) != -1) {
            l.add(s.substring(pos, delpos));
            pos = delpos + delimiter.length();
        }
        if (pos <= s.length()) {
            // Add rest of String
            l.add(s.substring(pos));
        }

        return (String[]) l.toArray(new String[l.size()]);

    } // delimitedListToStringArray

    /**
     * Convert a CSV list into an array of Strings
     *
     * @param s
     *            CSV list
     * @return an array of Strings. Returns the empty array if s is null.
     */
    public static String[] commaDelimitedListToStringArray(String s) {
        return delimitedListToStringArray(s, ",");
    }

    /**
     * Convenience method to return a String array as a delimited (e.g. CSV)
     * String. Useful for toString() implementations
     *
     * @param arr
     *            array to display. Elements may be of any type (toString() will
     *            be called on each element).
     * @param delim
     *            delimiter to use (probably a ,)
     */
    public static String arrayToDelimitedString(Object[] arr, String delim) {
        if (arr == null)
            return "";
        else {
            StringBuffer sb = new StringBuffer();
            for (int i = 0; i < arr.length; i++) {
                if (i > 0)
                    sb.append(delim);
                sb.append(arr[i]);
            }
            return sb.toString();
        }
    } // arrayToDelimitedString

    public static String arrayListToDelimitedString(ArrayList<Object> arr, String delim) {
        if (arr == null)
            return "";
        else {
            StringBuffer sb = new StringBuffer();
            for (int i = 0; i < arr.size(); i++) {
                if (i > 0)
                    sb.append(delim);
                sb.append(arr.get(i));
            }
            return sb.toString();
        }
    }

    /**
     * Convenience method to return a Collection as a delimited (e.g. CSV)
     * String. Useful for toString() implementations
     *
     * @param c
     *            Collection to display
     * @param delim
     *            delimiter to use (probably a ,)
     */
    public static String collectionToDelimitedString(Collection<Object> c, String delim) {
        if (c == null)
            return "";
        return iteratorToDelimitedString(c.iterator(), delim);
    } // collectionToDelimitedString

    /**
     * Convenience method to return a Collection as a delimited (e.g. CSV)
     * String. Useful for toString() implementations
     *
     * @param itr
     *            Iterator to display
     * @param delim
     *            delimiter to use (probably a ,)
     */
    public static String iteratorToDelimitedString(Iterator<Object> itr, String delim) {
        if (itr == null)
            return "";
        else {
            StringBuffer sb = new StringBuffer();
            int i = 0;
            while (itr.hasNext()) {
                if (i++ > 0)
                    sb.append(delim);
                sb.append(itr.next());
            }
            return sb.toString();
        }
    } // collectionToDelimitedString

    /**
     * Take a String which is a delimited list and convert it to a ArrayList
     *
     * @param s
     *            String
     * @param delimiter
     *            delimiter. This will not be returned
     * @return an array of the tokens in the list
     */
    public static ArrayList<Object> stringToDelimitedArrayList(String s, String delimiter) {
        ArrayList<Object> al = new ArrayList<Object>();
        int beforeIndex = 0;
        int index;

        if (s == null || s.trim().equals(""))
            return al;

        while ((index = s.indexOf(delimiter, beforeIndex)) != -1) {
            al.add(s.substring(beforeIndex, index));
            beforeIndex = index + 1;
        }

        if (beforeIndex + 1 <= s.length())
            al.add(s.substring(beforeIndex, s.length()));

        return al;
    }

    /**
     * 문자열의 길이가 len보다 적으면 문자열의 길이가 len이 될때 까지 공백을 추가 한다.
     *
     * @param p_str
     *            String
     * @param len
     *            int
     * @return String
     */
    public static String getAppendBlank(String p_str, int len) {

        String str = p_str;
        int str_length = str.length();

        if (str_length < len) {
            for (int i = 0; i < len - str_length; i++) {
                str += "&nbsp;";
            }

        }

        return str;
    }

    /**
     * 문자열의 길이가 len보다 적으면 문자열의 길이가 len이 될때 까지 공백을 추가 한다.
     *
     * @param p_str
     *            String
     * @param len
     *            int
     * @return String
     */
    public static String getAppendBlankLeft(String p_str, int len) {

        String str = p_str;
        int str_length = str.length();

        if (str_length < len) {
            for (int i = 0; i < len - str_length; i++) {
                str = " " + str;
            }

        }

        return str;
    }

    public static String getAppendBlankRight(String p_str, int len) {

        String str = p_str;
        int str_length = str.length();

        if (str_length < len) {
            for (int i = 0; i < len - str_length; i++) {
                str = str + " ";
            }

        }

        return str;
    }

    /**
     * 문자열을 boolean형 자료형으로 변환
     *
     * @param s
     *            String
     * @return boolean
     */
    public static boolean getBoolean(String s) {
        boolean is = false;
        try {
            is = (new Boolean(s)).booleanValue();
        } catch (Exception exception) {
        }

        return is;
    }

    /**
     * Object 자료형을 boolean형 자료형으로 변환
     *
     * @param o
     *            Object
     * @return boolean
     */
    public static boolean getBoolean(Object o) {
        boolean is = false;
        try {
            if (o != null)
                is = getBoolean((String) o);
        } catch (Exception exception) {
        }

        return is;
    }

    /*
     * public static String xmlToJSONValue(String xmlStr) { Document doc = null;
     * try { DocumentBuilderFactory factory =
     * DocumentBuilderFactory.newInstance();
     * factory.setIgnoringElementContentWhitespace(true); DocumentBuilder
     * builder = factory.newDocumentBuilder(); doc = builder.parse(new
     * InputSource(new StringReader(xmlStr))); } catch(Exception e) {
     * System.out.println(e); }
     *
     * }
     */

    public static String getCalcStr(String str, int sLoc, int eLoc) {
        byte[] bystStr;
        String rltStr = "";
        try {
            bystStr = str.getBytes();
            rltStr = new String(bystStr, sLoc, eLoc - sLoc);
        } catch (Exception e) {
            return "";
        }
        return rltStr;
    }

    public static String[] makeArrayToString(String raw, int len) {

        if (raw == null)
            return null;

        ArrayList<Object> aryList = new ArrayList<Object>();
        // String[] ary =null;
        try {
            // raw 의 byte
            byte[] rawBytes = raw.getBytes("MS949");
            int rawLength = rawBytes.length;

            if (rawLength > len) {

                int aryLength = (rawLength / len) + (rawLength % len != 0 ? 1 : 0);

                int endCharIndex = 0; // 문자열이 끝나는 위치
                String tmp;
                for (int i = 0; i < aryLength; i++) {

                    if (i == (aryLength - 1)) {

                        tmp = raw.substring(endCharIndex);
                        // else 부분에서 endCharIndex 가 작아져서 (이를테면 len 10 이고 else
                        // 부분에서 잘려지는 길이가 9 일때)
                        // 위에서 계산되어진 aryLength 길이보다 길이가 더 길어질 소지가 있습니다.
                        // 이에 길이가 더 길 경우에는 for 한번더 돌도록 한다.
                        if (tmp.getBytes("MS949").length > len) {
                            aryLength++;
                            i--;
                            continue;
                        }

                    } else {

                        int useByteLength = 0;
                        int rSize = 0;
                        for (; endCharIndex < raw.length(); endCharIndex++) {

                            if (raw.charAt(endCharIndex) > 0x007F) {
                                useByteLength += 2;
                            } else {
                                useByteLength++;
                            }
                            if (useByteLength > len) {
                                break;
                            }
                            rSize++;
                        }
                        tmp = raw.substring((endCharIndex - rSize), endCharIndex);
                    }
                    aryList.add(tmp);

                }

            } else {
                aryList.add(raw);
            }

        } catch (java.io.UnsupportedEncodingException e) {
        }

        return (String[]) aryList.toArray(new String[0]);
    }

    public static int getByteSize(String str) {

        byte[] bytes = str.getBytes();
        return bytes.length;
    }

    public static String formatDate(String src) {
        if (src == null || src.length() != 8)
            return "";
        else
            return src.substring(0, 4) + "-" + src.substring(4, 6) + "-" + src.substring(6);
    }

    /**
     * String에 포함된 모든 newline문자(\n)를 특정String으로 변환하여 줌
     *
     * @param source
     *            String 변환할 string
     * @param specialString
     *            newline문자를 대체할 string
     * @return the translated string.
     */
    public static String translateNewline(String source, String specialString) {

        String result = "";
        java.util.StringTokenizer st = new java.util.StringTokenizer(source, "\n");
        while (st.hasMoreTokens())
            result += st.nextToken() + specialString;
        return result;
    }

    public static String convert2CamelCase(String underScore) {
        if (underScore.indexOf('_') < 0 && Character.isLowerCase(underScore.charAt(0)))
            return underScore;
        StringBuffer result = new StringBuffer();
        boolean nextUpper = false;
        int len = underScore.length();
        for (int i = 0; i < len; i++) {
            char currentChar = underScore.charAt(i);
            if (currentChar == '_') {
                nextUpper = true;
                continue;
            }
            if (nextUpper) {
                result.append(Character.toUpperCase(currentChar));
                nextUpper = false;
            } else {
                result.append(Character.toLowerCase(currentChar));
            }
        }

        return result.toString();
    }

    public static String unscript(String data) {
        if (data == null || data.trim().equals("")) {
            return "";
        }

        String ret = data;

        ret = ret.replaceAll("<(S|s)(C|c)(R|r)(I|i)(P|p)(T|t)", "&lt;script");
        ret = ret.replaceAll("</(S|s)(C|c)(R|r)(I|i)(P|p)(T|t)", "&lt;/script");

        ret = ret.replaceAll("<(O|o)(B|b)(J|j)(E|e)(C|c)(T|t)", "&lt;object");
        ret = ret.replaceAll("</(O|o)(B|b)(J|j)(E|e)(C|c)(T|t)", "&lt;/object");

        ret = ret.replaceAll("<(A|a)(P|p)(P|p)(L|l)(E|e)(T|t)", "&lt;applet");
        ret = ret.replaceAll("</(A|a)(P|p)(P|p)(L|l)(E|e)(T|t)", "&lt;/applet");

        ret = ret.replaceAll("<(E|e)(M|m)(B|b)(E|e)(D|d)", "&lt;embed");
        ret = ret.replaceAll("</(E|e)(M|m)(B|b)(E|e)(D|d)", "&lt;embed");

        ret = ret.replaceAll("<(F|f)(O|o)(R|r)(M|m)", "&lt;form");
        ret = ret.replaceAll("</(F|f)(O|o)(R|r)(M|m)", "&lt;form");

        return ret;
    }

    public static String getMFormatStr(List<String> data, String f) {
        String s = "";
        if (data == null || data.size() == 0)
            return s;
        for (String _s : data) {
            s += _s + f;
        }
        s = substring(s, 0, s.length() - 1);
        return s;
    }

    public static String strTohtml(String sText) {
        sText = sText.trim();
        sText = sText.replace("&", "&amp;");
        sText = sText.replace("#", "&#35;");
        sText = sText.replace("<", "&lt;");
        sText = sText.replace(">", "&gt;");
        sText = sText.replace("%", "&#37;");
        sText = sText.replace("\"", "&quot;");
        sText = sText.replace("'", "&#39;");
        // sText = sText.replace(" ", "&nbsp;");
        sText = sText.replace("\n", "<br>");

        return sText;
    }

    public static String htmlTostr(String sText) {
        sText = sText.replace("&lt;", "<");
        sText = sText.replace("&gt;", ">");
        sText = sText.replace("&amp;", "&");
        sText = sText.replace("&#37;", (char) 37 + "");
        sText = sText.replace("&quot;", (char) 34 + "");
        sText = sText.replace("&#39;", (char) 39 + "");
        sText = sText.replace("&#35;", "#");
        // sText = sText.replace("\n", "<br>");

        return sText;
    }

    public static String getSortString(String str, int length) {
        byte[] byteString = str.getBytes();

        if (byteString.length <= length) {
            return str;
        } else {
            int minusByteCount = 0;
            for (int i = 0; i < length; i++) {
                minusByteCount += (byteString[i] < 0) ? 1 : 0;
            }

            if (minusByteCount % 2 != 0) {
                length--;
            }

            return new String(byteString, 0, length) + "..";
        }

    }

    public static String getSecretValue(String str, int firstNum) {
        if (str == null || str.length() == 0)
            return str;
        char[] charArray = str.toCharArray();
        for (int i = 0; i < charArray.length; i++) {
            if (i >= firstNum) {
                charArray[i] = '*';
            }
        }

        return new String(charArray);

    }

    public static String ignoreXml(String text) {
        StringBuffer result = new StringBuffer();
        int position = 0;
        int bIndex = -1;
        int eIndex = -1;
        String temp = text;
        int textLength;
        for (textLength = temp.length(); position < textLength;) {
            bIndex = temp.indexOf("<", position);
            eIndex = temp.indexOf(">", bIndex);
            if (bIndex > -1) {
                if (eIndex < 0 || eIndex > textLength)
                    eIndex = textLength - 1;
                result.append(temp.substring(position, bIndex));
                String tag = temp.substring(bIndex, eIndex + 1);
                position = eIndex + 1;
                if (tag.equalsIgnoreCase("<style>")) {
                    String closeTag = getCloseTag(tag);
                    int cIndex = temp.indexOf(closeTag, position);
                    if (cIndex > -1)
                        position = cIndex + closeTag.length();
                }
            } else {
                result.append(temp.substring(position));
                position = textLength;
            }
        }

        if (position < textLength)
            result.append(temp.substring(position));
        return result.toString();
    }

    public static String getCloseTag(String openTag) {
        String closeTag = openTag;
        if (openTag != null && openTag.length() > 1) {
            StringBuffer sb = new StringBuffer();
            sb.append("</").append(openTag.substring(1));
            closeTag = sb.toString();
        }
        return closeTag;
    }

    public static String getNotTagString(String value, int size) {
        if (value == null) {
            return "";
        }

        value = getNotTagString(value);

        if (value.length() > size) {
            value = value.substring(0, size) + "...";
        }
        return value;
    }

    public static String getNotTagString(String value) {
        if (value == null) {
            return "";
        }
        /* loop error 발생으로 인한 수정 20180920 jsy
        value = value.replaceAll("<(/)?([a-zA-Z]*)(\\s[a-zA-Z]*=[^>]*)?(\\s)*(/)?>", "").replaceAll("<!--.*-->", "").replaceAll("<(?:.|\\s)*?>", "");
        */
        Pattern TAGS = Pattern.compile("<(/)?([a-zA-Z]*)(\\s[a-zA-Z]*=[^>]*)?(\\s)*(/)?>",Pattern.DOTALL);
        Matcher m = TAGS.matcher(value);
        value = m.replaceAll("").replaceAll("<!--.*-->", "").replaceAll("<(?:.|\\s)*?>", "");
        return value;
    }

    public static String replaceAll(String value, String regex, String replacement) {
        if (value == null) {
            return value;
        }
        String result = value.replaceAll(regex, replacement);
        return result;
    }

    public static String nullSafeToString(Object obj) {
        if (obj == null) {
            return "";
        }

        return obj.toString();
    }

    public static String cleanPath(String path) {
        path = StringUtils.cleanPath(path);
        path = StringUtils.replace(path, "//", "/");
        return path;
    }

    /**
     * Check that the given String is neither {@code null} nor of length 0.
     * Note: Will return {@code true} for a String that purely consists of whitespace.
     * @param str the String to check (may be {@code null})
     * @return {@code true} if the String is not null and has length
     * @see #hasLength(CharSequence)
     */
    public static boolean hasLength(String str) {
        return hasLength((CharSequence) str);
    }

    public static boolean hasLength(CharSequence str) {
        return (str != null && str.length() > 0);
    }

    public static boolean hasText(String str) {
        return hasText((CharSequence) str);
    }

    public static boolean hasText(CharSequence str) {
        if (!hasLength(str)) {
            return false;
        }
        int strLen = str.length();
        for (int i = 0; i < strLen; i++) {
            if (!Character.isWhitespace(str.charAt(i))) {
                return true;
            }
        }
        return false;
    }

    public static String toName(String input) {
        StringBuilder strBuilder = new StringBuilder();
        strBuilder.append(input.substring(0,1).toUpperCase());
        strBuilder.append(input.substring(1));
        return strBuilder.toString();
    }
}

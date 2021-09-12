package com.csf.whoami.base.util;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Date;
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
}

/**
 * 
 */
package com.csf.whoami.base.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

import com.csf.whoami.base.constant.ConstantsDateFormat;
import com.csf.whoami.base.constant.ConstantsString;

public class DateTimeUtils {

    public static String YYYYMM = "yyyy-MM";
    public static String YYYYMMDD = "yyyy-MM-dd";
    public static String YYYYMMDDhhmmss = "yyyy-MM-dd HH:mm:ss";
    public static String hhmmss = "HH:mm:ss";
    public static String Z = "Z";
    public static String Z_OFFSET = "+00:00";
    public static final String PLAYLIST_TIME = "EEEEE, MM/dd";

    /**
     * private constructor
     */
    private DateTimeUtils() {
    }

    /**
     * convert String To Time
     * 
     * @param date    to convert
     * @param pattern in converting
     * @return date
     */
    public static Date convertStringToTime(String date, String pattern) throws Exception {
        SimpleDateFormat dateFormat = new SimpleDateFormat(pattern);
        try {
            return dateFormat.parse(date);
        } catch (ParseException e) {
            throw e;
        }
    }

    /**
     * convert Date To String
     * 
     * @param date
     * @param pattern
     * @return String
     */
    public static String dateToString(Date date, String pattern) {
        try {
            if (date == null) {
                return "";
            }
            if (ConstantsDateFormat.YYYYMMDDThhmmssZ.equals(pattern)) {
                pattern = ConstantsDateFormat.YYYYMMDDhhmmss;
                SimpleDateFormat dateFormat = new SimpleDateFormat(pattern);
                return dateFormat.format(date).replace(ConstantsString.SPACE, ConstantsString.T).concat(ConstantsString.Z);
            } else {
                SimpleDateFormat dateFormat = new SimpleDateFormat(pattern);
                return dateFormat.format(date);
            }
        } catch (Exception e) {
            throw e;
        }
    }

    /**
     * get SysDate Time
     * 
     * @param pattern to convert
     * @return String
     * @throws Exception if error
     */
    public static String getSysDateTime(String pattern) throws Exception {
        Calendar calendar = Calendar.getInstance();
        SimpleDateFormat dateFormat = new SimpleDateFormat(pattern);
        try {
            return dateFormat.format(calendar.getTime());
        } catch (Exception e) {
            throw e;
        }
    }

    /**
     * compare Date Time
     * 
     * @param sourceDate
     * @param compareDate
     * @return int
     */
    public static int compareDateTime(Date sourceDate, Date compareDate) {
        int result = 0;
        Calendar cal1 = Calendar.getInstance();
        Calendar cal2 = Calendar.getInstance();
        cal1.setTime(sourceDate);
        cal2.setTime(compareDate);
        if (cal1.after(cal2)) {
            result = 1;
        } else if (cal1.before(cal2)) {
            result = -1;
        }
        return result;
    }

    /**
     * convert String To Date Or Null
     * 
     * @param date
     * @param pattern
     * @return
     */
    public static Date convertStringToDateOrNull(String date, String pattern) {
        try {
            return convertStringToTime(date, pattern);
        } catch (Exception e) {
            return null;
        }
    }

    /**
     * get First Date Of Month
     * 
     * @param date
     * @return
     */
    public static Date getFirstDateOfMonth(Date date) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.set(Calendar.DAY_OF_MONTH, cal.getActualMinimum(Calendar.DAY_OF_MONTH));
        return cal.getTime();
    }

    /**
     * get Age
     * 
     * @param birthdate
     * @return
     */
    public static int getAge(final Date birthdate) {
        return getAge(Calendar.getInstance().getTime(), birthdate);
    }

    /**
     * get Age
     * 
     * @param current
     * @param birthdate
     * @return int
     */
    public static int getAge(final Date current, final Date birthdate) {

        if (birthdate == null) {
            return 0;
        }

        if (current == null) {
            return getAge(birthdate);
        } else {
            final Calendar calend = new GregorianCalendar();
            calend.set(Calendar.HOUR_OF_DAY, 0);
            calend.set(Calendar.MINUTE, 0);
            calend.set(Calendar.SECOND, 0);
            calend.set(Calendar.MILLISECOND, 0);

            calend.setTimeInMillis(current.getTime() - birthdate.getTime());

            int result = 0;
            result = calend.get(Calendar.YEAR) - 1970;
            result += (int) calend.get(Calendar.MONTH) / (int) 12;
            return result;
        }

    }

    /**
     * get Next Date
     * 
     * @param date
     * @return Date
     */
    public static Date getNextDate(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.DAY_OF_WEEK, 1);
        return calendar.getTime();
    }

    /**
     * get Date Before After
     * 
     * @param date
     * @param num
     * @return
     */
    public static Date getDateBeforeAfter(Date date, int num) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.DATE, num);
        return calendar.getTime();
    }

    /**
     * get Dates Between
     * 
     * @param startDate
     * @param endDate
     * @return List<Date>
     */
    public static List<Date> getDatesBetween(Date startDate, Date endDate) {
        List<Date> datesInRange = new ArrayList<>();
        Calendar calendar = new GregorianCalendar();
        calendar.setTime(startDate);

        Calendar endCalendar = new GregorianCalendar();
        endCalendar.setTime(endDate);

        while (calendar.before(endCalendar)) {
            Date result = calendar.getTime();
            datesInRange.add(result);
            calendar.add(Calendar.DATE, 1);
        }
        return datesInRange;
    }

    /**
     * Check if date in range
     *
     * @param date     date
     * @param fromDate fromDate
     * @param toDate   toDate
     * @return true if date in range
     */
    public static boolean isDateBetween(Date date, Date fromDate, Date toDate) {
        return date.after(fromDate) && date.before(toDate);
    }

    /**
     * @description check date format right
     * @param date
     * @return
     */
    public static boolean checkDateFormatFollowPattern(String date, String pattern) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
        try {
            simpleDateFormat.parse(date);
            return true;
        } catch (ParseException e) {
            return false;
        }
    }

    /**
     * @author at-tuandang
     * @param fromDate Start date.
     * @param toDate   End date.
     * @param pattern  Date format.
     * @return
     * @throws ParseException Error can happen when cast string to date.
     */
    public static long getDateNumberBetweenToDate(String fromDate, String toDate, String pattern) throws ParseException {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
        Date startDate = simpleDateFormat.parse(fromDate);
        Date endDate = simpleDateFormat.parse(toDate);

        return (endDate.getTime() - startDate.getTime()) / ConstantsDateFormat.DAY_MILISECONDS;
    }

    /**
     * convert Date To String
     *
     * @param date
     * @param pattern
     * @return String
     */
    public static String convertDateToString(Date date, String pattern) {
        SimpleDateFormat dateFormat = new SimpleDateFormat(pattern);
        return date == null ? null : dateFormat.format(date);
    }
}

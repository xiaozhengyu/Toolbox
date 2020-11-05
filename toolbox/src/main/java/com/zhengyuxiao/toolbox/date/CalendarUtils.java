package com.zhengyuxiao.toolbox.date;

import java.util.Calendar;
import java.util.Date;

/**
 * @author xzy
 * @date 2020-09-14 14:05
 * 说明：日期处理
 */
public class CalendarUtils {
    /**
     * Get a instance of Calendar by given Date instance.
     *
     * @param date the date want to convert
     * @return a instance of Calendar by given Date instance
     */
    public static Calendar date2Calendar(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        return calendar;
    }

    /**
     * Get a instance of Date by given Calendar instance.
     *
     * @param calendar the calendar want to convert
     * @return a instance of Date by given Calendar instance.
     */
    public static Date calendar2Date(Calendar calendar) {
        return calendar.getTime();
    }

    /**
     * Get the day of the week represented by this date.
     *
     * @param date -
     * @return 0：Sunday 1：Monday 2：Tuesday 3：Wednesday 4：Thursday 5：Friday 6：Saturday
     */
    public static int getDayOfWeek(Date date) {
        Calendar calendar = date2Calendar(date);
        return calendar.get(Calendar.DAY_OF_WEEK);
    }

    /**
     * Get the day of the year represented by this date.
     *
     * @param date -
     * @return the day of the year represented by this date.The first day of the year has value 1.
     */
    public static int getDayOfYear(Date date) {
        Calendar calendar = date2Calendar(date);
        return calendar.get(Calendar.DAY_OF_YEAR);
    }

    /**
     * Returns whether the two dates are the same day.
     *
     * @param date1 the date 1
     * @param date2 the date 2
     * @return whether the two dates are the same day.
     */
    public static boolean isTheSameDay(Date date1, Date date2) {
        if (date1 == null || date2 == null) {
            throw new IllegalArgumentException("The date must not be null");
        }
        Calendar cal1 = Calendar.getInstance();
        cal1.setTime(date1);
        Calendar cal2 = Calendar.getInstance();
        cal2.setTime(date2);
        return isSameDay(cal1, cal2);
    }

    public static boolean isSameDay(Calendar cal1, Calendar cal2) {
        if (cal1 == null || cal2 == null) {
            throw new IllegalArgumentException("The date must not be null");
        }

        return (cal1.get(Calendar.ERA) == cal2.get(Calendar.ERA) &&
                cal1.get(Calendar.YEAR) == cal2.get(Calendar.YEAR) &&
                cal1.get(Calendar.DAY_OF_YEAR) == cal2.get(Calendar.DAY_OF_YEAR));
    }
}

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
     * Get the number of days between two dates.
     *
     * @param startDate - the start date
     * @param endDate   - the end date
     * @return the number of days between two dates.
     */
    public static long getDaysOfDifference(Date startDate, Date endDate) {

        // 1.convert Date instance to Calendar instance
        Calendar startCalendar = Calendar.getInstance();
        Calendar endCalendar = Calendar.getInstance();
        startCalendar.setTime(startDate);
        endCalendar.setTime(endDate);

        // 2.get the number of milliseconds corresponding to the date（statistics from 1900）
        long millisForStartDate = startCalendar.getTimeInMillis();
        long millisForEndDate = endCalendar.getTimeInMillis();

        // 3.get the number of days corresponding to the date（statistics from 1900）
        long daysForStartDate = millisForStartDate / 1000 / 3600 / 24;
        long daysForEndDate = millisForEndDate / 1000 / 3600 / 24;

        // 4.get the number of days between two dates
        return daysForEndDate - daysForStartDate;
    }

    /**
     * Returns whether the two dates are the same day.
     *
     * @param date1 the date 1
     * @param date2 the date 2
     * @return whether the two dates are the same day.
     */
    public static boolean isTheSameDay(Date date1, Date date2) {
        return 0 == getDaysOfDifference(date1, date2);
    }
}

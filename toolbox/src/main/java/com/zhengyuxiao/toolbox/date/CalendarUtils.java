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
     * 计算两个日期间相差的天数。
     *
     * @param date1 - 日期1
     * @param date2 - 日期2
     * @return - 两个日期相差的天数。注意，本方法认为 “2020-01-01 23:59:59” 与 “2020-01-02 00:00:00”相差1天。
     */
    public static long getDaysOfDifference(Date date1, Date date2) {
        if (date1 == null || date2 == null) {
            throw new NullPointerException();
        }
        return getDaysOfDifference(date2Calendar(date1), date2Calendar(date2));
    }

    /**
     * 计算两个日期间相差的天数。
     *
     * @param cal1 - 日期1
     * @param cal2 - 日期2
     * @return - 两个日期相差的天数。注意，本方法认为 “2020-01-01 23:59:59” 与 “2020-01-02 00:00:00”相差1天。
     */
    public static long getDaysOfDifference(Calendar cal1, Calendar cal2) {
        if (cal1 == null || cal2 == null) {
            throw new NullPointerException();
        }
        cleanTime(cal1);
        cleanTime(cal2);

        // 1天 = 24小时 = 24*3600秒 = 24*3600*1000毫秒
        long dayOfDifference = (cal1.getTimeInMillis() - cal2.getTimeInMillis()) / (1000 * 3600 * 24);
        dayOfDifference = dayOfDifference < 0 ? -dayOfDifference : dayOfDifference;
        return dayOfDifference;
    }

    /**
     * Returns whether the two dates are the same day.
     *
     * @param date1 the date 1
     * @param date2 the date 2
     * @return whether the two dates are the same day.
     */
    public static boolean isSameDay(Date date1, Date date2) {
        if (date1 == null || date2 == null) {
            throw new NullPointerException();
        }
        return isSameDay(date2Calendar(date1), date2Calendar(date2));
    }

    /**
     * Returns whether the two dates are the same day.
     *
     * @param cal1 the date 1
     * @param cal2 the date 2
     * @return whether the two dates are the same day.
     */
    public static boolean isSameDay(Calendar cal1, Calendar cal2) {
        if (cal1 == null || cal2 == null) {
            throw new NullPointerException();
        }

        return (cal1.get(Calendar.ERA) == cal2.get(Calendar.ERA) &&
                cal1.get(Calendar.YEAR) == cal2.get(Calendar.YEAR) &&
                cal1.get(Calendar.DAY_OF_YEAR) == cal2.get(Calendar.DAY_OF_YEAR));
    }

    /**
     * 清洗时间——将时、分、秒、毫秒清零
     *
     * @param calendar - 待处理的日期
     */
    public static void cleanTime(Calendar calendar) {
        if (calendar == null) {
            throw new IllegalArgumentException("The date must not be null");
        }
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);
    }

    public static Date cleanTime(Date date) {
        Calendar calendar = date2Calendar(date);
        cleanTime(calendar);
        return calendar2Date(calendar);
    }

    /**
     * 清除日期——将年、月、日清零
     *
     * @param calendar - 待处理的日期
     */
    public static void cleanDate(Calendar calendar) {
        if (calendar == null) {
            throw new IllegalArgumentException("The date must not be null");
        }
        calendar.set(Calendar.YEAR, 0);
        calendar.set(Calendar.MONTH, 0);
        calendar.set(Calendar.DAY_OF_MONTH, 0);
    }

    public static Date cleanDate(Date date) {
        Calendar calendar = date2Calendar(date);
        cleanDate(calendar);
        return calendar2Date(calendar);
    }

    /**
     * 获取指定日期的“最初时刻”，即“yyyy-MM-dd 00:00:00:000”
     *
     * @param calendar - 待处理的日期
     */
    public static void firstMoment(Calendar calendar) {
        cleanTime(calendar);
    }

    public static Date firstMoment(Date date) {
        Calendar calendar = date2Calendar(date);
        firstMoment(calendar);
        return calendar2Date(calendar);
    }

    /**
     * 获取指定日期的“最终时刻”，即“yyyy-MM-dd 23:59:59:999”
     *
     * @param calendar - 待处理的日期
     */
    public static void lastMoment(Calendar calendar) {
        if (calendar == null) {
            throw new IllegalArgumentException("The date must not be null");
        }
        calendar.set(Calendar.HOUR_OF_DAY, 23);
        calendar.set(Calendar.MINUTE, 59);
        calendar.set(Calendar.SECOND, 59);
        calendar.set(Calendar.MILLISECOND, 999);
    }

    public static Date lastMoment(Date date) {
        Calendar calendar = date2Calendar(date);
        lastMoment(calendar);
        return calendar2Date(calendar);
    }

    /**
     * 按照日期的计算规则，将指定日期的指定字段加上或减去指定的量。
     *
     * @param date   - 待修改的日期
     * @param field  - 待修改的日期字段（具体取值需要查询Calendar接口）
     * @param amount - 修改量
     * @return - 修改后的日期
     */
    public static Date add(Date date, int field, int amount) {
        if (date == null) {
            throw new IllegalArgumentException("The date must not be null");
        }

        Calendar calendar = date2Calendar(date);
        calendar.add(field, amount);
        return calendar2Date(calendar);
    }

    private CalendarUtils() {
    }
}

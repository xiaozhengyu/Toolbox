package com.zhengyuxiao.toolbox.date;

import java.util.Calendar;
import java.util.Date;

/**
 * @author xzy
 * @date 2020-11-02 10:02
 * 说明：
 */
public class DateFormat {

    /**
     * 将“时分秒”置零
     *
     * @param date - 待处理日期
     * @return - “时分秒”被置零的日期
     */
    public static Date toDate(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        // 将“时分秒”置零
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        return calendar.getTime();
    }

    /**
     * 获取当前日期（时、分、秒、毫秒被置为0）
     *
     * @return - 当前日期（时、分、秒、毫秒被置为0）
     */
    public static Date today() {
        Calendar calendar = Calendar.getInstance();
        // 时、分、秒、毫秒置为0
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);
        return calendar.getTime();
    }

    public static void main(String[] args) {
    }
}

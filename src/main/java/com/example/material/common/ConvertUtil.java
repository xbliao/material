package com.example.material.common;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * ConvertUtil.java
 *
 * @author xbliao   2019/9/24
 */
public class ConvertUtil {

    private final static SimpleDateFormat sdf = new SimpleDateFormat("YYYY-MM");

    public static String convertDateToStr(Date goalDate) {
        try {
            return sdf.format(goalDate);
        } catch (Exception e) {
            return null;
        }
    }

    /**
     * 获取一天的起点
     */
    public static Date getBeginTimeOfTheDay(Date dayTime) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(dayTime);
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);
        return calendar.getTime();
    }

    /**
     * 获取一个月的起点
     */
    public static Date getBeginTimeOfTheMonth(Date dayTime) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(dayTime);
        calendar.set(Calendar.DAY_OF_MONTH,0);
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);
        return calendar.getTime();
    }

}
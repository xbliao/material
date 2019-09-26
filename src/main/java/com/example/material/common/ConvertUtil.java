package com.example.material.common;

import org.apache.commons.lang3.time.DateUtils;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * ConvertUtil.java
 *
 * @author xbliao   2019/9/24
 */
public class ConvertUtil {

    private final static int MONTH_OF_THE_YEAR = 12;


    private final static SimpleDateFormat sdf = new SimpleDateFormat("YYYY-MM");


    /**
     * 转换成 2019-10 格式
     */
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
        calendar.set(Calendar.DAY_OF_MONTH, 0);
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);
        return calendar.getTime();
    }


    /**
     * 两个日期间的月数
     */
    public static int betweenMonth(Date val1, Date val2) {
        Calendar calculateCalendar = DateUtils.toCalendar(val1);
        Calendar nowCalendar = DateUtils.toCalendar(val2);

        int nowMonthCount = nowCalendar.get(Calendar.YEAR) * 12 + nowCalendar.get(Calendar.MONTH);
        int calculateMonthCount = calculateCalendar.get(Calendar.YEAR) * 12 + calculateCalendar.get(Calendar.MONTH);
        return nowMonthCount - calculateMonthCount;
    }


    /**
     * 增加plusCount个月
     */
    public static Date plusMonth(Date val1, int plusCount) {
        Calendar calendar = DateUtils.toCalendar(val1);
        calendar.add(Calendar.MONTH, plusCount);
        return calendar.getTime();
    }


}
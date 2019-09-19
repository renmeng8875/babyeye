package com.babytree.babyeye.base.util;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;


public class DateFormatUtils {

    private static final ThreadLocal<DateFormat> DEFAULT_DATE_FORMAT = new ThreadLocal<DateFormat>() {
        @Override
        protected DateFormat initialValue() {
            return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        }
    };


    public static String format(long millis) {
        return DEFAULT_DATE_FORMAT.get().format((new Date(millis)));
    }
}

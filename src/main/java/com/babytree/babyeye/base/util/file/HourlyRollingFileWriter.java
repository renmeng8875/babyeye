package com.babytree.babyeye.base.util.file;


import com.babytree.babyeye.base.util.DateUtils;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;


public class HourlyRollingFileWriter extends AutoRollingFileWriter {

    private static final ThreadLocal<SimpleDateFormat> FILE_DATE_FORMAT = new ThreadLocal<SimpleDateFormat>() {
        @Override
        protected SimpleDateFormat initialValue() {
            return new SimpleDateFormat("'.'yyyy-MM-dd-HH");
        }
    };

    public HourlyRollingFileWriter(String fileName) {
        super(fileName);
    }

    @Override
    long getNextRollingTime(Date now) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(now);
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.SECOND, 0);
        cal.set(Calendar.MILLISECOND, 0);
        cal.add(Calendar.HOUR_OF_DAY, 1);
        return cal.getTime().getTime();
    }

    @Override
    String formatDateFileName(String fileName, Date date) {
        return fileName + FILE_DATE_FORMAT.get().format(date);
    }

    @Override
    boolean isSameEpoch(Date date1, Date date2) {
        return DateUtils.isSameHour(date1, date2);
    }

}

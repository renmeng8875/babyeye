package com.babytree.babyeye.base.util;

import java.text.DecimalFormat;


public final class NumFormatUtils {

    private static final ThreadLocal<DecimalFormat> decimalFormat = new ThreadLocal<DecimalFormat>(){
        @Override
        protected DecimalFormat initialValue() {
            return new DecimalFormat("0.00");
        }
    };

    public static String getFormatStr(double num) {
        return decimalFormat.get().format(num);
    }
}

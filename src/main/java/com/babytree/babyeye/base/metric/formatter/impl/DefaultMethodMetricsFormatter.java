package com.babytree.babyeye.base.metric.formatter.impl;

import com.babytree.babyeye.base.metric.MethodMetrics;
import com.babytree.babyeye.base.metric.formatter.MethodMetricsFormatter;
import com.babytree.babyeye.base.util.DateFormatUtils;

import java.util.List;

public final class DefaultMethodMetricsFormatter implements MethodMetricsFormatter {


    /**
     * 下面对组成格式说明的各项加以说明：
     * ①%：表示格式说明的起始符号，不可缺少。
     * ②-：有-表示左对齐输出，如省略表示右对齐输出。
     * ③0：有0表示指定空位填0,如省略表示指定空位不填。
     * ④m.n：m指域宽，即对应的输出项在输出设备上所占的字符数。N指精度。用于说明输出的实型数的小数位数。为指定n时，隐含的精度为n=6位。
     * %ms：输出的字符串占m列，如字符串本身长度大于m，则突破获m的限制,将字符串全部输出。若串长小于m，则左补空格。
     * %-ms：如果串长小于m，则在m列范围内，字符串向左靠，右补空格。
     * %m.ns：输出占m列，但只取字符串中左端n个字符。这n个字符输出在m列的右侧，左补空格。
     % -m.ns：其中m、n含义同上，n个字符输出在m列范围的左侧，右补空格。如果n>m，则自动取n值，即保证n个字符正常输出。
     * @param methodMetricsList
     * @param startMillis
     * @param stopMillis
     * @return
     */
    @Override
    public String format(List<MethodMetrics> methodMetricsList, long startMillis, long stopMillis) {
        int[] statisticsArr = getStatistics(methodMetricsList);
        int maxApiLength = statisticsArr[0];

        String dataTitleFormat = "%-" + maxApiLength + "s%9s%9s%9s%9s%9s%10s%9s%9s%9s%9s%n";
        StringBuilder sb = new StringBuilder((methodMetricsList.size() + 2) * (9 * 11 + 1 + maxApiLength));
        sb.append("Method Metrics [").append(DateFormatUtils.format(startMillis)).append(", ").append(DateFormatUtils.format(stopMillis)).append("]").append(String.format("%n"));
        sb.append(String.format(dataTitleFormat, "Method[" + methodMetricsList.size() + "]", "RPS", "Avg(ms)", "Min(ms)", "Max(ms)", "StdDev", "Count", "TP50", "TP90", "TP95", "TP99"));
        if (methodMetricsList.isEmpty()) {
            return sb.toString();
        }

        String dataFormat = "%-" + maxApiLength + "s%9d%9.2f%9d%9d%9.2f%10d%9d%9d%9d%9d%n";
        for (int i = 0; i < methodMetricsList.size(); ++i) {
            MethodMetrics methodMetrics = methodMetricsList.get(i);
            if (methodMetrics.getTotalCount() <= 0) {
                continue;
            }

            sb.append(String.format(dataFormat,
                    methodMetrics.getMethodTag().getSimpleDesc(),
                    methodMetrics.getRPS(),
                    methodMetrics.getAvgTime(),
                    methodMetrics.getMinTime(),
                    methodMetrics.getMaxTime(),
                    methodMetrics.getStdDev(),
                    methodMetrics.getTotalCount(),
                    methodMetrics.getTP50(),
                    methodMetrics.getTP90(),
                    methodMetrics.getTP95(),
                    methodMetrics.getTP99()));
        }
        return sb.toString();
    }

    /**
     * @param methodMetricsList
     * @return : int[0]:max(api.length)
     */
    private int[] getStatistics(List<MethodMetrics> methodMetricsList) {
        int[] result = {1};
        for (int i = 0; i < methodMetricsList.size(); ++i) {
            MethodMetrics stats = methodMetricsList.get(i);
            if (stats == null || stats.getMethodTag() == null) {
                continue;
            }

            result[0] = Math.max(result[0], stats.getMethodTag().getSimpleDesc().length());
        }
        return result;
    }

}

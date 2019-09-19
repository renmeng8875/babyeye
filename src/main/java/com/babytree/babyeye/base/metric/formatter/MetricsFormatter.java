package com.babytree.babyeye.base.metric.formatter;
import com.babytree.babyeye.base.metric.Metrics;
import java.util.List;


public interface MetricsFormatter<T extends Metrics> {

    String format(List<T> metricsList, long startMillis, long stopMillis);

}

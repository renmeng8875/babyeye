package com.babytree.babyeye.base.constant;


public interface PropertyValues {

    String DEFAULT_PRO_FILE = "/data/babyEye/babyEye.properties";

    String RECORDER_MODE_ACCURATE = "ACCURATE";

    String RECORDER_MODE_ROUGH = "ROUGH";

    int MIN_BACKUP_RECORDERS_COUNT = 1;

    int MAX_BACKUP_RECORDERS_COUNT = 8;

    String LOG_ROLLING_TIME_MINUTELY = "MINUTELY";

    String LOG_ROLLING_TIME_HOURLY = "HOURLY";

    String LOG_ROLLING_TIME_DAILY = "DAILY";

    String DEFAULT_METRICS_FILE = "/data/logs/babyEye/metrics.log";

    String NULL_FILE = "NULL";

    String STDOUT_FILE = "STDOUT";

    long DEFAULT_TIME_SLICE = 60 * 1000L;

    long MIN_TIME_SLICE = 1000L;

    long MAX_TIME_SLICE = 10 * 60 * 1000L;

    String FILTER_SEPARATOR = ";";

}

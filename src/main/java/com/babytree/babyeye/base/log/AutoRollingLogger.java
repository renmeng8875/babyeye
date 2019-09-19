package com.babytree.babyeye.base.log;
import com.babytree.babyeye.base.constant.PropertyValues;
import com.babytree.babyeye.base.util.file.AutoRollingFileWriter;
import com.babytree.babyeye.base.util.file.DailyRollingFileWriter;
import com.babytree.babyeye.base.util.file.HourlyRollingFileWriter;
import com.babytree.babyeye.base.util.file.MinutelyRollingFileWriter;

public class AutoRollingLogger implements ILogger {

    private final AutoRollingFileWriter writer;

    AutoRollingLogger(String logFile, String rollingTimeUnit) {
        switch (rollingTimeUnit.toUpperCase()) {
            case PropertyValues.LOG_ROLLING_TIME_HOURLY:
                this.writer = new HourlyRollingFileWriter(logFile);
                break;
            case PropertyValues.LOG_ROLLING_TIME_MINUTELY:
                this.writer = new MinutelyRollingFileWriter(logFile);
                break;
            default:
                this.writer = new DailyRollingFileWriter(logFile);
        }
    }

    @Override
    public void log(String msg) {
        writer.write(msg + '\n');
    }

    @Override
    public void logAndFlush(String msg) {
        writer.writeAndFlush(msg + '\n');
    }

    @Override
    public void flushLog() {
        writer.flush();
    }

    @Override
    public void preCloseLog() {
        writer.preCloseFile();
    }

    @Override
    public void closeLog() {
        writer.closeFile(true);
    }

    @Override
    protected void finalize() {
        writer.closeFile(true);
    }
}

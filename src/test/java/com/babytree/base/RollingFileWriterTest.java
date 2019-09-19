package com.babytree.base;


import com.babytree.babyeye.base.util.file.AutoRollingFileWriter;
import com.babytree.babyeye.base.util.file.DailyRollingFileWriter;
import com.babytree.babyeye.base.util.file.HourlyRollingFileWriter;
import com.babytree.babyeye.base.util.file.MinutelyRollingFileWriter;
import org.junit.Test;

public class RollingFileWriterTest {

    @Test
    public void test() {
        AutoRollingFileWriter writer1 = new MinutelyRollingFileWriter("/tmp/test1.log");
        test(writer1);

        AutoRollingFileWriter writer2 = new HourlyRollingFileWriter("/tmp/test2.log");
        test(writer2);

        AutoRollingFileWriter writer3 = new DailyRollingFileWriter("/tmp/test3.log");
        test(writer3);
    }

    private void test(AutoRollingFileWriter writer) {
        writer.write("111111");
        writer.write("222222");
        writer.write("333333");
        writer.writeAndFlush("44444");
        writer.preCloseFile();
        writer.closeFile(true);
    }
}

package com.babytree.base;


import com.babytree.babyeye.base.config.MyProperties;
import com.babytree.babyeye.base.constant.PropertyKeys;
import com.babytree.babyeye.base.constant.PropertyValues;
import com.babytree.babyeye.base.util.IOUtils;
import com.babytree.babyeye.base.util.Logger;
import com.babytree.babyeye.base.util.file.AutoRollingFileWriter;
import com.babytree.babyeye.base.util.file.MinutelyRollingFileWriter;
import org.junit.BeforeClass;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import static com.babytree.babyeye.base.constant.PropertyKeys.*;


public abstract class BaseTest {

    public static final String TEMP_FILE = "/tmp/MyPerf4J.properties";

    public static final String APP_NAME = "MyPerf4JBaseTest";

    public static final int METRICS_PROCESSOR_TYPE = 1;

    public static final String INCLUDE_PACKAGES = "MyPerf4J";

    public static final int MILLI_TIMES_LICE = 1000;

    @BeforeClass
    public static void init() {
        System.setProperty(PRO_FILE_NAME, TEMP_FILE);
        AutoRollingFileWriter writer = new MinutelyRollingFileWriter(TEMP_FILE);
        writer.write("AppName=" + APP_NAME + "\n");
        writer.write("MetricsProcessorType=" + METRICS_PROCESSOR_TYPE + "\n");
        writer.write("IncludePackages=" + INCLUDE_PACKAGES + "\n");
        writer.write("MilliTimeSlice=" + MILLI_TIMES_LICE + "\n");
        writer.closeFile(true);

        new File(TEMP_FILE).deleteOnExit();

        initProperties();
    }

    private static void initProperties() {
        InputStream in = null;
        try {
            in = new FileInputStream(System.getProperty(PRO_FILE_NAME, PropertyValues.DEFAULT_PRO_FILE));

            Properties properties = new Properties();
            properties.load(in);
            MyProperties.initial(properties);
        } catch (IOException e) {
            Logger.error("BaseTest.initProperties()", e);
        } finally {
            IOUtils.closeQuietly(in);
        }
    }

}

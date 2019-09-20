package com.babytree.core;


import com.babytree.babyeye.base.config.ProfilingParams;
import com.babytree.babyeye.base.constant.PropertyKeys;
import com.babytree.babyeye.base.util.file.AutoRollingFileWriter;
import com.babytree.babyeye.base.util.file.MinutelyRollingFileWriter;
import com.babytree.babyeye.core.AbstractBootstrap;
import com.babytree.babyeye.core.recorder.AbstractRecorderMaintainer;
import com.babytree.babyeye.core.recorder.Recorders;
import org.junit.Assert;
import org.junit.Test;


public class AbstractBootstrapTest {

    @Test
    public void test() {
        initPropertiesFile();
        boolean initial = new AbstractBootstrap() {
            @Override
            public AbstractRecorderMaintainer doInitRecorderMaintainer() {
                return new AbstractRecorderMaintainer() {
                    @Override
                    public boolean initOther() {
                        return true;
                    }

                    @Override
                    public void addRecorder(int methodTagId, ProfilingParams params) {
                        for (int i = 0; i < recordersList.size(); ++i) {
                            Recorders recorders = recordersList.get(i);
                            recorders.setRecorder(methodTagId, createRecorder(methodTagId, params.getMostTimeThreshold(), params.getOutThresholdCount()));
                        }
                    }
                };
            }

            @Override
            public boolean initOther() {
                return true;
            }
        }.initial();

        Assert.assertTrue(initial);

    }

    private void initPropertiesFile() {
        String propertiesFile = "/tmp/MyPerf4J.properties";
        System.setProperty(PropertyKeys.PRO_FILE_NAME, propertiesFile);
        AutoRollingFileWriter writer = new MinutelyRollingFileWriter(propertiesFile);
        writer.write("AppName=MyPerf4JTest\n");
        writer.write("IncludePackages=MyPerf4J\n");
        writer.write("MilliTimeSlice=1000\n");
        writer.closeFile(true);
    }
}

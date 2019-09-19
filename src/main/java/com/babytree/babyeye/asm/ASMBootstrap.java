package com.babytree.babyeye.asm;

import com.babytree.babyeye.asm.aop.ProfilingAspect;
import com.babytree.babyeye.base.config.ProfilingConfig;
import com.babytree.babyeye.base.util.Logger;
import com.babytree.babyeye.core.AbstractBootstrap;
import com.babytree.babyeye.core.recorder.AbstractRecorderMaintainer;

public class ASMBootstrap extends AbstractBootstrap {

    private static final ASMBootstrap instance = new ASMBootstrap();

    private ASMBootstrap() {
        //empty
    }

    public static ASMBootstrap getInstance() {
        return instance;
    }

    @Override
    public AbstractRecorderMaintainer doInitRecorderMaintainer() {
        boolean accurateMode = ProfilingConfig.getInstance().isAccurateMode();
        int backupRecorderCount = ProfilingConfig.getInstance().getBackupRecorderCount();

        ASMRecorderMaintainer maintainer = ASMRecorderMaintainer.getInstance();
        if (maintainer.initial(processor, accurateMode, backupRecorderCount)) {
            return maintainer;
        }
        return null;
    }

    @Override
    public boolean initOther() {
        return initProfilerAspect();
    }

    private boolean initProfilerAspect() {
        try {
            ProfilingAspect.setRecorderMaintainer((ASMRecorderMaintainer) maintainer);
            ProfilingAspect.setRunning(true);
            return true;
        } catch (Exception e) {
            Logger.error("ASMBootstrap.initProfilerAspect()", e);
        }
        return false;
    }

}

package com.babytree.babyeye.base.metric;

import java.lang.management.ThreadInfo;
import java.lang.management.ThreadMXBean;

import static java.lang.Thread.State.*;
import static java.lang.Thread.State.TERMINATED;
import static java.lang.Thread.State.TIMED_WAITING;


public class JvmThreadMetrics extends Metrics {

    private static final long serialVersionUID = 8514109576224018139L;

    private long totalStarted;

    private int active;

    private int peak;

    private int daemon;

    private int news;

    private int runnable;

    private int blocked;

    private int waiting;

    private int timedWaiting;

    private int terminated;

    public JvmThreadMetrics(ThreadMXBean bean) {
        long threadIds[] = bean.getAllThreadIds();
        this.totalStarted = bean.getTotalStartedThreadCount();
        this.active = bean.getThreadCount();
        this.peak = bean.getPeakThreadCount();
        this.daemon = bean.getDaemonThreadCount();

        int threadsNew = 0;
        int threadsRunnable = 0;
        int threadsBlocked = 0;
        int threadsWaiting = 0;
        int threadsTimedWaiting = 0;
        int threadsTerminated = 0;

        ThreadInfo[] threadInfoArr = bean.getThreadInfo(threadIds, 0);
        for (int i = 0; i < threadInfoArr.length; ++i) {
            ThreadInfo threadInfo = threadInfoArr[i];
            if (threadInfo == null) {
                continue;
            }

            Thread.State state = threadInfo.getThreadState();
            if (state == NEW) {
                threadsNew++;
            } else if (state == RUNNABLE) {
                threadsRunnable++;
            } else if (state == BLOCKED) {
                threadsBlocked++;
            } else if (state == WAITING) {
                threadsWaiting++;
            } else if (state == TIMED_WAITING) {
                threadsTimedWaiting++;
            } else if (state == TERMINATED) {
                threadsTerminated++;
            }
        }

        this.news = threadsNew;
        this.runnable = threadsRunnable;
        this.blocked = threadsBlocked;
        this.waiting = threadsWaiting;
        this.timedWaiting = threadsTimedWaiting;
        this.terminated = threadsTerminated;
    }

    public long getTotalStarted() {
        return totalStarted;
    }

    public void setTotalStarted(long totalStarted) {
        this.totalStarted = totalStarted;
    }

    public int getActive() {
        return active;
    }

    public void setActive(int active) {
        this.active = active;
    }

    public int getPeak() {
        return peak;
    }

    public void setPeak(int peak) {
        this.peak = peak;
    }

    public int getDaemon() {
        return daemon;
    }

    public void setDaemon(int daemon) {
        this.daemon = daemon;
    }

    public int getNews() {
        return news;
    }

    public void setNews(int news) {
        this.news = news;
    }

    public int getRunnable() {
        return runnable;
    }

    public void setRunnable(int runnable) {
        this.runnable = runnable;
    }

    public int getBlocked() {
        return blocked;
    }

    public void setBlocked(int blocked) {
        this.blocked = blocked;
    }

    public int getWaiting() {
        return waiting;
    }

    public void setWaiting(int waiting) {
        this.waiting = waiting;
    }

    public int getTimedWaiting() {
        return timedWaiting;
    }

    public void setTimedWaiting(int timedWaiting) {
        this.timedWaiting = timedWaiting;
    }

    public int getTerminated() {
        return terminated;
    }

    public void setTerminated(int terminated) {
        this.terminated = terminated;
    }

    @Override
    public String toString() {
        return "JvmThreadMetrics{" +
                "totalStarted=" + totalStarted +
                ", active=" + active +
                ", peak=" + peak +
                ", daemon=" + daemon +
                ", news=" + news +
                ", runnable=" + runnable +
                ", blocked=" + blocked +
                ", waiting=" + waiting +
                ", timedWaiting=" + timedWaiting +
                ", terminated=" + terminated +
                "} " + super.toString();
    }

}

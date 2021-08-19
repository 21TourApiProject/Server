package com.server.tourApiProject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.Trigger;
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;

public abstract class DynamicScheduledConfig {
    private Logger logger = LoggerFactory.getLogger(getClass());
    private ThreadPoolTaskScheduler scheduler;

    public void stopScheduler(){
        if(scheduler != null){
            scheduler.shutdown();
        }
    }

    public void startScheduler(){
        scheduler = new ThreadPoolTaskScheduler();
        scheduler.initialize();
        scheduler.setPoolSize(4);
        scheduler.schedule(getRunnable(), getTrigger());
    }

    public Runnable getRunnable(){
        return new Runnable() {
            @Override
            public void run() {
                runner();
            }
        };
    }

    public abstract void runner();
    public abstract Trigger getTrigger();
}

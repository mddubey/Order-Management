package com.mritunjd.order_management.job;

import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.scheduling.quartz.QuartzJobBean;

import java.util.Date;

public class MyScheduledJob extends QuartzJobBean {
    @Override
    protected void executeInternal(JobExecutionContext context) throws JobExecutionException {
        String author = (String) context.getMergedJobDataMap().get("author");
        System.out.println(String.format("This job is created by :- %s current date is :- %s", author, new Date().toString()));
    }
}

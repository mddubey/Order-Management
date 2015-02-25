package com.mritunjd.order_management.controller;

import com.mritunjd.order_management.job.MyScheduledJob;
import org.quartz.CronTrigger;
import org.quartz.JobDetail;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.quartz.CronTriggerFactoryBean;
import org.springframework.scheduling.quartz.JobDetailFactoryBean;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.text.ParseException;

@Controller
@RequestMapping(value = "/scheduler")
public class SchedulerController {
    private Scheduler scheduler;
    private CronTrigger cronTrigger;

    @Autowired
    public SchedulerController(Scheduler scheduler) {
        this.scheduler = scheduler;
    }


    @RequestMapping(value = "/start", method = RequestMethod.GET)
    public void startScheduler(@RequestParam(value = "name", defaultValue = "anonymous") String name) throws SchedulerException {
        JobDetail jobDetail = myScheduledJob(name);
        this.cronTrigger = getTrigger(jobDetail, "my.scheduled.job.trigger");
        scheduler.scheduleJob(jobDetail, cronTrigger);
    }

    @RequestMapping(value = "/stop", method = RequestMethod.GET)
    public void stopScheduler() throws SchedulerException {
        scheduler.unscheduleJob(cronTrigger.getKey());
    }

    private CronTrigger getTrigger(JobDetail jobDetail, String triggerName) {
        CronTriggerFactoryBean triggerFactoryBean = new CronTriggerFactoryBean();
        triggerFactoryBean.setName(triggerName);
        triggerFactoryBean.setCronExpression("0/3 * * * * ?");
        triggerFactoryBean.setJobDetail(jobDetail);
        try {
            triggerFactoryBean.afterPropertiesSet();
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return triggerFactoryBean.getObject();
    }

    private JobDetail myScheduledJob(String name) {
        JobDetailFactoryBean jobDetailFactoryBean = new JobDetailFactoryBean();
        jobDetailFactoryBean.setJobClass(MyScheduledJob.class);
        jobDetailFactoryBean.setName("my.scheduled.job");
        jobDetailFactoryBean.getJobDataMap().put("author", name);
        jobDetailFactoryBean.afterPropertiesSet();
        return jobDetailFactoryBean.getObject();
    }
}

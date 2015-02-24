package com.mritunjd.order_management;

import com.mritunjd.order_management.job.MyScheduledJob;
import org.quartz.CronTrigger;
import org.quartz.JobDetail;
import org.quartz.Scheduler;
import org.quartz.spi.JobFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.context.embedded.EmbeddedServletContainerFactory;
import org.springframework.boot.context.embedded.ServletContextInitializer;
import org.springframework.boot.context.embedded.tomcat.TomcatEmbeddedServletContainerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.scheduling.quartz.CronTriggerFactoryBean;
import org.springframework.scheduling.quartz.JobDetailFactoryBean;
import org.springframework.scheduling.quartz.SchedulerFactoryBean;
import org.springframework.scheduling.quartz.SpringBeanJobFactory;
import org.springframework.web.servlet.DispatcherServlet;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRegistration;
import java.text.ParseException;


@Configuration
public class Application {

    @Bean
    public SchedulerFactoryBean scheduler() {
        SchedulerFactoryBean schedulerFactoryBean = new SchedulerFactoryBean();

        schedulerFactoryBean.setConfigLocation(new ClassPathResource("quartz.properties"));
        schedulerFactoryBean.setJobFactory(jobFactory());


        schedulerFactoryBean.setTriggers(getTrigger());
        schedulerFactoryBean.setApplicationContextSchedulerContextKey("applicationContext");
        schedulerFactoryBean.setWaitForJobsToCompleteOnShutdown(true);
        try {
            schedulerFactoryBean.afterPropertiesSet();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return schedulerFactoryBean;
    }

    @Bean
    public JobFactory jobFactory() {
        SpringBeanJobFactory springBeanJobFactory = new SpringBeanJobFactory();
        return springBeanJobFactory;
    }

    private CronTrigger getTrigger() {
        CronTriggerFactoryBean triggerFactoryBean = new CronTriggerFactoryBean();
        triggerFactoryBean.setName("my.scheduled.job.trigger");
        triggerFactoryBean.setCronExpression("0/30 * * * * ?");
        triggerFactoryBean.setJobDetail(myScheduledJob());
        try {
            triggerFactoryBean.afterPropertiesSet();
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return triggerFactoryBean.getObject();
    }

    private JobDetail myScheduledJob() {
        JobDetailFactoryBean jobDetailFactoryBean = new JobDetailFactoryBean();
        jobDetailFactoryBean.setJobClass(MyScheduledJob.class);
        jobDetailFactoryBean.setName("my.scheduled.job");
        jobDetailFactoryBean.afterPropertiesSet();
        return jobDetailFactoryBean.getObject();
    }

    @Bean
    public EmbeddedServletContainerFactory getFactory() {
        TomcatEmbeddedServletContainerFactory factory = new TomcatEmbeddedServletContainerFactory();
        factory.addInitializers(new ServletContextInitializer() {
            @Override
            public void onStartup(ServletContext servletContext) throws ServletException {
                ServletRegistration.Dynamic om = servletContext.addServlet("OM", DispatcherServlet.class);
                om.addMapping("/");
                om.setInitParameter("contextClass", "org.springframework.web.context.support.AnnotationConfigWebApplicationContext");
                om.setInitParameter("contextConfigLocation", "com.mritunjd.order_management.config.WebMvcConfig");
            }
        });
        factory.setPort(8181);
        return factory;
    }

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}
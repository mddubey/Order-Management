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
import org.springframework.context.ApplicationContext;
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
    public SchedulerFactoryBean schedulerFactoryBean() {
        final SchedulerFactoryBean bean = new SchedulerFactoryBean();
        bean.setApplicationContextSchedulerContextKey(ApplicationContext.class.getName());
        return bean;
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
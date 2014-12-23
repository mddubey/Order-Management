package com.mritunjd.order_management;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.context.embedded.EmbeddedServletContainerFactory;
import org.springframework.boot.context.embedded.ServletContextInitializer;
import org.springframework.boot.context.embedded.tomcat.TomcatEmbeddedServletContainerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.DispatcherServlet;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRegistration;


@Configuration
public class Application {

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
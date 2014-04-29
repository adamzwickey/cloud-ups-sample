package com.pivotal.cloudfoundry.example;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ImportResource;

@EnableAutoConfiguration
@ComponentScan(basePackages="com.pivotal.cloudfoundry.example")
@ImportResource("classpath:spring-context.xml")
public class CloudFoundryApp {
	
	private static Logger LOG = LoggerFactory.getLogger(CloudFoundryApp.class);
	
	 public static void main(String[] args) {
		 SpringApplication.run(CloudFoundryApp.class, args);
	 }
	
	@Bean
	protected ServletContextListener listener() {
		return new ServletContextListener() {			
			public void contextInitialized(ServletContextEvent sce) {
				LOG.info("ServletContext initialized");
			}			
			public void contextDestroyed(ServletContextEvent sce) {
				LOG.info("ServletContext destroyed");
			}
		};
	}

}

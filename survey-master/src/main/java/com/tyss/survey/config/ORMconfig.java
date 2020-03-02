package com.tyss.survey.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.orm.jpa.LocalEntityManagerFactoryBean;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class ORMconfig implements WebMvcConfigurer{

		
//	@Override
//	public void addResourceHandlers(ResourceHandlerRegistry registry) {
//		registry.addResourceHandler("/uploads/**").addResourceLocations("file:uploads");
//	}
	@Bean
	public LocalEntityManagerFactoryBean createEntityManagerFactoryBean() {
		LocalEntityManagerFactoryBean bean = new LocalEntityManagerFactoryBean();
		bean.setPersistenceUnitName("survey");
		return bean;
		
		
	}
	
	
//	@Bean
//	public CommonsMultipartResolver commonsMultipartResolver()
//	{
//		CommonsMultipartResolver commonsMultipartResolver=new CommonsMultipartResolver();
//		commonsMultipartResolver.setMaxUploadSize(-1);
//		return commonsMultipartResolver;
//	}
//	
}

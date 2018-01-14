package com.takefour.themoment.themoment.config;

import java.util.List;

import org.springframework.boot.autoconfigure.web.ResourceProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import com.takefour.themoment.themoment.config.method.support.UserHandlerMethodArgumentResolver;

@Configuration
public class WebMvcConfig extends WebMvcConfigurerAdapter {

	private ResourceProperties resourceProperties = new ResourceProperties();
	private static final String[] STATIC_RESOURCES = {
			"/**/*.js",
			"/**/*.css",
			"/**/*.html",
			"/**/*.png",
			"/**/*.jpg",
			"/**/*.jpeg",
			"/**/*.gif",
			"/**/*.ico",
			"/**/*.eot",
			"/**/*.woff",
			"/**/*.map"};

	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		Integer cachePeriod = this.resourceProperties.getCachePeriod();
		registry.addResourceHandler(STATIC_RESOURCES)
				.addResourceLocations(this.resourceProperties.getStaticLocations())
				.setCachePeriod(cachePeriod);
		registry.addResourceHandler("swagger-ui.html")
				.addResourceLocations("classpath:/META-INF/resources/");
	}

	@Override
	public void addArgumentResolvers(List<HandlerMethodArgumentResolver> argumentResolvers) {
		argumentResolvers.add(userHandlerMethodArgumentResolver());
		super.addArgumentResolvers(argumentResolvers);
	}

	@Bean
	public UserHandlerMethodArgumentResolver userHandlerMethodArgumentResolver() {
		return new UserHandlerMethodArgumentResolver();
	}
}

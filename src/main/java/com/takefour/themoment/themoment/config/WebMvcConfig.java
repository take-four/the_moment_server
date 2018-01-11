package com.takefour.themoment.themoment.config;

import org.springframework.boot.autoconfigure.web.ResourceProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import com.takefour.themoment.themoment.security.FirebaseAuthInterceptor;

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

	@Bean
	public FirebaseAuthInterceptor firebaseAuthInterceptor() {
		return new FirebaseAuthInterceptor();
	}

	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		registry.addInterceptor(firebaseAuthInterceptor())
				.addPathPatterns("/**");
	}
}

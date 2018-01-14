package com.takefour.themoment.themoment.config;

import java.util.Collections;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.authentication.preauth.AbstractPreAuthenticatedProcessingFilter;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import com.takefour.themoment.themoment.security.FirebaseAuthenticationProvider;
import com.takefour.themoment.themoment.security.FirebaseFilter;
import com.takefour.themoment.themoment.security.FirebaseUserDetailsService;

/**
 * Created by hanbyeol on 2018. 1. 11..
 */
@Configuration
@EnableWebSecurity(debug = true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	private FirebaseAuthenticationProvider firebaseAuthenticationProvider;

	@Autowired
	private FirebaseUserDetailsService userService;

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(userService);
		auth.authenticationProvider(firebaseAuthenticationProvider);
	}

	@Override
	public void configure(WebSecurity web) throws Exception {
		web.ignoring()
				.antMatchers(
						"/**/*.jpg",
						"/**/*.jpeg",
						"/**/*.png",
						"/**/*.js",
						"/**/*.css",
						"/**/*.gif",
						"/**/*.ttf",
						"/**/*.wof",
						"/**/*.woff",
						"/**/*.woff2",
						"/v2/api-docs",
						"/configuration/ui",
						"/swagger-resources/**",
						"/configuration/security",
						"/swagger-ui.html",
						"/webjars/**",
						"/v2/swagger.json");
	}

	@Override
	protected AuthenticationManager authenticationManager() throws Exception {
		return new ProviderManager(Collections.singletonList(firebaseAuthenticationProvider));
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.csrf().disable();
		http.authorizeRequests()
				.antMatchers("/").permitAll()
				.anyRequest()
				.fullyAuthenticated()
				.and()
				.headers().disable()
				.csrf().disable();
		// enable pre auth filter
		http.addFilterAt(tokenAuthorizationFilter(), AbstractPreAuthenticatedProcessingFilter.class);
//		// TODO: 2018. 1. 14. Have to refactoring
//		http.addFilterBefore(tokenAuthorizationFilter(), BasicAuthenticationFilter.class).authorizeRequests()//
//				.antMatchers("/**").hasAnyRole("ROLE_ANONYMOUS")
//				.and().csrf().disable()
//				.anonymous().authorities("ROLE_ANONYMOUS");
	}

	private FirebaseFilter tokenAuthorizationFilter() {
		return new FirebaseFilter();
	}

}

package com.takefour.themoment.config;

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
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import com.takefour.themoment.security.FirebaseAuthenticationProvider;
import com.takefour.themoment.security.FirebaseFilter;
import com.takefour.themoment.security.FirebaseUserDetailsService;

/**
 * Created by hanbyeol on 2018. 1. 11..
 */
@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	private FirebaseAuthenticationProvider firebaseAuthenticationProvider;

	@Autowired
	private FirebaseUserDetailsService firebaseUserDetailsService;

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(firebaseUserDetailsService);
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
						"/v2/swagger.json",
						"/favicon.ico");
	}

	@Override
	protected AuthenticationManager authenticationManager() throws Exception {
		return new ProviderManager(Collections.singletonList(firebaseAuthenticationProvider));
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.csrf().disable();

		http.addFilterBefore(tokenAuthorizationFilter(), BasicAuthenticationFilter.class)
				.authorizeRequests()
				.antMatchers("/swagger-ui.html", "/").permitAll()
				.anyRequest()
//				.fullyAuthenticated()
//				.antMatchers(HttpMethod.GET, "/apis/**")
				.hasAnyRole("USER", "ADMIN")
				.and()
				.headers().disable();

		// enable pre auth filter
//		http.addFilterAt(tokenAuthorizationFilter(), AbstractPreAuthenticatedProcessingFilter.class);
	}

	private FirebaseFilter tokenAuthorizationFilter() {
		return new FirebaseFilter();
	}

}

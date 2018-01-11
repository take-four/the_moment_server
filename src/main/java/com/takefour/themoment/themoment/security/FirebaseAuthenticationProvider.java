package com.takefour.themoment.themoment.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationCredentialsNotFoundException;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

/**
 * Created by hanbyeol on 2018. 1. 11..
 */
@Component
public class FirebaseAuthenticationProvider implements AuthenticationProvider {

	@Autowired
	private FirebaseUserDetailsService firebaseUserDetailsService;

	@Override
	public Authentication authenticate(Authentication authentication) throws AuthenticationException {
		if (!supports(authentication.getClass())) {
			return null;
		}

		FirebaseAuthenticationToken authenticationToken = (FirebaseAuthenticationToken) authentication;
		UserDetails details = firebaseUserDetailsService.loadUserByUsername(authenticationToken.getName());
		if (details == null) {
			throw new AuthenticationCredentialsNotFoundException("User Not Found");
		}

		authenticationToken = new FirebaseAuthenticationToken(details, authentication.getCredentials(),
				details.getAuthorities());

		return authenticationToken;
	}

	@Override
	public boolean supports(Class<?> aClass) {
		return (FirebaseAuthenticationToken.class.isAssignableFrom(aClass));
	}
}

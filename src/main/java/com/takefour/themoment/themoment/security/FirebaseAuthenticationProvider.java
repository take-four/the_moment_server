package com.takefour.themoment.themoment.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationCredentialsNotFoundException;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import com.google.firebase.auth.FirebaseToken;

/**
 * Created by hanbyeol on 2018. 1. 11..
 */
@Component
public class FirebaseAuthenticationProvider implements AuthenticationProvider {

	@Autowired
	private FirebaseUserDetailsService firebaseUserDetailsService;

	// TODO: 2018. 1. 14. 나중에 더 좋은 코드로 리팩토링 필요함!! 
	@Override
	public Authentication authenticate(Authentication authentication) throws AuthenticationException {
		if (!supports(authentication.getClass())) {
			return null;
		}

		FirebaseAuthenticationToken authenticationToken = (FirebaseAuthenticationToken) authentication;
		UserDetails details = firebaseUserDetailsService.loadUserByUsername(authenticationToken.getName());
		if (details == null) {
			// 회원가입
			FirebaseToken token = (FirebaseToken)authenticationToken.getCredentials();
			details = firebaseUserDetailsService.save(token.getEmail(), token.getName());
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

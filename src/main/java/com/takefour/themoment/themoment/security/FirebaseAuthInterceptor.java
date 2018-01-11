package com.takefour.themoment.themoment.security;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseToken;
import com.google.firebase.tasks.Task;
import com.google.firebase.tasks.Tasks;

/**
 * Created by hanbyeol on 2018. 1. 10..
 */
public class FirebaseAuthInterceptor extends HandlerInterceptorAdapter {

	private static String AUTH_HEADER_NAME = "Authorization";

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
		String idToken = request.getHeader(AUTH_HEADER_NAME);

		if(!idToken.isEmpty()) {
			// BadCredentialsException
			Task<FirebaseToken> task = FirebaseAuth.getInstance().verifyIdToken(idToken);
			Tasks.await(task);
			FirebaseToken token = task.getResult();

			Authentication auth = new FirebaseAuthenticationToken(token.getEmail(), token);
			SecurityContextHolder.getContext().setAuthentication(auth);
		}

		return true;
	}
}

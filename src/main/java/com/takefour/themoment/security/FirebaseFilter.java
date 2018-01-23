package com.takefour.themoment.security;

import java.io.IOException;
import java.util.concurrent.ExecutionException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseToken;
import com.google.firebase.tasks.Task;
import com.google.firebase.tasks.Tasks;
import com.takefour.themoment.exception.TokenInvalidException;

public class FirebaseFilter extends OncePerRequestFilter {

	private static String AUTH_HEADER_NAME = "Authorization";

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {

		String idToken = request.getHeader(AUTH_HEADER_NAME);

		if(idToken != null) {
			// BadCredentialsException
			Task<FirebaseToken> task = FirebaseAuth.getInstance().verifyIdToken(idToken);
			try {
				Tasks.await(task);
			} catch (ExecutionException | InterruptedException e) {
				e.printStackTrace();
				throw new TokenInvalidException("Firebase ID token has expired or is not yet valid");
			}

			FirebaseToken token = task.getResult();

			Authentication auth = new FirebaseAuthenticationToken(token.getUid(), token);
			SecurityContextHolder.getContext().setAuthentication(auth);
			filterChain.doFilter(request, response);
		} else {
			filterChain.doFilter(request, response);
		}
	}

}

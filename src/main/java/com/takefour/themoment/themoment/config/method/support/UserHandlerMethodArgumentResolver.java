package com.takefour.themoment.themoment.config.method.support;

import org.springframework.core.MethodParameter;
import org.springframework.security.authentication.AuthenticationCredentialsNotFoundException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

import com.takefour.themoment.themoment.config.method.annotation.CurrentUser;


public class UserHandlerMethodArgumentResolver implements HandlerMethodArgumentResolver {

	@Override
	public boolean supportsParameter(MethodParameter parameter) {
		return parameter.getParameterAnnotation(CurrentUser.class) != null;
	}

	@Override
	public Object resolveArgument(MethodParameter parameter, ModelAndViewContainer mavContainer,
								  NativeWebRequest webRequest, WebDataBinderFactory binderFactory) throws Exception {
		return getCurrentUser();
	}

	private User getCurrentUser() {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		if (auth == null) {
			throw new AuthenticationCredentialsNotFoundException("No authentication");
		}
		Object obj = auth.getPrincipal();
		if ("anonymousUser".equals(obj)) {
			return null;
		}
		if (!(obj instanceof User)) {
			throw new AuthenticationCredentialsNotFoundException("Invalid authentication with " + obj);
		}
		return (User) obj;
	}

}

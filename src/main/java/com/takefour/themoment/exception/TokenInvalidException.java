package com.takefour.themoment.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.UNAUTHORIZED)
public class TokenInvalidException  extends RuntimeException {
	public TokenInvalidException(String msg) {
		super(msg);
	}

	public TokenInvalidException(String msg, Throwable t) {
		super(msg, t);
	}
}
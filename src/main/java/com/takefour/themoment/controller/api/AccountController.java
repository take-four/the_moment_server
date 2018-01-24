package com.takefour.themoment.controller.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.takefour.themoment.config.method.annotation.CurrentUser;
import com.takefour.themoment.model.Account;
import com.takefour.themoment.service.AccountService;

/**
 * Created by hanbyeol on 2018. 1. 3..
 */
@RestController
@RequestMapping("/apis/users")
public class AccountController {

	@Autowired
	private AccountService accountService;

	@PostMapping
	private Account signUp(@CurrentUser User user) {
		return accountService.findById(user.getUsername());
	}

}

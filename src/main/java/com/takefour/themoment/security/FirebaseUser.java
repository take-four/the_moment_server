package com.takefour.themoment.security;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;

import com.takefour.themoment.model.Account;

/**
 * Created by hanbyeol on 2018. 1. 22..
 */
public class FirebaseUser extends User {

	public FirebaseUser(Account account) {
		super(account.getId(), "", authorities(account));
	}

	private static Collection<? extends GrantedAuthority> authorities(Account account) {
		List<GrantedAuthority> authorities = new ArrayList<>();
		authorities.add(new SimpleGrantedAuthority("ROLE_".concat(Optional.ofNullable(account.getRole()).orElse("USER"))));
		return authorities;
	}
}

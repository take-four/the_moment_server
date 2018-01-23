package com.takefour.themoment.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.takefour.themoment.model.Account;
import com.takefour.themoment.repository.AccountRepository;

/**
 * Created by hanbyeol on 2018. 1. 10..
 */
@Service
public class FirebaseUserDetailsService implements UserDetailsService {

	@Autowired
	private AccountRepository accountRepository;

	@Override
	public UserDetails loadUserByUsername(String uid) throws UsernameNotFoundException {
		Account account = accountRepository.findOne(uid);
		if (account == null) {
//			throw new UsernameNotFoundException(uid);
			return null;
		}
		return new FirebaseUser(account);
	}

	public UserDetails save(String id, String email, String name) {
		Account user = new Account();
		user.setId(id);
		user.setEmail(email);
		user.setName(name);
		return new FirebaseUser(accountRepository.save(user));
	}
}

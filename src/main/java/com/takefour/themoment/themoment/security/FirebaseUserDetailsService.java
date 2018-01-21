package com.takefour.themoment.themoment.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.takefour.themoment.themoment.model.User;
import com.takefour.themoment.themoment.repository.UserRepository;

/**
 * Created by hanbyeol on 2018. 1. 10..
 */
@Service
public class FirebaseUserDetailsService implements UserDetailsService {

	@Autowired
	private UserRepository userRepository;

	@Override
	public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
//		User user = userRepository.findByEmail(userName);
//		if (user == null) {
//			return null;
//		}
//		return user;
		return null;
	}

	public UserDetails save(String email, String name) {
//		User user = new User();
//		user.setEmail(email);
//		user.setName(name);
//		return userRepository.save(user);
		return null;
	}
}

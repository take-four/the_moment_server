package com.takefour.themoment.themoment.repository;

import com.takefour.themoment.themoment.model.User;
import org.springframework.data.jpa.repository.JpaRepository;


public interface UserRepository extends JpaRepository<User, Integer> {
	User findByEmail(String email);

	User findByEmailAndPassword(String email, String password);

	User update(Integer id);
}

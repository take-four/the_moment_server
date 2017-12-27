package com.takefour.themoment.themoment.repository;

import com.takefour.themoment.themoment.model.User;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User,Integer> {
}

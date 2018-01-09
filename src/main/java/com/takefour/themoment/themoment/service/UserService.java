package com.takefour.themoment.themoment.service;

import com.takefour.themoment.themoment.model.User;
import com.takefour.themoment.themoment.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class UserService {
	@Autowired
	private UserRepository userRepository;

	public User save(User user) {
		return userRepository.save(user);
	}

	//findOne이 pk를 인자로 받는거
	public User findById(Integer id) {
		return userRepository.findOne(id);
	}

	public User findByEmail(String email) {
		return userRepository.findByEmail(email);
	}

	public User findByEmailAndPassword(String email, String password) {
		return userRepository.findByEmailAndPassword(email, password);
	}

	public List<User> findAll() {
		return userRepository.findAll();
	}

	//jpa에 트렌잭션 단위가있다
	//해당 아이디로 조회해와서 파라미터의 유저객체와 기존 디비의 값을 비교해서 바꾼다
	public User update(Integer id, User user){
		User updated = userRepository.findOne(id);
		updated.setUserName(user.getUserName());
		updated.setPassword(user.getPassword());
		return updated;
	}

	public void delete(Integer id) {
		userRepository.delete(id);
	}
}

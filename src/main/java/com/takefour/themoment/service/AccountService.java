package com.takefour.themoment.service;

import com.takefour.themoment.model.Account;
import com.takefour.themoment.repository.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class AccountService {
	@Autowired
	private AccountRepository accountRepository;

	public Account save(Account account) {
		return accountRepository.save(account);
	}

	//findOne이 pk를 인자로 받는거
	public Account findById(String id) {
		return accountRepository.findOne(id);
	}

	public List<Account> findAll() {
		return accountRepository.findAll();
	}

	//jpa에 트렌잭션 단위가있다
	//해당 아이디로 조회해와서 파라미터의 유저객체와 기존 디비의 값을 비교해서 바꾼다
	public Account update(String id, Account account){
		Account updated = accountRepository.findOne(id);
		updated.setName(account.getName());
		return updated;
	}

	public void delete(String id) {
		accountRepository.delete(id);
	}
}

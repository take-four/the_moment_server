package com.takefour.themoment.service;

import com.takefour.themoment.model.Heart;
import com.takefour.themoment.repository.HeartRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class HeartService {
	@Autowired
	private HeartRepository heartRepository;

//	POST: /apis/moments/{momentId}/heart

	public Heart save(String accountId, Integer momentId) {
		Heart heart = new Heart();
		heart.setAccountId(accountId);
		heart.setMomentId(momentId);
		return heartRepository.save(heart);
	}
//	DELETE: /apis/moments/{momentId}/heart

	public void deleteByAccountIdAndMomentId(String accountId, Integer momentId) {
		heartRepository.deleteByAccountIdAndMomentId(accountId, momentId);
	}
}

package com.takefour.themoment.themoment.service;

import com.takefour.themoment.themoment.model.Heart;
import com.takefour.themoment.themoment.repository.HeartRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class HeartService {
	@Autowired
	private HeartRepository heartRepository;

//	POST: /apis/moments/{momentId}/heart

	public Heart save(Integer userId, Integer momentId) {
		Heart heart = new Heart();
		heart.setUserId(userId);
		heart.setMomentId(momentId);
		return heartRepository.save(heart);
	}
//	DELETE: /apis/moments/{momentId}/heart

	public void deleteByUserIdAndMomentId(Integer userId, Integer momentId) {
		heartRepository.deleteByUserIdAndMomentId(userId,momentId);
	}
}

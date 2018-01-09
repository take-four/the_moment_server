package com.takefour.themoment.themoment.service;

import com.takefour.themoment.themoment.model.Moment;
import com.takefour.themoment.themoment.repository.MomentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MomentService {
	@Autowired
	private MomentRepository momentRepository;

//	GET: /apis/moments?userId=xxx
//	userId로 moment 조회
	public List<Moment> findByUserId(Integer userId){
		return momentRepository.findByUserId(userId);
	}

//	POST: /apis/moments (@requestbody Moment moment)
//	moment 저장
	public Moment save(Moment moment) {
		return momentRepository.save(moment);
	}

//	DELETE: /apis/moments/{momentId}
//	id에 해당하는 moment 삭제
	public void delete(Integer id) {
		momentRepository.delete(id);
	}
}

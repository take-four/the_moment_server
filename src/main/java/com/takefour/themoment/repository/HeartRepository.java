package com.takefour.themoment.repository;

import com.takefour.themoment.model.Heart;
import org.springframework.data.jpa.repository.JpaRepository;

public interface HeartRepository extends JpaRepository<Heart, Integer> {
	Heart deleteByAccountIdAndMomentId(String accountId, Integer momentId);

	Heart findByAccountIdAndMomentId(String accountId, Integer momentId);
}

package com.takefour.themoment.repository;

import com.takefour.themoment.model.Moment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MomentRepository extends JpaRepository<Moment, Integer> {
	List<Moment> findByAccountId(String accountId);

	List<Moment> findByPlaceIdOrderByCreateDateDesc(Integer placeId);
}

//<modelname, primary key type>

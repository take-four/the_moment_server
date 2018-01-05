package com.takefour.themoment.themoment.repository;

import com.takefour.themoment.themoment.model.Moment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MomentRepository extends JpaRepository<Moment, Integer> {
	List<Moment> findByUserId(Integer userId);
	//여러개라서
}

//<modelname, primary key type>

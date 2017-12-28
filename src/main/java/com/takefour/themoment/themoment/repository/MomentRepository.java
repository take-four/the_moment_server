package com.takefour.themoment.themoment.repository;

import com.takefour.themoment.themoment.model.Moment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MomentRepository extends JpaRepository<Moment,Integer> {
}

//<modelname, primary key type>

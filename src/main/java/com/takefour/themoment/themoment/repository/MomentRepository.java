package com.takefour.themoment.themoment.repository;

import com.takefour.themoment.themoment.model.Moment;
import org.springframework.data.repository.CrudRepository;

public interface MomentRepository extends CrudRepository<Moment,Integer> {
}

//<modelname, primary key type>

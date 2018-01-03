package com.takefour.themoment.themoment.repository;

import com.takefour.themoment.themoment.model.Heart;
import org.springframework.data.jpa.repository.JpaRepository;

public interface HeartRepository extends JpaRepository<Heart, Integer> {
}

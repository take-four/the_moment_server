package com.takefour.themoment.repository;

import com.takefour.themoment.model.City;

import org.springframework.data.jpa.repository.JpaRepository;

public interface CityRepository extends JpaRepository<City, Integer> {
	City findByName(String name);
}

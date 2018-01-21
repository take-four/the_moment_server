package com.takefour.themoment.themoment.repository;

import com.takefour.themoment.themoment.model.Place;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PlaceRepository extends JpaRepository<Place, Integer> {
	Place findByNameAndCityId(String name, Integer cityId);
}

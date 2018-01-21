package com.takefour.themoment.themoment.service;

import com.takefour.themoment.themoment.model.Place;
import com.takefour.themoment.themoment.repository.PlaceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PlaceService {
	@Autowired
	private PlaceRepository placeRepository;

	public Place save(Place moment) {
		return placeRepository.save(moment);
	}

	public Place findByNameAndCityId(String name, Integer cityId) {
		return placeRepository.findByNameAndCityId(name, cityId);
	}
}

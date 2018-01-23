package com.takefour.themoment.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.takefour.themoment.model.Place;
import com.takefour.themoment.repository.PlaceRepository;


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

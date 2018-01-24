package com.takefour.themoment.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.takefour.themoment.model.City;
import com.takefour.themoment.repository.CityRepository;

@Service
public class CityService {
	@Autowired
	private CityRepository cityRepository;

	public City save(City moment) {
		return cityRepository.save(moment);
	}

	public City findByName(String name) {
		return cityRepository.findByName(name);
	}
}

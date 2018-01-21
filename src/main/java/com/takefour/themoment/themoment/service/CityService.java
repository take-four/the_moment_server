package com.takefour.themoment.themoment.service;

import com.takefour.themoment.themoment.model.City;
import com.takefour.themoment.themoment.repository.CityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

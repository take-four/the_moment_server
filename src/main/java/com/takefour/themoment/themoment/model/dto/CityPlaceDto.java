package com.takefour.themoment.themoment.model.dto;

import lombok.Data;

import java.util.List;

@Data
public class CityPlaceDto {
	private String city;
	private List<String> places;

	public CityPlaceDto(String city, List<String> places) {
		this.city = city;
		this.places = places;
	}
}

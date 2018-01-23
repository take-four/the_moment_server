package com.takefour.themoment.model.dto;

import java.util.List;

import lombok.Data;

@Data
public class CityPlaceDto {
	private String city;
	private List<String> places;

	public CityPlaceDto(String city, List<String> places) {
		this.city = city;
		this.places = places;
	}
}

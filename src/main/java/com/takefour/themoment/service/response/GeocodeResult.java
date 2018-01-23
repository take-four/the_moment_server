package com.takefour.themoment.service.response;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Getter;

@Getter
public class GeocodeResult {
	@JsonProperty(value = "formatted_address")
	private String formattedAddress;
}

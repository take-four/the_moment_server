package com.takefour.themoment.service.response;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class GoogleGeocodeResponse {

	@JsonProperty("results")
	private List<GeocodeResult> results;

	@JsonProperty("status")
	private String status;
}

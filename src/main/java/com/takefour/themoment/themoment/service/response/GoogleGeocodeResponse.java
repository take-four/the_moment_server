package com.takefour.themoment.themoment.service.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.ToString;

import java.util.List;

@Getter
@ToString
public class GoogleGeocodeResponse {

	@JsonProperty("results")
	private List<GeocodeResult> results;

	@JsonProperty("status")
	private String status;
}

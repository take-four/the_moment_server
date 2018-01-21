package com.takefour.themoment.themoment.service.response;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Getter;
import lombok.ToString;

/**
 * Created by hanbyeol on 2018. 1. 12..
 */
@Getter
@ToString
public class GooglePlaceResponse {

	@JsonProperty("html_attributions")
	private List<String> htmlAttributions;

	@JsonProperty("results")
	private List<PlaceResult> results;

	@JsonProperty("status")
	private String status;
}

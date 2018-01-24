package com.takefour.themoment.service.request;

import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

/**
 * Created by hanbyeol on 2018. 1. 12..
 */
public class GooglePlaceQueryParameter {

	public static MultiValueMap<String, String> nearBySearch(String location, String keyword, String rankby, String key) {
		MultiValueMap<String, String> parameters = new LinkedMultiValueMap<>();
		parameters.add("location", location);
		parameters.add("keyword", keyword);
		parameters.add("rankby", rankby);
		parameters.add("key", key);
		return parameters;
	}

	public static MultiValueMap<String, String> geocode(String latlng, String result_type, String language, String key) {
		MultiValueMap<String, String> parameters = new LinkedMultiValueMap<>();
		parameters.add("latlng", latlng);
		parameters.add("result_type", result_type);
		parameters.add("language", language);
		parameters.add("key", key);
		return parameters;
	}

	}

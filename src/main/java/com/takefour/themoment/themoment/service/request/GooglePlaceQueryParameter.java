package com.takefour.themoment.themoment.service.request;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

/**
 * Created by hanbyeol on 2018. 1. 12..
 */
@Component
@PropertySource("classpath:config.properties")
public class GooglePlaceQueryParameter {

	@Value("${google-api-key}")
	private String GOOGLE_API_KEY;

	public MultiValueMap<String, String> nearBySearch(String location, String keyword, String rankby) {
		MultiValueMap<String, String> parameters = new LinkedMultiValueMap<>();
		parameters.add("location", location);
		parameters.add("keyword", keyword);
		parameters.add("rankby", rankby);
		parameters.add("key", GOOGLE_API_KEY);
		return parameters;
	}
}

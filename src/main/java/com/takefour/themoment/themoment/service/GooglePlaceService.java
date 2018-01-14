package com.takefour.themoment.themoment.service;

import java.net.URI;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import com.takefour.themoment.themoment.service.request.GooglePlaceQueryParameter;
import com.takefour.themoment.themoment.service.response.GooglePlaceResponse;

/**
 * Created by hanbyeol on 2018. 1. 14..
 */
@Service
public class GooglePlaceService {

	@Autowired
	private RestTemplate restTemplate;

	@Autowired
	private GooglePlaceQueryParameter googlePlaceQueryParameter;

	private static final String GOOGLE_BASE_URL = "https://maps.googleapis.com/maps/api/place/nearbysearch/json";

	public GooglePlaceResponse requestNearBySearch(String location, String keyword, String rankby) {
		URI url = generateUrl(googlePlaceQueryParameter.nearBySearch(location, keyword, rankby));
		ResponseEntity<GooglePlaceResponse> responseEntity = restTemplate.getForEntity(url, GooglePlaceResponse.class);
		return responseEntity.getBody();
	}

	private URI generateUrl(MultiValueMap<String, String> params) {
		return UriComponentsBuilder.fromHttpUrl(GOOGLE_BASE_URL)
				.queryParams(params)
				.build()
				.encode()
				.toUri();
	}
}

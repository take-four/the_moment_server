package com.takefour.themoment.themoment.service;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.takefour.themoment.themoment.service.response.GoogleGeocodeResponse;
import com.takefour.themoment.themoment.service.response.PlaceResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
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
@PropertySource("classpath:config.properties")
public class GooglePlaceService {

	@Autowired
	private RestTemplate restTemplate;

	@Value("${google-api-key}")
	private String GOOGLE_API_KEY;

	private static final String NEAR_BY_SEARCH_URL = "https://maps.googleapis.com/maps/api/place/nearbysearch/json";
	private static final String GEOCODE_URL = "https://maps.googleapis.com/maps/api/geocode/json";

	/**
	 * request 결과를 최대 5개 돌려줌
	 * @param location "{latitude},{logitude}"
	 * @param keyword {city} point of interest
	 * @param rankby distance
	 * @return 구글 place api를 통해 얻은 place name list
	 */
	public List<String> requestNearBySearch(String location, String keyword, String rankby) {
		URI url = generateUrl(NEAR_BY_SEARCH_URL, GooglePlaceQueryParameter.nearBySearch(location, keyword, rankby, GOOGLE_API_KEY));
		ResponseEntity<GooglePlaceResponse> responseEntity = restTemplate.getForEntity(url, GooglePlaceResponse.class);
		GooglePlaceResponse response = restTemplate.getForEntity(url,GooglePlaceResponse.class).getBody();


		List<String> results = new ArrayList<>();
		for(PlaceResult placeResult : response.getResults()){
			results.add(placeResult.getName());
		}
//		response.getResults().subList()
		response.getResults().stream().map(PlaceResult::getName).collect(Collectors.toList());
		response.getResults().stream().findFirst().ifPresent(
				r->r.getName()
		);
		response.getResults();
//		return response.getResults().get(0).getName();
		return results;
	}

	public String requestGeocode(String location, String result_type, String language) {
		URI url = generateUrl(GEOCODE_URL, GooglePlaceQueryParameter.geocode(location, result_type, language, GOOGLE_API_KEY));
		GoogleGeocodeResponse response = restTemplate.getForEntity(url, GoogleGeocodeResponse.class).getBody();

		return response.getResults().get(0).getFormattedAddress();
	}

	private URI generateUrl(String baseUrl, MultiValueMap<String, String> params) {
		return UriComponentsBuilder.fromHttpUrl(baseUrl)
				.queryParams(params)
				.build()
				.encode()
				.toUri();
	}
}

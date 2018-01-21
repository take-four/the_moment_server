package com.takefour.themoment.themoment.controller.api;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.takefour.themoment.themoment.model.City;
import com.takefour.themoment.themoment.model.Place;

import com.takefour.themoment.themoment.model.dto.CityPlaceDto;
import com.takefour.themoment.themoment.repository.PlaceRepository;
import com.takefour.themoment.themoment.service.CityService;
import com.takefour.themoment.themoment.service.MomentService;
import com.takefour.themoment.themoment.service.PlaceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.takefour.themoment.themoment.config.method.annotation.CurrentUser;
import com.takefour.themoment.themoment.model.Moment;
import com.takefour.themoment.themoment.model.User;
import com.takefour.themoment.themoment.service.GooglePlaceService;
import com.takefour.themoment.themoment.service.response.GooglePlaceResponse;

@RestController
@RequestMapping("/apis/moments")
public class MomentController {

	@Autowired
	private MomentService momentService;

	@Autowired
	private CityService cityService;

	@Autowired
	private GooglePlaceService googlePlaceService;

	@Autowired
	private PlaceService placeService;
	// TODO: 2018. 1. 5. mock 데이터 내려주기 

	@GetMapping("/places")
	public CityPlaceDto getNearbyPlaces(
	                                   @RequestParam Double latitude,
	                                   @RequestParam Double longitude,
	                                   @CurrentUser User user) {
		String location = latitude + "," + longitude;
		String city = googlePlaceService.requestGeocode(location, "locality", "en");
		List<String> places = googlePlaceService.requestNearBySearch(location, city.split(",")[0] + " point of interest", "distance");
		return new CityPlaceDto(city, places);
	}


	@PostMapping
	public Moment saveMoment(@RequestBody Moment moment,
	                         @RequestParam String cityName,
	                         @RequestParam String placeName,
	                         @CurrentUser User user) {
		return momentService.save(moment, cityName, placeName, user);
	}

	@GetMapping
	public List<Moment> getAllMoments(@RequestParam(required = false) Integer userId,
									  @RequestParam(required = false) Double latitude,
									  @RequestParam(required = false) Double longitude) {
		List<Moment> moments = new ArrayList<>();
		return moments;
	}

	@GetMapping("/{id}")
	public Moment getMoment(@PathVariable Integer id) {
		return momentService.findById(id);
	}

	@DeleteMapping("/{id}")
	public void deleteMoment(@PathVariable Integer id) {
		momentService.delete(id);
	}

	@PatchMapping("/{id}")
	public Moment updateMoment(@RequestBody Moment moment) {
		return moment;
	}

//	@GetMapping("/test")
//	public GooglePlaceResponse test() {
//		return googlePlaceService.requestNearBySearch("51.5054597,-0.0775452", "london point of interest", "distance");
//	}
}

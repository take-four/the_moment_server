package com.takefour.themoment.controller.api;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.takefour.themoment.config.method.annotation.CurrentUser;
import com.takefour.themoment.model.Account;
import com.takefour.themoment.model.City;
import com.takefour.themoment.model.Moment;
import com.takefour.themoment.model.Place;
import com.takefour.themoment.model.dto.CityPlaceDto;
import com.takefour.themoment.service.CityService;
import com.takefour.themoment.service.GooglePlaceService;
import com.takefour.themoment.service.MomentService;
import com.takefour.themoment.service.PlaceService;

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

	@GetMapping("/places")
	public CityPlaceDto getNearbyPlaces(@RequestParam Double latitude,
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
		return momentService.save(moment, cityName, placeName, user.getUsername());
	}


	@GetMapping
	public List<Moment> getAllMoments(@RequestParam(required = false) String accountId,
									  @RequestParam(required = false) Double latitude,
									  @RequestParam(required = false) Double longitude) {

		List<Moment> moments = new ArrayList<>();

		Moment moment = new Moment();
		moment.setId(1);
		moment.setDescription("test1");
		City city = new City();
		moment.setCity(city);
		Place place = new Place();
		moment.setPlace(place);
		moment.setCreateDate(LocalDateTime.now());


		Account account = new Account();
		account.setEmail("byeol3058@gmail.com");
		moment.setAccount(account);

		moments.add(moment);
		moments.add(moment);
		moments.add(moment);

		return moments;
	}

	@GetMapping("/city")
	public List<Moment> getNearbyMoment(@RequestParam Double latitude,
	                                    @RequestParam Double longitude){

		String location = latitude + "," + longitude;
		String cityName = googlePlaceService.requestGeocode(location, "locality", "en");
		List<String> places = googlePlaceService.requestNearBySearch(location, cityName.split(",")[0] + " point of interest", "distance");

		return momentService.finyByLocation(cityName,places);

	}

	@GetMapping("/{accountId}")
	public List<Moment> getMyMoment(@PathVariable String accountId) {
		return momentService.findByAccountId(accountId);
	}
	//현재 위치에서 가까운 모먼트

	//시간순으로 정렬 가능한가
	@GetMapping("/{placeId}")
	public List<Moment> getMomentOfPlace(@PathVariable Integer placeId){
		List<Moment> moments = momentService.findByPlaceIdOrderByCreateDateDesc(placeId);
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
}

package com.takefour.themoment.controller.api;

import com.takefour.themoment.config.method.annotation.CurrentUser;
import com.takefour.themoment.model.Account;
import com.takefour.themoment.model.City;
import com.takefour.themoment.model.Heart;
import com.takefour.themoment.model.Moment;
import com.takefour.themoment.model.dto.CityPlaceDto;
import com.takefour.themoment.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/apis/moments")
public class MomentController {

	@Autowired
	private MomentService momentService;

	@Autowired
	private GooglePlaceService googlePlaceService;

	@Autowired
	private HeartService heartService;

	@Autowired
	private AccountService accountService;

	@Autowired
	private CityService cityService;

	@Autowired
	private BookmarkService bookmarkService;

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
	public List<Moment> getAllMoments(@RequestParam(required = false) Double latitude,
									  @RequestParam(required = false) Double longitude) {

		if(latitude != null && longitude != null) {
			String location = latitude + "," + longitude;
			String cityName = googlePlaceService.requestGeocode(location, "locality", "en");
			List<String> places = googlePlaceService.requestNearBySearch(location, cityName.split(",")[0] + " point of interest", "distance");

			return momentService.finyByLocation(cityName,places);
		}

		List<Moment> moments = new ArrayList<>();

		return moments;
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

	@PostMapping("/{momentId}/hearts")
	public void toggleHeart(@PathVariable Integer momentId,
	                         @CurrentUser User user) {
		if(heartService.findByAccountIdAndMomentId(user.getUsername(), momentId) == null) {
			heartService.save(user.getUsername(), momentId);
		}
		else {
			heartService.deleteByAccountIdAndMomentId(user.getUsername(), momentId);
		}
	}

	@PostMapping("/visited")
	public boolean visitedCity(@RequestParam Double latitude,
	                           @RequestParam Double longitude,
	                           @CurrentUser User user){
		String location = latitude + "," + longitude;
		String cityName = googlePlaceService.requestGeocode(location, "locality", "en");
		City city = cityService.findByName(cityName);
		if (city == null) {
			city = new City(cityName);
			cityService.save(city);
		}

		Account account = new Account();
		if (account.getVisitedList().contains(city.getId())) {
			return false;
		}
		else {
			accountService.updateVisited(user.getUsername(), city);
			return true;
		}
	}

	@PostMapping("/{momentId}/bookmarks")
	public void bookmarkMoment(@PathVariable Integer momentId,
	                           @CurrentUser User user
	                           ) {
		bookmarkService.save(user.getUsername(), momentId);
	}

	@GetMapping("/bookmarks")
	public List<Moment> getBookmarkedMoments(@CurrentUser User user){
		List<Moment> moments = bookmarkService.findMomentsByAccountId(user.getUsername());
		return moments;
	}

	@DeleteMapping("/{momentId}/bookmarks")
	public void deleteBookmarkedMoment(@PathVariable Integer momentId,
	                                   @CurrentUser User user){
		bookmarkService.deleteByAccountIdAndMomentId(user.getUsername(), momentId);
	}

}

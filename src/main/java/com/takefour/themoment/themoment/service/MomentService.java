package com.takefour.themoment.themoment.service;

import com.takefour.themoment.themoment.model.City;
import com.takefour.themoment.themoment.model.Moment;
import com.takefour.themoment.themoment.model.Place;
import com.takefour.themoment.themoment.model.User;
import com.takefour.themoment.themoment.repository.MomentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class MomentService {
	@Autowired
	private MomentRepository momentRepository;

	@Autowired
	private CityService cityService;

	@Autowired
	private PlaceService placeService;
//	GET: /apis/moments?userId=xxx
//	userId로 moment 조회
	public List<Moment> findByUserId(Integer userId){
		return momentRepository.findByUserId(userId);
	}

//	POST: /apis/moments (@requestbody Moment moment)
//	moment 저장
	public Moment save(Moment moment, String cityName, String placeName, User user) {

//		City city = new City();
		//중복검사

		City city = cityService.findByName(cityName);
		if (city == null) {
			city = new City(cityName);
			city = cityService.save(city);
		}
		moment.setCity(city);

		Place place = placeService.findByNameAndCityId(placeName, city.getId());
		if (place == null){
			place = new Place(placeName, city.getId());
			place = placeService.save(place);
		}
		moment.setPlace(place);

		moment.setCreateDate(LocalDateTime.now());
		moment.setUser(user);

		return momentRepository.save(moment);
	}

//	DELETE: /apis/moments/{momentId}
//	id에 해당하는 moment 삭제
	public void delete(Integer id) {
		momentRepository.delete(id);
	}

	public Moment findById(Integer id) {
		return momentRepository.findOne(id);
	}
}

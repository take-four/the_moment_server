package com.takefour.themoment.service;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.takefour.themoment.model.Account;
import com.takefour.themoment.model.City;
import com.takefour.themoment.model.Moment;
import com.takefour.themoment.model.Place;
import com.takefour.themoment.repository.MomentRepository;


@Service
public class MomentService {
	@Autowired
	private MomentRepository momentRepository;

	@Autowired
	private CityService cityService;

	@Autowired
	private PlaceService placeService;
//	GET: /apis/moments?accountId=xxx
//	accountId로 moment 조회
	public List<Moment> findByAccountId(String accountId){
		return momentRepository.findByAccountId(accountId);
	}

//	POST: /apis/moments (@requestbody Moment moment)
//	moment 저장
	public Moment save(Moment moment, String cityName, String placeName, String accountId) {

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
		Account account = new Account();
		account.setId(accountId);
		moment.setAccount(account);

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

package com.takefour.themoment.service;

import com.takefour.themoment.model.Account;
import com.takefour.themoment.model.City;
import com.takefour.themoment.model.Moment;
import com.takefour.themoment.model.Place;
import com.takefour.themoment.repository.MomentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;


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


	public List<Moment> findByPlaceIdOrderByCreateDateDesc(Integer placeId){
		return momentRepository.findByPlaceIdOrderByCreateDateDesc(placeId);
	}
//	POST: /apis/moments (@requestbody Moment moment)
//	moment 저장
	public List<Moment> finyByLocation(String cityName, List<String> placeNames){

		City city = cityService.findByName(cityName);
		if (city == null) {
			city = new City(cityName);
			cityService.save(city);
		}
		//city가 없다 = 저장된 모먼트가없다 즉 그 시티를 저장하고 모먼트를 적으라는 알림을 띄운다던지 뭐 그렇게 하나?
		//이후에는 있다는가정하에하겠다
		//2.플레이스 가까운순으로 리스트받는다
		//3.제일 가까운 플레이스의 모먼트를 나열하되, 최신순으로?
		List<Moment> moments = new ArrayList<>();
		for(String placeName: placeNames) {
			Place place = placeService.findByNameAndCityId(placeName, city.getId());
			moments.addAll(findByPlaceIdOrderByCreateDateDesc(place.getId()));
		}


		return moments;
	}

	public Moment save(Moment moment, String cityName, String placeName, String accountId) {
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

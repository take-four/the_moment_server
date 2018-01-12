package com.takefour.themoment.themoment.controller.api;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.takefour.themoment.themoment.model.Moment;
import com.takefour.themoment.themoment.model.User;

@RestController
@RequestMapping("/apis/moments")
public class MomentController {
	// TODO: 2018. 1. 5. mock 데이터 내려주기 

	@PostMapping
	public Moment saveMoment(@RequestBody Moment moment) {
		return new Moment();
	}

	@GetMapping
	public List<Moment> getAllMoments(@RequestParam(required = false) Integer userId,
									  @RequestParam(required = false) Long latitude,
									  @RequestParam(required = false) Long longitude) {
		List<Moment> moments = new ArrayList<>();

		Moment moment = new Moment();
		moment.setId(1);
		moment.setDescription("test1");
//		moment.setCityId("city");
		moment.setPlaceId("place");
		moment.setCreateDate(LocalDateTime.now());


		User user = new User();
		user.setEmail("byeol3058@gmail.com");
		moment.setUser(user);

		moments.add(moment);
		moments.add(moment);
		moments.add(moment);

		return moments;
	}

	@GetMapping("/{id}")
	public Moment getMoment() {
		Moment moment = new Moment();
		moment.setId(1);
		moment.setDescription("test1");
//		moment.setCityId("city");
		moment.setPlaceId("place");
		moment.setCreateDate(LocalDateTime.now());


		User user = new User();
		user.setEmail("byeol3058@gmail.com");
		moment.setUser(user);
		return moment;
	}

	@DeleteMapping("/{id}")
	public void deleteMoment() {
	}

	@PatchMapping("/{id}")
	public Moment updateMoment(@RequestBody Moment moment) {
		return moment;
	}

}

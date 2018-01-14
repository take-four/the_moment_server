package com.takefour.themoment.themoment.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.takefour.themoment.themoment.service.response.GooglePlaceResponse;

/**
 * Created by hanbyeol on 2018. 1. 14..
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class GoogleServiceTest {

	@Autowired
	private GooglePlaceService googlePlaceService;

	@Test
	public void nearBySearchTest() {
		GooglePlaceResponse response = googlePlaceService.requestNearBySearch("51.5054597,-0.0775452", "london point of interest", "distance");
		System.out.println(response);
	}
}

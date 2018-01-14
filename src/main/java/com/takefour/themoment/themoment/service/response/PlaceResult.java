package com.takefour.themoment.themoment.service.response;

import java.util.List;

import lombok.Getter;
import lombok.ToString;

/**
 * Created by hanbyeol on 2018. 1. 14..
 */
@Getter
@ToString
public class PlaceResult {
	private String name;
	private List<String> types;
}

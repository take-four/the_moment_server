package com.takefour.themoment.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CollectionTable;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.Id;

import lombok.Data;

//실행될때 이걸보고 테이블을 만들어준다.
@Entity
@Data
public class Account {
	@Id // primary key
	private String id;

	private String email;

	private String name;

	@ElementCollection
	@CollectionTable(name = "visited")
	private List<Integer> visitedList = new ArrayList<>();

	private String role;
}

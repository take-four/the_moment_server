package com.takefour.themoment.themoment.model;

import lombok.Data;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.ArrayList;
import java.util.List;

//실행될때 이걸보고 테이블을 만들어준다.
@Entity
@Data
public class User {
	@Id // primary key
	@GeneratedValue //auto increment
	private Integer id;

	@Column(nullable = false)
	private String email;

	@JsonIgnore
	@Column(nullable = false)
	private String password;

	private String name;

	@ElementCollection
	@CollectionTable(name = "visited")
	private List<Integer> cityId = new ArrayList<>();
	//cmd+n getter and setter
}

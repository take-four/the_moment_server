package com.takefour.themoment.themoment.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;

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

	private String role;
}

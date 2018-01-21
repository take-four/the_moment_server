package com.takefour.themoment.themoment.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Data
@Entity
@NoArgsConstructor
public class City {
	//country name language
	@Id
	@GeneratedValue
	private Integer id;

	private String name;

	@Column(columnDefinition = "varchar(5) default 'en'")
	private String language;

	public City(String name) {
		this.name = name;
	}
}

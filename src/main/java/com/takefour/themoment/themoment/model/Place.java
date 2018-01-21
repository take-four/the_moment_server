package com.takefour.themoment.themoment.model;


import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.ColumnDefault;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Data
@Entity
@NoArgsConstructor
public class Place {
	//name lang cityid
	@Id
	@GeneratedValue
	private Integer id;

	private String name;

	@Column(nullable = false)
	private Integer cityId;

	public Place(String name, Integer cityId) {
		this.name = name;
		this.cityId = cityId;
	}


}

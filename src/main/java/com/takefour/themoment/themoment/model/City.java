package com.takefour.themoment.themoment.model;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
@Data
public class City {
	//country name language
	@Id
	@GeneratedValue
	private Integer id;

	private String name;

	@Column(columnDefinition = "varchar(5) default 'kr'")
	private String language;

}

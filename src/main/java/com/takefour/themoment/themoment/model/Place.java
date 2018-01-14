package com.takefour.themoment.themoment.model;


import lombok.Data;
import org.hibernate.annotations.ColumnDefault;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
@Data
public class Place {
	//name lang cityid
	@Id
	@GeneratedValue
	private Integer id;

	private String name;

	@Column(nullable = false)
	private Integer cityId;


}

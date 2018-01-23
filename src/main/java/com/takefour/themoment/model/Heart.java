package com.takefour.themoment.model;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
@Data
public class Heart {
	@Id
	@GeneratedValue
	private Integer id;
	private String accountId;
	private Integer momentId;
}

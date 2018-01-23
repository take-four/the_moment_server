package com.takefour.themoment.model;

import lombok.Data;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Data
public class Moment {
	@Id
	@GeneratedValue
	private Integer id;

	private String photo;

	@Column(nullable = false)
	private String description;

	@Column(nullable = false, columnDefinition = "timestamp")
	private LocalDateTime createDate;

	@ManyToOne
	@JoinColumn(name = "city_id", nullable = false)
	private City city;

	@ManyToOne
	@JoinColumn(name = "place_id", nullable = false)
	private Place place;

	@ManyToOne
	@JoinColumn(name = "account_id")
	private Account account;

}

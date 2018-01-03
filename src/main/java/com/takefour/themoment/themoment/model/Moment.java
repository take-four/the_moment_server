package com.takefour.themoment.themoment.model;

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

	@Column(nullable = false)
	private String cityId;

	@Column(nullable = false)
	private String placeId;

	@ManyToOne
	private User user;

}

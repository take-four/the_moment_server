package com.takefour.themoment.themoment.model;

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
    private Integer userId;
    private Integer momentId;
}

package com.takefour.themoment.themoment.model;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

//실행될때 이걸보고 테이블을 만들어준다.
@Entity
@Data
public class User {
    @Id // primary key
    @GeneratedValue //auto increment
    private Integer id;

    @Column(nullable = false)
    private String email;

    @Column(nullable = false)
    private String password;

    private String userName;

    //cmd+n getter and setter
}

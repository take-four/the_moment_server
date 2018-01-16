package com.takefour.themoment.themoment.model;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;

import lombok.Data;

//실행될때 이걸보고 테이블을 만들어준다.
@Entity
@Data
public class User implements UserDetails {
	@Id // primary key
	@GeneratedValue //auto increment
	private Integer id;

	@Column(nullable = false)
	private String email;

	private String name;

	@ElementCollection
	@CollectionTable(name = "visited")
	private List<Integer> cityId = new ArrayList<>();

	private String role;

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return AuthorityUtils.createAuthorityList(role, "USER");
	}

	@Override
	public String getPassword() {
		return null;
	}

	// getUserName 이랑 구분되어야 함
	@Override
	public String getUsername() {
		return email;
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return true;
	}

}

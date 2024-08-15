package com.swipeup.blog.entity;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Data;


@Data
@Entity
@Table(name="users")
public class User {
	
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	
	private Integer id;
	
	@Column(name="user_name",nullable = false,length=100)
	private String name;
	@Column(name="user_email",nullable = false,length=100)
	private String email;
	@Column(name="user_password",nullable = false,length=100)
	private String password;
	@Column(name="user_about")
	private String about;
	
	@OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
	List<Post> posts = new ArrayList<Post>();
	
	
	
	
	

}

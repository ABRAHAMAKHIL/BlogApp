package com.swipeup.blog.entity;

import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name="posts")

public class Post {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;
	
	@Column(name="post_title" , nullable = false)
	private String title;
	
	@Column(name="post_content" , nullable = false)
	private String content;
	
	@Column(name="post_image")
	private String image;
	
	@Column(name="post_created_date")
	private Date date;
	
	
	@ManyToOne
	private Category category;
	
	@ManyToOne
	private User user;
	
	

}

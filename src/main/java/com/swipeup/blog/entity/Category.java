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

@Entity
@Table(name="category")
@Data
public class Category {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;
	
	@Column(name="category_title")
	private String title;
	
	@Column(name="category_descptn")
	private String description;
	
	
	@OneToMany(mappedBy = "category",cascade = CascadeType.ALL)
	List<Post> posts = new ArrayList<Post>();
	
	
	

}

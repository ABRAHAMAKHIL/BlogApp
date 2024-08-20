package com.swipeup.blog.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name="role")
public class Role {
	
	@Id
	private Integer id;
	
	@Column(name="role_name")
	private String name;
	

	
	

}

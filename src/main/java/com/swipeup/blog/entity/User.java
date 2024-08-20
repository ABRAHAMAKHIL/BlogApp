package com.swipeup.blog.entity;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Data;


@Data
@Entity
@Table(name="users")
public class User implements UserDetails {
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

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
	
	@OneToMany(mappedBy = "user",cascade = CascadeType.ALL)
	List<Comment> comments = new ArrayList<Comment>();
	
	@ManyToMany(cascade = CascadeType.ALL)
	@JoinTable(name="user_role",
	joinColumns = @JoinColumn(name="usersid", referencedColumnName = "id"),
	inverseJoinColumns = @JoinColumn(name="roleid" , referencedColumnName = "id")
			)
	Set<Role> userRole = new HashSet<>();

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getUsername() {
		// TODO Auto-generated method stub
		return this.email;
	}
	
	
	
	
	

}

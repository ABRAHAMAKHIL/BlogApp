package com.swipeup.blog;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.swipeup.blog.entity.Role;
import com.swipeup.blog.repositories.RoleRepo;

@SpringBootApplication
public class BlogAppApiApplication  implements CommandLineRunner{
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Autowired
	private RoleRepo roleRepo;
	
	public static void main(String[] args) {
		SpringApplication.run(BlogAppApiApplication.class, args);
	}

	@Bean
	public ModelMapper modelMapper() {
		return new ModelMapper();

	}

	@Override
	public void run(String... args) throws Exception {
		System.out.println( this.passwordEncoder.encode("33aXyz12"));
		
		try {
			
			Role role = new Role();
			role.setId(501);
			role.setName("ADMIN_USER");
			Role role1 = new Role();
			role1.setId(502);
			role1.setName("NORMAL_USER");
			List<Role> roles = List.of(role,role1);
			
			roles.forEach((r)->System.out.println(r.getName()));
			
			
			
			
			
			
			
		}catch(Exception e) {
			
			e.printStackTrace();
		}
		
		
	}
	

	

}

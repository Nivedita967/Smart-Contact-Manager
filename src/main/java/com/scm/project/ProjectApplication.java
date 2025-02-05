package com.scm.project;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.scm.project.entities.User;
import com.scm.project.repository.UserRepo;

@SpringBootApplication
public class ProjectApplication {

	@Autowired
	private UserRepo userRepo;

	public static void main(String[] args) {
		SpringApplication.run(ProjectApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		User user = new User();
		user.setUserId(UUID.randomUUID().toString());
		user.setName("test");
		user.setEmail("test@gmail.com");
		user.setEnabled(true);
		user.setAbout("This is test user");

		userRepo.findByEmail("test@gmail.com").ifPresentOrElse(user1 -> {},() -> {
			userRepo.save(user);
			System.out.println("user created");
		});


	}

}

package net.mv.forum.boot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages="net.mv.forum")
public class AngularForumApplication {
	
	public static void main(String[] args) {
		SpringApplication.run(AngularForumApplication.class, args);
	}

}

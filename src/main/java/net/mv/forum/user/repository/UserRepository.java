package net.mv.forum.user.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import net.mv.forum.user.domain.User;


public interface UserRepository extends JpaRepository<User, Long>{
	
	public User findUserByUsername(String username);

}

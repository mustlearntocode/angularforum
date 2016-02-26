package net.mv.forum.user.repository;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import net.mv.forum.user.domain.User;


public interface UserRepository extends JpaRepository<User, Long>{
	
	public User findUserByUsername(String username);
	@Query("select u from User u")
	public List<User> findTop5Users(Pageable page);

}

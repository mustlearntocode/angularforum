package net.mv.forum.post.repository;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import net.mv.forum.post.domain.Post;

public interface PostRepository extends JpaRepository<Post, Long>{

	@Query("select p from Post p")
	public List<Post> findTop5Posts(Pageable page);
	
}

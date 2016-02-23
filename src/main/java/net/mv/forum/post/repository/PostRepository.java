package net.mv.forum.post.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import net.mv.forum.post.domain.Post;

public interface PostRepository extends JpaRepository<Post, Long>{

	
	
}

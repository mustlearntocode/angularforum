package net.mv.forum.forum.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import net.mv.forum.forum.domain.Forum;

public interface ForumRepository extends JpaRepository<Forum, Long>{
	
}

package net.mv.forum.forum.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import net.mv.forum.forum.domain.Forum;

public interface ForumRepository extends JpaRepository<Forum, Long>{
	
	public List<Forum> findAllByOrderByDateCreatedDesc();
	public Page<Forum> findAllByOrderByDateCreatedDesc(Pageable pageable);
	
}

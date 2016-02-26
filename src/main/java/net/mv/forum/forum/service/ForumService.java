package net.mv.forum.forum.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import net.mv.forum.forum.dto.ForumDto;

public interface ForumService {
	
	public ForumDto addForum(ForumDto forumDto);
	public List<ForumDto> findAllForums();
	public Page<ForumDto> findAllForums(Pageable page);
	public ForumDto findForumById(Long id);
	public Long findForumCount();
	public List<ForumDto> findTopFiveForums();

}

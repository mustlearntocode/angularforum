package net.mv.forum.forum.service;

import java.util.List;

import net.mv.forum.forum.dto.ForumDto;

public interface ForumService {
	
	public ForumDto addForum(ForumDto forumDto);
	public List<ForumDto> findAllForums();
	public ForumDto findForumById(Long id);

}

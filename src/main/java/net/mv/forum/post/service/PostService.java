package net.mv.forum.post.service;

import java.util.List;

import net.mv.forum.post.dto.PostDto;

public interface PostService {
	
	public PostDto addPost(PostDto post);
	public List<PostDto> findTop5Posts();

}

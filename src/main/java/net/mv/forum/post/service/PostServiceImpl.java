package net.mv.forum.post.service;

import java.util.Date;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import net.mv.forum.forum.domain.Forum;
import net.mv.forum.forum.repository.ForumRepository;
import net.mv.forum.post.domain.Post;
import net.mv.forum.post.dto.PostDto;
import net.mv.forum.post.repository.PostRepository;
import net.mv.forum.user.domain.User;
import net.mv.forum.user.repository.UserRepository;

@Service
@Transactional
public class PostServiceImpl implements PostService{
	
	@Autowired
	private PostRepository postRepository;
	@Autowired
	private UserRepository userRepository;
	@Autowired
	private ForumRepository forumRepository;

	@Override
	/**
	 * Figure this out why it was not properly saving Post objects.
	 */
	public PostDto addPost(PostDto postDto) {
		
		User user = userRepository.findUserByUsername(postDto.getAuthor());
		
		Forum forum = forumRepository.findOne(postDto.getForumId());
		
		Post post = new Post();
		
		post.setText(postDto.getText());
		post.setForum(forum);
		post.setUser(user);
		post.setDateAuthored(new Date());
		
		forum.getPosts().add(post);
		//postRepository.saveAndFlush(post);
		forumRepository.save(forum);
		
		postDto.setId(post.getId());
		postDto.setCreated(post.getDateAuthored());
		
		return postDto;
	}

}

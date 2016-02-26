package net.mv.forum.post.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import net.mv.forum.post.dto.PostDto;
import net.mv.forum.post.service.PostService;

@Controller
@RequestMapping("/post")
public class PostController {
	
	@Autowired
	private PostService postService;
	
	@RequestMapping("/add")
	public ResponseEntity<PostDto> addPost(@RequestBody PostDto postDto){
		
		return new ResponseEntity<PostDto>(postService.addPost(postDto), HttpStatus.OK);
	}
	
	@RequestMapping("/top")
	public ResponseEntity<List<PostDto>> viewFiveMostRecentPosts(){
		
		return new ResponseEntity<List<PostDto>>(postService.findTop5Posts(), HttpStatus.OK);
	}

}

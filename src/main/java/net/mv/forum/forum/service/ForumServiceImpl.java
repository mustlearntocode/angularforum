package net.mv.forum.forum.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import net.mv.forum.forum.domain.Forum;
import net.mv.forum.forum.dto.ForumDto;
import net.mv.forum.forum.repository.ForumRepository;
import net.mv.forum.user.domain.User;
import net.mv.forum.user.repository.UserRepository;

@Service
@Transactional
public class ForumServiceImpl implements ForumService{
	
	@Autowired
	private ForumRepository forumRepository;
	
	@Autowired
	private UserRepository userRepository;

	@Override
	/**
	 * Save forum prior to saving post
	 */
	public ForumDto addForum(ForumDto forumDto) {
		System.out.println(forumDto);
		Forum forum = new Forum(forumDto);
		User user = userRepository.findUserByUsername(forumDto.getAuthor());
		forum.setUser(user);
		forum.setDateCreated(new Date());
		//forum.getPosts().add(new Post(forumDto.getPosts().get(0)));
		forumRepository.saveAndFlush(forum);
		forumDto.setId(forum.getId());
		forumDto.setAuthor(user.getUsername());
		return forumDto;
	}

	@Override
	public List<ForumDto> findAllForums() {
		List<Forum> forums = forumRepository.findAllByOrderByDateCreatedDesc();
		
		List<ForumDto> forumDtos = new ArrayList<ForumDto>();
		
		for(Forum forum : forums){
			ForumDto forumDto = new ForumDto(forum);
			forumDtos.add(forumDto);
		}
		
		return forumDtos;
	}

	@Override
	public ForumDto findForumById(Long id) {
		
		Forum forum = forumRepository.findOne(id);
		
		System.out.println(forum.getPosts());
		
		ForumDto forumDto = new ForumDto(forum);
		
		System.out.println(forumDto.getPosts());
		
		return forumDto;
	}

	@Override
	public Page<ForumDto> findAllForums(Pageable page) {
		
		Page<Forum> forums = forumRepository.findAllByOrderByDateCreatedDesc(page);
		
		List<ForumDto> forumDtoList = new ArrayList<>();
		
		for(Forum forum : forums){
			forumDtoList.add(new ForumDto(forum));
		}
		
		Page<ForumDto> forumDtos = new PageImpl<ForumDto>(forumDtoList);
		
		return forumDtos;
	}

	@Override
	public Long findForumCount() {
		return forumRepository.count();
	}

	@Override
	public List<ForumDto> findTopFiveForums() {
		
		Pageable topFive = new PageRequest(0, 5, Direction.DESC, "dateCreated"); 
		
		List<Forum> forums = forumRepository.findTop5Forums(topFive);
		
		List<ForumDto> forumDtoList = new ArrayList<>();
		
		for(Forum forum : forums){
			forumDtoList.add(new ForumDto(forum));
		}
		
		return forumDtoList;
	}
	
	

}

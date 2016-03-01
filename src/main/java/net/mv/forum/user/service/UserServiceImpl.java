package net.mv.forum.user.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import net.mv.forum.mail.service.MailService;
import net.mv.forum.roles.domain.Role;
import net.mv.forum.roles.repository.RoleRepository;
import net.mv.forum.user.domain.User;
import net.mv.forum.user.dto.UserDto;
import net.mv.forum.user.repository.UserRepository;

@Service
@Transactional
public class UserServiceImpl implements UserService{
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private RoleRepository roleRepository;
	
	@Autowired
	private MailService mailServiceImpl;
	
	@Override
	public void activateUser(Long id){
		userRepository.getOne(id).setEnabled(true);
	}

	@Override
	public UserDto retrieveUserDetails(String username) {
		UserDto user = new UserDto(userRepository.findUserByUsername(username));
		return user;
	}
	
	@Override
	public void registerUser(UserDto userDto){
		User userToRegister = new User(userDto);
		Role role = roleRepository.getRoleByRoleName("USER");
		System.out.println(role);
		System.out.println("Sending mail...");
		userToRegister.getRoles().add(role);
		userRepository.save(userToRegister);
		mailServiceImpl.sendMail(
				userToRegister.getEmail(), 
				"Welcome to the forum! Please follow this link to activate your account:"
				+ "http://angularforum-greanspace.rhcloud.com/user/activate?id="+userToRegister.getUserId()
				, "Angular Forum Registration");
	}

	@Override
	public List<UserDto> retrieve5MostRecentUsers() {
		Pageable page = new PageRequest(0, 5, Direction.DESC, "id");
		List<User> users = userRepository.findTop5Users(page);
		
		List<UserDto> userDtos = new ArrayList<UserDto>();
		
		for(User user : users){
			
			userDtos.add(new UserDto(user));
			
		}
		
		return userDtos;
	}

}

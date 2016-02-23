package net.mv.forum.user.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
		userToRegister.getRoles().add(role);
		userRepository.save(userToRegister);
	}

}

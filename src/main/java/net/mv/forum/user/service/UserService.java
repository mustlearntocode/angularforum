package net.mv.forum.user.service;

import net.mv.forum.user.dto.UserDto;

public interface UserService {
	
	public UserDto retrieveUserDetails(String username);
	public void registerUser(UserDto userDto);

}

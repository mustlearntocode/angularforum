package net.mv.forum.user.controller;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import net.mv.forum.exception.ExceptionDto;
import net.mv.forum.user.dto.UserDto;
import net.mv.forum.user.service.UserService;

@RestController
@RequestMapping("/user")
public class UserController {

	@Autowired
	private UserService userServiceImpl;

	@RequestMapping("/login")
	public Principal getUser(Principal user) {
		return user;
	}

	@RequestMapping(value = "/register", method = { RequestMethod.POST }, consumes = {
			MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<Boolean> registerUser(@RequestBody UserDto user) {
		System.out.println("UserDto value: "+user);
		userServiceImpl.registerUser(user);
		return new ResponseEntity<Boolean>(true, HttpStatus.CREATED);
	}

	@RequestMapping("/details")
	public ResponseEntity<UserDto> getUserDetails(String username) {
		System.out.println(username);
		return new ResponseEntity<UserDto>(userServiceImpl.retrieveUserDetails(username), HttpStatus.OK);
	}

	@ExceptionHandler(value = DataAccessException.class)
	public ResponseEntity<ExceptionDto> handleDataAccessException(DataAccessException dae) {
		return new ResponseEntity<ExceptionDto>(new ExceptionDto("The user already exists.", dae), HttpStatus.CONFLICT);
	}

}

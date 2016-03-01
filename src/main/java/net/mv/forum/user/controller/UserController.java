package net.mv.forum.user.controller;

import java.io.IOException;
import java.security.Principal;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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
	
	@RequestMapping("/activate")
	public void activateUser(Long id, HttpServletResponse response, HttpServletRequest request) throws IOException{
		userServiceImpl.activateUser(id);
		response.sendRedirect(request.getContextPath()+"/index.jsp");
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
	
	@RequestMapping("/top")
	public ResponseEntity<List<UserDto>> view5MostRecentUsers(){
		return new ResponseEntity<List<UserDto>>(userServiceImpl.retrieve5MostRecentUsers(), HttpStatus.OK);
	}

	@ExceptionHandler(value = DataAccessException.class)
	public ResponseEntity<ExceptionDto> handleDataAccessException(DataAccessException dae) {
		System.out.println("This is my messahge: ");
		System.out.println(dae.getMessage());
		System.out.println(dae.getMostSpecificCause());
		ExceptionDto dto = null;
		if(dae.getMostSpecificCause().toString().contains("email_key")){
			dto = new ExceptionDto("That email is already taken.", dae);
		}else if(dae.getMostSpecificCause().toString().contains("username_key")){
			dto = new ExceptionDto("That username is already taken.", dae);
		}else{
			dto = new ExceptionDto("Woahh, dude. Something unexpected has occured.", dae);
		}
		
		return new ResponseEntity<ExceptionDto>(dto, HttpStatus.CONFLICT);
	}

}

package net.mv.forum.user.dto;

import net.mv.forum.user.domain.User;

public class UserDto {

	private Long id;
	private String username;
	private String email;
	private String password;

	public UserDto() {
		super();
	}

	public UserDto(User user) {
		super();
		this.id = user.getUserId();
		this.username = user.getUsername();
		this.email = user.getEmail();
	}

	@Override
	public String toString() {
		return "UserDto [id=" + id + ", username=" + username + ", email=" + email + ", password=" + password + "]";
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

}

package net.mv.forum.user.domain;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import net.mv.forum.roles.domain.Role;
import net.mv.forum.user.dto.UserDto;

@Entity
@Table(name = "users")
public class User {

	@Id
	@Column(name = "u_id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long userId;
	@Column(name="username",unique=true)
	private String username;
	private String password;
	private Boolean enabled;
	@Column(name="email",unique=true)
	private String email;

	@ManyToMany(cascade=CascadeType.PERSIST)
	@JoinTable(name = "users_roles", joinColumns = { @JoinColumn(name = "u_r_id") }, inverseJoinColumns = {
			@JoinColumn(name = "r_id") })
	private Set<Role> roles = new HashSet<Role>();

	public User() {
		super();
	}

	public User(UserDto user) {
		super();
		this.username = user.getUsername();
		this.password = user.getPassword();
		this.enabled = false;
		this.email = user.getEmail();
	}

	public User(Long userId, String username, String password, Boolean enabled, String email) {
		super();
		this.userId = userId;
		this.username = username;
		this.password = password;
		this.enabled = enabled;
		this.email = email;
	}

	@Override
	public String toString() {
		return "User [userId=" + userId + ", username=" + username + ", password=" + password + ", enabled=" + enabled
				+ ", email=" + email + "]";
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Boolean getEnabled() {
		return enabled;
	}

	public void setEnabled(Boolean enabled) {
		this.enabled = enabled;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Set<Role> getRoles() {
		return roles;
	}

	public void setRoles(Set<Role> roles) {
		this.roles = roles;
	}

}

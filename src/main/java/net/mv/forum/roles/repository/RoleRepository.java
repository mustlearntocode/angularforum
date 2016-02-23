package net.mv.forum.roles.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import net.mv.forum.roles.domain.Role;

public interface RoleRepository extends JpaRepository<Role, Long>{
	
	public Role getRoleByRoleName(String roleName);

}

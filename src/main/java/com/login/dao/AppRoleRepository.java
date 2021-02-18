package com.login.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import com.login.entities.AppRole;
@RepositoryRestResource
public interface AppRoleRepository extends JpaRepository<AppRole,Long>{
	//
	public AppRole findByRoleName(String roleName);

}

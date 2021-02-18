package com.login.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import com.login.entities.AppUser;
@RepositoryRestResource
public interface AppUserRepository extends JpaRepository<AppUser,Long> {
	//authentification : if name is in database
	public AppUser findByUserName(String userName);

}

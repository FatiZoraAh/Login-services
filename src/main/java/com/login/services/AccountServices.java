package com.login.services;

import com.login.entities.AppRole;
import com.login.entities.AppUser;

public interface AccountServices {
	//enregistrer user
	public AppUser saveUser(String userName,String passWord,String ConfirmedPassword);
	//save a role 
	public AppRole save(AppRole role);
	//charger un user 
	public AppUser loadUser(String userName);
	//ajouter un role a un user
	public void addRoleToUser(String userName,String roleName);

}

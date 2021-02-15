package com.login.services;

import com.login.entities.AppRole;
import com.login.entities.AppUser;

public interface AccountServices {
	public AppUser saveUser(String userName,String passWord,String ConfirmedPassword);
	public AppRole save(AppRole role);
	public AppUser loadUser(String userName);
	public void addRoleToUser(String userName,String roleName);

}

package com.login.services;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.login.dao.AppRoleRepository;
import com.login.dao.AppUserRepository;
import com.login.entities.AppRole;
import com.login.entities.AppUser;

@Service
@Transactional
public class AccountServicesImpl implements AccountServices {

	private AppUserRepository appUserRepo;

	private AppRoleRepository appRoleRepo;
	private BCryptPasswordEncoder bCryptPassword;

	public AccountServicesImpl(AppUserRepository appUserRepo, AppRoleRepository appRoleRepo,
			BCryptPasswordEncoder bCryptPassword) {
		super();
		this.appUserRepo = appUserRepo;
		this.appRoleRepo = appRoleRepo;
		this.bCryptPassword = bCryptPassword;
	}

	@Override
	public AppUser saveUser(String userName, String passWord, String ConfirmedPassword) {
		AppUser user = appUserRepo.findByUserName(userName);
		if (user != null)
			throw new RuntimeException("UserName alrady existe!");
		if (!passWord.equals(ConfirmedPassword))
			throw new RuntimeException("Please confirm your password!");
		AppUser appUser = new AppUser();
		appUser.setUserName(userName);
		// crypter le password
		appUser.setActived(true);
		appUser.setPassWord(bCryptPassword.encode(passWord));

		appUserRepo.save(appUser);
		addRoleToUser(userName, "USER");

		return appUser;
	}

	@Override
	public AppRole save(AppRole role) {
		// TODO Auto-generated method stub
		return appRoleRepo.save(role);
	}

	@Override
	public AppUser loadUser(String userName) {
		// TODO Auto-generated method stub
		return appUserRepo.findByUserName(userName);
	}

	@Override
	public void addRoleToUser(String userName, String roleName) {
		// TODO Auto-generated method stub
		AppUser appUser = appUserRepo.findByUserName(userName);
		AppRole appRole = appRoleRepo.findByRoleName(roleName);
		appUser.getRole().add(appRole);

	}

}

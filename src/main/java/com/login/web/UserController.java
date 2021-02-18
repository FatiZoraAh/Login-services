package com.login.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.login.entities.AppUser;
import com.login.services.AccountServices;

import lombok.Data;

@RestController
public class UserController {
	@Autowired
	private AccountServices accountServices;
	@PostMapping("/register")
	public AppUser register(@RequestBody UserForm userForm) {
		return accountServices.saveUser(userForm.getUsername(),userForm.getPassword(),userForm.getConfirmedPassword());
	}

}
@Data
class UserForm{
	private String username;
	private String password;
	private String confirmedPassword;
}

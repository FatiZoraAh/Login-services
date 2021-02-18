package com.login.sec;

import java.util.ArrayList;
import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.login.entities.AppUser;
import com.login.services.AccountServices;
@Service
public class UserDetailsServiceImpl implements UserDetailsService{
    @Autowired
	private AccountServices accountServices;
    
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		AppUser appUser = accountServices.loadUser(username);
		if(appUser==null) throw new UsernameNotFoundException("Name invalid");
		Collection<GrantedAuthority> authorities=new ArrayList<>();
		appUser.getRole().forEach(r->{
			authorities.add(new SimpleGrantedAuthority(r.getRoleName()));
		});
		
		return new User(appUser.getUserName(),appUser.getPassWord(),authorities);
	}

}

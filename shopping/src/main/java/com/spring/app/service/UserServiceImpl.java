package com.spring.app.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.spring.app.entity.Role;
import com.spring.app.entity.User;
import com.spring.app.repository.RoleRepository;
import com.spring.app.repository.UserRepository;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserRepository arepo;
	
	@Autowired
	private RoleRepository brepo;

	@Override
	public void registerUser(User user) {
		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
		String encodedpw = encoder.encode(user.getPassword());
		user.setPassword(encodedpw);
		
		Role roleUser = brepo.findByName("Admin");
		user.addRole(roleUser);
		
		arepo.save(user);
	}
	
}

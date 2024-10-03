package com.sparkshare.demo.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.sparkshare.demo.repository.UserRepository;
import com.sparkshare.demo.model.User;

@Service
public class UserService {
	private final UserRepository userRepository;
	
	@Autowired
	public UserService (UserRepository userRepository){
		this.userRepository = userRepository;
	}
	
	public User saveUser(User user) {
		return userRepository.save(user);
	}
	
	public Optional<User> getUserByUsername(String username) {
		return userRepository.findByUsername(username);
	}
	
	public Optional<User> getUserById(Long id) {
		return userRepository.findById(id);
	}
	
	public List<User> getAllUsers(){
		List<User> users = userRepository.findAll();
		return users;
	}
	
	public void deleteUser(Long id) {
		userRepository.deleteById(id);
	}
}

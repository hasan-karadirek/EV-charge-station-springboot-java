package com.sparkshare.demo.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import com.sparkshare.demo.repository.UserRepository;
import com.sparkshare.demo.exception.ApiException;
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
	
	public User getUserByUsername(String username) {
		Optional<User> user = userRepository.findByUsername(username);
		if(user.isEmpty()){
			throw new ApiException("There is no such a user with this username", 404);
		}
		return user.get();
	}
	
	public User getUserById(Long id) {
		Optional<User> user = userRepository.findById(id);
		if(user.isEmpty()){
			throw new ApiException("There is no such a user associated with this id" + id, 404);
		}
		return user.get();
	}
	
	public Page<User> getAllUsers(Integer page, Integer size){
		Integer pageFromZero = page -1;
        Pageable pageable = PageRequest.of(pageFromZero,size);
		Page<User> users = userRepository.findAll(pageable);
		return users;
	}
	
	public void deleteUser(Long id) {
		User user=getUserById(id);
		
		userRepository.deleteById(user.getId());
	}
}

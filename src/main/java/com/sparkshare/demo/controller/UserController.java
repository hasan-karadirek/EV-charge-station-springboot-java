package com.sparkshare.demo.controller;

import com.sparkshare.demo.exception.ApiException;
import com.sparkshare.demo.model.User;
import com.sparkshare.demo.service.UserService;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/users")
public class UserController {
	private final UserService userService;
	
	@Autowired
	public UserController (UserService userService) {
		this.userService = userService;
	}
	
	@PostMapping("/register")
	public ResponseEntity<User> registerUser(@RequestBody User user){
		User savedUser = userService.saveUser(user);
		return ResponseEntity.ok(savedUser);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<User> getUserById(@PathVariable Long id){
		User user = userService.getUserById(id);
		
		return ResponseEntity.ok(user);
	}
	@GetMapping("/")
	public ResponseEntity<Page<User>> getAllUsers(@RequestParam(defaultValue = "1") @Min(1) Integer page, @RequestParam(defaultValue = "10") @Min(1) @Max(100) Integer size){
		
		Page<User> users=userService.getAllUsers(page, size);
		return ResponseEntity.ok(users);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deleteUserById(@PathVariable Long id){
		userService.deleteUser(id);
		return ResponseEntity.noContent().build();
	}
	
	
}

package com.sample.hibernate.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.sample.hibernate.model.User;
import com.sample.hibernate.response.JSONResponse;
import com.sample.hibernate.service.UserService;

@RestController
@RequestMapping("/api/user")
public class UserController {

	@Autowired
	UserService userService;

	@PostMapping("/addUser")
	public ResponseEntity<JSONResponse> addUser(@RequestBody User user) {

		user.getEmployee().setUser(user);
		User userDetails = userService.addUser(user);
		JSONResponse jsonResponse = new JSONResponse();
		if (userDetails.getUserId() != 0)
			jsonResponse.setResult(userDetails);
		else
			jsonResponse.setErrorMessage("User Not Inserted");

		return new ResponseEntity<>(jsonResponse, HttpStatus.OK);
	}

	@GetMapping("/loadAllUser")
	public ResponseEntity<JSONResponse> loadAllUser() {

		List<User> userDetails = userService.loadAllUser();
		JSONResponse jsonResponse = new JSONResponse();
		if (!userDetails.isEmpty())
			jsonResponse.setResult(userDetails);
		else
			jsonResponse.setErrorMessage("No User Available");

		return new ResponseEntity<>(jsonResponse, HttpStatus.OK);
	}

	@GetMapping("/loadUserById")
	public ResponseEntity<JSONResponse> loadUserById(@RequestParam long userId) {

		Optional<User> userDetails = userService.loadUserById(userId);
		JSONResponse jsonResponse = new JSONResponse();
		if (userDetails.isPresent())
			jsonResponse.setResult(userDetails);
		else
			jsonResponse.setErrorMessage("No User Available");

		return new ResponseEntity<>(jsonResponse, HttpStatus.OK);
	}

	@PutMapping("/updateUser")
	public ResponseEntity<JSONResponse> updateUser(@RequestBody User user) {

		user.getEmployee().setUser(user);
		Optional<User> userDetails = userService.loadUserById(user.getUserId());
		JSONResponse jsonResponse = new JSONResponse();
		jsonResponse.setStatus(200);
		if (userDetails.isPresent()) {
			user = userService.updateUser(user);
			jsonResponse.setResult(userDetails);
		} else {
			jsonResponse.setErrorMessage("No user found for update");
		}

		return new ResponseEntity<>(jsonResponse, HttpStatus.OK);
	}

	@DeleteMapping("/deleteUser")
	public ResponseEntity<JSONResponse> deleteUser(@RequestParam long userId) {

		Optional<User> userDetails = userService.loadUserById(userId);
		JSONResponse jsonResponse = new JSONResponse();
		jsonResponse.setStatus(200);
		if (userDetails.isPresent()) {
			userService.deleteUser(userId);
			jsonResponse.setResult("User Deleted Successfully");
		} else {
			jsonResponse.setErrorMessage("User is not deleted");
		}

		return new ResponseEntity<>(jsonResponse, HttpStatus.OK);
	}

}

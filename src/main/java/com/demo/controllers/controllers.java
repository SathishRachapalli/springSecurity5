package com.demo.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.demo.DataTransferObjects.registrationDTO;

@Controller
public class controllers {

	@Autowired
//	JdbcUserDetailsManager userDetailsManager;

	@GetMapping("/hi")
	@ResponseBody
	public String sayHi() {
		return "hi spring 4";
	}

	@GetMapping("/bye")
	@ResponseBody
	public String sayBye() {
		return "bye";
	}

	@GetMapping("/hello")
	public String sayingHello() {
		return "hello";
	}

	@GetMapping("/register")
	public String RegisterDetails(@ModelAttribute("reg") registrationDTO registrationObj) {

		return "register";
	}

//	@PostMapping("/ProcessRegistration")
//	@ResponseBody
//	// public String registering(@RequestParam("username")String username,
//	// @RequestParam("password") String password)
//	public String registering(registrationDTO registrationObj) {
//
//		UserDetails userDetails = User.
//				withUsername(registrationObj.getUsername()).
//				password(registrationObj.getPassword()).
//				roles("user").build();
//
//		userDetailsManager.createUser(userDetails);
//
//		return "Registration successfully completed for the user: " + registrationObj.getUsername();
//	}

	@PostMapping("/addData")
	@ResponseBody
	public String addData() {
		return "addingData";
	}

}

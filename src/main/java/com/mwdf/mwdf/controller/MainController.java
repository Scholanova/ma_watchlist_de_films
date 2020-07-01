package com.mwdf.mwdf.controller;

import com.mwdf.mwdf.repository.UserRepository;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@EnableAutoConfiguration
public class MainController {

	private UserRepository userRepository;

	public MainController(UserRepository userRepository){
		this.userRepository = userRepository;
	}
//
//	@RequestMapping("/")
//	@ResponseBody
//	public String sayHello() {
//		return "Hello World!";
//	}

	@RequestMapping("/user")
	@ResponseBody
	public String getUser(){
		String user = userRepository.getUser();
		return "" +user;
	}
}

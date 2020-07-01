package com.mwdf.mwdf.controller;

import com.mwdf.mwdf.repository.UserRepository;
import com.mwdf.mwdf.service.UserService;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@EnableAutoConfiguration
public class MainController {

	private UserService userService;

	public MainController(UserService userService){
		this.userService = userService;
	}

	@RequestMapping("/")
	@ResponseBody
	public String sayHello() {
		return "Hello World!";
	}

	@RequestMapping("/user")
	@ResponseBody
	public String getUser(){
		String user = userService.getUser();
		return "Utilisateur avec l'id 1 : " +user;
	}
}

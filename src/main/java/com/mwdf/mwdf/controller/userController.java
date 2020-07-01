package com.mwdf.mwdf.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;


@Controller
public class userController
{
	@Autowired
	//UserRepository userRepo;
	
	@GetMapping("/inscription")
	public String inscription(/*User user*/)
	{
		return "inscription";
	}
	
	@GetMapping("/connexion")
	public String connexion(/*User user*/)
	{
		return "connexion";
	}
	
	//@PostMapping("/saveUser")
	@RequestMapping("/saveUser")
	@ResponseBody
	public String savePlayer(/*@Valid User user,BindingResult result,Model model*/)
	{
		return "redirection to saveUser ok";
	}
	
	//@PostMapping("/connectUser")
	@RequestMapping("/connectUser")
	@ResponseBody
	public String connectUser(/*@Valid User user,BindingResult result,Model model*/)
	{
		return "redirection to connectUser ok";
	}
}

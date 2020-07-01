package com.mwdf.mwdf.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

//import sn.domain.Player;
//import sn.persistence.PlayerRepository;

@Controller
public class userController
{
	@Autowired
	//UserRepository userRepo;
	
	@GetMapping("/inscription")
	public String inscription(/*User user*/)
	{
		return "inscription"; //le nom du template
	}
	
	@GetMapping("/connexion")
	public String connexion(/*User user*/)
	{
		return "connexion"; //le nom du template
	}
	
	/*@PostMapping("/saveUser")
	public String savePlayer(@Valid User user,BindingResult result,Model model)
	{
		userRepo.save(user);
		model.addAttribute("users", userRepo.findAll()); // c'est ça qui affiche tous les utilisateur
		return "index";
	}
	
	@GetMapping("/updatePlayer/{playerId}")
	public String updatePlayer(@PathVariable long playerId,Model model) throws Exception
	{	
		Player updatedPlayer=playerRepo.findById(playerId).orElseThrow(()->new Exception("player with ID "+playerId+"not found"));
		model.addAttribute("updatedPlayer",updatedPlayer);
		
		return "update-player";
	}
	
	@PostMapping("/saveUpdatedPlayer/{playerId}") 
	public String saveChanges(@PathVariable long playerId,@Valid Player player,BindingResult result,Model model) throws Exception
	{
		Player updatedPlayer=playerRepo.findById(playerId).orElseThrow(()->new Exception("player with ID "+playerId+"not found"));
		updatedPlayer.setTeam(player.getTeam());
		updatedPlayer.setFirstname(player.getFirstname());
		updatedPlayer.setSecondname(player.getSecondname());
		updatedPlayer.setNumber(player.getNumber());
		playerRepo.save(updatedPlayer);
		model.addAttribute("players", playerRepo.findAll());
		return "index";
	}
	
	@DeleteMapping("/deletePlayer/{playerId}")
	public String deletePlayer(@PathVariable long playerId,Model model) throws Exception
	{	
		Player deletedPlayer=playerRepo.findById(playerId).orElseThrow(()->new Exception("player with ID "+playerId+"not found"));
		playerRepo.delete(deletedPlayer);
		return "index";
	}*/
}
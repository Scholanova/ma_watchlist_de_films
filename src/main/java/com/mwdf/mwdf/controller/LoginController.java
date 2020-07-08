package com.mwdf.mwdf.controller;

import com.mwdf.mwdf.entity.Movie;
import com.mwdf.mwdf.services.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class LoginController {



    public LoginController() {
        super();
    }

    @GetMapping("/connexion")
    public ModelAndView loginGet() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();

        //si connecté
        if (!(auth instanceof AnonymousAuthenticationToken)) {
            return new ModelAndView("redirect:/");
        }
        return new ModelAndView("connexion/connexion");
    }

    // Login form with error
    @GetMapping("/connexion-error")
    public String loginError(Model model) {
        model.addAttribute("loginError", true);
        return "connexion/connexion";
    }
}
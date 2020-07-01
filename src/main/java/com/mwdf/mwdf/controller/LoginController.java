package com.mwdf.mwdf.controller;

import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class LoginController {

    @RequestMapping(value = "/connexion", method = RequestMethod.GET)
    public ModelAndView loginGet() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();

        if (!(auth instanceof AnonymousAuthenticationToken)) {
            return new ModelAndView("redirect:/");
        }
        return new ModelAndView("connexion/connexion");
    }

    // Login form with error
    @RequestMapping("/connexion-error")
    public String loginError(Model model) {
        model.addAttribute("loginError", true);
        return "connexion/connexion";
    }
}
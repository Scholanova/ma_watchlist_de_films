package com.mwdf.mwdf.controller;

import com.mwdf.mwdf.models.User;
import com.mwdf.mwdf.repositories.UserRepository;
import com.mwdf.mwdf.utils.RoleEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.Collections;

@Controller
@EnableAutoConfiguration
public class RegisterController {

    @Autowired
    private UserRepository userRepository;


    @RequestMapping(value = "/inscription", method = RequestMethod.GET)
    public ModelAndView getRegistred() {

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();

        if (!(auth instanceof AnonymousAuthenticationToken)) {
            return new ModelAndView("redirect:/");
        }

        return new ModelAndView("/connexion/inscription");
    }

    @RequestMapping(value = "/inscription-error", method = RequestMethod.GET)
    public String getRegistredWithError(Model model) {
        model.addAttribute("registrationError", true);

        return "connexion/inscription";
    }


    @RequestMapping(value = "/inscription", method = RequestMethod.POST)
    public ModelAndView postRegistred(@RequestParam("userName") String userName,
                                             @RequestParam("password") String password,
                                             @RequestParam("firstName") String firstName,
                                             @RequestParam("lastName") String lastName) {
        try {
            User user = new User(userName, password, firstName, lastName, Collections.singleton(RoleEnum.USER));
            userRepository.save(user);

            return new ModelAndView("redirect:" + "/connexion");

        } catch (Exception e) {
            return new ModelAndView("redirect:" + "/inscription-error");
        }
    }
}

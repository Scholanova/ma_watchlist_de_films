package com.mwdf.mwdf.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mwdf.mwdf.entity.Movie;
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
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.Collections;

@Controller
@EnableAutoConfiguration
public class UserController {

    @Autowired
    private UserRepository userRepository;

    @RequestMapping("/user/{username}")
    public String getUserInformation(@PathVariable("username") String username, Model model) {
        User userInfo = userRepository.findByUsername(username);

        model.addAttribute("userInfos", userInfo);
        return "information/user";
    }

}

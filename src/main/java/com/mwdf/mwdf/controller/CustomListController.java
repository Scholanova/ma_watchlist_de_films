package com.mwdf.mwdf.controller;

import com.mwdf.mwdf.models.CustomList;
import com.mwdf.mwdf.models.User;
import com.mwdf.mwdf.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class CustomListController {

    @Autowired
    private UserRepository userRepository;

    @PostMapping("/create_list")
    public ModelAndView postCreateList(@RequestParam("listTitle") String title) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (!(authentication instanceof AnonymousAuthenticationToken)) {
            String currentUserName = authentication.getName();

            User user = userRepository.findByUsername(currentUserName);
            CustomList list = new CustomList(title);

            list.getUsers().add(user);
            user.getLists().add(list);

            userRepository.save(user);

            return new ModelAndView("redirect:" + "/");
        }

        return new ModelAndView("redirect:" + "connexion/connexion");
    }

    @GetMapping("/mes_lists")
    public ModelAndView getMyLists(Model model) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (!(authentication instanceof AnonymousAuthenticationToken)) {
            String currentUserName = authentication.getName();

            User user = userRepository.findByUsername(currentUserName);

            model.addAttribute("lists", user.getLists());
            return new ModelAndView("lists/myLists");
        }

        return new ModelAndView("redirect:" + "connexion");
    }

    @GetMapping("/new_list")
    public ModelAndView getNewList(Model model) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (!(authentication instanceof AnonymousAuthenticationToken)) {

            return new ModelAndView("lists/newList");
        }

        return new ModelAndView("redirect:" + "connexion");
    }
}

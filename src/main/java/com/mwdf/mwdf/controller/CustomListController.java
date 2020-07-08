package com.mwdf.mwdf.controller;

import com.mwdf.mwdf.models.CustomList;
import com.mwdf.mwdf.models.User;
import com.mwdf.mwdf.repositories.CustomListRepository;
import com.mwdf.mwdf.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.Comparator;
import java.util.LinkedHashSet;
import java.util.Set;
import java.util.stream.Collectors;

@Controller
@EnableAutoConfiguration
public class CustomListController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private CustomListRepository customListRepository;

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

    @GetMapping("/mes_listes")
    public ModelAndView getMyLists(Model model) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (!(authentication instanceof AnonymousAuthenticationToken)) {
            String currentUserName = authentication.getName();

            User user = userRepository.findByUsername(currentUserName);

            Set<CustomList> lists = user.getLists();
            Set<CustomList> sortedList = lists.stream()
                    .sorted(Comparator.comparing(CustomList::getTitle)).collect(Collectors.toCollection(LinkedHashSet::new));

            model.addAttribute("lists", sortedList);

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

    @PostMapping("/delete_list")
    public ModelAndView deleteAList(@RequestParam("listId") long listId) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (!(authentication instanceof AnonymousAuthenticationToken)) {
            String currentUserName = authentication.getName();

            User currentUser = userRepository.findByUsername(currentUserName);
            CustomList custList = customListRepository.findByIdList(listId);

            currentUser.getLists().removeIf(cl -> cl.getIdList().equals(custList.getIdList()));
//            custList.getUsers().removeIf(u -> u.getIdUser().equals(currentUser.getIdUser())); need to be fixed, doesnt work
            custList.getUsers().clear();

            customListRepository.save(custList);

            return new ModelAndView("redirect:" + "/mes_listes");
        }

        return new ModelAndView("redirect:" + "connexion");
    }
}

package com.mwdf.mwdf.service;

import com.mwdf.mwdf.repository.UserRepository;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private UserRepository userRepository;

    public UserService(UserRepository userRepository){
        this.userRepository = userRepository;
    }

    public String getUser(){
        return userRepository.getUser();
    }

}

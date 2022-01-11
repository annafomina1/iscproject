package com.example.application.data.service;

import com.example.application.data.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.vaadin.artur.helpers.CrudService;

@Service
public class AuthService{

    public class AuthException extends Exception{

    }

    private UserRepository userRepository;

    public AuthService(@Autowired UserRepository userRepository){this.userRepository = userRepository;}
    public void authenticate(String username, String password) throws AuthException{
        User user = userRepository.getByUsername(username);
        if ((user != null) && user.checkPassword(password)){
        }
        else throw new AuthException();
    }
}

package com.unochapeco.dogssereleps.services;

import com.unochapeco.dogssereleps.models.User;
import com.unochapeco.dogssereleps.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements  UserService {

    private UserRepository userRepository;

    UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public User save(User user) {
        try {
            return userRepository.save(user);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public User findUserByEmail(String email) {
       try {
            return userRepository.findUserByEmail(email);
       } catch (Exception e) {
           e.printStackTrace();
       }
       return new User();
    }

    @Override
    public User findUserByEmailAndSenha(String email, String senha) {
        try {
            return userRepository.findUserByEmailAndSenha(email, senha);
        }catch (Exception e) {
            return null;
        }
    }

    @Override
    public User getCurrentUser() {
        try {
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            return (User)authentication.getPrincipal();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}

package com.unochapeco.dogssereleps.services;

import com.unochapeco.dogssereleps.models.User;

public interface UserService {
    public User save(User user);
    public User findUserByEmail(String email);
    public User findUserByEmailAndSenha(String email, String senha);
}

package com.unochapeco.dogssereleps.components;

import com.unochapeco.dogssereleps.models.User;
import com.unochapeco.dogssereleps.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class DataBaseProvider implements AuthenticationProvider {

    @Autowired
    private UserService userService;


    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        try {
            String email = authentication.getName().trim();
            String senha = authentication.getCredentials().toString().trim();

            Optional<User> op = null;
            op = Optional.of(userService.findUserByEmailAndSenha(email, senha));

            return new UsernamePasswordAuthenticationToken(op.get(), senha, null);
        } catch (Exception e) {
            return null;
        }
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return authentication.equals(UsernamePasswordAuthenticationToken.class);
    }
}

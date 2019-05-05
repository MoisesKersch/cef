//package com.mk.cep.components;
//
//import com.mk.cep.models.User;
//import com.mk.cep.services.UserService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.authentication.AuthenticationProvider;
//import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
//import org.springframework.security.core.Authentication;
//import org.springframework.security.core.AuthenticationException;
//import org.springframework.stereotype.Component;
//
//import java.util.Optional;
//
//@Component
//public class DataBaseProvider implements AuthenticationProvider {
//
//    @Autowired
//    private UserService userService;
//
//
//    @Override
//    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
//        try {
//            String email = authentication.getName().trim();
//            String senha = authentication.getCredentials().toString().trim();
//
//            Optional<User> op = null;
//            op = Optional.of(userService.findUserByEmailAndSenha(email, senha));
//
//            return new UsernamePasswordAuthenticationToken(op.get(), senha, null);
//        } catch (Exception e) {
//            return null;
//        }
//    }
//
//    @Override
//    public boolean supports(Class<?> authentication) {
//        return authentication.equals(UsernamePasswordAuthenticationToken.class);
//    }
//}

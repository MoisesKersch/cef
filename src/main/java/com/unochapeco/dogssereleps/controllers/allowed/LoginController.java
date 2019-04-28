package com.unochapeco.dogssereleps.controllers.allowed;

import com.unochapeco.dogssereleps.components.DataBaseProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import static org.springframework.security.web.context.HttpSessionSecurityContextRepository.SPRING_SECURITY_CONTEXT_KEY;

@Controller
@RequestMapping(value = "allowed")
public class LoginController {

    @Autowired
    private DataBaseProvider authProvider;

    @GetMapping(path = "login")
    public ModelAndView login(HttpServletRequest request) {
        ModelAndView modelAndView = new ModelAndView(request.getServletPath());
        return modelAndView;
    }

    @PostMapping(path = "manuallogin")
    public void login(HttpServletRequest req, String email, String senha) {
        UsernamePasswordAuthenticationToken authReq = new UsernamePasswordAuthenticationToken(email, senha);
        Authentication auth = authProvider.authenticate(authReq);

        SecurityContext sc = SecurityContextHolder.getContext();
        sc.setAuthentication(auth);
        HttpSession session = req.getSession(true);
        session.setAttribute(SPRING_SECURITY_CONTEXT_KEY, sc);
    }
}

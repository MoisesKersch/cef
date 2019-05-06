package com.mk.cef.controllers.allowed;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(value = "allowed")
public class LoginController {

//    @Autowired
//    private DataBaseProvider authProvider;

//    @GetMapping(path = "login")
//    public ModelAndView login(HttpServletRequest request) {
//        ModelAndView modelAndView = new ModelAndView(request.getServletPath());
//        return modelAndView;
//    }
//
//    @PostMapping(path = "manuallogin")
//    public void login(HttpServletRequest req, String email, String senha) {
//        UsernamePasswordAuthenticationToken authReq = new UsernamePasswordAuthenticationToken(email, senha);
//        Authentication auth = authProvider.authenticate(authReq);
//
//        SecurityContext sc = SecurityContextHolder.getContext();
//        sc.setAuthentication(auth);
//        HttpSession session = req.getSession(true);
//        session.setAttribute(SPRING_SECURITY_CONTEXT_KEY, sc);
//    }
}

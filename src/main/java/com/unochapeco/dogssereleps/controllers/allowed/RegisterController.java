package com.unochapeco.dogssereleps.controllers.allowed;

import com.unochapeco.dogssereleps.models.User;
import com.unochapeco.dogssereleps.services.UserService;
import com.unochapeco.dogssereleps.utils.PageUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping(value = "allowed")
public class RegisterController {

    @Autowired
    private UserService userService;

    @GetMapping(path = "register")
    public ModelAndView register(HttpServletRequest request) {
        PageUtil pageUtil = new PageUtil(request.getServletPath());
        pageUtil.setJs("register.js");
        return pageUtil.getModel();
    }

    @GetMapping(path = "save")
    public ResponseEntity<?> save(User user) {
        return ResponseEntity.ok(userService.save(user));
    }
}

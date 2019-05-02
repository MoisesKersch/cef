package com.unochapeco.dogssereleps.controllers.app;

import com.unochapeco.dogssereleps.models.User;
import com.unochapeco.dogssereleps.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.swing.text.html.parser.Entity;

@Controller
@RequestMapping(value = "app")
public class DashboardController {

    @Autowired
    UserService userService;

    @GetMapping(path = "dashboard")
    public ModelAndView dashboard(HttpServletRequest request) {
        ModelAndView modelAndView = new ModelAndView(request.getServletPath());
        modelAndView.addObject("js", "dashboard.js");
        return modelAndView;
    }

    @PostMapping(path = "updatelocation")
    public ResponseEntity<?> save(double latitude, double longitude) {
        User user = userService.getCurrentUser();
        user.setLatitude(latitude);
        user.setLongitude(longitude);
        return ResponseEntity.ok(this.userService.save(user));
    }

}

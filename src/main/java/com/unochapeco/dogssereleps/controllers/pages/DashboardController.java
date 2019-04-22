package com.unochapeco.dogssereleps.controllers.pages;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping(value = "des")
public class DashboardController {

    @GetMapping(value = "dashboard")
    public ModelAndView dashboard(HttpServletRequest request) {
        ModelAndView modelAndView = new ModelAndView(request.getServletPath());
        return modelAndView;
    }
}

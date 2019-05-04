package com.unochapeco.dogssereleps.controllers.app;

import com.unochapeco.dogssereleps.models.Animal;
import com.unochapeco.dogssereleps.services.AnimalService;
import com.unochapeco.dogssereleps.services.UserService;
import com.unochapeco.dogssereleps.utils.PageUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping(value = "app/conta")
public class ContaController {

    @Autowired
    private UserService userService;

    @GetMapping
    public ModelAndView register(HttpServletRequest request) {
        PageUtil pageUtil = new PageUtil(request.getServletPath() + "/conta");
        pageUtil.setJs("conta.js");
        pageUtil.setAttr("usuario", this.userService.getCurrentUser());
        return pageUtil.getModel();
    }

}

package com.unochapeco.dogssereleps.controllers.app;

import com.unochapeco.dogssereleps.models.Animal;
import com.unochapeco.dogssereleps.models.User;
import com.unochapeco.dogssereleps.services.AnimalService;
import com.unochapeco.dogssereleps.services.EnderecoService;
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
@RequestMapping(value = "app/animal")
public class AnimalController {

    @Autowired
    private AnimalService animalService;

    @Autowired
    private UserService userService;

    @GetMapping
    public ModelAndView register(HttpServletRequest request) {
        PageUtil pageUtil = new PageUtil(request.getServletPath() + "/animal");
        pageUtil.setJs("animal.js");
        pageUtil.setFormId("animal-form");
        return pageUtil.getModel();
    }

    @PostMapping
    public ResponseEntity<?> save(@RequestBody Animal animal) {
        animal.setUser(userService.getCurrentUser());
        return ResponseEntity.ok(animalService.save(animal));
    }

    @GetMapping("/list")
    public ModelAndView getList(HttpServletRequest request) {
        PageUtil pageUtil = new PageUtil(request.getServletPath());
        pageUtil.setJs("animal-list.js");
        pageUtil.setTableId("animal-table");
        return pageUtil.getModel();
    }

    @PostMapping("list")
    public ResponseEntity<?> postList(HttpServletRequest request) {
        return ResponseEntity.ok(this.animalService.findByUser(userService.getCurrentUser()));
    }
}

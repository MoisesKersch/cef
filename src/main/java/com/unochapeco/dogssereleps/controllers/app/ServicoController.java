package com.unochapeco.dogssereleps.controllers.app;

import com.unochapeco.dogssereleps.models.Servico;
import com.unochapeco.dogssereleps.repositories.ServicoRepository;
import com.unochapeco.dogssereleps.repositories.TipoServicoRepository;
import com.unochapeco.dogssereleps.services.AnimalService;
import com.unochapeco.dogssereleps.services.ServicoService;
import com.unochapeco.dogssereleps.services.UserService;
import com.unochapeco.dogssereleps.utils.PageUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.io.Serializable;

@Controller
@RequestMapping(value = "app/servico")
public class ServicoController implements Serializable {

    @Autowired
    private ServicoService servicoService;

    @Autowired
    private UserService userService;

    @Autowired
    private AnimalService animalService;

    @Autowired
    private TipoServicoRepository tipoServicoRepository;

    @GetMapping
    public ModelAndView register(HttpServletRequest request) {
        PageUtil pageUtil = new PageUtil(request.getServletPath() + "/servico");
        pageUtil.setAttr("user", userService.getCurrentUser().getId());
        pageUtil.setJs("servico.js");
        pageUtil.setFormId("servico-form");
        pageUtil.setAttr("tipoServicos", this.tipoServicoRepository.findAll());
        pageUtil.setAttr("animais", this.animalService.findByUser(this.userService.getCurrentUser()));
        return pageUtil.getModel();
    }

    @PostMapping
    public ResponseEntity<?> save(Servico servico, BindingResult bindingResult) {
        return ResponseEntity.ok(servicoService.save(servico));
    }

    @GetMapping("/list")
    public ModelAndView getList(HttpServletRequest request) {
        PageUtil pageUtil = new PageUtil(request.getServletPath());
        pageUtil.setJs("servico-list.js");
        pageUtil.setTableId("servico-table");
        return pageUtil.getModel();
    }

    @PostMapping("list")
    public ResponseEntity<?> postList(HttpServletRequest request) {
        return ResponseEntity.ok(this.servicoService.findByUser(userService.getCurrentUser()));
    }
}

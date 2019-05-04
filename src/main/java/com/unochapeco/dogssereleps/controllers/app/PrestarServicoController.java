package com.unochapeco.dogssereleps.controllers.app;

import com.unochapeco.dogssereleps.services.ServicoService;
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
@RequestMapping(value = "app/prestar")
public class PrestarServicoController {

    @Autowired
    private UserService userService;

    @Autowired
    private ServicoService servicoService;


    @GetMapping
    public ModelAndView register(HttpServletRequest request) {
        PageUtil pageUtil = new PageUtil(request.getServletPath() + "/prestar");
        pageUtil.setJs("prestar.js");

        pageUtil.setAttr("servicos", this.servicoService.findByStatusNovo(this.userService.getCurrentUser()));

        return pageUtil.getModel();
    }

//    @PostMapping
//    public ResponseEntity<?> save(@RequestBody PrestarServico prestarservico) {
//        prestarservico.setUser(userService.getCurrentUser());
//        return ResponseEntity.ok(prestarservicoService.save(prestarservico));
//    }
//
//    @GetMapping("/list")
//    public ModelAndView getList(HttpServletRequest request) {
//        PageUtil pageUtil = new PageUtil(request.getServletPath());
//        pageUtil.setJs("prestarservico-list.js");
//        pageUtil.setTableId("prestarservico-table");
//        return pageUtil.getModel();
//    }
//
//    @PostMapping("list")
//    public ResponseEntity<?> postList(HttpServletRequest request) {
//        return ResponseEntity.ok(this.prestarservicoService.findByUser(userService.getCurrentUser()));
//    }
}

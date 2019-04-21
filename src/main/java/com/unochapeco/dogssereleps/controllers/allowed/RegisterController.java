package com.unochapeco.dogssereleps.controllers.allowed;

import com.unochapeco.dogssereleps.models.Endereco;
import com.unochapeco.dogssereleps.models.User;
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
@RequestMapping(value = "allowed")
public class RegisterController {

    @Autowired
    private UserService userService;

    @Autowired
    private EnderecoService enderecoService;

    @GetMapping(path = "register")
    public ModelAndView register(HttpServletRequest request) {
        PageUtil pageUtil = new PageUtil(request.getServletPath());
        pageUtil.setJs("register.js");
        pageUtil.setFormId("register-form");
        return pageUtil.getModel();
    }

    @PostMapping(path = "save")
    public ResponseEntity<?> save(@RequestBody User user) {
        return ResponseEntity.ok(userService.save(user));
    }

    @PostMapping(value = "/findbycidadenome")
    public ResponseEntity<?> findByCidadeNome(String cidadeNome) {
        return ResponseEntity.ok(enderecoService.findByCidadeNome(cidadeNome));
    }

    @PostMapping(value = "/findbycidadenomeanduf")
    public ResponseEntity<?> findByCidadeNomeAndUf(String cidadeNome, String uf) {
        return ResponseEntity.ok(enderecoService.findByCidadeNomeAndUf(cidadeNome, uf));
    }
}

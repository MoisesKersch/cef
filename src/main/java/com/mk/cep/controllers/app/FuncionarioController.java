package com.mk.cep.controllers.app;

import com.mk.cep.models.Funcionario;
import com.mk.cep.repositories.FuncionarioRepository;
import com.mk.cep.services.FuncionarioService;
import com.mk.cep.utils.PageUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.Optional;

@Controller
@RequestMapping(value = "app/funcionario")
public class FuncionarioController {

    @Autowired
    private FuncionarioRepository funcionarioRepository;

    @Autowired
    private FuncionarioService funcionarioService;

    @GetMapping
    public ModelAndView register(HttpServletRequest request) {
        PageUtil pageUtil = new PageUtil(request.getServletPath() + "/funcionario");
        pageUtil.setJs("funcionario.js");
        pageUtil.setFormId("funcionario-form");
        return pageUtil.getModel();
    }

    @GetMapping("/list")
    public ModelAndView getList(HttpServletRequest request) {
        PageUtil pageUtil = new PageUtil(request.getServletPath());
        pageUtil.setJs("funcionario-list.js");
        pageUtil.setTableId("funcionario-table");
        return pageUtil.getModel();
    }

    @PostMapping("list")
    public ResponseEntity<?> postList(HttpServletRequest request) {
        return ResponseEntity.ok(this.funcionarioRepository.findAll());
    }

    @PostMapping
    public ResponseEntity<?> save(Funcionario servico, BindingResult bindingResult) {
        return ResponseEntity.ok(this.funcionarioService.save(servico));
    }

    @GetMapping(value = "/iscpf")
    public  ResponseEntity<?> isCpf(String cpf, Long id) throws Exception {
        if (id != null) {
            if (this.funcionarioRepository.findById(id).isPresent()) {
                return ResponseEntity.ok(false);
            }
        }
        Boolean t = false;
        t = this.funcionarioRepository.findByCpf(cpf).isPresent();
        return ResponseEntity.ok(t);
    }
}

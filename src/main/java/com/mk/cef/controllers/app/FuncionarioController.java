package com.mk.cef.controllers.app;

import com.mk.cef.models.Funcionario;
import com.mk.cef.repositories.FuncionarioRepository;
import com.mk.cef.utils.PageUtil;
import com.mk.cef.services.FuncionarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
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
    public ModelAndView register(HttpServletRequest request, Long id) {
        PageUtil pageUtil = new PageUtil(request.getServletPath() + "/funcionario");
        pageUtil.setJs("funcionario.js");
        Funcionario obj = this.funcionarioRepository.findById(Optional.ofNullable(id).orElse(new Long("0"))).orElse( new Funcionario() );
        pageUtil.setFormId("funcionario-form");
        pageUtil.setAttr("obj", obj);
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
            Funcionario funcionario = this.funcionarioRepository.findById(id).orElse(null);
            if (funcionario.getCpf().equals(cpf))
                return  ResponseEntity.ok(false);
        }
        Boolean t = false;
        t = this.funcionarioRepository.findByCpf(cpf).isPresent();
        return ResponseEntity.ok(t);
    }

    @DeleteMapping( "delete/{id}")
    public ResponseEntity<?> save(@PathVariable("id") Long id) {
        return ResponseEntity.ok(this.funcionarioService.deleteById(id));
    }
}

package com.mk.cef.controllers.app;

import com.mk.cef.models.Parcela;
import com.mk.cef.repositories.EmprestimoRepository;
import com.mk.cef.repositories.ParcelaRepository;
import com.mk.cef.services.EmprestimoService;
import com.mk.cef.utils.PageUtil;
import com.mk.cef.models.Emprestimo;
import com.mk.cef.repositories.FuncionarioRepository;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping(value = "app/emprestimo")
public class EmprestimoController {

    @Autowired
    private ParcelaRepository parcelaRepository;

    @Autowired
    private FuncionarioRepository funcionarioRepository;

    @Autowired
    private EmprestimoService emprestimoService;

    @GetMapping
    public ModelAndView register(HttpServletRequest request, Long id) {
        PageUtil pageUtil = new PageUtil(request.getServletPath() + "/emprestimo");
        pageUtil.setJs("emprestimo.js");
        Emprestimo obj = new Emprestimo();

        pageUtil.setFormId("emprestimo-form");
        pageUtil.setTableId("emprestimo-table");
        pageUtil.setAttr("obj", obj);
        pageUtil.setAttr("funcionarios", this.funcionarioRepository.findAll());
        return pageUtil.getModel();
    }

    @GetMapping("/list")
    public ModelAndView getList(HttpServletRequest request) {
        PageUtil pageUtil = new PageUtil(request.getServletPath());
        pageUtil.setJs("emprestimo-list.js");
        pageUtil.setTableId("emprestimo-table");
        pageUtil.setFormId("emprestimo-form");
        return pageUtil.getModel();
    }

    @PostMapping("list")
    public ResponseEntity<?> postList(HttpServletRequest request, Date dataInicial, Date dataFinal) {
        return ResponseEntity.ok(this.parcelaRepository.findByDataInicialAndDataFinal(dataInicial, dataFinal));
    }

    @PostMapping("detalhes")
    public ResponseEntity<?> postDetalhes(Emprestimo emprestimo, BindingResult bindingResult) {
        return ResponseEntity.ok(this.emprestimoService.gerarDetalhes(emprestimo));
    }

    @PostMapping
    public ResponseEntity<?> save(Emprestimo emprestimo, BindingResult bindingResult) {
        return ResponseEntity.ok(this.emprestimoService.save((List<Parcela>) this.emprestimoService.gerarDetalhes(emprestimo)));
    }

    @GetMapping("/isfuncionario")
    public ResponseEntity<?> isFuncionario(Long id) {
        return ResponseEntity.ok(this.parcelaRepository.findByFuncionarioId(Optional.ofNullable(id).orElse(Long.valueOf(0))).isPresent());
    }

    @PostMapping(value = "baixa")
    public ResponseEntity<?> baixa(Long id) {
        try {
            this.parcelaRepository.findById(id).ifPresent(parcela -> {
                parcela.setValorPago(parcela.getValor());
                this.parcelaRepository.save(parcela);
            });
        } catch (Exception e) {
            return ResponseEntity.ok(false);
        }
        return ResponseEntity.ok(true);
    }
}

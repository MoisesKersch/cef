package com.mk.cep.controllers.allowed;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(value = "allowed")
public class RegisterController {

//    @Autowired
//    private UserService userService;
//
//    @Autowired
//    private EnderecoService enderecoService;
//
//    @GetMapping(path = "register")
//    public ModelAndView register(HttpServletRequest request) {
//        PageUtil pageUtil = new PageUtil(request.getServletPath());
//        pageUtil.setJs("register.js");
//        pageUtil.setFormId("register-form");
//        return pageUtil.getModel();
//    }
//
//    @PostMapping(path = "save")
//    public ResponseEntity<?> save(@RequestBody User user) {
//        return ResponseEntity.ok(userService.save(user));
//    }
//
//    @PostMapping(value = "/findbycidadenome")
//    public ResponseEntity<?> findByCidadeNome(String cidadeNome) {
//        return ResponseEntity.ok(enderecoService.findByCidadeNome(cidadeNome));
//    }
//
//    @PostMapping(value = "/findbycidadenomeanduf")
//    public ResponseEntity<?> findByCidadeNomeAndUf(String cidadeNome, String uf) {
//        return ResponseEntity.ok(enderecoService.findByCidadeNomeAndUf(cidadeNome, uf));
//    }
//
//    @GetMapping(value = "/isemail")
//    public ResponseEntity<?> findByCidadeNomeAndUf(String email) {
//        return ResponseEntity.ok(userService.findUserByEmail(email));
//    }


}

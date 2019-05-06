package com.mk.cef.services;

import com.mk.cef.models.Funcionario;

public interface FuncionarioService {
    Funcionario save(Funcionario funcionario);
    Object deleteById(Long id);
}

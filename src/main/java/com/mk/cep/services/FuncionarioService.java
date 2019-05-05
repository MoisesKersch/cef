package com.mk.cep.services;

import com.mk.cep.models.Funcionario;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FuncionarioService {
    Funcionario save(Funcionario funcionario);
}

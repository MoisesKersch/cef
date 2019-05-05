package com.mk.cep.services;

import com.mk.cep.models.Funcionario;
import com.mk.cep.repositories.FuncionarioRepository;
import org.springframework.stereotype.Service;

@Service
public class FuncionarioServiceImpl implements FuncionarioService {

    private FuncionarioRepository funcionarioRepository;

    FuncionarioServiceImpl(FuncionarioRepository funcionarioRepository) {
        this.funcionarioRepository = funcionarioRepository;
    }

    @Override
    public Funcionario save(Funcionario funcionario) {
        try {
            return this.funcionarioRepository.save(funcionario);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}

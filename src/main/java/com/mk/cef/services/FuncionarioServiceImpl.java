package com.mk.cef.services;

import com.mk.cef.models.Funcionario;
import com.mk.cef.repositories.FuncionarioRepository;
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

    @Override
    public Object deleteById(Long id) {
        try {
            this.funcionarioRepository.deleteById(id);
        } catch (Exception e) {
            return e.getLocalizedMessage();
        }
        return true;
    }
}

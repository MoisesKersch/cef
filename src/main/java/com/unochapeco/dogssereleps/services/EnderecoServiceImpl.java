package com.unochapeco.dogssereleps.services;

import com.unochapeco.dogssereleps.models.Endereco;
import com.unochapeco.dogssereleps.models.User;
import com.unochapeco.dogssereleps.repositories.EnderecoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EnderecoServiceImpl implements EnderecoService {

    @Autowired
    private EnderecoRepository enderecoRepository;

    @Override
    public List<Endereco> findByCidadeNome(String cidadeNome) {
        try {
            return enderecoRepository.findByCidadeNome(cidadeNome);
        } catch (Exception e) {
            return null;
        }
    }

    @Override
    public Endereco findByCidadeNomeAndUf(String cidadeNome, String uf) {
        try {
            return enderecoRepository.findByCidadeNomeAndUf(cidadeNome, uf);
        } catch (Exception e) {
            return null;
        }
    }

    @Override
    public Endereco findByCidadeId(String cidadeId) {
        try {
            return enderecoRepository.findByCidadeId(cidadeId);
        } catch (Exception e) {
            return null;
        }
    }
}

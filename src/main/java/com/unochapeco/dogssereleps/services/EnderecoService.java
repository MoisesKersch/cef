package com.unochapeco.dogssereleps.services;

import com.unochapeco.dogssereleps.models.Endereco;
import com.unochapeco.dogssereleps.models.User;

import java.util.List;


public interface EnderecoService {
    List<Endereco> findByCidadeNome(String cidadeNome);
    Endereco findByCidadeNomeAndUf(String cidadeNome, String uf);
    Endereco findByCidadeId(String cidadeId);
}

package com.unochapeco.dogssereleps.repositories;

import com.unochapeco.dogssereleps.models.Endereco;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface EnderecoCustomRepository {
    List<Endereco> findByCidadeNome(String cidadeNome);
    Endereco findByCidadeNomeAndUf(String cidadeNome, String uf);
    Endereco findByCidadeId(String cidadeId);
}

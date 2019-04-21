package com.unochapeco.dogssereleps.repositories;

import com.unochapeco.dogssereleps.models.Endereco;
import com.unochapeco.dogssereleps.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EnderecoRepository extends JpaRepository<Endereco, String>, EnderecoCustomRepository {

}

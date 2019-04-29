package com.unochapeco.dogssereleps.repositories;

import com.unochapeco.dogssereleps.models.Animal;
import com.unochapeco.dogssereleps.models.Servico;
import com.unochapeco.dogssereleps.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ServicoRepository extends JpaRepository<Servico, Long> {
    public List<Servico> findByUser(User user);
    public List<Servico> findByStatusAndUserNot(String status, User user);
}


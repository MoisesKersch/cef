package com.unochapeco.dogssereleps.services;

import com.unochapeco.dogssereleps.models.Servico;
import com.unochapeco.dogssereleps.models.User;

import java.util.List;

public interface ServicoService {
    public Servico save(Servico servico);
    public List<Servico> findByUser(User user);
    public List<Servico> findByStatusNovo(User user);
}

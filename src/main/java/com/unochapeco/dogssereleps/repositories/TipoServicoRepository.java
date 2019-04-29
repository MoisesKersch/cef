package com.unochapeco.dogssereleps.repositories;

import com.unochapeco.dogssereleps.models.Servico;
import com.unochapeco.dogssereleps.models.TipoServico;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TipoServicoRepository extends JpaRepository<TipoServico, Long> {
}


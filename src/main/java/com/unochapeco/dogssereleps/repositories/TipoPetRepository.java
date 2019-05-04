package com.unochapeco.dogssereleps.repositories;

import com.unochapeco.dogssereleps.models.Animal;
import com.unochapeco.dogssereleps.models.TipoPet;
import com.unochapeco.dogssereleps.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TipoPetRepository extends JpaRepository<TipoPet, Long> {

}

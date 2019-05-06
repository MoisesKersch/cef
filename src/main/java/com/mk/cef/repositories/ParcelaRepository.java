package com.mk.cef.repositories;

import com.mk.cef.models.Emprestimo;
import com.mk.cef.models.Funcionario;
import com.mk.cef.models.Parcela;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Date;
import java.util.List;
import java.util.Optional;

public interface ParcelaRepository extends JpaRepository<Parcela, Long> {

    @Query("SELECT obj FROM Parcela obj WHERE obj.funcionario.id = :id")
    Optional<List<Parcela>> findByFuncionarioId(@Param("id") Long id);

    @Query("SELECT obj FROM Parcela obj WHERE obj.vencimento BETWEEN :dataInicial AND :dataFinal")
    Optional<List<Parcela>> findByDataInicialAndDataFinal(@Param("dataInicial") Date dataInicial, @Param("dataFinal") Date dataFinal);

}

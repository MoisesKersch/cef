package com.unochapeco.dogssereleps.repositories;

import com.unochapeco.dogssereleps.models.Endereco;
import org.springframework.data.jpa.repository.JpaRepository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;

public class EnderecoRepositoryImpl implements EnderecoCustomRepository {

    @PersistenceContext
    private EntityManager em;

    @SuppressWarnings("unchecked")
    @Override
    public List<Endereco> findByCidadeNome(String cidadeNome) {
        cidadeNome = cidadeNome.trim();
        Query q = em.createNativeQuery(findByCidadeNomeQuery(), Endereco.class);
        q.setParameter("cidadeNome", "%" + cidadeNome + "%");
        q.setParameter("cidadeNome1", "% " + cidadeNome);
        return q.getResultList();
    }

    @Override
    public Endereco findByCidadeNomeAndUf(String cidadeNome, String uf) {
        cidadeNome = cidadeNome.trim();
        uf = uf.trim();
        Query q = em.createNativeQuery(findByCidadeNomeAndUfQuery(), Endereco.class);
        q.setParameter("cidadeNome", cidadeNome);
        q.setParameter("uf", uf);
        return (Endereco) q.getSingleResult();
    }

    private String findByCidadeNomeQuery() {
        return " select row_number() over() as id, e.id as estado_id, c.id as cidade_id, e.codigo as estado_codigo, e.nome as estado_nome, c.nome as cidade_nome "
                + " from estado e join cidade c  " + " on e.id = c.estado_id "
                + " where (  unaccent(c.nome) ilike unaccent(:cidadeNome) or unaccent(c.nome) ilike unaccent(:cidadeNome1) ) limit 20 ";
    }

    private String findByCidadeNomeAndUfQuery() {
        return " select row_number() over() as id, e.id as estado_id, c.id as cidade_id, e.codigo as estado_codigo, e.nome as estado_nome, c.nome as cidade_nome "
                + " from estado e join cidade c " + " on e.id = c.estado_id "
                + " where upper(unaccent(c.nome)) = upper(unaccent(:cidadeNome)) and unaccent(e.codigo) = upper(:uf) ";
    }

    private String findByCidadeIdQuery() {
        return " select row_number() over() as id, e.id as estado_id, c.id as cidade_id, e.codigo as estado_codigo, e.nome as estado_nome, c.nome as cidade_nome "
                + " from estado e join cidade c " + " on e.id = c.estado_id "
                + " where c.id = :cidadeId";
    }

    @Override
    public Endereco findByCidadeId(String cidadeId) {
        cidadeId = cidadeId.trim();
        Query q = em.createNativeQuery(findByCidadeIdQuery(), Endereco.class);
        q.setParameter("cidadeId", cidadeId);
        return (Endereco) q.getSingleResult();
    }

}

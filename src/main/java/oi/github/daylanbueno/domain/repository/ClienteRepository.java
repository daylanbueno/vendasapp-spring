package oi.github.daylanbueno.domain.repository;

import oi.github.daylanbueno.domain.entity.Cliente;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;

@Repository
public class ClienteRepository {

    private final EntityManager entityManager;

    public ClienteRepository(EntityManager entityManager) {
        this.entityManager = entityManager;
    }


    @Transactional
    public Cliente salvar(Cliente cliente) {
        entityManager.persist(cliente);
        return cliente;
    }

}

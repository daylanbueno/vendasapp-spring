package oi.github.daylanbueno.domain.repository;

import oi.github.daylanbueno.domain.entity.Cliente;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.util.List;

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

    @Transactional
    public Cliente atualizarCliente(Cliente cliente) {
        entityManager.merge(cliente);
        return cliente;
    }

    @Transactional
    public void deleteCliente(Integer id) {
        Cliente cliente = entityManager.find(Cliente.class, id);
        entityManager.remove(cliente);
    }

    @Transactional(readOnly = true)
    public List<Cliente> recuperaPorNome(String nome) {
        String jpql = " Select c from Cliente c where c.nome like :nome ";
        TypedQuery<Cliente> query = entityManager.createQuery(jpql, Cliente.class);
         query.setParameter("nome", "%"+nome+"%");
        return query.getResultList();
    }


    @Transactional(readOnly = true)
    public List<Cliente> recuperaTodos() {
        String jpql = "select c from Cliente c";
        return entityManager
                .createQuery(jpql, Cliente.class)
                .getResultList();
    }

}

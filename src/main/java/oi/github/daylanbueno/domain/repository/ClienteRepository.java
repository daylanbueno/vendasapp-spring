package oi.github.daylanbueno.domain.repository;

import oi.github.daylanbueno.domain.entity.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ClienteRepository  extends JpaRepository<Cliente, Integer> {

    List<Cliente> findByNomeLike(String amanda);

    @Query("select c from Cliente c where c.nome like %:nome%")
    List<Cliente> obterPorNome(@Param("nome") String nome);

    @Query(value = " select * from cliente c where c.nome like %:nome% ",nativeQuery = true)
    List<Cliente> obterPorNomeSqlNativo(@Param("nome") String nome);

    Boolean existsByNome(String nome);

    @Query("select c from Cliente c left join fetch c.pedidos  where c.id = :id ")
    Cliente findClienteFechPedidos(@Param("id") Integer id);
}

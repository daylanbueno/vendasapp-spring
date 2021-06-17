package oi.github.daylanbueno.repository;

import oi.github.daylanbueno.domain.entity.Cliente;
import oi.github.daylanbueno.domain.entity.Pedido;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface PedidoRepository extends JpaRepository<Pedido, Integer> {

    List<Pedido> findByCliente(Cliente marcos);

    @Query(" select p from Pedido p left join fetch p.itesPedidos where p.id = :id ")
    Optional<Pedido> findByidFetchItens(@Param("id") Integer id);
}

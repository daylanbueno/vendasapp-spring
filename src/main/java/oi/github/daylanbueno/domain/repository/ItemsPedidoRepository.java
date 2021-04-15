package oi.github.daylanbueno.domain.repository;

import oi.github.daylanbueno.domain.entity.ItemPedido;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ItemsPedidoRepository extends JpaRepository<ItemPedido,Integer> {
}

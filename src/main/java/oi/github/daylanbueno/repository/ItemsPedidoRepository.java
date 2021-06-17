package oi.github.daylanbueno.repository;

import oi.github.daylanbueno.domain.entity.ItemPedido;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ItemsPedidoRepository extends JpaRepository<ItemPedido,Integer> {
}

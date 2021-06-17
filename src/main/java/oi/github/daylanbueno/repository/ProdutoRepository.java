package oi.github.daylanbueno.repository;

import oi.github.daylanbueno.domain.entity.Produto;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProdutoRepository extends JpaRepository<Produto, Integer> {

}

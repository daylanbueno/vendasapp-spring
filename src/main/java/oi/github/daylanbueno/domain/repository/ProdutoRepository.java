package oi.github.daylanbueno.domain.repository;

import oi.github.daylanbueno.domain.entity.Produto;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProdutoRepository extends JpaRepository<Produto, Integer> {

}

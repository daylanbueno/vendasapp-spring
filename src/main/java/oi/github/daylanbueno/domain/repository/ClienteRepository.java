package oi.github.daylanbueno.domain.repository;

import oi.github.daylanbueno.domain.entity.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ClienteRepository  extends JpaRepository<Cliente, Integer> {
    List<Cliente> findByNomeLike(String amanda);
}

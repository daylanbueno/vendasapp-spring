package oi.github.daylanbueno.domain.service.impl;

import oi.github.daylanbueno.domain.dto.ClienteDto;
import oi.github.daylanbueno.domain.entity.Cliente;

import java.util.List;

public interface ClienteService {
    ClienteDto obterClientePorId(Integer id);

    ClienteDto save(ClienteDto clienteDto);

    void deletar(Integer id);

    ClienteDto alterar(ClienteDto clienteDto);

    List<ClienteDto> obterClientesPorFiltro(ClienteDto filtro);
}

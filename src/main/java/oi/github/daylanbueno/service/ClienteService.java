package oi.github.daylanbueno.service;

import oi.github.daylanbueno.domain.dto.ClienteDto;

import java.util.List;

public interface ClienteService {
    ClienteDto obterClientePorId(Integer id);

    ClienteDto save(ClienteDto clienteDto);

    void deletar(Integer id);

    ClienteDto alterar(ClienteDto clienteDto);

    List<ClienteDto> obterClientesPorFiltro(ClienteDto filtro);
}

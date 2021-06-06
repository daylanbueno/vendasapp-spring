package oi.github.daylanbueno.domain.service.impl;

import lombok.RequiredArgsConstructor;
import oi.github.daylanbueno.domain.dto.ClienteDto;
import oi.github.daylanbueno.domain.entity.Cliente;
import oi.github.daylanbueno.domain.repository.ClienteRepository;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
@RequiredArgsConstructor
public class clienteServiceImpl implements  ClienteService {

    private final ClienteRepository clienteRepository;
    private final ModelMapper modelMapper;

    @Override
    public ClienteDto obterClientePorId(Integer id) {
        Cliente cliente = clienteRepository
                .findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Cliente não encontrado!"));
        return modelMapper.map(cliente, ClienteDto.class);
    }
}

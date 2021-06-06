package oi.github.daylanbueno.domain.controller;

import lombok.RequiredArgsConstructor;
import oi.github.daylanbueno.domain.dto.ClienteDto;
import oi.github.daylanbueno.domain.entity.Cliente;
import oi.github.daylanbueno.domain.repository.ClienteRepository;
import oi.github.daylanbueno.domain.service.impl.ClienteService;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/clientes")
@RequiredArgsConstructor
public class ClienteController {

    private final ClienteRepository clienteRepository;
    private final ClienteService clienteService;

    @GetMapping("/{id}")
    public ClienteDto obterClientePorId(@PathVariable Integer id) {
        return clienteService.obterClientePorId(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ClienteDto salvarCliente(@RequestBody @Valid ClienteDto clienteDto) {
        return clienteService.save(clienteDto);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteCliente(@PathVariable Integer id) {
        clienteService.deletar(id);
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public ClienteDto update(@PathVariable Integer id, @RequestBody @Valid ClienteDto clienteDto) {
            clienteDto.setId(id);
           return clienteService.alterar(clienteDto);
    }

    @GetMapping
    public List<ClienteDto> find(ClienteDto filtro) {
       return clienteService.obterClientesPorFiltro(filtro);
    }
}

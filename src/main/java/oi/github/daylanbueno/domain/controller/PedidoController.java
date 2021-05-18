package oi.github.daylanbueno.domain.controller;

import oi.github.daylanbueno.domain.entity.Pedido;
import oi.github.daylanbueno.domain.repository.PedidoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("/api/pedidos")
public class PedidoController {

    @Autowired
    private PedidoRepository pedidoRepository;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Pedido salvar(@RequestBody Pedido pedido) {
        return pedidoRepository.save(pedido);
    }

    @PutMapping("/{id}")
    public void altera(@PathVariable Integer id,  @RequestBody Pedido pedido) {
        pedidoRepository
                .findById(id)
                .map(pedidoAtual -> {
                    pedido.setId(id);
                    return pedidoRepository.save(pedido);
                }).orElseThrow(() -> new  ResponseStatusException(HttpStatus.NOT_FOUND, "Pedido não encontrado!"));
    }

    @GetMapping("/{id}")
    public Pedido buscarPorId(@PathVariable Integer id) {
        return pedidoRepository
                .findById(id)
                    .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Pedido não encontrado!"));
    }

    @GetMapping
    public List<Pedido> buscarPorFiltro(Pedido filtro) {
        ExampleMatcher matcher = ExampleMatcher.matching()
                .withIgnoreCase()
                .withStringMatcher(ExampleMatcher.StringMatcher.CONTAINING);
        Example example = Example.of(filtro, matcher);
        return pedidoRepository.findAll(example);
    }
}

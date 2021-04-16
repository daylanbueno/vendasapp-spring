package oi.github.daylanbueno.domain.controller;

import oi.github.daylanbueno.domain.entity.Cliente;
import oi.github.daylanbueno.domain.repository.ClienteRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@RequestMapping("/api/clientes")
public class ClienteController {

    private final ClienteRepository clienteRepository;

    public ClienteController(ClienteRepository clienteRepository) {
        this.clienteRepository = clienteRepository;
    }

    @GetMapping("/{id}")
    @ResponseBody
    public ResponseEntity<Cliente> obterClientePorId(@PathVariable Integer id) {
        Optional<Cliente> clienteOptional = clienteRepository.findById(id);
        if(clienteOptional.isPresent()) {
            return ResponseEntity.ok(clienteOptional.get());
        }
        return ResponseEntity.notFound().build();
    }

    @PostMapping
    @ResponseBody
    public ResponseEntity<Cliente> salvarCliente(@RequestBody Cliente cliente) {
        return ResponseEntity.ok(clienteRepository.save(cliente));
    }
}

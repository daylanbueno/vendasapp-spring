package oi.github.daylanbueno.domain.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/clientes")
public class ClienteController {

    @GetMapping("/hello/{nome}")
    @ResponseBody
    public String helloCliente(@PathVariable("nome") String nome) {
        return String.format("hello %s", nome);
    }
}

package oi.github.daylanbueno.domain.controller;

import lombok.RequiredArgsConstructor;
import oi.github.daylanbueno.domain.dto.LoginDto;
import oi.github.daylanbueno.domain.entity.Usuario;
import oi.github.daylanbueno.domain.service.impl.UsuarioServiceImpl;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/usuarios")
@RequiredArgsConstructor
public class UsuarioController {

    private final UsuarioServiceImpl usuarioService;

    @PostMapping
    public Usuario salvar(@RequestBody @Valid Usuario usuario) {
         return usuarioService.salvar(usuario);
    }


    @PostMapping("/auth")
    public String auth(@RequestBody @Valid LoginDto user) {
        return usuarioService.login(user);
    }
}

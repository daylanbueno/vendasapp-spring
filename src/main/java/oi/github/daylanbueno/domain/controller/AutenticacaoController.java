package oi.github.daylanbueno.domain.controller;

import lombok.RequiredArgsConstructor;
import oi.github.daylanbueno.domain.entity.Usuario;
import oi.github.daylanbueno.domain.service.impl.UsuarioServiceImpl;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/autenticacao")
@RequiredArgsConstructor
public class AutenticacaoController {

    private final UsuarioServiceImpl usuarioService;

    @PostMapping("/login")
    public String login(@RequestBody Usuario usuario) {
      return usuarioService.login(usuario);
    }

}

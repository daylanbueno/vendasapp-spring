package oi.github.daylanbueno.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import lombok.RequiredArgsConstructor;
import oi.github.daylanbueno.domain.dto.LoginDto;
import oi.github.daylanbueno.domain.dto.UsuarioDto;
import oi.github.daylanbueno.service.impl.UsuarioServiceImpl;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/usuarios")
@RequiredArgsConstructor
@Api("Api de usuários")
public class UsuarioController {

    private final UsuarioServiceImpl usuarioService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @ApiOperation("salvar novo usuário")
    @ApiResponse(code = 201, message = "usuário incluido com sucesso")
    public UsuarioDto salvar(@RequestBody @Valid UsuarioDto usuario) {
         return usuarioService.salvar(usuario);
    }

    @PostMapping("/auth")
    @ApiOperation("fazer login")
    @ApiResponse(code = 200, message = "devolve o token jwt")
    public String auth(@RequestBody @Valid LoginDto user) {
        return usuarioService.login(user);
    }
}

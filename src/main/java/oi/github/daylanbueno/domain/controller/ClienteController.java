package oi.github.daylanbueno.domain.controller;

import io.swagger.annotations.*;
import lombok.RequiredArgsConstructor;
import oi.github.daylanbueno.domain.dto.ClienteDto;
import oi.github.daylanbueno.domain.service.ClienteService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/clientes")
@RequiredArgsConstructor
@Api("Api clientes")
public class ClienteController {
    private final ClienteService clienteService;

    @GetMapping("/{id}")
    @ApiOperation("obter um cliente pelo ID")
    @ApiResponses({
            @ApiResponse( code = 200, message = "Retorna um cliente"),
            @ApiResponse( code = 404, message = "Cliente não encontrado")})
    public ClienteDto obterClientePorId(@PathVariable @ApiParam("ID do cliente")  Integer id) {
        return clienteService.obterClientePorId(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @ApiOperation("Salvar cliente")
    @ApiResponse( code = 201, message = "Cliente salvo com sucesso")
    public ClienteDto salvarCliente(@RequestBody @Valid ClienteDto clienteDto) {
        return clienteService.save(clienteDto);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @ApiOperation("Deletar cliente")
    @ApiResponse(code = 204, message = "Cliente apagado com sucesso")
    public void deleteCliente(@PathVariable  @ApiParam("ID do cliente") Integer id) {
        clienteService.deletar(id);
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @ApiOperation("Alterar cliente")
    @ApiResponse(code = 204, message = "Cliente alterado com sucesso")
    public ClienteDto update(@PathVariable @ApiParam("ID do cliente") Integer id, @RequestBody @Valid ClienteDto clienteDto) {
            clienteDto.setId(id);
           return clienteService.alterar(clienteDto);
    }

    @GetMapping
    @ApiOperation("Obter clientes por filtros")
    public List<ClienteDto> find(ClienteDto filtro) {
       return clienteService.obterClientesPorFiltro(filtro);
    }
}

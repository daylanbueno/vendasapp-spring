package oi.github.daylanbueno.domain.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import lombok.RequiredArgsConstructor;
import oi.github.daylanbueno.domain.dto.InformacaoPedidoDTO;
import oi.github.daylanbueno.domain.dto.PedidoDTO;
import oi.github.daylanbueno.domain.dto.StatusPedidoDto;
import oi.github.daylanbueno.domain.service.PedidoService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/pedidos")
@RequiredArgsConstructor
@Api("Api de pedidos")
public class PedidoController {

    private final PedidoService pedidoService;

    @PostMapping
    @ApiOperation("salvar novo pedido")
    @ApiResponse(code = 200, message = "Pedido salvo com sucesso")
    public Integer salvar(@RequestBody @Valid PedidoDTO  pedidoDTO) {
        return pedidoService.salva(pedidoDTO);
    }

    @GetMapping("/{id}")
    @ApiOperation("obter pedido pelo ID")
    public InformacaoPedidoDTO buscaInformacaoPedidoPorId(@PathVariable @ApiParam("ID do pedido") Integer id) {
        return pedidoService.buscaInformacaoPedidoPorId(id);
    }

    @PatchMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @ApiOperation("Alterar status do pedido")
    @ApiResponse(code = 204, message = "Status alterado com sucesso")
    public void alteraStatus(@PathVariable @ApiParam("ID do pedido") Integer id, @RequestBody StatusPedidoDto dto) {
        pedidoService.atualizaStatus(id, dto.getStatus());
    }
}

package oi.github.daylanbueno.domain.controller;

import lombok.RequiredArgsConstructor;
import oi.github.daylanbueno.domain.dto.InformacaoPedidoDTO;
import oi.github.daylanbueno.domain.dto.PedidoDTO;
import oi.github.daylanbueno.domain.dto.StatusPedidoDto;
import oi.github.daylanbueno.domain.service.PedidoService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/pedidos")
@RequiredArgsConstructor
public class PedidoController {

    private final PedidoService pedidoService;

    @PostMapping
    public Integer salvar(@RequestBody PedidoDTO pedidoDTO) {
        return pedidoService.salva(pedidoDTO);
    }

    @GetMapping("/{id}")
    public InformacaoPedidoDTO buscaInformacaoPedidoPorId(@PathVariable Integer id) {
        return pedidoService.buscaInformacaoPedidoPorId(id);
    }

    @PatchMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void alteraStatus(@PathVariable Integer id, @RequestBody StatusPedidoDto dto) {
        pedidoService.atualizaStatus(id, dto.getStatus());
    }
}

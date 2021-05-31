package oi.github.daylanbueno.domain.service;

import oi.github.daylanbueno.domain.dto.InformacaoPedidoDTO;
import oi.github.daylanbueno.domain.dto.PedidoDTO;

public interface PedidoService {
    Integer salva(PedidoDTO pedidoDTO);

    InformacaoPedidoDTO buscaInformacaoPedidoPorId(Integer id);

    void atualizaStatus(Integer id, String novoStatus);
}

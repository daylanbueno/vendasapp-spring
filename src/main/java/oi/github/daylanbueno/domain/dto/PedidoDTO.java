package oi.github.daylanbueno.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import oi.github.daylanbueno.validation.NotEmptyList;

import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Collection;
import java.util.List;


@AllArgsConstructor
@NoArgsConstructor
@Data
public class PedidoDTO {
    @NotNull(message = "{campo.cliente.obrigatorio}")
    private Integer cliente;
    @NotNull(message = "{campo.total.obrigatorio}")
    private BigDecimal total;
    private LocalDate dataPedido;
    @NotEmptyList(message = "{campo.itens-pedido.obrigatorio}")
    private List<ItemPedidoDTO> itens;
}

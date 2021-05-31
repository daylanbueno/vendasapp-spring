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
    @NotNull(message = "O cliente é obrigatório.")
    private Integer cliente;
    @NotNull(message = "O valor total é obrigatório.")
    private BigDecimal total;
    private LocalDate dataPedido;

    @NotEmptyList(message = "O pedido não pode ser salvo sem itens.")
    private List<ItemPedidoDTO> itens;
}

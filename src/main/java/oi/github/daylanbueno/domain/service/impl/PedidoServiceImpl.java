package oi.github.daylanbueno.domain.service.impl;

import lombok.RequiredArgsConstructor;
import oi.github.daylanbueno.domain.dto.ItemPedidoDTO;
import oi.github.daylanbueno.domain.dto.PedidoDTO;
import oi.github.daylanbueno.domain.entity.Cliente;
import oi.github.daylanbueno.domain.entity.ItemPedido;
import oi.github.daylanbueno.domain.entity.Pedido;
import oi.github.daylanbueno.domain.entity.Produto;
import oi.github.daylanbueno.domain.exception.RegraNegocioException;
import oi.github.daylanbueno.domain.repository.ClienteRepository;
import oi.github.daylanbueno.domain.repository.PedidoRepository;
import oi.github.daylanbueno.domain.repository.ProdutoRepository;
import oi.github.daylanbueno.domain.service.PedidoService;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PedidoServiceImpl  implements PedidoService {

    private final ClienteRepository clienteRepository;
    private final ProdutoRepository produtoRepository;
    private final PedidoRepository pedidoRepository;

    @Override
    public Integer salva(PedidoDTO pedidoDTO) {
        Cliente cliente = buscaClientePorId(pedidoDTO.getCliente());

        Pedido pedido = new Pedido();
        pedido.setTotal(pedidoDTO.getTotal());
        pedido.setDataPedido(LocalDate.now());
        pedido.setCliente(cliente);

        pedido.setItesPedidos(converterItems(pedido,pedidoDTO.getItens()));
        pedidoRepository.save(pedido);
        return pedido.getId();
    }

    private Cliente buscaClientePorId(Integer id) {
        return clienteRepository.
                findById(id).
                orElseThrow(() -> new RegraNegocioException("O código do cliente é inválido"));
    }

    private List<ItemPedido> converterItems(Pedido pedido, Collection<ItemPedidoDTO> itens) {
        if(itens.isEmpty()) {
            throw new RegraNegocioException("Não é possível realizar um pedido sem itens.");
        }

        return itens.
                stream().
                map(dto -> {
                    Produto produto = buscaProduto(dto);
                    return  new ItemPedido(pedido, produto, dto.getQuantidade());
                }).collect(Collectors.toList());
    }

    private Produto buscaProduto(ItemPedidoDTO dto) {
        return produtoRepository
                .findById(dto.getProduto())
                .orElseThrow(() -> new RegraNegocioException(" Código do produto não existe " + dto.getProduto()));
    }
}

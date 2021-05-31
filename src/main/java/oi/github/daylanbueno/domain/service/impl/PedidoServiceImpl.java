package oi.github.daylanbueno.domain.service.impl;

import lombok.RequiredArgsConstructor;
import oi.github.daylanbueno.domain.dto.InformacaoItemPedidoDTO;
import oi.github.daylanbueno.domain.dto.InformacaoPedidoDTO;
import oi.github.daylanbueno.domain.dto.ItemPedidoDTO;
import oi.github.daylanbueno.domain.dto.PedidoDTO;
import oi.github.daylanbueno.domain.entity.Cliente;
import oi.github.daylanbueno.domain.entity.ItemPedido;
import oi.github.daylanbueno.domain.entity.Pedido;
import oi.github.daylanbueno.domain.entity.Produto;
import oi.github.daylanbueno.domain.enums.StatusPedido;
import oi.github.daylanbueno.domain.exception.ObjetoNaoEncontradoExeption;
import oi.github.daylanbueno.domain.exception.RegraNegocioException;
import oi.github.daylanbueno.domain.repository.ClienteRepository;
import oi.github.daylanbueno.domain.repository.ItemsPedidoRepository;
import oi.github.daylanbueno.domain.repository.PedidoRepository;
import oi.github.daylanbueno.domain.repository.ProdutoRepository;
import oi.github.daylanbueno.domain.service.PedidoService;
import oi.github.daylanbueno.domain.util.DataUtil;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.time.LocalDate;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PedidoServiceImpl  implements PedidoService {

    private final ClienteRepository clienteRepository;
    private final ProdutoRepository produtoRepository;
    private final PedidoRepository pedidoRepository;
    private final ItemsPedidoRepository itemsPedidoRepository;

    @Override
    @Transactional
    public Integer salva(PedidoDTO pedidoDTO) {
        Cliente cliente = buscaClientePorId(pedidoDTO.getCliente());

        Pedido pedido = new Pedido();
        pedido.setTotal(pedidoDTO.getTotal());
        pedido.setStatus(StatusPedido.REALIZADO);
        pedido.setDataPedido(LocalDate.now());
        pedido.setCliente(cliente);

        List<ItemPedido> itensPedidos =  converterItems(pedido,pedidoDTO.getItens());
        pedidoRepository.save(pedido);
        itemsPedidoRepository.saveAll(itensPedidos);
        pedido.setItesPedidos(itensPedidos);
        return pedido.getId();
    }

    @Override
    public InformacaoPedidoDTO buscaInformacaoPedidoPorId(Integer id) {
        return pedidoRepository.findByidFetchItens(id)
                .map(pedidoAtual ->  converterInformacaoPedido(pedidoAtual))
                .orElseThrow(() ->  new RegraNegocioException("Pedido não encontrado!"));
    }

    @Override
    @Transactional
    public void atualizaStatus(Integer id, String novoStatus) {
        StatusPedido status = StatusPedido.valueOf(novoStatus);
        if (status == null) {
            throw new RegraNegocioException("Status informado não é válido!");
        }
        Pedido pedido = pedidoRepository
                .findById(id)
                .orElseThrow(() -> new ObjetoNaoEncontradoExeption("Pedido não encontrado"));
        pedido.setStatus(status);
        pedidoRepository.save(pedido);
    }

    private InformacaoPedidoDTO converterInformacaoPedido(Pedido pedidoAtual) {
        return InformacaoPedidoDTO.builder()
                .codigo(pedidoAtual.getId())
                .nomeCliente(pedidoAtual.getCliente().getNome())
                .cpf(pedidoAtual.getCliente().getCpf())
                .total(pedidoAtual.getTotal())
                .status(pedidoAtual.getStatus().name())
                .dataPedido(DataUtil.converterDataEmStringBr(pedidoAtual.getDataPedido()))
                .itens(converterItensPedido(pedidoAtual.getItesPedidos()))
                        .build();
    }

    private List<InformacaoItemPedidoDTO> converterItensPedido(List<ItemPedido> itensPedidos) {
            if(CollectionUtils.isEmpty(itensPedidos)) {
                return Collections.emptyList();
            }

            return itensPedidos.stream().map(
                    item ->
                        InformacaoItemPedidoDTO.builder()
                            .descricaoProduto(item.getProduto().getDescricao())
                            .precoUnitario(item.getProduto().getPreco())
                            .quantidade(item.getQuantidade())
                            .build()).collect(Collectors.toList());
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

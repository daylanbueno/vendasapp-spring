package oi.github.daylanbueno.service;

import oi.github.daylanbueno.domain.dto.ProdutoDto;

import java.util.List;

public interface ProdutoService {
    ProdutoDto save(ProdutoDto produtoDto);

    ProdutoDto alterar(ProdutoDto produtoDto);

    void deletar(Integer id);

    List<ProdutoDto> obterProdutoPorFiltro(ProdutoDto filtro);

    ProdutoDto obterProdutoPorId(Integer id);
}

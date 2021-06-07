package oi.github.daylanbueno.domain.service.impl;

import lombok.RequiredArgsConstructor;
import oi.github.daylanbueno.domain.dto.ProdutoDto;
import oi.github.daylanbueno.domain.entity.Produto;
import oi.github.daylanbueno.domain.exception.RegraNegocioException;
import oi.github.daylanbueno.domain.repository.ProdutoRepository;
import oi.github.daylanbueno.domain.service.ProdutoService;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ProdutoServiceImpl implements ProdutoService {

    private final ProdutoRepository produtoRepository;
    private final ModelMapper modelMapper;

    @Override
    public ProdutoDto save(ProdutoDto produtoDto) {
        Produto produto = modelMapper.map(produtoDto, Produto.class);
        Produto novoProduto = produtoRepository.save(produto);
        return modelMapper.map(novoProduto, ProdutoDto.class);
    }

    @Override
    public ProdutoDto alterar(ProdutoDto produtoDto) {

        produtoRepository.findById(produtoDto.getId())
                .orElseThrow(() -> new RegraNegocioException("Produto não encontrado."));

        Produto produtoAlterado = modelMapper.map(produtoDto, Produto.class);
        produtoRepository.save(produtoAlterado);

        return modelMapper.map(produtoAlterado,ProdutoDto.class);
    }

    @Override
    public void deletar(Integer id) {
        produtoRepository
                .findById(id)
                .map(produto -> {
                    produtoRepository.delete(produto);
                    return produto;
                }).orElseThrow(() -> new RegraNegocioException("Produto não encontrado"));
    }

    @Override
    public List<ProdutoDto> obterProdutoPorFiltro(ProdutoDto produtoDto) {
        Produto filtro = modelMapper.map(produtoDto, Produto.class);
        ExampleMatcher matcher = ExampleMatcher.matching()
                .withIgnoreCase()
                .withStringMatcher(ExampleMatcher.StringMatcher.CONTAINING);
        Example example = Example.of(filtro,matcher);
        List<Produto> produtos = produtoRepository.findAll(example);

        List<ProdutoDto> resultado = produtos.stream().map((entity) -> modelMapper.map(entity, ProdutoDto.class)).collect(Collectors.toList());

        return resultado;
    }

    @Override
    public ProdutoDto obterProdutoPorId(Integer id) {
        Produto produto = produtoRepository
                .findById(id)
                .orElseThrow(() -> new RegraNegocioException("Produto não encontrado"));
        return modelMapper.map(produto,ProdutoDto.class);
    }
}

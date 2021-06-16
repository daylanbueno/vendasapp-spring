package oi.github.daylanbueno.domain.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import lombok.RequiredArgsConstructor;
import oi.github.daylanbueno.domain.dto.ProdutoDto;
import oi.github.daylanbueno.domain.repository.ProdutoRepository;
import oi.github.daylanbueno.domain.service.ProdutoService;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/produtos")
@RequiredArgsConstructor
@Api("Api de produtos")
public class ProdutoController {

    private final ProdutoRepository produtoRepository;
    private final ProdutoService produtoService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @ApiOperation("salvar novo produto")
    @ApiResponse(code = 201, message = "Produto incluido com sucesso")
    public ProdutoDto salva(@RequestBody @Valid ProdutoDto produtoDto) {
        return produtoService.save(produtoDto);
    }

    @PutMapping("/{id}")
    @ApiOperation("alterar produto")
    @ApiResponse(code = 200, message = "Produto alterado com sucesso")
    public ProdutoDto alterar(@PathVariable @ApiParam("ID do produto") Integer id, @RequestBody @Valid ProdutoDto produtoDto) {
        produtoDto.setId(id);
      return produtoService.alterar(produtoDto);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @ApiOperation("deletar um produto")
    @ApiResponse(code = 204, message = "Produto deletado com sucesso")
    public void deletar(@PathVariable @ApiParam("ID do produto") Integer id) {
        produtoService.deletar(id);
    }

    @GetMapping
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @ApiOperation("obter produtos por filtro")
    @ApiResponse(code = 200, message = "devolve lista produtos")
    public List<ProdutoDto> obterProdutoPorFiltro(ProdutoDto filtro) {
       return produtoService.obterProdutoPorFiltro(filtro);
    }

    @GetMapping("/{id}")
    @ApiOperation("obter produto por id")
    @ApiResponse(code = 200, message = "devolve o produto")
    public ProdutoDto obterProdutoPorId(@PathVariable @Param("ID do produto") Integer id) {
        return produtoService.obterProdutoPorId(id);
    }
}

package oi.github.daylanbueno.domain.controller;

import lombok.RequiredArgsConstructor;
import oi.github.daylanbueno.domain.dto.ProdutoDto;
import oi.github.daylanbueno.domain.repository.ProdutoRepository;
import oi.github.daylanbueno.domain.service.ProdutoService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/produtos")
@RequiredArgsConstructor
public class ProdutoController {

    private final ProdutoRepository produtoRepository;
    private final ProdutoService produtoService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ProdutoDto salva(@RequestBody @Valid ProdutoDto produtoDto) {
        return produtoService.save(produtoDto);
    }

    @PutMapping("/{id}")
    public ProdutoDto alterar(@PathVariable Integer id, @RequestBody @Valid ProdutoDto produtoDto) {
        produtoDto.setId(id);
      return produtoService.alterar(produtoDto);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deletar(@PathVariable Integer id) {
        produtoService.deletar(id);
    }

    @GetMapping
    public List<ProdutoDto> obterProdutoPorFiltro(ProdutoDto filtro) {
       return produtoService.obterProdutoPorFiltro(filtro);
    }

    @GetMapping("/{id}")
    public ProdutoDto obterProdutoPorId(@PathVariable Integer id) {
        return produtoService.obterProdutoPorId(id);
    }
}

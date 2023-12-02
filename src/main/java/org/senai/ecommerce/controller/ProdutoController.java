package org.senai.ecommerce.controller;
import org.senai.ecommerce.entity.Produto;
import org.senai.ecommerce.repositories.dto.ProdutoDTO;
import org.senai.ecommerce.service.ProdutoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/produtos")
public class ProdutoController {
    private final ProdutoService produtoService;

    @Autowired
    public ProdutoController(ProdutoService produtoService) {
        this.produtoService = produtoService;
    }

    @GetMapping
    public ResponseEntity<?> getProdutos() {
        return new ResponseEntity<>(produtoService.getProdutos(), HttpStatus.OK);
    }

    @GetMapping("/{codigo}")
    public ResponseEntity<?> getProdutoPorCodigo(@PathVariable Long codigo) {
        return new ResponseEntity<>(produtoService.getProdutoPorCodigo(codigo), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<?> criarProduto(@RequestBody ProdutoDTO produtoDTO) {
        return new ResponseEntity<>(produtoService.criarProduto(produtoDTO), HttpStatus.CREATED);
    }

    @PutMapping("/{codigo}")
    public ResponseEntity<?> atualizarProduto(@PathVariable Long codigo, @RequestBody ProdutoDTO produtoDetalhes) {
        produtoService.atualizarProduto(codigo, produtoDetalhes);
        return new ResponseEntity<>(produtoService.atualizarProduto(codigo, produtoDetalhes), HttpStatus.OK);
    }

    @DeleteMapping("/{codigo}")
    public ResponseEntity<?> deleteProduct(@PathVariable Long codigo) {
        produtoService.excluirProduto(codigo);
        return new ResponseEntity<>("Produto Excluído do Sucesso!", HttpStatus.OK);
    }
}

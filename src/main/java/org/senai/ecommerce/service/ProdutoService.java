package org.senai.ecommerce.service;

import jakarta.persistence.EntityNotFoundException;
import org.senai.ecommerce.entity.Produto;
import org.senai.ecommerce.repositories.ProdutoRepository;
import org.senai.ecommerce.repositories.dto.ProdutoDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ProdutoService{

    @Autowired
    ProdutoRepository produtoRepository;

    public ProdutoService(ProdutoRepository produtoRepository){
        this.produtoRepository = produtoRepository;
    }

    public List<Produto> getProdutos() {
        return produtoRepository.findAll();
    }

    public Optional<Produto> getProdutoPorCodigo(Long codigo) {
        return produtoRepository.findById(codigo);
    }

    public Produto criarProduto(ProdutoDTO novoProdutoDTO) {
        Produto produto = new Produto();
        produto.setNome(novoProdutoDTO.getNome());
        produto.setDescricao(novoProdutoDTO.getDescricao());
        produto.setPreco(novoProdutoDTO.setPreco());
        produto.setEstoque(novoProdutoDTO.setEstoque());
        produto = produtoRepository.save(produto);
        return produto.getCodigo();
    }

    public Produto atualizarProduto(Long codigo, ProdutoDTO produtoDTO) {
        Produto produtoExistente = produtoRepository.findById(codigo).orElseThrow(() -> new EntityNotFoundException("Produto não encontrado!"));

        if(produtoDTO.getNome() != null){
            produtoExistente.setNome(ProdutoDTO.getNome());
        }

        if (produtoDTO.getDescricao() != null){
            produtoExistente.setDescricao(produtoExistente.getDescricao());
        }

        if (produtoDTO.getPreco() != null){
            produtoExistente.setPreco(produtoExistente.getPreco());
        }

        if (produtoDTO.getEstoque() != null){
            produtoExistente.setEstoque(produtoExistente.getEstoque());
        }

        return produtoRepository.save(produtoExistente);

    }

    public void excluirProduto(Long codigo) {
        produtoRepository.deleteById(codigo);
    }
}

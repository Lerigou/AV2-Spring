package org.senai.ecommerce.repositories.dto;

import lombok.Data;

@Data
public class ProdutoDTO {
    private Long codigo;
    private String nome;
    private String descricao;
    private double preco;
    private int estoque;
}

package br.com.matheus.testevr.apivenda.model;

public record Produto(Long id, String descricao, float estoque, float preco, String unidade) {
}

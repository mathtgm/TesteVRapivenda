package br.com.matheus.testevr.apivenda.model;

import java.util.Map;

public record ProdutoAPIRespostaHttp(String message, Map<String, ProdutoAPIErro> errors) {
}

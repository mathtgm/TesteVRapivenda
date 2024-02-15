package br.com.matheus.testevr.apivenda.dto.venda;

import java.util.List;

public record CadastroVendaDTO(List<CadastroProdutoVendaDTO> listaProdutos, Long idCliente) {
}

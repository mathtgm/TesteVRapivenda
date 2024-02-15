package br.com.matheus.testevr.apivenda.dto.venda;

import java.util.List;

public record AlteracaoDadosVendaDTO(List<Long> produtosRemovidos, List<CadastroProdutoVendaDTO> produtosAdicionados,
                                     Long idCliente, Long idVenda) {
}

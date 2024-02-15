package br.com.matheus.testevr.apivenda.dto.relatorio;

import jakarta.persistence.Tuple;

public class RelatorioConsolidadoDTO {

    private Long idVenda;
    private String nomeCliente;
    private double totalVenda;
    private boolean status;

    public RelatorioConsolidadoDTO() {
    }

    public RelatorioConsolidadoDTO(Tuple relatorioConsolidado) {

        this.idVenda = (Long) relatorioConsolidado.get(0);
        this.nomeCliente = (String) relatorioConsolidado.get(1);
        this.totalVenda = (double) relatorioConsolidado.get(2);
        this.status = (boolean) relatorioConsolidado.get(3);

    }

    public Long getIdVenda() {
        return idVenda;
    }

    public String getNomeCliente() {
        return nomeCliente;
    }

    public double getTotalVenda() {
        return totalVenda;
    }

    public boolean isStatus() {
        return status;
    }
}

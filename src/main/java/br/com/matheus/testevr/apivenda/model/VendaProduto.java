package br.com.matheus.testevr.apivenda.model;

import br.com.matheus.testevr.apivenda.dto.venda.CadastroProdutoVendaDTO;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class VendaProduto {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    private Long idProduto;
    private String descricao;
    private float preco;
    private String unidade;
    private float quantidadeVenda;
    private boolean finalizado = false;

    public VendaProduto() {
    }

    public VendaProduto(CadastroProdutoVendaDTO produtoVendaDTO) {

        this.idProduto = produtoVendaDTO.idProduto();
        this.quantidadeVenda = produtoVendaDTO.quantidade();
        this.unidade = produtoVendaDTO.unidade();
        this.descricao = produtoVendaDTO.descricao();
        this.preco = produtoVendaDTO.preco();

    }

    public void adicionaQuantidade(float quantidade) {
        this.quantidadeVenda += quantidade;
    }

    public void finalizarVendaProduto() {

        this.finalizado = true;

    }

    public Long getIdProduto() {
        return idProduto;
    }

    public float getQuantidadeVenda() {
        return quantidadeVenda;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public boolean isFinalizado() {
        return finalizado;
    }

    public String getDescricao() {
        return descricao;
    }

    public float getPreco() {
        return preco;
    }

    public String getUnidade() {
        return unidade;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        VendaProduto that = (VendaProduto) o;

        return idProduto.equals(that.idProduto);
    }

    @Override
    public int hashCode() {
        return idProduto.hashCode();
    }
}

package br.com.matheus.testevr.apivenda.model;

import br.com.matheus.testevr.apivenda.dto.venda.CadastroProdutoVendaDTO;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Entity
public class Venda {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "id_venda")
    private List<VendaProduto> listaProdutos = new ArrayList<>();

    @ManyToOne
    @JoinColumn(name = "id_cliente")
    private Cliente cliente;

    private boolean finalizado = false;

    public Venda() {
    }

    public Venda(List<CadastroProdutoVendaDTO> cadastroProdutoVendaDTO, Cliente cliente) {

        this.cliente = cliente;
        List<VendaProduto> listaVendaProdutos = new ArrayList<>();

        for (CadastroProdutoVendaDTO produtoVendaDTO : cadastroProdutoVendaDTO) {

            listaVendaProdutos.add(new VendaProduto(produtoVendaDTO));

        }

        adicionarProdutosVenda(listaVendaProdutos);


    }

    public void excluiProdutosDuplicados() {

        for (int indexComparador = 0; indexComparador < listaProdutos.size(); indexComparador++) {

            VendaProduto vendaProdutoComparador = listaProdutos.get(indexComparador);

            for (int indexComparado = indexComparador; indexComparado < listaProdutos.size(); indexComparado++) {
                VendaProduto vendaProdutoComparado = listaProdutos.get(indexComparado);

                if (vendaProdutoComparador.getIdProduto().equals(vendaProdutoComparado.getIdProduto()) && indexComparado != indexComparador) {

                    vendaProdutoComparador.adicionaQuantidade(vendaProdutoComparado.getQuantidadeVenda());
                    listaProdutos.remove(indexComparado);
                    indexComparado--;

                }

            }

        }

    }

    public void alterarDadosVenda(List<Long> produtosRemovidos, List<CadastroProdutoVendaDTO> produtosAdicionados, Cliente cliente) {

        this.cliente = cliente;
        List<VendaProduto> listaVendaProdutos = new ArrayList<>();
        removerProdutoVenda(produtosRemovidos);

        for (CadastroProdutoVendaDTO produtoVendaDTO : produtosAdicionados) {

            listaVendaProdutos.add(new VendaProduto(produtoVendaDTO));

        }

        adicionarProdutosVenda(listaVendaProdutos);


    }

    public void removerProdutoVenda(List<Long> listaProdutoRemovidos) {

        for (Long produtoRemovido : listaProdutoRemovidos) {

            listaProdutos.removeIf(produtoVenda -> produtoVenda.getId().equals(produtoRemovido));

        }

    }

    public void adicionarProdutosVenda(List<VendaProduto> listaProdutoAdicionados) {

        listaProdutos.addAll(listaProdutoAdicionados);
        excluiProdutosDuplicados();

    }

    public void finalizarVenda(Map<String, ProdutoAPIErro> listaProdutosErro) {

        this.listaProdutos.forEach(produto -> {

            //Se produto não for encontrado na lista de erro finaliza o produto
            if (!listaProdutosErro.containsKey(produto.getIdProduto().toString())) {

                produto.finalizarVendaProduto();

            }

        });

        this.finalizado = true;

    }

    public Long getId() {
        return id;
    }

    public List<VendaProduto> getListaProdutos() {
        return listaProdutos;
    }

    public boolean isFinalizado() {
        return finalizado;
    }

    public Cliente getCliente() {
        return cliente;
    }


}

package br.com.matheus.testevr.apivenda.repository;

import br.com.matheus.testevr.apivenda.model.Venda;
import jakarta.persistence.Tuple;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface VendaRepository extends JpaRepository<Venda, Long> {

    public boolean existsById(Long idVenda);

    public boolean existsByIdAndFinalizadoTrue(Long idVenda);

    @Query("SELECT v.id, c.nome, sum(pv.quantidadeVenda * pv.preco), v.finalizado FROM Venda v JOIN v.cliente c JOIN v.listaProdutos pv GROUP BY c.nome, v.finalizado, v.id")
    public List<Tuple> relatorioConsolidado();

}

package br.com.matheus.testevr.apivenda.service;

import br.com.matheus.testevr.apivenda.dto.relatorio.RelatorioConsolidadoDTO;
import br.com.matheus.testevr.apivenda.dto.venda.AlteracaoDadosVendaDTO;
import br.com.matheus.testevr.apivenda.dto.venda.CadastroVendaDTO;
import br.com.matheus.testevr.apivenda.model.Cliente;
import br.com.matheus.testevr.apivenda.model.ProdutoAPIErro;
import br.com.matheus.testevr.apivenda.model.Venda;
import br.com.matheus.testevr.apivenda.repository.ClienteRepository;
import br.com.matheus.testevr.apivenda.repository.ProdutoAPIRepository;
import br.com.matheus.testevr.apivenda.repository.VendaRepository;
import br.com.matheus.testevr.apivenda.validation.venda.alteracao.ValidacaoAlteracaoVenda;
import com.google.gson.Gson;
import jakarta.persistence.Tuple;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public class VendaService {

    @Autowired
    private VendaRepository vendaRepository;

    @Autowired
    private ProdutoAPIRepository produtoAPIRepository;

    @Autowired
    private ClienteRepository clienteRepository;

    @Autowired
    Gson gson;

    @Autowired
    private List<ValidacaoAlteracaoVenda> validacaoAlteracaoVendas;

    public List<Venda> listarVendas() {

        return vendaRepository.findAll();

    }

    public Venda consultarVenda(Long idVenda) {

        return vendaRepository.findById(idVenda).get();

    }

    public String finalizarVenda(Long idVenda) throws IOException, InterruptedException {

        validacaoAlteracaoVendas.forEach(validacao -> validacao.validar(idVenda));

        Venda venda = vendaRepository.findById(idVenda).get();

        Map<String, ProdutoAPIErro> listaProdutosErro = produtoAPIRepository.baixaEstoqueEmLote(venda.getListaProdutos());

        venda.finalizarVenda(listaProdutosErro);

        vendaRepository.save(venda);

        return gson.toJson(listaProdutosErro);

    }

    public Venda cadastrarVenda(CadastroVendaDTO cadastroVendaDTO) {

        Cliente cliente = clienteRepository.findById(cadastroVendaDTO.idCliente()).get();

        Venda venda = new Venda(cadastroVendaDTO.listaProdutos(), cliente);

        return vendaRepository.save(venda);

    }

    public Venda alterarDadosVenda(AlteracaoDadosVendaDTO alteracaoDadosVendaDTO) {

        validacaoAlteracaoVendas.forEach(validacao -> validacao.validar(alteracaoDadosVendaDTO.idVenda()));

        Venda venda = vendaRepository.findById(alteracaoDadosVendaDTO.idVenda()).get();
        Cliente cliente = clienteRepository.findById(alteracaoDadosVendaDTO.idCliente()).get();

        venda.alterarDadosVenda(alteracaoDadosVendaDTO.produtosRemovidos(), alteracaoDadosVendaDTO.produtosAdicionados(), cliente);

        return vendaRepository.save(venda);

    }

    public List<RelatorioConsolidadoDTO> relatorioConsolidado() {

        List<Tuple> listaTuple = vendaRepository.relatorioConsolidado();
        List<RelatorioConsolidadoDTO> relatorioConsolidadoDTOS = new ArrayList<>();

        for (Tuple tuple : listaTuple) {
            relatorioConsolidadoDTOS.add(new RelatorioConsolidadoDTO(tuple));
        }

        return relatorioConsolidadoDTOS;

    }


}

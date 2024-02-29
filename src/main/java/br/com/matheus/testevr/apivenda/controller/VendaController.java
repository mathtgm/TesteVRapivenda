package br.com.matheus.testevr.apivenda.controller;

import br.com.matheus.testevr.apivenda.dto.relatorio.RelatorioConsolidadoDTO;
import br.com.matheus.testevr.apivenda.dto.venda.AlteracaoDadosVendaDTO;
import br.com.matheus.testevr.apivenda.dto.venda.CadastroVendaDTO;
import br.com.matheus.testevr.apivenda.exception.ValidacaoException;
import br.com.matheus.testevr.apivenda.model.Venda;
import br.com.matheus.testevr.apivenda.service.VendaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/vendas")
public class VendaController {

    @Autowired
    VendaService vendaService;

    @PostMapping
    public ResponseEntity<Venda> cadastrar(@RequestBody CadastroVendaDTO cadastroVendaDTO) {

        return ResponseEntity.ok().body(vendaService.cadastrarVenda(cadastroVendaDTO));

    }

    @PostMapping(path = "/finalizar/{idVenda}")
    public ResponseEntity<String> finalizar(@PathVariable Long idVenda) {

        try {

            String produtosPendentes = vendaService.finalizarVenda(idVenda);

            if (produtosPendentes.isEmpty()) {

                return ResponseEntity.ok().body("Venda finalizada com sucesso");

            } else {

                return ResponseEntity.status(207).body(produtosPendentes);

            }

        } catch (IOException | InterruptedException mensagem) {

            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Sem conexão com API de produto");

        } catch (ValidacaoException mensagem) {

            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(mensagem.getMessage());

        }

    }

    @PutMapping
    public ResponseEntity<Venda> alterar(@RequestBody AlteracaoDadosVendaDTO alteracaoDadosVendaDTO) {

        try {

            return ResponseEntity.ok().body(vendaService.alterarDadosVenda(alteracaoDadosVendaDTO));

        } catch (ValidacaoException mensagem) {

            return ResponseEntity.notFound().build();

        }

    }

    @GetMapping
    public ResponseEntity<List<Venda>> listar() {

        return ResponseEntity.ok().body(vendaService.listarVendas());
    }

    @GetMapping(path = "/{idVenda}")
    public ResponseEntity<Venda> consulta(@PathVariable Long idVenda) {

        try {

            return ResponseEntity.ok().body(vendaService.consultarVenda(idVenda));

        } catch (ValidacaoException mensagem) {

            return ResponseEntity.notFound().build();

        }

    }

    @GetMapping(path = "/relatorioConsolidacao")
    public ResponseEntity<List<RelatorioConsolidadoDTO>> salva() {

        return ResponseEntity.ok().body(vendaService.relatorioConsolidado());

    }

}

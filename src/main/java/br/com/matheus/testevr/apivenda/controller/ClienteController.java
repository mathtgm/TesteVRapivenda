package br.com.matheus.testevr.apivenda.controller;

import br.com.matheus.testevr.apivenda.exception.ValidacaoException;
import br.com.matheus.testevr.apivenda.model.Cliente;
import br.com.matheus.testevr.apivenda.service.ClienteService;
import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/clientes")
public class ClienteController {

    @Autowired
    ClienteService clienteService;

    @Autowired
    Gson gson;

    @PostMapping("/cadastrar")
    public ResponseEntity<String> cadastrarCliente(@RequestBody Cliente cliente) {

        try {

            return ResponseEntity.ok().body(gson.toJson(clienteService.cadastrarCliente(cliente)));

        } catch (ValidacaoException msg) {

            return ResponseEntity.badRequest().body(msg.getMessage());

        }

    }

    @PostMapping
    public ResponseEntity<String> alterarCliente(@RequestBody Cliente cliente) {

        try {
            clienteService.atualizarInformacoes(cliente);

            return ResponseEntity.ok().body("Cliente alterado com sucesso");

        } catch (ValidacaoException msg) {

            return ResponseEntity.badRequest().body(msg.getMessage());

        }

    }

    @GetMapping("/{idCliente}")
    public ResponseEntity<Cliente> consultarCliente(@PathVariable Long idCliente) {

        return ResponseEntity.ok().body(clienteService.consultarCliente(idCliente));

    }

    @GetMapping
    public ResponseEntity<List<Cliente>> consultarListaClientes() {

        return ResponseEntity.ok().body(clienteService.listarClientes());

    }
}

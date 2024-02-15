package br.com.matheus.testevr.apivenda.controller;

import br.com.matheus.testevr.apivenda.model.Cliente;
import br.com.matheus.testevr.apivenda.service.ClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/clientes")
public class ClienteController {

    @Autowired
    ClienteService clienteService;

    @PostMapping("/cadastrar")
    public ResponseEntity<Cliente> cadastrarCliente(@RequestBody Cliente cliente) {

        return ResponseEntity.ok().body(clienteService.cadastrarCliente(cliente));

    }

    @PostMapping
    public ResponseEntity<String> alterarCliente(@RequestBody Cliente cliente) {

        clienteService.atualizarInformacoe(cliente);

        return ResponseEntity.ok().body("Dados do cliente alterado");
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

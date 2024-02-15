package br.com.matheus.testevr.apivenda.service;

import br.com.matheus.testevr.apivenda.model.Cliente;
import br.com.matheus.testevr.apivenda.repository.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClienteService {

    @Autowired
    ClienteRepository clienteRepository;

    public List<Cliente> listarClientes() {

        return clienteRepository.findAll();

    }

    public Cliente consultarCliente(Long idCliente) {

        return clienteRepository.findById(idCliente).get();
    }

    public void atualizarInformacoe(Cliente cliente) {

        clienteRepository.save(cliente);

    }

    public Cliente cadastrarCliente(Cliente cliente) {

        return clienteRepository.save(cliente);

    }

}

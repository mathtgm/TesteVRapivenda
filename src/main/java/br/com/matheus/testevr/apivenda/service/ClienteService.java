package br.com.matheus.testevr.apivenda.service;

import br.com.matheus.testevr.apivenda.model.Cliente;
import br.com.matheus.testevr.apivenda.repository.ClienteRepository;
import br.com.matheus.testevr.apivenda.validation.cliente.validacaoAlteracao.ValidacaoAlteracaoCliente;
import br.com.matheus.testevr.apivenda.validation.cliente.validacaoCadastro.ValidacaoCadastroCliente;
import br.com.matheus.testevr.apivenda.validation.cliente.validacaoDados.ValidacaoDadosCliente;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClienteService {

    @Autowired
    ClienteRepository clienteRepository;

    @Autowired
    List<ValidacaoDadosCliente> validacaoDadosClientes;

    @Autowired
    List<ValidacaoCadastroCliente> validacaoCadastroClientes;

    @Autowired
    List<ValidacaoAlteracaoCliente> validacaoAlteracaoClientes;

    public List<Cliente> listarClientes() {

        return clienteRepository.findAll();

    }

    public Cliente consultarCliente(Long idCliente) {

        return clienteRepository.findById(idCliente).get();
    }

    public void atualizarInformacoes(Cliente cliente) {

        validacaoDadosClientes.forEach(validacao -> validacao.validar(cliente));
        validacaoAlteracaoClientes.forEach(validacao -> validacao.validar(cliente));

        clienteRepository.save(cliente);

    }

    public Cliente cadastrarCliente(Cliente cliente) {

        validacaoDadosClientes.forEach(validacao -> validacao.validar(cliente));
        validacaoCadastroClientes.forEach(validacao -> validacao.validar(cliente));

        return clienteRepository.save(cliente);

    }

}

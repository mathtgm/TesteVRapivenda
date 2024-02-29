package br.com.matheus.testevr.apivenda.validation.cliente.validacaoAlteracao;

import br.com.matheus.testevr.apivenda.exception.ValidacaoException;
import br.com.matheus.testevr.apivenda.model.Cliente;
import br.com.matheus.testevr.apivenda.repository.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ValidacaoDocumentoClienteExiste implements ValidacaoAlteracaoCliente {

    @Autowired
    ClienteRepository clienteRepository;

    @Override
    public void validar(Cliente cliente) {

        Cliente clienteBanco = clienteRepository.findById(cliente.getId()).get();

        if (!clienteBanco.getDocumento().equalsIgnoreCase(cliente.getDocumento())) {

            if (clienteRepository.existsByDocumento(cliente.getDocumento())) {

                throw new ValidacaoException("Outro cliente já utiliza o mesmo documento");

            }

        }


    }
}

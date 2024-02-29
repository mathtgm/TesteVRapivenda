package br.com.matheus.testevr.apivenda.validation.cliente.validacaoAlteracao;

import br.com.matheus.testevr.apivenda.exception.ValidacaoException;
import br.com.matheus.testevr.apivenda.model.Cliente;
import br.com.matheus.testevr.apivenda.repository.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ValidacaoRazaoSocialExiste implements ValidacaoAlteracaoCliente {

    @Autowired
    ClienteRepository clienteRepository;

    @Override
    public void validar(Cliente cliente) {

        Cliente clienteBanco = clienteRepository.findById(cliente.getId()).get();

        if (!clienteBanco.getRazaoSocial().equalsIgnoreCase(cliente.getRazaoSocial())) {

            if (clienteRepository.existsByRazaoSocial(cliente.getRazaoSocial())) {

                throw new ValidacaoException("Outro cliente já utiliza a Razao social");

            }
        }

    }
}

package br.com.matheus.testevr.apivenda.validation.cliente.validacaoCadastro;

import br.com.matheus.testevr.apivenda.exception.ValidacaoException;
import br.com.matheus.testevr.apivenda.model.Cliente;
import br.com.matheus.testevr.apivenda.repository.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ValidacaoRazaoSocialClienteCadastrada implements ValidacaoCadastroCliente {

    @Autowired
    ClienteRepository clienteRepository;

    @Override
    public void validar(Cliente cliente) {

        if (clienteRepository.existsByRazaoSocial(cliente.getRazaoSocial())) {

            throw new ValidacaoException("Razão social já cadastrad no sistema");

        }

    }
}

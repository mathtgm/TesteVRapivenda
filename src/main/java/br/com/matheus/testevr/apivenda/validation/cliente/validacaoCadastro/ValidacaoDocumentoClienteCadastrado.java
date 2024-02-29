package br.com.matheus.testevr.apivenda.validation.cliente.validacaoCadastro;

import br.com.matheus.testevr.apivenda.exception.ValidacaoException;
import br.com.matheus.testevr.apivenda.model.Cliente;
import br.com.matheus.testevr.apivenda.repository.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ValidacaoDocumentoClienteCadastrado implements ValidacaoCadastroCliente {

    @Autowired
    ClienteRepository clienteRepository;

    @Override
    public void validar(Cliente cliente) {

        if (clienteRepository.existsByDocumento(cliente.getDocumento())) {
            throw new ValidacaoException("Documento já cadastrado");
        }

    }
}

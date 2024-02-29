package br.com.matheus.testevr.apivenda.validation.cliente.validacaoDados;

import br.com.caelum.stella.validation.CNPJValidator;
import br.com.caelum.stella.validation.CPFValidator;
import br.com.caelum.stella.validation.InvalidStateException;
import br.com.matheus.testevr.apivenda.exception.ValidacaoException;
import br.com.matheus.testevr.apivenda.model.Cliente;
import org.springframework.stereotype.Component;

@Component
public class ValidacaoDocumentoClienteValido implements ValidacaoDadosCliente {

    CNPJValidator cnpjValidator;

    CPFValidator cpfValidator;

    public ValidacaoDocumentoClienteValido() {
        cpfValidator = new CPFValidator();
        cnpjValidator = new CNPJValidator();
    }

    @Override
    public void validar(Cliente cliente) {

        try {

            if (cliente.getDocumento().length() <= 10 || cliente.getDocumento().isBlank()) {
                throw new ValidacaoException("Tamanho do documento Invalido!");
            } else if (cliente.getDocumento().length() == 11) {
                cpfValidator.assertValid(cliente.getDocumento());
            } else {
                cnpjValidator.assertValid(cliente.getDocumento());
            }

        } catch (InvalidStateException e) {
            throw new ValidacaoException("Documento invalido!");
        }

    }
}

package br.com.matheus.testevr.apivenda.validation.venda.alteracao;

import br.com.matheus.testevr.apivenda.exception.ValidacaoException;
import br.com.matheus.testevr.apivenda.repository.VendaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ValidacaoSeVendaFinalizada implements ValidacaoAlteracaoVenda {

    @Autowired
    VendaRepository vendaRepository;

    @Override
    public void validar(Long idVenda) {

        if (vendaRepository.existsByIdAndFinalizadoTrue(idVenda)) {
            throw new ValidacaoException("Venda já finalizada, alterações não podem serem feitas");
        }

    }
}

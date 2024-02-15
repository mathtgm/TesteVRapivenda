package br.com.matheus.testevr.apivenda.validation.venda.alteracao;

import br.com.matheus.testevr.apivenda.exception.ValidacaoException;
import br.com.matheus.testevr.apivenda.model.Venda;
import br.com.matheus.testevr.apivenda.repository.VendaRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.BDDMockito;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class ValidacaoSeVendaFinalizadaTest {

    @Mock
    VendaRepository vendaRepository;

    @InjectMocks
    ValidacaoSeVendaFinalizada validacaoSeVendaFinalizada;

    @Mock
    Venda venda;

    @Test
    public void recusaAlteracaoVendaFinalizada() {

        BDDMockito.given(vendaRepository.existsByIdAndFinalizadoTrue(venda.getId())).willReturn(true);

        Assertions.assertThrows(ValidacaoException.class, () -> validacaoSeVendaFinalizada.validar(venda.getId()));
    }

    @Test
    public void permiteAlteracaoVenda() {

        BDDMockito.given(vendaRepository.existsByIdAndFinalizadoTrue(venda.getId())).willReturn(false);

        Assertions.assertDoesNotThrow(() -> validacaoSeVendaFinalizada.validar(venda.getId()));

    }


}
package br.com.matheus.testevr.apivenda.service;

import br.com.matheus.testevr.apivenda.model.Cliente;
import br.com.matheus.testevr.apivenda.repository.ClienteRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class ClienteServiceTest {

    @InjectMocks
    ClienteService clienteService;

    @Mock
    ClienteRepository clienteRepository;

    @Captor
    ArgumentCaptor<Cliente> clienteArgumentCaptor;

    Cliente cliente;

    @Test
    void cadastraCliente() {

        this.cliente = new Cliente("Matheus", "43432479883", "");
        BDDMockito.given(clienteRepository.save(cliente)).willReturn(new Cliente());

        clienteService.cadastrarCliente(cliente);

        BDDMockito.then(clienteRepository).should().save(clienteArgumentCaptor.capture());
        Cliente clienteResultado = clienteArgumentCaptor.getValue();

        Assertions.assertEquals(this.cliente.getNome(), clienteResultado.getNome());
        Assertions.assertEquals(this.cliente.getId(), clienteResultado.getId());

    }

}
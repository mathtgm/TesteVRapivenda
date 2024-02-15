package br.com.matheus.testevr.apivenda.repository;

import br.com.matheus.testevr.apivenda.model.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClienteRepository extends JpaRepository<Cliente, Long> {


}

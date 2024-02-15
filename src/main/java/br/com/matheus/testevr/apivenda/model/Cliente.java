package br.com.matheus.testevr.apivenda.model;

import jakarta.persistence.*;

@Entity
public class Cliente {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;
    @Column(length = 60)
    private String nome;
    @Column(length = 14)
    private String identificacao;

    @Column(length = 60)
    private String razaoSocial;


    public Cliente() {
    }

    public Cliente(String nome, String identificacao, String razaoSocial) {
        this.nome = nome;
        this.identificacao = identificacao;
        this.razaoSocial = razaoSocial;
    }

    public void alterarInformacoes(Cliente cliente) {
        this.nome = cliente.getNome();
        this.razaoSocial = cliente.getRazaoSocial();
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getRazaoSocial() {
        return razaoSocial;
    }

    public void setRazaoSocial(String razaoSocial) {
        this.razaoSocial = razaoSocial;
    }

    public Long getId() {
        return id;
    }

    public String getIdentificacao() {
        return identificacao;
    }

    public void setIdentificacao(String identificacao) {
        this.identificacao = identificacao;
    }
}

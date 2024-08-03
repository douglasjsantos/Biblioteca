package br.com.douglas.sistemabiblioteca.sistema.de.biblioteca.domain;

import ch.qos.logback.core.net.server.Client;
import jakarta.persistence.*;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity
public class Endereco {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String rua;
    private String bairro;
    private String numero;
    private String estado;
    private String cidade;
    private String cep;

    @ManyToMany(mappedBy = "enderecos")
    private Set<Usuario> clientes = new HashSet<>();

    public Endereco() {
    }

    public Endereco(Long id, String rua, String bairro, String numero, String estado, String cidade, String cep, Set<Usuario> clientes) {
        this.id = id;
        this.rua = rua;
        this.bairro = bairro;
        this.numero = numero;
        this.estado = estado;
        this.cidade = cidade;
        this.cep = cep;
        this.clientes = clientes;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Endereco endereco = (Endereco) o;
        return Objects.equals(id, endereco.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getRua() {
        return rua;
    }

    public void setRua(String rua) {
        this.rua = rua;
    }

    public String getBairro() {
        return bairro;
    }

    public void setBairro(String bairro) {
        this.bairro = bairro;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getCidade() {
        return cidade;
    }

    public void setCidade(String cidade) {
        this.cidade = cidade;
    }

    public String getCep() {
        return cep;
    }

    public void setCep(String cep) {
        this.cep = cep;
    }

    public Set<Usuario> getClientes() {
        return clientes;
    }

    public void setClientes(Set<Usuario> clientes) {
        this.clientes = clientes;
    }
}

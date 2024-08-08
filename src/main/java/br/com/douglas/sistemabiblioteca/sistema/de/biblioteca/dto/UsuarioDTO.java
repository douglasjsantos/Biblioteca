package br.com.douglas.sistemabiblioteca.sistema.de.biblioteca.dto;

import br.com.douglas.sistemabiblioteca.sistema.de.biblioteca.domain.Endereco;

import java.util.Set;

public class UsuarioDTO {
    private Long id;
    private String nome;
    private String email;
    private String senha;
    private String cpf;
    private String telefone;
    private Set<EnderecoDTO> enderecos;

    public UsuarioDTO(Long id, String nome, String email, String senha, String cpf, String telefone, Set<EnderecoDTO> enderecos) {
        this.id = id;
        this.nome = nome;
        this.email = email;
        this.senha = senha;
        this.cpf = cpf;
        this.telefone = telefone;
        this.enderecos = enderecos;
    }

    public UsuarioDTO() {}

    public UsuarioDTO(String nome, String email, String cpf, String telefone, Set<EnderecoDTO> enderecos) {
        this.nome = nome;
        this.email = email;
        this.cpf = cpf;
        this.telefone = telefone;
        this.enderecos = enderecos;
    }

    public UsuarioDTO(Long id, String nome, String email, String cpf, String telefone) {
        this.nome = nome;
        this.email = email;
        this.cpf = cpf;
        this.telefone = telefone;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }


    public Set<EnderecoDTO> getEnderecos() {
        return enderecos;
    }

    public void setEnderecos(Set<EnderecoDTO> enderecos) {
        this.enderecos = enderecos;
    }
}


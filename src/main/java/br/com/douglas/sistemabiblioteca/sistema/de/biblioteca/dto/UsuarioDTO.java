package br.com.douglas.sistemabiblioteca.sistema.de.biblioteca.dto;

import br.com.douglas.sistemabiblioteca.sistema.de.biblioteca.domain.Endereco;

import java.util.Set;

public record UsuarioDTO(Long id, String nome, String email, String senha, String cpf, String telefone, Set<EnderecoDTO> enderecos) {
}

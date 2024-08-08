package br.com.douglas.sistemabiblioteca.sistema.de.biblioteca.repository;

import br.com.douglas.sistemabiblioteca.sistema.de.biblioteca.domain.Endereco;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EnderecoRepository extends JpaRepository<Endereco, Long> {
}

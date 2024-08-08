package br.com.douglas.sistemabiblioteca.sistema.de.biblioteca.repository;

import br.com.douglas.sistemabiblioteca.sistema.de.biblioteca.domain.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Long> {

    Usuario findByCpf(String cpf);
}

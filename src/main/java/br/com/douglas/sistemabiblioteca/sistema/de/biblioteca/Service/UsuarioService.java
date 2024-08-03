package br.com.douglas.sistemabiblioteca.sistema.de.biblioteca.Service;

import br.com.douglas.sistemabiblioteca.sistema.de.biblioteca.domain.Endereco;
import br.com.douglas.sistemabiblioteca.sistema.de.biblioteca.domain.Usuario;
import br.com.douglas.sistemabiblioteca.sistema.de.biblioteca.dto.EnderecoDTO;
import br.com.douglas.sistemabiblioteca.sistema.de.biblioteca.dto.UsuarioDTO;
import br.com.douglas.sistemabiblioteca.sistema.de.biblioteca.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository repository;

    @Transactional
    public UsuarioDTO buscarUsuarioPorId(Long id){
        Optional<Usuario> obj = repository.findById(id);
        Usuario entity = obj.orElseThrow();
        Set<EnderecoDTO> enderecosDTO = new HashSet<>();
        for (Endereco endereco : entity.getEnderecos()) {
            enderecosDTO.add(new EnderecoDTO(endereco.getId(), endereco.getRua(), endereco.getBairro(), endereco.getNumero(), endereco.getEstado(), endereco.getCidade(), endereco.getCep()));
        }

        return new UsuarioDTO(entity.getId(), entity.getNome(), entity.getEmail(), entity.getSenha(), entity.getCpf(), entity.getTelefone(), enderecosDTO );
    }

    public UsuarioDTO inserir(UsuarioDTO usuarioDTO){
        Usuario entity = new Usuario();
        entity.setNome(usuarioDTO.nome());
        entity.setEmail(usuarioDTO.email());
        entity.setSenha(usuarioDTO.senha());
        entity.setCpf(usuarioDTO.cpf());
        entity.setTelefone(usuarioDTO.telefone());

        Set<Endereco> enderecos = new HashSet<>();
        for (EnderecoDTO enderecoDTO : usuarioDTO.enderecos()) {
            Endereco endereco = new Endereco();
            endereco.setRua(enderecoDTO.rua());
            endereco.setBairro(enderecoDTO.bairro());
            endereco.setNumero(enderecoDTO.numero());
            endereco.setEstado(enderecoDTO.estado());
            endereco.setCidade(enderecoDTO.cidade());
            endereco.setCep(enderecoDTO.cep());
        }
        entity.setEnderecos(enderecos);

        repository.save(entity);

        Set<EnderecoDTO> enderecosDTO = new HashSet<>();
        for (Endereco endereco : entity.getEnderecos()) {
            enderecosDTO.add(new EnderecoDTO(endereco.getId(), endereco.getRua(), endereco.getBairro(), endereco.getNumero(), endereco.getEstado(), endereco.getCidade(), endereco.getCep()));
        }

        return new UsuarioDTO(entity.getId(), entity.getNome(), entity.getEmail(), entity.getSenha(), entity.getCpf(), entity.getTelefone(), enderecosDTO);
    }

}

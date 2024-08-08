package br.com.douglas.sistemabiblioteca.sistema.de.biblioteca.Service;

import br.com.douglas.sistemabiblioteca.sistema.de.biblioteca.domain.Endereco;
import br.com.douglas.sistemabiblioteca.sistema.de.biblioteca.domain.Usuario;
import br.com.douglas.sistemabiblioteca.sistema.de.biblioteca.dto.EnderecoDTO;
import br.com.douglas.sistemabiblioteca.sistema.de.biblioteca.dto.UsuarioDTO;
import br.com.douglas.sistemabiblioteca.sistema.de.biblioteca.repository.EnderecoRepository;
import br.com.douglas.sistemabiblioteca.sistema.de.biblioteca.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository repository;

    @Autowired
    private EnderecoRepository enderecoRepository;


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

    @Transactional
    public UsuarioDTO buscarUsuarioPorCpf(String cpf){
        Usuario usuario = repository.findByCpf(cpf);

        // Construindo a lista de EnderecoDTO
        Set<EnderecoDTO> enderecosDTO = new HashSet<>();
        for (Endereco endereco : usuario.getEnderecos()) {
            enderecosDTO.add(new EnderecoDTO(endereco.getId(), endereco.getRua(), endereco.getBairro(), endereco.getNumero(), endereco.getEstado(), endereco.getCidade(), endereco.getCep()));
        }

        // Retornando o novo DTO sem id e senha
        return new UsuarioDTO(usuario.getNome(), usuario.getEmail(), usuario.getCpf(), usuario.getTelefone(), enderecosDTO);
    }

    @Transactional
    public UsuarioDTO inserir(UsuarioDTO usuarioDTO){
        Usuario entity = new Usuario();

        entity.setNome(usuarioDTO.getNome());
        entity.setEmail(usuarioDTO.getEmail());
        entity.setSenha(usuarioDTO.getSenha());
        entity.setCpf(usuarioDTO.getCpf());
        entity.setTelefone(usuarioDTO.getTelefone());

        Set<Endereco> enderecos = new HashSet<>();
        for (EnderecoDTO enderecoDTO : usuarioDTO.getEnderecos()) {
            Endereco endereco = new Endereco();
            endereco.setRua(enderecoDTO.rua());
            endereco.setBairro(enderecoDTO.bairro());
            endereco.setNumero(enderecoDTO.numero());
            endereco.setEstado(enderecoDTO.estado());
            endereco.setCidade(enderecoDTO.cidade());
            endereco.setCep(enderecoDTO.cep());
            enderecos.add(enderecoRepository.save(endereco));
        }
        entity.setEnderecos(enderecos);

        repository.save(entity);

        Set<EnderecoDTO> enderecosDTO = new HashSet<>();
        for (Endereco endereco : entity.getEnderecos()) {
            enderecosDTO.add(new EnderecoDTO(endereco.getId(), endereco.getRua(), endereco.getBairro(), endereco.getNumero(), endereco.getEstado(), endereco.getCidade(), endereco.getCep()));
        }

        return new UsuarioDTO(entity.getId(), entity.getNome(), entity.getEmail(), entity.getSenha(), entity.getCpf(), entity.getTelefone(), enderecosDTO);
    }


    @Transactional(readOnly = true)
    public Page<UsuarioDTO> buscarTodosOsUsuarios(Pageable pageable){
        Page<Usuario> list = repository.findAll(pageable);

        return list.map(usuario -> new UsuarioDTO(
                usuario.getId(),
                usuario.getNome(),
                usuario.getEmail(),
                usuario.getCpf(),
                usuario.getTelefone()
        ));
    }

}

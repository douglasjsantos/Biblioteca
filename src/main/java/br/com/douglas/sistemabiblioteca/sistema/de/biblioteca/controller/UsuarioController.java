package br.com.douglas.sistemabiblioteca.sistema.de.biblioteca.controller;

import br.com.douglas.sistemabiblioteca.sistema.de.biblioteca.Service.UsuarioService;
import br.com.douglas.sistemabiblioteca.sistema.de.biblioteca.dto.UsuarioDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("/api")
public class UsuarioController {

    @Autowired
    private UsuarioService service;

    @GetMapping
    public String hello(){
        return "Olá";
    }

    @GetMapping("/id/{id}")
    public ResponseEntity<UsuarioDTO> buscarPorId(@PathVariable Long id){
        UsuarioDTO dto = service.buscarUsuarioPorId(id);

        return ResponseEntity.ok().body(dto);
    }

    @PostMapping("/usuario")
    public ResponseEntity<UsuarioDTO> salvar(@RequestBody UsuarioDTO dto){
        dto = service.inserir(dto);

        URI uri = ServletUriComponentsBuilder.fromCurrentRequestUri().path("/{id}")
                .buildAndExpand(dto.getId()).toUri();

        return ResponseEntity.created(uri).body(dto);
    }

    @GetMapping("/cpf/{cpf}")
    public ResponseEntity<UsuarioDTO> buscarUsuarioPorCpf(@PathVariable String cpf) {
        UsuarioDTO dto = service.buscarUsuarioPorCpf(cpf);

        return ResponseEntity.ok().body(dto);
    }

    @GetMapping("/usuario")
    public ResponseEntity<Page<UsuarioDTO>> buscarTodosOsUsuarios(Pageable pageable){
        Page<UsuarioDTO> list = service.buscarTodosOsUsuarios(pageable);

        return ResponseEntity.ok().body(list);
    }

}

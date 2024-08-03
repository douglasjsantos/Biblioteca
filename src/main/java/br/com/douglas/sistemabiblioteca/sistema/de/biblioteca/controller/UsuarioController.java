package br.com.douglas.sistemabiblioteca.sistema.de.biblioteca.controller;

import br.com.douglas.sistemabiblioteca.sistema.de.biblioteca.Service.UsuarioService;
import br.com.douglas.sistemabiblioteca.sistema.de.biblioteca.dto.UsuarioDTO;
import org.springframework.beans.factory.annotation.Autowired;
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

    @GetMapping("/{id}")
    public ResponseEntity<UsuarioDTO> buscarPorId(@PathVariable Long id){
        UsuarioDTO dto = service.buscarUsuarioPorId(id);

        return ResponseEntity.ok().body(dto);
    }

    @PostMapping
    public ResponseEntity<UsuarioDTO> salvar(@RequestBody UsuarioDTO dto){
        dto = service.inserir(dto);

        URI uri = ServletUriComponentsBuilder.fromCurrentRequestUri().path("/{id}")
                .buildAndExpand(dto.id()).toUri();

        return ResponseEntity.created(uri).body(dto);
    }
}

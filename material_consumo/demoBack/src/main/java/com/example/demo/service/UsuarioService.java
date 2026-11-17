package com.example.demo.service;

import com.example.demo.controller.dto.UsuarioRequest;
import com.example.demo.controller.dto.UsuarioResponse;
import com.example.demo.model.Usuario;
import com.example.demo.repository.UsuarioRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UsuarioService {

    private final UsuarioRepository usuarioRepository;

    public UsuarioService(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    public List<UsuarioResponse> listar() {
        return usuarioRepository.findAll()
                .stream()
                .map(this::aResponse)
                .toList();
    }

    public UsuarioResponse buscar(Long id) {
        return aResponse(obtener(id));
    }

    public UsuarioResponse crear(UsuarioRequest datos) {
        Usuario usuario = new Usuario();
        usuario.setNombre(datos.getNombre());
        usuario.setCorreo(datos.getCorreo());
        usuario.setEdad(datos.getEdad());
        return aResponse(usuarioRepository.save(usuario));
    }

    public UsuarioResponse actualizar(Long id, UsuarioRequest datos) {
        Usuario usuario = obtener(id);
        usuario.setNombre(datos.getNombre());
        usuario.setCorreo(datos.getCorreo());
        usuario.setEdad(datos.getEdad());
        return aResponse(usuarioRepository.save(usuario));
    }

    public void eliminar(Long id) {
        usuarioRepository.delete(obtener(id));
    }

    private Usuario obtener(Long id) {
        return usuarioRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("El usuario no existe"));
    }

    private UsuarioResponse aResponse(Usuario usuario) {
        return new UsuarioResponse(
                usuario.getId(),
                usuario.getNombre(),
                usuario.getCorreo(),
                usuario.getEdad()
        );
    }
}

package com.example.demo.config;

import com.example.demo.model.Usuario;
import com.example.demo.repository.UsuarioRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class DatosIniciales implements CommandLineRunner {

    private final UsuarioRepository usuarioRepository;

    public DatosIniciales(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    @Override
    public void run(String... args) {
        if (usuarioRepository.count() > 0) {
            return;
        }

        usuarioRepository.save(new Usuario(null, "Ana Pérez", "ana@correo.com", 24));
        usuarioRepository.save(new Usuario(null, "Luis Ramírez", "luis@correo.com", 29));
        usuarioRepository.save(new Usuario(null, "María Gómez", "maria@correo.com", 21));
    }
}

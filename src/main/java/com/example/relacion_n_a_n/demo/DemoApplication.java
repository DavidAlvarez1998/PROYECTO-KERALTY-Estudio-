package com.example.relacion_n_a_n.demo;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.example.relacion_n_a_n.demo.models.EstudioModel;
import com.example.relacion_n_a_n.demo.models.UsuarioModel;
import com.example.relacion_n_a_n.demo.services.EstudioService;
import com.example.relacion_n_a_n.demo.services.UsuarioService;

@SpringBootApplication
public class DemoApplication implements CommandLineRunner {

	@Autowired
	UsuarioService usuarioService;

	@Autowired
	EstudioService estudioService;

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		consultarEstudiosPorUsuarios();
	}

	public void consultarEstudiosPorUsuarios() {
		Optional<UsuarioModel> usuOptional = usuarioService.ConsultarEstudiosPorUsuario(1l);
		System.out.println(usuOptional);
	}

	public void crearEstudiosPorUsuario() {
		EstudioModel estudio = EstudioModel.builder()
				.nombre("Ingles")
				.horas(108)
				.build();
		EstudioModel estudio2 = EstudioModel.builder()
				.nombre("React")
				.horas(39)
				.build();
		UsuarioModel usuario = usuarioService.registrarEstudiosPorUsuario(1L, List.of(estudio, estudio2));
		System.out.println(usuario);

	}

	public void CrearUsuarioConEstudios() {
		EstudioModel estudio = EstudioModel.builder()
				.nombre("Canto")
				.horas(60)
				.build();
		EstudioModel estudio2 = EstudioModel.builder()
				.nombre("N")
				.horas(66)
				.build();
		UsuarioModel usurio = UsuarioModel.builder()
				.nombre("Leo Messi")
				.estado("Activo")
				.estudioList(List.of(estudio, estudio2)).build();
		UsuarioModel u = usuarioService.createUsuario(usurio);
		System.out.println(u);

	}

}

package com.example.relacion_n_a_n.demo;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.example.relacion_n_a_n.demo.models.EstudioModel;
import com.example.relacion_n_a_n.demo.models.RelacionModel;
import com.example.relacion_n_a_n.demo.models.UsuarioModel;
import com.example.relacion_n_a_n.demo.services.EstudioService;
import com.example.relacion_n_a_n.demo.services.RelacionService;
import com.example.relacion_n_a_n.demo.services.UsuarioService;

@SpringBootApplication
public class DemoApplication implements CommandLineRunner {

	@Autowired
	UsuarioService usuarioService;

	@Autowired
	EstudioService estudioService;

	@Autowired
	RelacionService relacionService;

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		// consultarEstudiosPorUsuarios();
		// CrearUsuarioConEstudios();
		// consultarEstudiosPorUsuarios

		System.out.println(crearEstudiosPorUsuario());
	}

	////////////////////////////////////////////////////////////

	public List<RelacionModel> consultarEstudiosPorUsuarios() {
		List<RelacionModel> usuOptional = usuarioService.ConsultarEstudiosPorUsuario(1L);
		return usuOptional;
	}

	public UsuarioModel crearEstudiosPorUsuario() {
		EstudioModel estudio = EstudioModel.builder()
				.nombre("Ingles")
				.horas(50)
				.build();
		EstudioModel estudio2 = EstudioModel.builder()
				.nombre("React")
				.horas(21)
				.build();
		UsuarioModel usuario = usuarioService.registrarEstudiosPorUsuario(3L, List.of(estudio, estudio2));
		return usuario;

	}

	public UsuarioModel CrearUsuarioConEstudios() {
		UsuarioModel usuario = UsuarioModel.builder()
				.nombres("Leo")
				.apellidos("Messi")
				.email("lmessi@g.com")
				.celular("31344359003")
				.estado("Activo")
				.build();

		// Guardo Usuario
		UsuarioModel usuarioCreado = usuarioService.createUsuario(usuario);

		EstudioModel estudio1 = EstudioModel.builder()
				.nombre("ReactNative")
				.horas(44)
				.fechaInicio(LocalDateTime.of(2025, 1, 1, 7, 0))
				.fechaFin(LocalDateTime.of(2025, 6, 28, 11, 0))
				.build();

		EstudioModel estudio2 = EstudioModel.builder()
				.nombre("SQL")
				.horas(11)
				.fechaInicio(LocalDateTime.of(2025, 1, 1, 7, 0))
				.fechaFin(LocalDateTime.of(2025, 6, 28, 11, 0))
				.build();

		// Guardo Estudios
		EstudioModel estudioGuardado1 = estudioService.createEstudio(estudio1);
		EstudioModel estudioGuardado2 = estudioService.createEstudio(estudio2);

		// Crear La Relacion
		RelacionModel relacion1 = RelacionModel.builder()
				.usuario(usuarioCreado)
				.estudio(estudioGuardado1)
				.estado("activo")
				.build();

		RelacionModel relacion2 = RelacionModel.builder()
				.usuario(usuarioCreado)
				.estudio(estudioGuardado2)
				.estado("activo")
				.build();

		// Guardar Relacion
		relacionService.createRelacion(relacion1);
		relacionService.createRelacion(relacion2);

		Optional<UsuarioModel> usuarioOptional = usuarioService.buscarPorID(usuarioCreado.getUsuario_id());

		if (usuarioOptional.isPresent()) {
			return usuarioOptional.get();
		} else {
			return null;
		}
	}

}

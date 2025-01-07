package com.example.relacion_n_a_n.demo;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.example.relacion_n_a_n.demo.models.EstudioModel;
import com.example.relacion_n_a_n.demo.models.RelacionId;
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

		// CrearUsuarioConEstudios();
		// consultarEstudiosPorUsuarios();
		// crearEstudiosPorUsuario();

		// System.out.println(consultarEstudiosPorUsuarios());
	}

	/// 
	/// 
	/// 
	/// 

	public List<EstudioModel> consultarEstudiosPorUsuarios() {
		List<EstudioModel> usuOptional = usuarioService.ConsultarEstudiosPorUsuario(1L);
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
		UsuarioModel usuario = usuarioService.registrarEstudiosPorUsuario(1L, List.of(estudio, estudio2));
		return usuario;

	}

	public UsuarioModel CrearUsuarioConEstudios() {
		UsuarioModel usuario = UsuarioModel.builder()
				.nombres("Shakira")
				.apellidos("Aguirre")
				.email("shaa@g.com")
				.celular("3112259003")
				.estado("Activo")
				.build();

		// Guardo Usuario
		UsuarioModel usuarioCreado = usuarioService.createUsuario(usuario);

		EstudioModel estudio1 = EstudioModel.builder()
				.nombre("Python")
				.horas(44)
				.fechaInicio(LocalDateTime.of(2025, 1, 1, 7, 0))
				.fechaFin(LocalDateTime.of(2025, 6, 28, 11, 0))
				.build();

		EstudioModel estudio2 = EstudioModel.builder()
				.nombre("Java")
				.horas(11)
				.fechaInicio(LocalDateTime.of(2025, 1, 1, 7, 0))
				.fechaFin(LocalDateTime.of(2025, 6, 28, 11, 0))
				.build();

		// Guardo Estudios
		EstudioModel estudioGuardado1 = estudioService.createEstudio(estudio1);
		EstudioModel estudioGuardado2 = estudioService.createEstudio(estudio2);

		// Crear las relaciones con claves compuestas
		RelacionId relacionId1 = new RelacionId(usuarioCreado.getUsuario_id(), estudioGuardado1.getEstudio_id());
		RelacionId relacionId2 = new RelacionId(usuarioCreado.getUsuario_id(), estudioGuardado2.getEstudio_id());

		RelacionModel relacion1 = RelacionModel.builder()
				.relacionId(relacionId1)
				.usuario(usuarioCreado)
				.estudio(estudioGuardado1)
				.estado("activo")
				.build();

		RelacionModel relacion2 = RelacionModel.builder()
				.relacionId(relacionId2)
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

package com.example.relacion_n_a_n.demo.controllers;

import com.example.relacion_n_a_n.demo.DTOs.request.EstudioRequestDTO;
import com.example.relacion_n_a_n.demo.DTOs.response.EstudioResponseDTO;
import com.example.relacion_n_a_n.demo.services.EstudioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/estudio")
@CrossOrigin(origins = "http://localhost:5173", methods = { RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT,
        RequestMethod.DELETE })
public class EstudioController {

    @Autowired
    EstudioService estudioService;

    // CREATE
    @PostMapping
    public ResponseEntity<EstudioResponseDTO> createEstudio(@RequestBody EstudioRequestDTO estudioRequestDTO) {
        EstudioResponseDTO estudioCreado = estudioService.createEstudio(estudioRequestDTO);
        // Devuelves 201 CREATED + el objeto en el body
        return ResponseEntity.status(HttpStatus.CREATED).body(estudioCreado);
    }

    // READ (listar)
    @GetMapping
    public ResponseEntity<List<EstudioResponseDTO>> allEstudios() {
        List<EstudioResponseDTO> listaEstudios = estudioService.allEstudios();
        // 200 OK con la lista
        return ResponseEntity.ok(listaEstudios);
    }

    // READ (buscar por ID)
    @GetMapping("/{id}")
    public ResponseEntity<EstudioResponseDTO> obtenerEstudioPorId(@PathVariable("id") Long estudio_id) {
        EstudioResponseDTO estudio = estudioService.findById(estudio_id);
        // 200 OK con el estudio
        return ResponseEntity.ok(estudio);
    }

    // UPDATE
    @PutMapping("/{id}")
    public ResponseEntity<EstudioResponseDTO> updateEstudio(
            @PathVariable("id") Long estudioId,
            @RequestBody EstudioRequestDTO estudioRequestDTO) {
        EstudioResponseDTO estudioActualizado = estudioService.updateEstudio(estudioId, estudioRequestDTO);
        // 200 OK con el estudio actualizado
        return ResponseEntity.ok(estudioActualizado);
    }

    // DELETE
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteEstudio(@PathVariable("id") Long estudioId) {
        estudioService.deleteEstudio(estudioId);
        // 204 No Content
        return ResponseEntity.noContent().build();
    }
}

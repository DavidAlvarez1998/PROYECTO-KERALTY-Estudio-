package com.example.relacion_n_a_n.demo.controllers;

import com.example.relacion_n_a_n.demo.DTOs.request.EstudioRequestDTO;
import com.example.relacion_n_a_n.demo.DTOs.response.EstudioResponseDTO;
import com.example.relacion_n_a_n.demo.services.EstudioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/estudio")
public class EstudioController {

    @Autowired
    EstudioService estudioService;

    @PostMapping()
    public EstudioResponseDTO createEstudio(@RequestBody EstudioRequestDTO estudioRequestDTO) {
        return estudioService.createEstudio(estudioRequestDTO);
    }

    @GetMapping()
    public List<EstudioResponseDTO> allEstudio() {
        return estudioService.allEstudios();
    }

    @GetMapping(path = "/{id}")
    public EstudioResponseDTO obtenerEstudioPorId(@PathVariable("id") Long estudio_id) {
        return estudioService.findById(estudio_id);
    }
}

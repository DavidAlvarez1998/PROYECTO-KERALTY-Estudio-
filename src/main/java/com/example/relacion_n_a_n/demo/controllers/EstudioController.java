package com.example.relacion_n_a_n.demo.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.relacion_n_a_n.demo.models.EstudioModel;
import com.example.relacion_n_a_n.demo.services.EstudioService;

@RestController
@RequestMapping("/estudio")
public class EstudioController {

    @Autowired
    EstudioService estudioService;

    @PostMapping()
    public EstudioModel createEstudio(@RequestBody EstudioModel estudio) {
        return estudioService.createEstudio(estudio);
    }

    @GetMapping()
    public List<EstudioModel> allEstudio() {
        return estudioService.allEstudios();
    }

}

package com.example.relacion_n_a_n.demo.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "tbl_estudios")
public class EstudioModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long estudio_id;
    
    @Column(nullable = false, unique = true)
    private String nombre;
    
    private Integer horas;

}

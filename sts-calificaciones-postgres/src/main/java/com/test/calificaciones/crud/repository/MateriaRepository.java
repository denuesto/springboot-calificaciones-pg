package com.test.calificaciones.crud.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.test.calificaciones.crud.entity.Materia;

@Repository
public interface MateriaRepository extends JpaRepository< Materia, Integer>{

}

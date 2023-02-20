package com.test.calificaciones.crud.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.test.calificaciones.crud.entity.Alumno;
import com.test.calificaciones.crud.entity.Calificacion;

@Repository
public interface CalificacionRepository extends JpaRepository<Calificacion, Integer>{

	public List<Calificacion> findByAlumno(Alumno alumno);
}

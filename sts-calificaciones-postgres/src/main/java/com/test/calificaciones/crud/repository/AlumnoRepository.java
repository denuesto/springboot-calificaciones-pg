package com.test.calificaciones.crud.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.test.calificaciones.crud.entity.Alumno;

@Repository
public interface AlumnoRepository extends JpaRepository<Alumno, Integer>{
	
	public List<Alumno> findByNombreAndApPaternoAndApMaterno(String nombre,String apPaterno, String apMaterno);

}

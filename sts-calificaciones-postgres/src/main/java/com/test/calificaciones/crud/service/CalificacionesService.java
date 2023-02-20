package com.test.calificaciones.crud.service;

import java.util.List;

import com.test.calificaciones.crud.entity.Alumno;
import com.test.calificaciones.crud.entity.Calificacion;
import com.test.calificaciones.crud.entity.Materia;
import com.test.calificaciones.crud.request.dto.CalificacionRequestDTO;
import com.test.calificaciones.crud.response.dto.CalificacionesAlumnoResponse;

public interface CalificacionesService {
	
	public Calificacion save(CalificacionRequestDTO calificacionRequestDTO);
	
	public Calificacion update(Integer idCalificacion,Double calific);
	
	public CalificacionesAlumnoResponse getCalificacionesAlumno(Integer id);
	
	public void delete(Integer idCalificacion);
	
	public List<Alumno> getAlumnos();
	
	public List<Materia> getMaterias();

}

package com.test.calificaciones.crud.service.impl;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.test.calificaciones.crud.entity.Alumno;
import com.test.calificaciones.crud.entity.Calificacion;
import com.test.calificaciones.crud.entity.Materia;
import com.test.calificaciones.crud.repository.AlumnoRepository;
import com.test.calificaciones.crud.repository.CalificacionRepository;
import com.test.calificaciones.crud.repository.MateriaRepository;
import com.test.calificaciones.crud.request.dto.CalificacionRequestDTO;
import com.test.calificaciones.crud.response.dto.CalificacionAlumnoDTO;
import com.test.calificaciones.crud.response.dto.CalificacionesAlumnoResponse;
import com.test.calificaciones.crud.service.CalificacionesService;

@Service
public class CalificacionesServiceImpl implements CalificacionesService {
	
	@Autowired
	private AlumnoRepository alumnoRepository;
	
	@Autowired
	private MateriaRepository materiaRepository;
	
	@Autowired
	private CalificacionRepository calificacionRepository;

	@Override
	public Calificacion save(CalificacionRequestDTO calificacionRequestDTO) {
		
		
		Alumno alumno = alumnoRepository.findById(calificacionRequestDTO.getIdAlumno())
				.orElseThrow(()-> new EntityNotFoundException("no se encontró Alumno"));
		
		Materia materia = materiaRepository.findById(calificacionRequestDTO.getIdMateria())
				.orElseThrow(() -> new EntityNotFoundException("no se encontró Materia"));
		
		Calificacion calificacion = new Calificacion();
		calificacion.setAlumno(alumno);
		calificacion.setMateria(materia);
		calificacion.setFechaRegistro(new java.sql.Date( new java.util.Date().getTime()));
		calificacion.setCalificacion(BigDecimal.valueOf(calificacionRequestDTO.getCalificacion()));
		
		return calificacionRepository.save(calificacion);
	}
	

	@Override
	public CalificacionesAlumnoResponse getCalificacionesAlumno(Integer id) {
		CalificacionesAlumnoResponse calificacionesAlumnoResponse = null;
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/YYYY");
		List<CalificacionAlumnoDTO> calificacionesAlumno = new ArrayList<>();
		
		Alumno alumno = alumnoRepository.findById(id)
							.orElseThrow(()-> new EntityNotFoundException("no se encontró Alumno"));
		
		List<Calificacion> calificaciones =   calificacionRepository.findByAlumno(alumno);
		if(calificaciones!= null && !calificaciones.isEmpty()) {
			 Double promedio = 0d;
			calificacionesAlumnoResponse = new CalificacionesAlumnoResponse();
			
			for(Calificacion c: calificaciones) {
				promedio = promedio + c.getCalificacion().doubleValue();
				
				calificacionesAlumno.add(new CalificacionAlumnoDTO(c.getAlumno().getId(),
																	c.getId(),
																	c.getAlumno().getNombre(),
																	c.getAlumno().getApPaterno(),
																	c.getMateria().getNombre(),
																	c.getCalificacion().doubleValue(),
																	sdf.format(c.getFechaRegistro())));
			}
			
			promedio= promedio/calificaciones.size();
			calificacionesAlumnoResponse.setCalificacionesAlumno(calificacionesAlumno);
			calificacionesAlumnoResponse.setPromedio(promedio);
		}else {
			 throw new EntityNotFoundException("no se encontraron calificaciones del alumno");
		}
		
		return calificacionesAlumnoResponse;
	}

	@Override
	public Calificacion update(Integer idCalificacion, Double calific) {
		
		Calificacion calificacion = calificacionRepository.findById(idCalificacion )
				.orElseThrow(() -> new EntityNotFoundException("no se encontró Calificación"));
		
		calificacion.setFechaRegistro(new java.sql.Date( new java.util.Date().getTime()));
		calificacion.setCalificacion(BigDecimal.valueOf(calific));
		
		return calificacionRepository.save(calificacion);
	}

	@Override
	public void delete(Integer idCalificacion) {
		
		Calificacion calificacion = calificacionRepository.findById(idCalificacion )
				.orElseThrow(() -> new EntityNotFoundException("no se encontró Calificación"));
		
		calificacionRepository.delete(calificacion);
		
	}
	

	@Override
	public List<Alumno> getAlumnos() {
		return alumnoRepository.findAll();
	}

	@Override
	public List<Materia> getMaterias() {
		return materiaRepository.findAll();
	}

}

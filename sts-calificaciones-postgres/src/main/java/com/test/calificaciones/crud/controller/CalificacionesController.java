package com.test.calificaciones.crud.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.test.calificaciones.crud.entity.Alumno;
import com.test.calificaciones.crud.entity.Calificacion;
import com.test.calificaciones.crud.entity.Materia;
import com.test.calificaciones.crud.request.dto.CalificacionRequestDTO;
import com.test.calificaciones.crud.response.dto.CalificacionesAlumnoResponse;
import com.test.calificaciones.crud.response.dto.MensajeResponse;
import com.test.calificaciones.crud.service.CalificacionesService;

import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/api/calificaciones")
@Slf4j
public class CalificacionesController {
	
	@Autowired
	private CalificacionesService calificacionesService;
	
	
	@GetMapping(value = "/alumno/{alumno}")
	public ResponseEntity<CalificacionesAlumnoResponse> getCalificacionesAlumno(@PathVariable("alumno") Integer id) {
		return ResponseEntity.ok( calificacionesService.getCalificacionesAlumno(id));
	}
	
	@PostMapping(value = "/save")
	public ResponseEntity<MensajeResponse> saveCalificacion(@Valid @RequestBody  CalificacionRequestDTO calificacionRequestDTO){
		
		log.info(calificacionRequestDTO.toString());
		MensajeResponse mensajeResponse = null;
		
		Calificacion calificacion = calificacionesService.save(calificacionRequestDTO);
		mensajeResponse = new MensajeResponse();
		
		if(calificacion!= null) {
			mensajeResponse.setSuccess("ok");
			mensajeResponse.setMsg("calificacion registrada");
			return  ResponseEntity.ok(mensajeResponse);
		}else {
			mensajeResponse.setSuccess("Error");
			mensajeResponse.setMsg("No se pudo registrar la calificacion");
			return  ResponseEntity.internalServerError().body(mensajeResponse);
		}
	}
	
	@PutMapping(value = "/update/{idCalificacion}/{calificacion}")
	public ResponseEntity<MensajeResponse> updateCalificacion(@PathVariable("idCalificacion") Integer id,
															 @PathVariable("calificacion") Double calif){
		
		
		MensajeResponse mensajeResponse = null;
		
		Calificacion calificacion = calificacionesService.update(id,calif);
		mensajeResponse = new MensajeResponse();
		
		if(calificacion!= null) {
			mensajeResponse.setSuccess("ok");
			mensajeResponse.setMsg("calificacion actualizada");
			return  ResponseEntity.ok(mensajeResponse);
		}else {
			mensajeResponse.setSuccess("Error");
			mensajeResponse.setMsg("No se pudo actualizar la calificacion");
			return  ResponseEntity.internalServerError().body(mensajeResponse);
		}
	}
	
	@DeleteMapping(value = "/delete/{idCalificacion}")
	public ResponseEntity<MensajeResponse> updateCalificacion(@PathVariable("idCalificacion") Integer id){

			MensajeResponse mensajeResponse = null;
			calificacionesService.delete(id);
			
			mensajeResponse = new MensajeResponse();
			mensajeResponse.setSuccess("ok");
			mensajeResponse.setMsg("calificacion eliminada");
			return  ResponseEntity.ok(mensajeResponse);
	}
	
	
	
	@GetMapping(value = "/listaalumnos")
	public ResponseEntity<List<Alumno>> getAlumnos() {
		return ResponseEntity.ok( calificacionesService.getAlumnos());
	}
	
	@GetMapping(value = "/listamaterias")
	public ResponseEntity<List<Materia>> getMaterias() {
		return ResponseEntity.ok( calificacionesService.getMaterias());
	}
	
	

}

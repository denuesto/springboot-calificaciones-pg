package com.test.calificaciones.crud.response.dto;

import java.util.List;

import lombok.Data;

@Data
public class CalificacionesAlumnoResponse {
	
	private List<CalificacionAlumnoDTO> calificacionesAlumno;
	
	private Double promedio;

}

package com.test.calificaciones.crud.request.dto;



import javax.validation.constraints.NotNull;

import lombok.Data;

@Data
public class CalificacionRequestDTO {
	
	@NotNull(message = "idMateria es obligatorio")
	private Integer idMateria;
	@NotNull(message = "idAlumno no puede ir vacío")
	private Integer idAlumno;
	@NotNull(message = "calificacion no puede ir vacío")
	private Double calificacion;
	

}

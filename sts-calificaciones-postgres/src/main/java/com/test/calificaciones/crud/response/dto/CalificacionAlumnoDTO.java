package com.test.calificaciones.crud.response.dto;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CalificacionAlumnoDTO implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private Integer id_t_usuario;
	private Integer idCalificacion;
	private String nombre;
	private String apellido;
	private String materia;
	private double calificacion;
	private String fecha_registro;

}

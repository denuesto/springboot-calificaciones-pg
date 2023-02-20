package com.test.calificaciones.crud.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Table(name = "t_calificaciones")
@Data
public class Calificacion  implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_t_calificaciones")
	private Integer id;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "id_t_materias")
	private Materia materia; 
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "id_t_usuarios")
	private Alumno alumno;
	
	@Column(name = "calificacion", precision = 4, scale = 2)
	private BigDecimal calificacion;
	
	@Column(name = "fecha_registro")
	private Date fechaRegistro;

}

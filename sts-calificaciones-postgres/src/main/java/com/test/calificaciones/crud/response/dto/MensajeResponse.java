package com.test.calificaciones.crud.response.dto;

import java.io.Serializable;

import lombok.Data;

@Data
public class MensajeResponse implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String success;
	private String msg;

}

$(document).ready(inicio);

function inicio(){

	llenarTablaAlumnos(1);
	consultaAlumnos(true);
	
	//======= boton Buscar Calificaciones ===============
	$('#btnBuscarCalificacion').on('click', function(){
		let idAlumno = $('#idSelectAlumno').val();
		llenarTablaAlumnos(idAlumno);
		
	});
	
	//======= boton Nuevo ===============
	$('#btnNuevo').on('click', function(){
			limpiar();
			$('#modalAlumno').modal('show');
			$('#estausGuardar').val('1');
			
			consultaAlumnos(false);
			consultaMaterias();
			

			$("#divNuevo").show();
			$("#divEditar").hide();
			acciones();
		
	});
	
	
	
	//======= boton guardar ===============
	$('#btnGuardar').on('click', function(){
		if( validaFormulario() == true ){
		
			if( $('#estausGuardar').val() == '1' ){
				guardar();
			}else{
				actualizar();
			}
			
			
		}
	});	
}  // fin inicio


function consultaAlumnos(selectInicio){
	
	$.ajax({
		url:"api/calificaciones/listaalumnos",
		type:"GET",
		dataType:"json",
		success:function(respuesta){
			
			var datos ="";
			$.each(respuesta, function(i,item){
				
				datos +="<option value='"+item.id+"'>" +item.id + " - "+  item.nombre + " "+ item.apPaterno +"</option>";
			});
			
			if(selectInicio == true){
				$("#idSelectAlumno").html(datos);
			}else{
				$("#selectAlumno").html(datos);
			}
			
		},
		error:function(respuesta){
			console.log(respuesta);
			alert(respuesta.responseJSON.message);
		}
	});
}

function consultaMaterias(){
	$.ajax({
		url:"api/calificaciones/listamaterias",
		type:"GET",
		dataType:"json",
		success:function(respuesta){
			
			var datos ="";
			$.each(respuesta, function(i,item){
				
				datos +="<option value='"+item.id+"'>" +item.id + " - "+  item.nombre + "</option>";
			});
			$("#selectMateria").html(datos);
		},
		error:function(respuesta){
			console.log(respuesta);
			alert(respuesta.responseJSON.message);
		}
	});
}

function guardar(){
	var parametros = {
					 "idMateria": $('#selectMateria').val(),
					 "idAlumno":$('#selectAlumno').val(), 
					 "calificacion":$('#calificacion').val()
					 };
	
	$.ajax({
		url:"api/calificaciones/save",
		type:"POST",
		dataType:"json",
		data: JSON.stringify(parametros),
  		contentType: "application/json; charset=utf-8",
		success:function(respuesta){
			console.log(respuesta);
			
			$('#modalAlumno').modal('hide');
			llenarTablaAlumnos($('#selectAlumno').val());
			limpiar();
			alert(respuesta.msg);
		},
		error:function(jqXHR, textStatus, errorThrown){
			console.log(textStatus);
			var mensaje = "No se pudo concretar la operación";
			$('#modal-error').html(mensaje);
			$('#modal-error').show();
		}
	});	
	    
}


function llenarTablaAlumnos( idAlumno){

	$("#tbodyAlumnos").html("");
	
	$.ajax({
		url:"api/calificaciones/alumno/"+idAlumno,
		type:"GET",
		dataType:"json",
		success:function(respuesta){
			
			var tr ="";
			
			$.each(respuesta.calificacionesAlumno, function(i,item){
				
				tr +="<tr>"  +
					  "<td>" + item.idCalificacion 		 + "</td>"+
					  "<td>" + item.id_t_usuario 	 + "</td>"+
					  "<td>" + item.nombre 	 + "</td>"+
					  "<td>" + item.apellido  + "</td>"+
					  "<td>" + item.materia  + "</td>"+
					  "<td>" + item.calificacion + "</td>"+
					  "<td> <button type='button' class='btn btn-secondary btnEditar' data-id='"           + item.idCalificacion + "' "+ 
					  																 "data-idalumno='"     + item.id_t_usuario         + "' "+
					      															 "data-nombre='"       + item.nombre         + "' "+
					      															 "data-paterno='"      + item.apellido       + "' "+
					      															 "data-materia='"      + item.materia        + "' "+
					      															 "data-calificacion='" + item.calificacion   + "' >Editar</button>	"+
					  "     <button type='button' class='btn btn-danger btnEliminar'  data-id='" 		   + item.idCalificacion +  "' "+ 
						 															 "data-idalumno='"     + item.id_t_usuario   +  "'>Eliminar</button>	</td>"+
					"</tr>";
			});
			
			$("#tbodyAlumnos").append(tr);
			acciones();
		},
		error:function(respuesta){
			console.log(respuesta);
			alert(respuesta.responseJSON.message);
		}
	});
	
	
}

 
function actualizar(){
	
	$.ajax({
		url:"api/calificaciones/update/"+$('#idCalificacion').val()+"/"+$('#calificacion').val(),
		type:"PUT",
		dataType:"json",
  		contentType: "application/json; charset=utf-8",
		success:function(respuesta){
			console.log(respuesta);
			
			
			llenarTablaAlumnos($('#idAlumno').val());
			$('#modalAlumno').modal('hide');
			alert(respuesta.msg);
			limpiar();
			
		},
		error:function(jqXHR, textStatus, errorThrown){
			console.log(textStatus);
			var mensaje = "No se pudo concretar la operación";
			$('#modal-error').html(mensaje);
			$('#modal-error').show();
		}
	});	
	    
}

function acciones(){

	//======editar=============
	$.each($('.btnEditar'), function(i,item){
		$(this).click(function(){
			$('#idCalificacion').val( $(this).data('id'));
			$('#idAlumno').val( $(this).data('idalumno'));
			$('#nombre').val( $(this).data('nombre'));
			$('#paterno').val( $(this).data('paterno'));
			$('#materia').val( $(this).data('materia'));
			$('#calificacion').val( $(this).data('calificacion'));
			
			
			$('#estausGuardar').val('0');
			
			$("#divNuevo").hide();
			$("#divEditar").show();
			$('#modalAlumno').modal('show');
			
		})
	});
	
	//======eliminar============
	$.each($('.btnEliminar'), function(i,item){
		$(this).click(function(){
			if(confirm('Se eliminará el registro')){
			
				var id = $(this).data('id');
				var idAlumno = $(this).data('idalumno');
				$.ajax({
					url:"api/calificaciones/delete/" + id,
					type:"DELETE",
					dataType:"json",
					success:function(respuesta){
						llenarTablaAlumnos(idAlumno);
						alert(respuesta.msg);
					}
				});
				
			}
		})
	});
}

function validaFormulario(){	
	var mensaje="Los siguientes datos son obligatorios <br><br>";
	var esValido = true;
	
	if($('#calificacion').val().length < 1 ){
		esValido = false;
		mensaje +="calificacion debe tener un valor<br>";
	}
	if($('#calificacion').val() > 10 || $('#calificacion').val() < 0 ){
		esValido = false;
		mensaje +="calificacion debe tener un valor entre 0 y 10 <br>";
	}

	if( esValido == false ){
		$('#modal-error').html(mensaje);
		$('#modal-error').show();	
	}
	return esValido;
}

function limpiar(){
	$('#idCalificacion').val('');
	$('#nombre').val('');
	$('#paterno').val('');
	$('#materia').val('');
	$('#calificacion').val('');
	
	
	
	$('#selectAlumno').val('1');
	$('#selectMateria').val('1');
	
	$('#modal-error').html('');
	$('#modal-error').hide();	
}
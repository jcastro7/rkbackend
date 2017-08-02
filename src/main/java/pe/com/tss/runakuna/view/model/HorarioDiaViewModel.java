package pe.com.tss.runakuna.view.model;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import pe.com.tss.runakuna.json.JsonFlagIntegerDeserializer;
import pe.com.tss.runakuna.json.JsonFlagIntegerSerializer;
import pe.com.tss.runakuna.json.JsonStringByteDeserializer;
import pe.com.tss.runakuna.json.JsonStringByteSerializer;
import pe.com.tss.runakuna.json.JsonStringIntegerDeserializer;
import pe.com.tss.runakuna.json.JsonStringIntegerSerializer;

public class HorarioDiaViewModel extends AuditingEntityViewModel{

	private Long idHorarioDia;
	private String codigo;
	private String nombre;
	private String entrada;
	private String salida;
	private String almuerzo;
	
	private String diaSemana;
	private String nombreDiaSemana;
	
	@JsonSerialize(using=JsonFlagIntegerSerializer.class)
	@JsonDeserialize(using=JsonFlagIntegerDeserializer.class)
	private Integer laboral;
	
	private Integer tiempoAlmuerzo;
	
	private Integer numeroMarcaciones; 
	
	public String getCodigo() {
		return codigo;
	}
	
	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}
	
	public String getNombre() {
		return nombre;
	}
	
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getEntrada() {
		return entrada;
	}

	public void setEntrada(String entrada) {
		this.entrada = entrada;
	}

	public String getSalida() {
		return salida;
	}

	public void setSalida(String salida) {
		this.salida = salida;
	}

	public String getAlmuerzo() {
		return almuerzo;
	}

	public void setAlmuerzo(String almuerzo) {
		this.almuerzo = almuerzo;
	}

	public String getDiaSemana() {
		return diaSemana;
	}

	public void setDiaSemana(String diaSemana) {
		this.diaSemana = diaSemana;
	}

	public String getNombreDiaSemana() {
		return nombreDiaSemana;
	}

	public void setNombreDiaSemana(String nombreDiaSemana) {
		this.nombreDiaSemana = nombreDiaSemana;
	}

	public Long getIdHorarioDia() {
		return idHorarioDia;
	}

	public void setIdHorarioDia(Long idHorarioDia) {
		this.idHorarioDia = idHorarioDia;
	}

	public Integer  getLaboral() {
		return laboral;
	}

	public void setLaboral(Integer  laboral) {
		this.laboral = laboral;
	}

	public Integer getTiempoAlmuerzo() {
		return tiempoAlmuerzo;
	}

	public void setTiempoAlmuerzo(Integer tiempoAlmuerzo) {
		this.tiempoAlmuerzo = tiempoAlmuerzo;
	}

	public Integer getNumeroMarcaciones() {
		return numeroMarcaciones;
	}

	public void setNumeroMarcaciones(Integer numeroMarcaciones) {
		this.numeroMarcaciones = numeroMarcaciones;
	}
	
}

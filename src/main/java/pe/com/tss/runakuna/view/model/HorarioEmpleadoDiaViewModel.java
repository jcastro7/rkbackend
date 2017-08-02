package pe.com.tss.runakuna.view.model;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import pe.com.tss.runakuna.json.JsonFlagIntegerDeserializer;
import pe.com.tss.runakuna.json.JsonFlagIntegerSerializer;
import pe.com.tss.runakuna.json.JsonStringIntegerDeserializer;
import pe.com.tss.runakuna.json.JsonStringIntegerSerializer;

public class HorarioEmpleadoDiaViewModel extends AuditingEntityViewModel {

	private Long idHorarioEmpleadoDia;
	private String diaSemana;
	private String entrada;
	private String salida;
	private Integer tiempoAlmuerzo;
	private String nombreDiaSemana;
	
	@JsonSerialize(using=JsonFlagIntegerSerializer.class)
	@JsonDeserialize(using=JsonFlagIntegerDeserializer.class)
	private Integer laboral;
	
	private String nombreLaboral;
	
	private Integer numeroMarcaciones;
	
	public Long getIdHorarioEmpleadoDia() {
		return idHorarioEmpleadoDia;
	}
	public void setIdHorarioEmpleadoDia(Long idHorarioEmpleadoDia) {
		this.idHorarioEmpleadoDia = idHorarioEmpleadoDia;
	}
	public String getDiaSemana() {
		return diaSemana;
	}
	public void setDiaSemana(String diaSemana) {
		this.diaSemana = diaSemana;
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
	public Integer getTiempoAlmuerzo() {
		return tiempoAlmuerzo;
	}
	public void setTiempoAlmuerzo(Integer tiempoAlmuerzo) {
		this.tiempoAlmuerzo = tiempoAlmuerzo;
	}
	public String getNombreDiaSemana() {
		return nombreDiaSemana;
	}
	public void setNombreDiaSemana(String nombreDiaSemana) {
		this.nombreDiaSemana = nombreDiaSemana;
	}
	public Integer getLaboral() {
		return laboral;
	}
	public void setLaboral(Integer laboral) {
		this.laboral = laboral;
	}
	public String getNombreLaboral() {
		return nombreLaboral;
	}
	public void setNombreLaboral(String nombreLaboral) {
		this.nombreLaboral = nombreLaboral;
	}
	public Integer getNumeroMarcaciones() {
		return numeroMarcaciones;
	}
	public void setNumeroMarcaciones(Integer numeroMarcaciones) {
		this.numeroMarcaciones = numeroMarcaciones;
	}
	
}
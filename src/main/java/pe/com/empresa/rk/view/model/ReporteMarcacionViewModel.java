package pe.com.empresa.rk.view.model;

import java.util.List;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import pe.com.empresa.rk.json.JsonFlagIntegerDeserializer;
import pe.com.empresa.rk.json.JsonFlagIntegerSerializer;

/**
 * Created by josediaz on 14/12/2016.
 */
public class ReporteMarcacionViewModel extends AuditingEntityViewModel {

    private Long idReporteMarcacion;
    private Long idEmpresa;
    private Long idUnidadDeNegocio;
    private Long idDepartamentoArea;
    
    private Long idProyecto;
    private Long idEmpleado;
    private String nombreEmpleado;
    private String nombreProyecto;
    private Long idJefeProyecto;
    private Long idJefe;
    private String jefeInmediato;
	private String estado;
	
	@JsonSerialize(using=JsonFlagIntegerSerializer.class)
	@JsonDeserialize(using=JsonFlagIntegerDeserializer.class)
	private Integer reporteDiario;
	@JsonSerialize(using=JsonFlagIntegerSerializer.class)
	@JsonDeserialize(using=JsonFlagIntegerDeserializer.class)
	private Integer reporteAcumulado;
	
    private List<ReporteMarcacionSubscriptorViewModel> subscriptores;

	public Long getIdReporteMarcacion() {
		return idReporteMarcacion;
	}

	public void setIdReporteMarcacion(Long idReporteMarcacion) {
		this.idReporteMarcacion = idReporteMarcacion;
	}

	public Long getIdEmpresa() {
		return idEmpresa;
	}

	public void setIdEmpresa(Long idEmpresa) {
		this.idEmpresa = idEmpresa;
	}

	public Long getIdUnidadDeNegocio() {
		return idUnidadDeNegocio;
	}

	public void setIdUnidadDeNegocio(Long idUnidadDeNegocio) {
		this.idUnidadDeNegocio = idUnidadDeNegocio;
	}

	public Long getIdDepartamentoArea() {
		return idDepartamentoArea;
	}

	public void setIdDepartamentoArea(Long idDepartamentoArea) {
		this.idDepartamentoArea = idDepartamentoArea;
	}

	public Long getIdProyecto() {
		return idProyecto;
	}

	public void setIdProyecto(Long idProyecto) {
		this.idProyecto = idProyecto;
	}

	public Long getIdJefeProyecto() {
		return idJefeProyecto;
	}

	public void setIdJefeProyecto(Long idJefeProyecto) {
		this.idJefeProyecto = idJefeProyecto;
	}

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	public Integer getReporteDiario() {
		return reporteDiario;
	}

	public void setReporteDiario(Integer reporteDiario) {
		this.reporteDiario = reporteDiario;
	}

	public Integer getReporteAcumulado() {
		return reporteAcumulado;
	}

	public void setReporteAcumulado(Integer reporteAcumulado) {
		this.reporteAcumulado = reporteAcumulado;
	}

	public List<ReporteMarcacionSubscriptorViewModel> getSubscriptores() {
		return subscriptores;
	}

	public void setSubscriptores(List<ReporteMarcacionSubscriptorViewModel> subscriptores) {
		this.subscriptores = subscriptores;
	}

	

	public String getNombreProyecto() {
		return nombreProyecto;
	}

	public void setNombreProyecto(String nombreProyecto) {
		this.nombreProyecto = nombreProyecto;
	}

	public String getJefeInmediato() {
		return jefeInmediato;
	}

	public void setJefeInmediato(String jefeInmediato) {
		this.jefeInmediato = jefeInmediato;
	}

	

	public Long getIdEmpleado() {
		return idEmpleado;
	}

	public void setIdEmpleado(Long idEmpleado) {
		this.idEmpleado = idEmpleado;
	}

	public String getNombreEmpleado() {
		return nombreEmpleado;
	}

	public void setNombreEmpleado(String nombreEmpleado) {
		this.nombreEmpleado = nombreEmpleado;
	}

	public Long getIdJefe() {
		return idJefe;
	}

	public void setIdJefe(Long idJefe) {
		this.idJefe = idJefe;
	}
	
   
}

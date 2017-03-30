package pe.com.empresa.rk.view.model;

import java.util.List;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import pe.com.empresa.rk.json.JsonFlagIntegerDeserializer;
import pe.com.empresa.rk.json.JsonFlagIntegerSerializer;

public class EmpresaViewModel extends AuditingEntityViewModel {
	
	private Long idEmpresa;
	private String codigo;
	private String razonSocial;
	private String ruc;
	private String nombreEstado;
	private String estado;
	private Long idJefe;
	private Long idJefeReemplazo;
	
	@JsonSerialize(using=JsonFlagIntegerSerializer.class)
	@JsonDeserialize(using=JsonFlagIntegerDeserializer.class)
	private Integer jefeNoDisponible;
	
	private String jefe;
	private String jefeReemplazo;
		
	private List<UnidadDeNegocioViewModel> unidadesDeNegocio;

	public Long getIdEmpresa() {
		return idEmpresa;
	}

	public void setIdEmpresa(Long idEmpresa) {
		this.idEmpresa = idEmpresa;
	}

	public String getCodigo() {
		return codigo;
	}

	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}

	public String getRazonSocial() {
		return razonSocial;
	}

	public void setRazonSocial(String razonSocial) {
		this.razonSocial = razonSocial;
	}

	public List<UnidadDeNegocioViewModel> getUnidadesDeNegocio() {
		return unidadesDeNegocio;
	}

	public void setUnidadesDeNegocio(List<UnidadDeNegocioViewModel> unidadesDeNegocio) {
		this.unidadesDeNegocio = unidadesDeNegocio;
	}

	public String getRuc() {
		return ruc;
	}

	public void setRuc(String ruc) {
		this.ruc = ruc;
	}

	public String getNombreEstado() {
		return nombreEstado;
	}

	public void setNombreEstado(String nombreEstado) {
		this.nombreEstado = nombreEstado;
	}

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	public Long getIdJefe() {
		return idJefe;
	}

	public void setIdJefe(Long idJefe) {
		this.idJefe = idJefe;
	}

	public Long getIdJefeReemplazo() {
		return idJefeReemplazo;
	}

	public void setIdJefeReemplazo(Long idJefeReemplazo) {
		this.idJefeReemplazo = idJefeReemplazo;
	}

	public Integer getJefeNoDisponible() {
		return jefeNoDisponible;
	}

	public void setJefeNoDisponible(Integer jefeNoDisponible) {
		this.jefeNoDisponible = jefeNoDisponible;
	}

	public String getJefe() {
		return jefe;
	}

	public void setJefe(String jefe) {
		this.jefe = jefe;
	}

	public String getJefeReemplazo() {
		return jefeReemplazo;
	}

	public void setJefeReemplazo(String jefeReemplazo) {
		this.jefeReemplazo = jefeReemplazo;
	}
	
}

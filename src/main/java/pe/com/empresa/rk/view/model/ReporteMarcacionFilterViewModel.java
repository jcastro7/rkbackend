package pe.com.empresa.rk.view.model;

public class ReporteMarcacionFilterViewModel extends FileViewModel{
	
	
	private Long idEmpleado;
	private String estado;
	private String tipoReporte;
	
	private Integer reporteDiario;
	private Integer reporteAcumulado;
	
	
	public Long getIdEmpleado() {
		return idEmpleado;
	}
	public void setIdEmpleado(Long idEmpleado) {
		this.idEmpleado = idEmpleado;
	}
	public String getEstado() {
		return estado;
	}
	public void setEstado(String estado) {
		this.estado = estado;
	}
	public String getTipoReporte() {
		return tipoReporte;
	}
	public void setTipoReporte(String tipoReporte) {
		this.tipoReporte = tipoReporte;
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
	
	
    

		    
    

}

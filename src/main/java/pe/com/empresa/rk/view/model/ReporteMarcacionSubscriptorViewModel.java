package pe.com.empresa.rk.view.model;

/**
 * Created by josediaz on 14/12/2016.
 */
public class ReporteMarcacionSubscriptorViewModel extends AuditingEntityViewModel {

	private Long idReporteMarcacionSubscriptor;
    private Long idReporteMarcacion;
    private Long idEmpleado;
    private String nombreEmpleado;
    
	public Long getIdReporteMarcacionSubscriptor() {
		return idReporteMarcacionSubscriptor;
	}
	public void setIdReporteMarcacionSubscriptor(Long idReporteMarcacionSubscriptor) {
		this.idReporteMarcacionSubscriptor = idReporteMarcacionSubscriptor;
	}
	public Long getIdReporteMarcacion() {
		return idReporteMarcacion;
	}
	public void setIdReporteMarcacion(Long idReporteMarcacion) {
		this.idReporteMarcacion = idReporteMarcacion;
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
	
    

   
}

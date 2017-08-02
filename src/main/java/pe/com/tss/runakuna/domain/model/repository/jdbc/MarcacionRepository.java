package pe.com.tss.runakuna.domain.model.repository.jdbc;

import java.util.Date;
import java.util.List;

import org.springframework.jdbc.core.BeanPropertyRowMapper;

import pe.com.tss.runakuna.support.WhereParams;
import pe.com.tss.runakuna.view.model.EmpleadoViewModel;
import pe.com.tss.runakuna.view.model.GenerarReporteMarcacionAcumuladaViewModel;
import pe.com.tss.runakuna.view.model.GenerarReporteMarcacionViewModel;
import pe.com.tss.runakuna.view.model.MarcacionFilterViewModel;
import pe.com.tss.runakuna.view.model.MarcacionViewModel;
import pe.com.tss.runakuna.view.model.NotificacionTardanzaViewModel;
import pe.com.tss.runakuna.view.model.RegistroMarcacionEmpleadoViewModel;
import pe.com.tss.runakuna.view.model.RegistroMarcacionViewModel;
import pe.com.tss.runakuna.view.model.ReporteMarcacionViewModel;

public interface MarcacionRepository {
	
	List<RegistroMarcacionEmpleadoViewModel> obtenerMarcacion();
	
	MarcacionViewModel obtenerMarcacionesPorEmpleadoyFechaActual(EmpleadoViewModel empleado);
	
	List<GenerarReporteMarcacionViewModel> obtenerMarcacionesSegunParametros(ReporteMarcacionViewModel reporteMarcacionDt);
	
	List<GenerarReporteMarcacionAcumuladaViewModel> obtenerMarcacionesAcumuladasSegunParametros(ReporteMarcacionViewModel reporteMarcacionDto);

	List<RegistroMarcacionViewModel> obtenerRegistroMarcacionNoProcesado();
	
	void updateRegistroMarcacionAError(RegistroMarcacionViewModel registro);
	
	void updateRegistroMarcacionAProcesado(List<RegistroMarcacionViewModel> registros);
	void updateRegistroMarcacionANoProcesado(List<RegistroMarcacionViewModel> registros);

	MarcacionViewModel obtenerMarcacionesPorEmpleadoyFecha(EmpleadoViewModel empleado, Date fecha);

	MarcacionViewModel findById(Long idMarcacion);
	
	List<MarcacionViewModel> getMarcacionesByFiltro(MarcacionFilterViewModel filter);

	int countMarcacionesPorCodigo(String codigo, Date fecha);


    int countAlertasEmpleadoPorMarcador(String codigo);
    
    void updateMarcacionRecalcular(Long idEmpleado, Date fechaInicio, String horaIngresoHorario, String horaSalidaHorario, int dayNumber);
    
    List<MarcacionViewModel> getMarcacionesByIdProyecto(Date fecha, Long idProyecto);
    
    List<MarcacionViewModel> getMarcacionesByIdDepartamentoArea(Date fecha, Long idDepArea);
    
    List<MarcacionViewModel> getMarcacionesByIdUnidadDeNegocio(Date fecha, Long idUnidad);
    
    List<NotificacionTardanzaViewModel> getEmpleadosPorTardanzaPorUnidadDeNegocio(Date today, Long idEmpleado, Long unidadDeNegocio);

	List<MarcacionViewModel> getEmpleadosPorTardanzaPorProyecto(Date today,Long idProyecto);

	List<MarcacionViewModel> getTardanzasByIdDepartamentoArea(Date today, Long idDepartamentoArea);

	List<MarcacionViewModel> getTardanzasByIdUnidadDeNegocio(Date today, Long idUnidadDeNegocio);

}

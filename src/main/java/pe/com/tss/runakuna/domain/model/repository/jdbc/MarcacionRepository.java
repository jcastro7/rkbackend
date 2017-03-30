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
import pe.com.tss.runakuna.view.model.RegistroMarcacionEmpleadoViewModel;
import pe.com.tss.runakuna.view.model.RegistroMarcacionViewModel;
import pe.com.tss.runakuna.view.model.ReporteMarcacionViewModel;

public interface MarcacionRepository {
	
	List<RegistroMarcacionEmpleadoViewModel> obtenerMarcacion();
	
	MarcacionViewModel obtenerMarcacionesPorEmpleadoyFechaActual(EmpleadoViewModel empleado);
	
	List<GenerarReporteMarcacionViewModel> obtenerMarcacionesSegunParametros(ReporteMarcacionViewModel reporteMarcacionDt);
	
	List<GenerarReporteMarcacionAcumuladaViewModel> obtenerMarcacionesAcumuladasSegunParametros(ReporteMarcacionViewModel reporteMarcacionDto);

	List<RegistroMarcacionViewModel> obtenerRegistroMarcacionNoProcesado();
	
	void updateRegistroMarcacionAProcesado(List<RegistroMarcacionViewModel> registros);

	MarcacionViewModel obtenerMarcacionesPorEmpleadoyFecha(EmpleadoViewModel empleado, Date fecha);

	MarcacionViewModel findById(Long idMarcacion);
	
	List<MarcacionViewModel> getMarcacionesByFiltro(MarcacionFilterViewModel filter);

	int countMarcacionesPorCodigo(String codigo);



}

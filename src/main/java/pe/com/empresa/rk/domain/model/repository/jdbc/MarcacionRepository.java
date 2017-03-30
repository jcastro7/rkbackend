package pe.com.empresa.rk.domain.model.repository.jdbc;

import java.util.Date;
import java.util.List;

import pe.com.empresa.rk.view.model.GenerarReporteMarcacionViewModel;
import pe.com.empresa.rk.view.model.MarcacionViewModel;
import pe.com.empresa.rk.view.model.EmpleadoViewModel;
import pe.com.empresa.rk.view.model.GenerarReporteMarcacionAcumuladaViewModel;
import pe.com.empresa.rk.view.model.MarcacionFilterViewModel;
import pe.com.empresa.rk.view.model.RegistroMarcacionEmpleadoViewModel;
import pe.com.empresa.rk.view.model.RegistroMarcacionViewModel;
import pe.com.empresa.rk.view.model.ReporteMarcacionViewModel;

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

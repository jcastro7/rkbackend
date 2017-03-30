package pe.com.empresa.rk.domain.model.repository.jdbc;

import java.util.Date;
import java.util.List;

import pe.com.empresa.rk.view.model.EmpleadoViewModel;
import pe.com.empresa.rk.view.model.HistoriaLaboralViewModel;
import pe.com.empresa.rk.view.model.LicenciaEmpleadoViewModel;

public interface HistoriaLaboralRepository {
	
	List<HistoriaLaboralViewModel> obtenerHistoriaLaboral(Long idEmpleado);
	List<HistoriaLaboralViewModel> obtenerIdHistoriaLaboral(Long idHistorialLaboral);
	HistoriaLaboralViewModel obtenerHistoriaLaboralActual(EmpleadoViewModel empleado);
	
	List<HistoriaLaboralViewModel> obtenerHistoriasLaboralesActualPorEmpleado(EmpleadoViewModel empleado);
	
	HistoriaLaboralViewModel obtenerUltimoCargo(Long idEmpleado);
	
	HistoriaLaboralViewModel obtenerPrimerCargo(Long idEmpleado);
	
	Long obtenerCantidadCargos(Long idEmpleado);
	
	HistoriaLaboralViewModel obtenerHistoriaLaboralLicencia(LicenciaEmpleadoViewModel licenciaEmpleadoD);
	
	List<HistoriaLaboralViewModel> obtenerHistoriasLaboralesPorEmpleado(Long idEmpleado) ;
	HistoriaLaboralViewModel obtenerHistoriaLaboralVigenteFecha(EmpleadoViewModel empleado, Date fecha);
	
	List<HistoriaLaboralViewModel> obtenerHistoriasLaboralesPorEmpleadoContrato(Long idEmpleado);
	
}

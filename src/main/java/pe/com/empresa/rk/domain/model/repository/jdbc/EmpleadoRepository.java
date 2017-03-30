package pe.com.empresa.rk.domain.model.repository.jdbc;

import pe.com.empresa.rk.view.model.EmpleadoFilterViewModel;
import pe.com.empresa.rk.view.model.EmpleadoResultViewModel;
import pe.com.empresa.rk.view.model.MarcacionFilterViewModel;
import pe.com.empresa.rk.view.model.PermisoEmpleadoFilterViewModel;
import pe.com.empresa.rk.view.model.DependienteViewModel;
import pe.com.empresa.rk.view.model.EducacionViewModel;
import pe.com.empresa.rk.view.model.EmpleadoViewModel;
import pe.com.empresa.rk.view.model.EquipoEntregadoViewModel;
import pe.com.empresa.rk.view.model.HorarioEmpleadoDiaViewModel;
import pe.com.empresa.rk.view.model.HorarioEmpleadoResultViewModel;
import pe.com.empresa.rk.view.model.HorarioEmpleadoViewModel;
import pe.com.empresa.rk.view.model.LicenciaViewModel;
import pe.com.empresa.rk.view.model.MarcacionViewModel;
import pe.com.empresa.rk.view.model.PeriodoEmpleadoViewModel;
import pe.com.empresa.rk.view.model.PermisoEmpleadoViewModel;
import pe.com.empresa.rk.view.model.QuickFilterViewModel;
import pe.com.empresa.rk.view.model.VacacionEmpleadoViewModel;

import java.math.BigDecimal;
import java.util.List;

/**
 * Created by josediaz on 2/11/2016.
 */
public interface EmpleadoRepository {
     List<EmpleadoResultViewModel> busquedaEmpleado(EmpleadoFilterViewModel busquedaEmpleadoDto);
     List<EmpleadoViewModel> busquedaEmpleadoExport(EmpleadoFilterViewModel busquedaEmpleadoDto);
     
     EmpleadoViewModel verEmpleado(Long idEmpleado);
     List<EducacionViewModel> verEducacion(Long idEmpleado);
     List<EquipoEntregadoViewModel> verEquipoEntregado(Long idEmpleado);
     List<DependienteViewModel> verDependiente(Long idEmpleado);
     List<LicenciaViewModel> verLicencia(PeriodoEmpleadoViewModel periodoEmpleado);
     List<MarcacionViewModel> busquedaMarcacionEmpleado(MarcacionFilterViewModel busquedaMarcacionDto);
     HorarioEmpleadoViewModel verHorarioEmpleado(Long idEmpleado);
     List<HorarioEmpleadoViewModel> verHorariosEmpleado(Long idEmpleado);
     List<HorarioEmpleadoResultViewModel> busquedaHorariosEmpleado(Long idEmpleado);
     List<HorarioEmpleadoDiaViewModel> verHorarioEmpleadoDia(HorarioEmpleadoViewModel horarioEmpleadoDto);
     List<PeriodoEmpleadoViewModel> verPeriodoEmpleado(Long idEmpleado);
     List<PermisoEmpleadoViewModel> verPermisoEmpleado(PeriodoEmpleadoViewModel periodoEmpleado);
     List<VacacionEmpleadoViewModel> verVacaciones(PeriodoEmpleadoViewModel periodoEmpleado);
     List<PermisoEmpleadoFilterViewModel> autocompleteEmpleado(String search);
     List<MarcacionViewModel> verMarcaciones(Long idEmpleado);
     List<EmpleadoResultViewModel> busquedaRapidaEmpleado(QuickFilterViewModel quickFilter);
	List<MarcacionViewModel> busquedaRapidaMarcacionEmpleado(QuickFilterViewModel quickFilter);
	BigDecimal obtenerHorasSemanalesLunesViernes(Long idHorario);
	HorarioEmpleadoDiaViewModel obtenerHorarioDiaPorDia(Long idHorario, String dia);
	BigDecimal obtenerHorasSemanalesCubiertas(Long idHorario);
	EmpleadoViewModel obtenerEmpleadoCabecera(Long idEmpleado);

}

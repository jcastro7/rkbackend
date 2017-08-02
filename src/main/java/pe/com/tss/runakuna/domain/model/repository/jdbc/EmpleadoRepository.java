package pe.com.tss.runakuna.domain.model.repository.jdbc;

import pe.com.tss.runakuna.view.model.EmpleadoFilterViewModel;
import pe.com.tss.runakuna.view.model.EmpleadoResultViewModel;
import pe.com.tss.runakuna.view.model.MarcacionFilterViewModel;
import pe.com.tss.runakuna.view.model.MarcacionResultViewModel;
import pe.com.tss.runakuna.view.model.PermisoEmpleadoFilterViewModel;
import pe.com.tss.runakuna.view.model.PermisoEmpleadoResultViewModel;
import pe.com.tss.runakuna.view.model.DependienteViewModel;
import pe.com.tss.runakuna.view.model.EducacionViewModel;
import pe.com.tss.runakuna.view.model.EmpleadoCabeceraViewModel;
import pe.com.tss.runakuna.view.model.EmpleadoDirectorioResultViewModel;
import pe.com.tss.runakuna.view.model.EmpleadoViewModel;
import pe.com.tss.runakuna.view.model.EquipoEntregadoViewModel;
import pe.com.tss.runakuna.view.model.HorarioDiaViewModel;
import pe.com.tss.runakuna.view.model.HorarioEmpleadoDiaViewModel;
import pe.com.tss.runakuna.view.model.HorarioEmpleadoResultViewModel;
import pe.com.tss.runakuna.view.model.HorarioEmpleadoViewModel;
import pe.com.tss.runakuna.view.model.LicenciaViewModel;
import pe.com.tss.runakuna.view.model.MarcacionViewModel;
import pe.com.tss.runakuna.view.model.PeriodoEmpleadoViewModel;
import pe.com.tss.runakuna.view.model.PermisoEmpleadoViewModel;
import pe.com.tss.runakuna.view.model.QuickFilterViewModel;
import pe.com.tss.runakuna.view.model.VacacionEmpleadoViewModel;
import pe.com.tss.runakuna.view.model.VacacionViewModel;

import java.math.BigDecimal;
import java.util.List;

/**
 * Created by josediaz on 2/11/2016.
 */
public interface EmpleadoRepository {
     List<EmpleadoResultViewModel> busquedaEmpleado(EmpleadoFilterViewModel busquedaEmpleadoDto);
     List<EmpleadoViewModel> busquedaEmpleadoExport(EmpleadoFilterViewModel busquedaEmpleadoDto);
     List<EmpleadoViewModel> busquedaEmpleadoExportBusquedaRapida(QuickFilterViewModel quickFilter);
     
     EmpleadoViewModel verEmpleado(Long idEmpleado);
     List<EducacionViewModel> verEducacion(Long idEmpleado);
     List<EquipoEntregadoViewModel> verEquipoEntregado(Long idEmpleado);
     List<DependienteViewModel> verDependiente(Long idEmpleado);
     List<LicenciaViewModel> verLicencia(PeriodoEmpleadoViewModel periodoEmpleado);
     List<MarcacionResultViewModel> busquedaMarcacionEmpleado(MarcacionFilterViewModel busquedaMarcacionDto);
     HorarioEmpleadoViewModel verHorarioEmpleado(Long idEmpleado);
     List<HorarioEmpleadoViewModel> verHorariosEmpleado(Long idEmpleado);
     List<HorarioEmpleadoResultViewModel> busquedaHorariosEmpleado(Long idEmpleado);
     List<HorarioEmpleadoDiaViewModel> verHorarioEmpleadoDia(HorarioEmpleadoViewModel horarioEmpleadoDto);
     List<PeriodoEmpleadoViewModel> verPeriodoEmpleado(Long idEmpleado);
     
     List<VacacionEmpleadoViewModel> verVacaciones(PeriodoEmpleadoViewModel periodoEmpleado);
     List<PermisoEmpleadoFilterViewModel> autocompleteEmpleado(String search);
     List<PermisoEmpleadoFilterViewModel> autocompleteJefe(String search);
     List<PermisoEmpleadoFilterViewModel> autocompleteEmpleadoConJefe(String search, Long idJefe);
     List<MarcacionViewModel> verMarcaciones(Long idEmpleado);
     List<EmpleadoResultViewModel> busquedaRapidaEmpleado(QuickFilterViewModel quickFilter);
	List<MarcacionResultViewModel> busquedaRapidaMarcacionEmpleado(QuickFilterViewModel quickFilter);
	BigDecimal obtenerHorasSemanalesLunesViernes(Long idHorario);
	HorarioEmpleadoDiaViewModel obtenerHorarioDiaPorDia(Long idHorario, String dia);
	BigDecimal obtenerHorasSemanalesCubiertas(Long idHorario);
	EmpleadoCabeceraViewModel obtenerEmpleadoCabecera(Long idEmpleado);
	EmpleadoViewModel obtenerEmpleadoPorCodigo(String codigo);
	List<EmpleadoDirectorioResultViewModel> busquedaDirectorioEmpleado(QuickFilterViewModel quickFilter);
}

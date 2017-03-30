package pe.com.tss.runakuna.domain.model.repository.jdbc;

import java.util.List;

import pe.com.tss.runakuna.view.model.HorasExtraEmpleadoFilterViewModel;
import pe.com.tss.runakuna.view.model.HorasExtraEmpleadoResultViewModel;
import pe.com.tss.runakuna.view.model.HorasExtraViewModel;
import pe.com.tss.runakuna.view.model.ModuloFilterViewModel;
import pe.com.tss.runakuna.view.model.ModuloResultViewModel;
import pe.com.tss.runakuna.view.model.PeriodoEmpleadoViewModel;
import pe.com.tss.runakuna.view.model.QuickFilterViewModel;

/**
 * Created by josediaz on 25/11/2016.
 */
public interface HoraExtraRepository {

	 List<ModuloResultViewModel> buscarModulos(ModuloFilterViewModel filterViewModel);

	 List<HorasExtraEmpleadoResultViewModel> busquedaRapidaHorasExtrasEmpleado(QuickFilterViewModel quickFilter);

	 List<HorasExtraViewModel> buscarHorasExtrasPorMesCompensacion(HorasExtraEmpleadoFilterViewModel filterViewModel);

	 List<HorasExtraViewModel> verHorasExtras(PeriodoEmpleadoViewModel periodoEmpleado);
	 
}

package pe.com.empresa.rk.domain.model.repository.jdbc;

import java.util.List;

import pe.com.empresa.rk.view.model.HorasExtraEmpleadoFilterViewModel;
import pe.com.empresa.rk.view.model.HorasExtraEmpleadoResultViewModel;
import pe.com.empresa.rk.view.model.HorasExtraViewModel;
import pe.com.empresa.rk.view.model.ModuloFilterViewModel;
import pe.com.empresa.rk.view.model.ModuloResultViewModel;
import pe.com.empresa.rk.view.model.PeriodoEmpleadoViewModel;
import pe.com.empresa.rk.view.model.QuickFilterViewModel;

/**
 * Created by josediaz on 25/11/2016.
 */
public interface HoraExtraRepository {

	 List<ModuloResultViewModel> buscarModulos(ModuloFilterViewModel filterViewModel);

	 List<HorasExtraEmpleadoResultViewModel> busquedaRapidaHorasExtrasEmpleado(QuickFilterViewModel quickFilter);

	 List<HorasExtraViewModel> buscarHorasExtrasPorMesCompensacion(HorasExtraEmpleadoFilterViewModel filterViewModel);

	 List<HorasExtraViewModel> verHorasExtras(PeriodoEmpleadoViewModel periodoEmpleado);
	 
}

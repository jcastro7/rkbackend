package pe.com.empresa.rk.domain.model.repository.jdbc;

import java.math.BigDecimal;
import java.util.List;

import pe.com.empresa.rk.view.model.PeriodoEmpleadoTipoLicenciaViewModel;

public interface PeriodoEmpleadoTipoLicenciaRepository {
	
	List<PeriodoEmpleadoTipoLicenciaViewModel> obtenerDiasPorPeriodoEmpleadoTipoLicencia(PeriodoEmpleadoTipoLicenciaViewModel periodoEmpleadoTipoLicenciaDto);

	BigDecimal obtenerDiasLicenciaPorTipo(Long idTipoLicencia);

	
}

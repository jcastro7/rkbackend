package pe.com.tss.runakuna.domain.model.repository.jdbc;

import java.math.BigDecimal;
import java.util.List;

import pe.com.tss.runakuna.view.model.PeriodoEmpleadoTipoLicenciaViewModel;

public interface PeriodoEmpleadoTipoLicenciaRepository {
	
	List<PeriodoEmpleadoTipoLicenciaViewModel> obtenerDiasPorPeriodoEmpleadoTipoLicencia(PeriodoEmpleadoTipoLicenciaViewModel periodoEmpleadoTipoLicenciaDto);

	BigDecimal obtenerDiasLicenciaPorTipo(Long idTipoLicencia);

	
}

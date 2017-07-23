package pe.com.tss.runakuna.service.impl;

import java.util.List;
import org.dozer.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pe.com.tss.runakuna.domain.model.entities.PeriodoEmpleado;
import pe.com.tss.runakuna.domain.model.entities.TablaGeneral;
import pe.com.tss.runakuna.domain.model.entities.Vacacion;
import pe.com.tss.runakuna.domain.model.repository.jdbc.VacacionEmpleadoRepository;
import pe.com.tss.runakuna.domain.model.repository.jpa.EmpleadoJpaRepository;
import pe.com.tss.runakuna.domain.model.repository.jpa.PeriodoEmpleadoJpaRepository;
import pe.com.tss.runakuna.domain.model.repository.jpa.TablaGeneralJpaRepository;
import pe.com.tss.runakuna.domain.model.repository.jpa.VacacionJpaRepository;
import pe.com.tss.runakuna.service.VacacionService;
import pe.com.tss.runakuna.view.model.NotificacionViewModel;
import pe.com.tss.runakuna.view.model.QuickFilterViewModel;
import pe.com.tss.runakuna.view.model.VacacionFilterViewModel;
import pe.com.tss.runakuna.view.model.VacacionPendienteResultViewModel;
import pe.com.tss.runakuna.view.model.VacacionResultViewModel;
import pe.com.tss.runakuna.view.model.VacacionViewModel;

@Service
public class VacacionServiceImpl implements VacacionService{

	@Autowired
	VacacionEmpleadoRepository vacacionEmpleadoRepository;
	
	@Autowired
	VacacionJpaRepository vacacionJpaRepository;
	
	@Autowired
	EmpleadoJpaRepository empleadoJpaRepository;
	
	@Autowired
	PeriodoEmpleadoJpaRepository periodoEmpleadoJpaRepository;
	
	@Autowired
	TablaGeneralJpaRepository tablaGeneralJpaRepository;
	
	@Autowired
	Mapper mapper;
	
	

	@Override
	public List<VacacionResultViewModel> search(VacacionFilterViewModel filterViewModel) {
		
		return vacacionEmpleadoRepository.busquedaVacacionesEmpleado(filterViewModel);
	}

	@Override
	public VacacionViewModel findOne(Long id) {
		
		Vacacion vacacion=vacacionJpaRepository.findOne(id);
		
		VacacionViewModel vacacionViewModel=new VacacionViewModel();
		
		mapper.map(vacacion,vacacionViewModel);
		
		TablaGeneral estadoVacacion = tablaGeneralJpaRepository.findByGrupoAndCodigo("Vacaciones.Estado", vacacion.getEstado());
		
		vacacionViewModel.setIdEmpleado(vacacion.getPeriodoEmpleado().getEmpleado().getIdEmpleado());
		vacacionViewModel.setIdAtendidoPor(vacacion.getAtendidoPor().getIdEmpleado());
		vacacionViewModel.setNombreJefeInmediato(vacacion.getAtendidoPor().getApellidoPaterno()+" "+vacacion.getAtendidoPor().getApellidoMaterno()+' '+vacacion.getAtendidoPor().getNombre());
		vacacionViewModel.setNombreEmpleado(vacacion.getPeriodoEmpleado().getEmpleado().getApellidoPaterno()+" "+vacacion.getPeriodoEmpleado().getEmpleado().getApellidoMaterno()+", "+vacacion.getPeriodoEmpleado().getEmpleado().getNombre());
		vacacionViewModel.setNombreEstado(estadoVacacion.getNombre());
		vacacionViewModel.setIdPeriodoEmpleado(vacacion.getPeriodoEmpleado().getIdPeriodoEmpleado());
		vacacionViewModel.setPeriodo(vacacion.getPeriodoEmpleado().getPeriodo());
		
		return vacacionViewModel;
	}

	@Override
	public NotificacionViewModel create(VacacionViewModel manteinanceViewModel) {
		return null;
	}

	@Override
	public NotificacionViewModel update(VacacionViewModel manteinanceViewModel) {
		return null;
	}

	@Override
	public NotificacionViewModel delete(Long id) {
		return null;
	}

	@Override
	public List<VacacionResultViewModel> quickSearch(QuickFilterViewModel quickFilter) {
		return vacacionEmpleadoRepository.busquedaRapidaVacacionesEmpleado(quickFilter);
	}

	@Override
	public List<VacacionPendienteResultViewModel> searchVacacionesPendientes(VacacionFilterViewModel filterViewModel) {
		return vacacionEmpleadoRepository.busquedaVacacionesPendientesEmpleado(filterViewModel);
	}

}

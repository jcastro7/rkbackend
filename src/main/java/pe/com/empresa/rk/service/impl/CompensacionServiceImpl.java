package pe.com.empresa.rk.service.impl;

import java.util.List;

import org.dozer.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pe.com.empresa.rk.view.model.CompensacionFilterViewModel;
import pe.com.empresa.rk.view.model.HorasExtraViewModel;
import pe.com.empresa.rk.view.model.QuickFilterViewModel;
import pe.com.empresa.rk.domain.model.repository.jdbc.CompensacionRepository;
import pe.com.empresa.rk.domain.model.repository.jdbc.HoraExtraRepository;
import pe.com.empresa.rk.domain.model.repository.jdbc.PermisoEmpleadoJdbcRepository;
import pe.com.empresa.rk.service.CompensacionService;
import pe.com.empresa.rk.view.model.CompensacionDetalleViewModel;
import pe.com.empresa.rk.view.model.CompensacionResultViewModel;
import pe.com.empresa.rk.view.model.CompensacionViewModel;
import pe.com.empresa.rk.view.model.HorasExtraEmpleadoFilterViewModel;
import pe.com.empresa.rk.view.model.NotificacionViewModel;
import pe.com.empresa.rk.view.model.PermisoEmpleadoFilterViewModel;
import pe.com.empresa.rk.view.model.PermisoEmpleadoViewModel;

@Service
public class CompensacionServiceImpl implements CompensacionService {

	@Autowired
	Mapper mapper;
	
	@Autowired
	CompensacionRepository compensacionRepository;
	
	@Autowired
	PermisoEmpleadoJdbcRepository permisoEmpleadoRepository;
	
	@Autowired
	HoraExtraRepository horaExtraRepository;

	@Override
	public List<CompensacionResultViewModel> search(CompensacionFilterViewModel filterViewModel) {
		List<CompensacionResultViewModel> result = compensacionRepository.SearchByFilter(filterViewModel);
		return result;
	}

	@Override
	public CompensacionViewModel findOne(Long id) {
		CompensacionViewModel dto = new CompensacionViewModel();
		dto  = compensacionRepository.findById(id);
		return dto;
	}

	@Override
	public NotificacionViewModel create(CompensacionViewModel manteinanceViewModel) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public NotificacionViewModel update(CompensacionViewModel manteinanceViewModel) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public NotificacionViewModel delete(Long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<CompensacionResultViewModel> quickSearch(QuickFilterViewModel quickFilter) {
		List<CompensacionResultViewModel> result = compensacionRepository.busquedaRapidaCompensaciones(quickFilter);
		return result;
	}

	@Override
	public CompensacionDetalleViewModel findDetalle(Long idEmpleadoCompensacion) {
		
		CompensacionDetalleViewModel result = new CompensacionDetalleViewModel();
		
		CompensacionViewModel dto = new CompensacionViewModel();
		dto  = compensacionRepository.findById(idEmpleadoCompensacion);
		
		result.setCompensacion(dto);
		
		if(dto !=null){
			String[] mes = dto.getMes().split("/");
			
			PermisoEmpleadoFilterViewModel permisoFilter = new PermisoEmpleadoFilterViewModel();
			permisoFilter.setIdEmpleado(dto.getIdEmpleado());
			permisoFilter.setMes(Integer.parseInt(mes[0]));
			permisoFilter.setAnio(Integer.parseInt(mes[1]));
			List<PermisoEmpleadoViewModel> permisos = permisoEmpleadoRepository.buscarPermisoEmpleadoPorMesCompensacion(permisoFilter);
			result.setPermisosEmpleado(permisos);
			
			HorasExtraEmpleadoFilterViewModel horasExtraFilter = new HorasExtraEmpleadoFilterViewModel();
			horasExtraFilter.setIdEmpleado(dto.getIdEmpleado());
			horasExtraFilter.setMes(Integer.parseInt(mes[0]));
			horasExtraFilter.setAnio(Integer.parseInt(mes[1]));
			List<HorasExtraViewModel> horasExtras = horaExtraRepository.buscarHorasExtrasPorMesCompensacion(horasExtraFilter);
			result.setHorasExtras(horasExtras);
		}
		
		return result;
	}
	
}

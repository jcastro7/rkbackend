package pe.com.tss.runakuna.service.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.dozer.Mapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pe.com.tss.runakuna.domain.model.entities.BandaSalarial;
import pe.com.tss.runakuna.domain.model.entities.Cargo;
import pe.com.tss.runakuna.domain.model.entities.DepartamentoArea;
import pe.com.tss.runakuna.domain.model.entities.Empleado;
import pe.com.tss.runakuna.domain.model.entities.Empresa;
import pe.com.tss.runakuna.domain.model.entities.HistorialLaboral;
import pe.com.tss.runakuna.domain.model.entities.Moneda;
import pe.com.tss.runakuna.domain.model.entities.Proyecto;
import pe.com.tss.runakuna.domain.model.entities.UnidadDeNegocio;
import pe.com.tss.runakuna.domain.model.repository.jdbc.CargoRepository;
import pe.com.tss.runakuna.domain.model.repository.jdbc.HistoriaLaboralRepository;
import pe.com.tss.runakuna.domain.model.repository.jpa.BandaSalarialJpaRepository;
import pe.com.tss.runakuna.domain.model.repository.jpa.CargoJpaRepository;
import pe.com.tss.runakuna.domain.model.repository.jpa.DepartamentoAreaJpaRepository;
import pe.com.tss.runakuna.domain.model.repository.jpa.EmpleadoJpaRepository;
import pe.com.tss.runakuna.domain.model.repository.jpa.EmpresaJpaRepository;
import pe.com.tss.runakuna.domain.model.repository.jpa.HistorialLaboralJpaRepository;
import pe.com.tss.runakuna.domain.model.repository.jpa.HorarioEmpleadoDiaJpaRepository;
import pe.com.tss.runakuna.domain.model.repository.jpa.HorarioEmpleadoJpaRepository;
import pe.com.tss.runakuna.domain.model.repository.jpa.HorarioJpaRepository;
import pe.com.tss.runakuna.domain.model.repository.jpa.MonedaJpaRepository;
import pe.com.tss.runakuna.domain.model.repository.jpa.ProyectoJpaRepository;
import pe.com.tss.runakuna.domain.model.repository.jpa.UnidaDeNegocioJpaRepository;
import pe.com.tss.runakuna.enums.SeverityStatusEnum;
import pe.com.tss.runakuna.view.model.BandaSalarialViewModel;
import pe.com.tss.runakuna.view.model.CargoComboViewModel;
import pe.com.tss.runakuna.view.model.CargoFilterViewModel;
import pe.com.tss.runakuna.view.model.CargoResultViewModel;
import pe.com.tss.runakuna.view.model.CargoViewModel;
import pe.com.tss.runakuna.view.model.HistoriaLaboralViewModel;
import pe.com.tss.runakuna.view.model.HistorialLaboralViewModel;
import pe.com.tss.runakuna.view.model.HorarioViewModel;
import pe.com.tss.runakuna.view.model.NotificacionViewModel;
import pe.com.tss.runakuna.view.model.ProyectoFilterViewModel;
import pe.com.tss.runakuna.view.model.QuickFilterViewModel;
import pe.com.tss.runakuna.exception.BusinessException;
import pe.com.tss.runakuna.exception.GenericRestException;
import pe.com.tss.runakuna.service.CargoService;

@Service
public class CargoServiceImpl implements CargoService{

	final Logger LOGGER = LoggerFactory.getLogger(getClass());
	
	@Autowired
	Mapper mapper;
	
	@Autowired
	CargoRepository cargoRepository;
	
	@Autowired
	HistorialLaboralJpaRepository historialLaboralJpaRepository;
	
	@Autowired
	CargoJpaRepository cargoJpaRepository;
	
	@Autowired
	EmpleadoJpaRepository empleadoJpaRepository;
	
	@Autowired
	ProyectoJpaRepository proyectoJpaRepository;
	
	@Autowired
	MonedaJpaRepository monedaJpaRepository;
	
	@Autowired
	HorarioEmpleadoJpaRepository horarioEmpleadoJpaRepository;
	
	@Autowired
	HorarioJpaRepository horarioJpaRepository;
	
	@Autowired
	HorarioEmpleadoDiaJpaRepository horarioEmpleadoDiaJpaRepository;
	
	@Autowired
	UnidaDeNegocioJpaRepository unidaDeNegocioJpaRepository;
	
	@Autowired
	DepartamentoAreaJpaRepository departamentoAreaJpaRepository;
	
	@Autowired
	HistoriaLaboralRepository historiaLaboralRepository;
	
	@Autowired
	EmpresaJpaRepository empresaJpaRepository;
	
	@Autowired
	BandaSalarialJpaRepository bandaSalarialJpaRepository;
	
	@Override
	public String save(CargoViewModel dto) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<CargoViewModel> getCargo() {
		// TODO Auto-generated method stub
		return cargoRepository.findCargo();
	}

	@Override
	public NotificacionViewModel actualizarCargo(HistorialLaboralViewModel historiaLaboralDto) {
		
		NotificacionViewModel notificacion = new NotificacionViewModel();
		
		HistorialLaboral entity = new HistorialLaboral();

		try {
			mapper.map(historiaLaboralDto, entity);
			
			//Se puede actualizar el cargo
			Empleado empleado = empleadoJpaRepository.findOne(historiaLaboralDto.getIdEmpleado());
			entity.setEmpleado(empleado);
			
			if(historiaLaboralDto.getIdProyecto() != null){
				Proyecto proyecto = proyectoJpaRepository.findOne(historiaLaboralDto.getIdProyecto());
				entity.setProyecto(proyecto);
			}else{
				entity.setProyecto(null);
			}

			if(historiaLaboralDto.getIdCargo() != null){
				Cargo cargo = cargoJpaRepository.findOne(historiaLaboralDto.getIdCargo());
				entity.setCargo(cargo);
			}else{
				entity.setCargo(null);
			}
			Moneda moneda = monedaJpaRepository.findOne(1L);
			entity.setMoneda(moneda);
			
			UnidadDeNegocio unidadDeNegocio = unidaDeNegocioJpaRepository.findOne(historiaLaboralDto.getIdUnidadDeNegocio());
			entity.setUnidadDeNegocio(unidadDeNegocio);
			
			if(historiaLaboralDto.getIdDepartamentoArea() != null){
				DepartamentoArea departamentoArea = departamentoAreaJpaRepository.findOne(historiaLaboralDto.getIdDepartamentoArea());
				entity.setDepartamentoArea(departamentoArea);
			}else{
				entity.setDepartamentoArea(null);
			}
			
			historialLaboralJpaRepository.save(entity);
			historialLaboralJpaRepository.flush();
			
			notificacion.setCodigo(1L);
			notificacion.setSeverity("success");
			notificacion.setSummary("Runakuna Success");
			notificacion.setDetail("Se actualizo correctamente");
		} catch (Exception e) {
			notificacion.setCodigo(0L);
			notificacion.setSeverity("error");
			notificacion.setSummary("Runakuna Error");
			notificacion.setDetail("No es posible actualizar, "+e.getMessage());
			e.printStackTrace();
		}
		
		return notificacion;
	}

	@Override
	public NotificacionViewModel crearCargo(HistoriaLaboralViewModel historiaLaboralDto) {
		
		Date date = new Date();
		NotificacionViewModel notificacion = new NotificacionViewModel();
		
		//NUEVO
		HistorialLaboral entity;
		if(historiaLaboralDto.getIdHistorialLaboral() == null)
			entity = new HistorialLaboral();
		else entity = historialLaboralJpaRepository.findOne(historiaLaboralDto.getIdHistorialLaboral());
		
		//Se puede grabar el nuevo cargo
		Empleado empleado = empleadoJpaRepository.findOne(historiaLaboralDto.getIdEmpleado());
		entity.setEmpleado(empleado);
		
		if(historiaLaboralDto.getIdProyecto() != null){
			Proyecto proyecto = proyectoJpaRepository.findOne(historiaLaboralDto.getIdProyecto());
			entity.setProyecto(proyecto);
		}else{
			entity.setProyecto(null);
		}

		if(historiaLaboralDto.getIdCargo() != null){
			Cargo cargo = cargoJpaRepository.findOne(historiaLaboralDto.getIdCargo());
			entity.setCargo(cargo);
		}else{
			entity.setCargo(null);
		}
		
		entity.setFechaInicio(historiaLaboralDto.getFechaInicio());
		
		if(historiaLaboralDto.getFechaFin() != null){
			entity.setFechaFin(historiaLaboralDto.getFechaFin());
		}else{
			entity.setFechaFin(null);
		}
		
		if(historiaLaboralDto.getDescripcion() != null){
			entity.setDescripcion(historiaLaboralDto.getDescripcion());
		}else{
			entity.setDescripcion(null);
		}
		
//		if(historiaLaboralDto.getSalario() != null){
//			entity.setSalario(historiaLaboralDto.getSalario());
//		}else{
//			entity.setSalario(null);
//		}
		entity.setSalario(new BigDecimal(0));
		
		Moneda moneda = monedaJpaRepository.findOne(1L);
		entity.setMoneda(moneda);
		
		UnidadDeNegocio unidadDeNegocio = unidaDeNegocioJpaRepository.findOne(historiaLaboralDto.getIdUnidadDeNegocio());
		entity.setUnidadDeNegocio(unidadDeNegocio);
		
		if(historiaLaboralDto.getIdDepartamentoArea() != null){
			DepartamentoArea departamentoArea = departamentoAreaJpaRepository.findOne(historiaLaboralDto.getIdDepartamentoArea());
			entity.setDepartamentoArea(departamentoArea);
		}else{
			entity.setDepartamentoArea(null);
		}		
					
		historialLaboralJpaRepository.save(entity);
		
		notificacion.setCodigo(1L);
		notificacion.setSeverity("success");
		notificacion.setSummary("Runakuna Success");
		notificacion.setDetail("Se Registro correctamente");
		return notificacion;
				
	}
	
	public Date restarDiasFecha(Date fecha, int dias){
	
		       Calendar calendar = Calendar.getInstance();
		       calendar.setTime(fecha);
		       calendar.add(Calendar.DAY_OF_YEAR, dias);
	
		       return calendar.getTime();
	
	}


	@Override
	public List<HorarioViewModel> getObtenerNombreHorario() {
		// TODO Auto-generated method stub
		return cargoRepository.obtenerNombreHorario();
	}

	@Override
	public NotificacionViewModel eliminarCargo(Long idHistorialLaboral) {
		
		NotificacionViewModel notificacion = new NotificacionViewModel();
		
		Date fechaActual = new Date();			
		HistorialLaboral entity = historialLaboralJpaRepository.findOne(idHistorialLaboral);
		
		if (entity == null) {
			throw new BusinessException(SeverityStatusEnum.ERROR.getCode(),"Runakuna Error", "The record was changed by another user. Please re-enter the screen.");
		}
		try {
			if(fechaActual.before(entity.getFechaInicio())){
				historialLaboralJpaRepository.delete(idHistorialLaboral);
				historialLaboralJpaRepository.flush();
			
				notificacion.setCodigo(1L);
	            notificacion.setSeverity(SeverityStatusEnum.SUCCESS.getCode());
				notificacion.setSummary("Runakuna Success");
	            notificacion.setDetail("Registro fue eliminado correctamente.");
				
			}else{
				throw new BusinessException(SeverityStatusEnum.ERROR.getCode(),"Runakuna Error", "Restriction is Being used, Can't be deleted");
			}
			
		} catch (Exception e) {
			throw new GenericRestException("ERR_001", "Restriction is Being used, Can't be deleted");
		}
		
		return notificacion;
		
	}

		
	private NotificacionViewModel crearActualizarCargo(CargoViewModel cargoDto){
		NotificacionViewModel notificacionDto=new NotificacionViewModel();
		Cargo cargo=new Cargo();
		Empresa empresa=null;
		UnidadDeNegocio unidadDeNegocio=null;
		DepartamentoArea departamentoArea=null;
		Cargo superior= null;
		Proyecto proyecto=null;
		try{
			mapper.map(cargoDto, cargo);
			if(cargoDto.getIdEmpresa()!=null)
			  empresa=empresaJpaRepository.findOne(cargoDto.getIdEmpresa());
			if(cargoDto.getIdUnidadDeNegocio()!=null)
			  unidadDeNegocio=unidaDeNegocioJpaRepository.findOne(cargoDto.getIdUnidadDeNegocio());
			if(cargoDto.getIdDepartamentoArea()!=null)
			   departamentoArea=departamentoAreaJpaRepository.findOne(cargoDto.getIdDepartamentoArea());
			if(cargoDto.getIdSuperior()!=null)
				superior=cargoJpaRepository.findOne(cargoDto.getIdSuperior());
			if(cargoDto.getIdProyecto()!=null)
				proyecto=proyectoJpaRepository.findOne(cargoDto.getIdProyecto());
			cargo.setEmpresa(empresa);
			cargo.setUnidadDeNegocio(unidadDeNegocio);
			cargo.setDepartamentoArea(departamentoArea);
			cargo.setSuperior(superior);
			cargo.setProyecto(proyecto);
			cargo.setBandasSalariales(new ArrayList<>());
			for(BandaSalarialViewModel bandaSalarialDto: cargoDto.getBandasSalariales()) {
				BandaSalarial bandaSalarial=new BandaSalarial();
				mapper.map(bandaSalarialDto, bandaSalarial);
				bandaSalarial.setCargo(cargo);
				bandaSalarial.setMoneda(monedaJpaRepository.findOne(bandaSalarialDto.getIdMoneda()));
				cargo.getBandasSalariales().add(bandaSalarial);
			}
			cargoJpaRepository.save(cargo);
			cargoJpaRepository.flush();
			notificacionDto.setCodigo(1L);
			notificacionDto.setSeverity("success");
			notificacionDto.setSummary("Runakuna Success");
			notificacionDto.setDetail("Se registro correctamente");
		} catch (Exception e) {
			notificacionDto.setCodigo(0L);
			notificacionDto.setSeverity("error");
			notificacionDto.setSummary("Runakuna Error");
			notificacionDto.setDetail("No es posible registrar, "+e.getMessage());
		}
		return notificacionDto;
	}

	@Override
	public List<CargoResultViewModel> search(CargoFilterViewModel filterViewModel) {
		return cargoRepository.obtenerCargos(filterViewModel);
	}

	@Override
	public CargoViewModel findOne(Long idCargo) {
		CargoViewModel dto=new CargoViewModel();
		dto.setBandasSalariales(new ArrayList<BandaSalarialViewModel>());
		Cargo cargo=cargoJpaRepository.findOne(idCargo);
		mapper.map(cargo, dto);
		dto.setIdEmpresa(cargo.getEmpresa().getIdEmpresa());
		
		if(cargo.getUnidadDeNegocio()!=null)
			dto.setIdUnidadDeNegocio(cargo.getUnidadDeNegocio().getIdUnidadDeNegocio());
		
		if(cargo.getDepartamentoArea()!=null)
			dto.setIdDepartamentoArea(cargo.getDepartamentoArea().getIdDepartamentoArea());
		
		if(cargo.getSuperior()!=null)
			dto.setIdSuperior(cargo.getSuperior().getIdCargo());
		
		if(cargo.getProyecto()!=null)
			dto.setIdProyecto(cargo.getProyecto().getIdProyecto());
		
		dto.setBandasSalariales(new ArrayList<>());
		
		for(BandaSalarial banda:cargo.getBandasSalariales()) {
			BandaSalarialViewModel bandaDto=new BandaSalarialViewModel();
			mapper.map(banda,bandaDto);
			bandaDto.setIdCargo(banda.getCargo().getIdCargo());
			Moneda moneda=monedaJpaRepository.findOne(banda.getMoneda().getIdMoneda());
			bandaDto.setNombreMoneda(moneda.getNombre());
			bandaDto.setIdMoneda(moneda.getIdMoneda());
			dto.getBandasSalariales().add(bandaDto);
		}
		return dto;
	}

	@Override
	public NotificacionViewModel create(CargoViewModel manteinanceViewModel) {
		NotificacionViewModel notificacionDto=new NotificacionViewModel();
		notificacionDto=crearActualizarCargo(manteinanceViewModel);
		return notificacionDto;
	}

	@Override
	public NotificacionViewModel update(CargoViewModel manteinanceViewModel) {
		NotificacionViewModel notificacionDto=new NotificacionViewModel();
		notificacionDto=crearActualizarCargo(manteinanceViewModel);
		return notificacionDto;
	}

	@Override
	public NotificacionViewModel delete(Long idCargo) {
		NotificacionViewModel notificacion=new NotificacionViewModel();
		if (cargoJpaRepository.findOne(idCargo) == null) {
			throw new BusinessException(SeverityStatusEnum.ERROR.getCode(),"Runakuna Error", "The record was changed by another user. Please re-enter the screen.");
		}
		try {
			cargoJpaRepository.delete(idCargo);
			cargoJpaRepository.flush();
			notificacion.setCodigo(1L);
            notificacion.setSeverity(SeverityStatusEnum.SUCCESS.getCode());
			notificacion.setSummary("Runakuna Success");
            notificacion.setDetail("Registro eliminado correctamente.");
		} catch (Exception e) {
			throw new BusinessException(SeverityStatusEnum.ERROR.getCode(),"Runakuna Error", "Restriction is Being used, Can't be deleted");
		}
		return notificacion;
	}

	@Override
	public List<CargoResultViewModel> quickSearch(QuickFilterViewModel quickFilter) {
		return cargoRepository.busquedaRapidaCargos(quickFilter);
	}

	@Override
	public List<CargoComboViewModel> obtenerCargoComboHistorialLaboral(ProyectoFilterViewModel filter) {
		return cargoRepository.obtenerCargoComboHistorialLaboral(filter);
	}

	

}

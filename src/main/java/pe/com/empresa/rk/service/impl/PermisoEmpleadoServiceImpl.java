package pe.com.empresa.rk.service.impl;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Calendar;
import java.util.Date;

import org.dozer.Mapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pe.com.empresa.rk.domain.model.repository.jdbc.EmpleadoRepository;
import pe.com.empresa.rk.domain.model.repository.jdbc.HistoriaLaboralRepository;
import pe.com.empresa.rk.domain.model.repository.jdbc.PeriodoEmpleadoRepository;
import pe.com.empresa.rk.domain.model.repository.jdbc.PermisoEmpleadoJdbcRepository;
import pe.com.empresa.rk.enums.SeverityStatusEnum;
import pe.com.empresa.rk.exception.BusinessException;
import pe.com.empresa.rk.service.PermisoEmpleadoService;
import pe.com.empresa.rk.util.DateUtil;
import pe.com.empresa.rk.util.StringUtil;
import pe.com.empresa.rk.domain.model.entities.HorarioEmpleado;
import pe.com.empresa.rk.domain.model.entities.HorarioEmpleadoDia;
import pe.com.empresa.rk.domain.model.entities.Empleado;
import pe.com.empresa.rk.domain.model.entities.PeriodoEmpleado;
import pe.com.empresa.rk.domain.model.entities.PermisoEmpleado;
import pe.com.empresa.rk.domain.model.repository.jpa.EmpleadoJpaRepository;
import pe.com.empresa.rk.domain.model.repository.jpa.HorarioEmpleadoDiaJpaRepository;
import pe.com.empresa.rk.domain.model.repository.jpa.HorarioEmpleadoJpaRepository;
import pe.com.empresa.rk.domain.model.repository.jpa.PeriodoEmpleadoJpaRepository;
import pe.com.empresa.rk.domain.model.repository.jpa.PermisoEmpleadoJpaRepository;
import pe.com.empresa.rk.view.model.PermisoEmpleadoFilterViewModel;
import pe.com.empresa.rk.view.model.PermisoEmpleadoResultViewModel;
import pe.com.empresa.rk.view.model.EmpleadoViewModel;
import pe.com.empresa.rk.view.model.HistoriaLaboralViewModel;
import pe.com.empresa.rk.view.model.LicenciaEmpleadoViewModel;
import pe.com.empresa.rk.view.model.NotificacionViewModel;
import pe.com.empresa.rk.view.model.PeriodoEmpleadoViewModel;
import pe.com.empresa.rk.view.model.PermisoEmpleadoViewModel;
import pe.com.empresa.rk.view.model.QuickFilterViewModel;


@Service
public class PermisoEmpleadoServiceImpl implements PermisoEmpleadoService {

	private static final Logger LOGGER  = LoggerFactory.getLogger(PermisoEmpleadoServiceImpl.class);

	@Autowired
    PermisoEmpleadoJpaRepository permisoEmpleadoJpaRepository;
	
	@Autowired
    PermisoEmpleadoJdbcRepository permisoEmpleadoJdbcRepository;
	
	@Autowired
    HistoriaLaboralRepository historiaLaboralRepository;
	
	@Autowired
    PeriodoEmpleadoRepository periodoEmpleadoRepository;
	
	@Autowired
    PeriodoEmpleadoJpaRepository periodoEmpleadoJpaRepository;
	
	@Autowired
    EmpleadoJpaRepository empleadoJpaRepository;
	
	@Autowired
    EmpleadoRepository empleadoRepository;
	
	@Autowired
    HorarioEmpleadoJpaRepository horarioEmpleadoJpaRepository;
	
	@Autowired
    HorarioEmpleadoDiaJpaRepository horarioEmpleadoDiaJpaRepository;

	@Autowired
	Mapper mapper;
	
	@Override
	public NotificacionViewModel enviarPermisoEmpleado(PermisoEmpleadoViewModel permisoEmpleado) {

		NotificacionViewModel notificacion = new NotificacionViewModel();
		PermisoEmpleado entity = permisoEmpleadoJpaRepository.findOne(permisoEmpleado.getIdPermisoEmpleado());

		entity.setEstado("E");
		permisoEmpleadoJpaRepository.save(entity);
		permisoEmpleadoJpaRepository.flush();
		
		notificacion.setCodigo(1L);
		notificacion.setSeverity("success");
		notificacion.setSummary("Runakuna Success");
		notificacion.setDetail("El registro fue enviado correctamente.");
		return notificacion;
	}
	
	@Override
	public NotificacionViewModel devolverPermisoEmpleado(PermisoEmpleadoViewModel permisoEmpleado) {
		NotificacionViewModel notificacion = new NotificacionViewModel();
		PermisoEmpleado entity = permisoEmpleadoJpaRepository.findOne(permisoEmpleado.getIdPermisoEmpleado());

		entity.setEstado("P");
		entity.setComentarioResolucion(permisoEmpleado.getComentarioResolucion());
		permisoEmpleadoJpaRepository.save(entity);
		permisoEmpleadoJpaRepository.flush();
		
		notificacion.setCodigo(1L);
		notificacion.setSeverity("success");
		notificacion.setSummary("Runakuna Success");
		notificacion.setDetail("El registro fue devuelto correctamente");
		return notificacion;
	}
	
	@Override
	public NotificacionViewModel aprobarPermisoEmpleado(PermisoEmpleadoViewModel permisoEmpleado) {
		NotificacionViewModel notificacion = new NotificacionViewModel();
		PermisoEmpleado entity = permisoEmpleadoJpaRepository.findOne(permisoEmpleado.getIdPermisoEmpleado());
		
		PeriodoEmpleado periodoEntity = periodoEmpleadoJpaRepository.findOne(entity.getPeriodoEmpleado().getIdPeriodoEmpleado());
		
		periodoEntity.setPermisosDisponibles(periodoEntity.getPermisosDisponibles() - 1);
		
		periodoEmpleadoJpaRepository.save(periodoEntity);
		periodoEmpleadoJpaRepository.flush();
		
		entity.setEstado("A");
		permisoEmpleadoJpaRepository.save(entity);
		permisoEmpleadoJpaRepository.flush();
		
		notificacion.setCodigo(1L);
		notificacion.setSeverity("success");
		notificacion.setSummary("Runakuna Success");
		notificacion.setDetail("El registro fue aprobado correctamente.");
		return notificacion;
	}
	
	@Override
	public NotificacionViewModel rechazarPermisoEmpleado(PermisoEmpleadoViewModel permisoEmpleado) {
		NotificacionViewModel notificacion = new NotificacionViewModel();
		PermisoEmpleado entity = permisoEmpleadoJpaRepository.findOne(permisoEmpleado.getIdPermisoEmpleado());

		entity.setEstado("R");
		entity.setComentarioResolucion(permisoEmpleado.getComentarioResolucion());
		permisoEmpleadoJpaRepository.save(entity);
		permisoEmpleadoJpaRepository.flush();
		
		notificacion.setCodigo(1L);
		notificacion.setSeverity("success");
		notificacion.setSummary("Runakuna Success");
		notificacion.setDetail("El registro fue rechazado correctamente.");
		return notificacion;
	}
	
	@Override
	public NotificacionViewModel actualizarPermisoEmpleado(PermisoEmpleadoViewModel permisoEmpleado) {

		NotificacionViewModel notificacion = new NotificacionViewModel();
		
		PermisoEmpleado entity = permisoEmpleadoJpaRepository.findOne(permisoEmpleado.getIdPermisoEmpleado());

		
		entity.setMotivo(permisoEmpleado.getMotivo());
		entity.setRazon(permisoEmpleado.getRazon());
		entity.setFecha(permisoEmpleado.getFecha());
		entity.setHoraInicio(permisoEmpleado.getHoraInicio());
		entity.setHoraFin(permisoEmpleado.getHoraFin());
		entity.setEstado(permisoEmpleado.getEstado());
		entity.setHoras(permisoEmpleado.getHoras());
		entity.setFechaRecuperacion(permisoEmpleado.getFechaRecuperacion());
		entity.setHoraInicioRecuperacion(permisoEmpleado.getHoraInicioRecuperacion());
		entity.setHoraFinRecuperacion(permisoEmpleado.getHoraFinRecuperacion());


		
		PeriodoEmpleado periodoEntity = periodoEmpleadoJpaRepository.findOne(permisoEmpleado.getPeriodoEmpleado().getIdPeriodoEmpleado());
		entity.setPeriodoEmpleado(periodoEntity);

		Empleado empleado = empleadoJpaRepository.findOne(permisoEmpleado.getIdAtendidoPor());
		entity.setEmpleado(empleado);
		
		permisoEmpleadoJpaRepository.save(entity);
		permisoEmpleadoJpaRepository.flush();
		
		notificacion.setCodigo(1L);
		notificacion.setSeverity("success");
		notificacion.setSummary("Runakuna Success");
		notificacion.setDetail("Se actualizo el codigo: "+permisoEmpleado.getIdPermisoEmpleado());
		return notificacion;
	}
	
	@Override
	public NotificacionViewModel registrarPermisoEmpleado(PermisoEmpleadoViewModel permisoEmpleado) {
		NotificacionViewModel notificacion = new NotificacionViewModel();
		
		try{
			Calendar c1 = Calendar.getInstance();
		
			List<PermisoEmpleado> lista = permisoEmpleadoJpaRepository.buscarPermisoPorFecha(permisoEmpleado.getFecha());
			
			if(lista != null && lista.size() >0){
				throw new BusinessException(SeverityStatusEnum.ERROR.getCode(),"Runakuna Error","La Fecha del Permiso ya existe.");
			}
			
			HorarioEmpleado horarioEmpleado = horarioEmpleadoJpaRepository.obtenerHorarioPorFechaVigente(permisoEmpleado.getFecha(), permisoEmpleado.getPeriodoEmpleado().getIdEmpleado());
			
			if(horarioEmpleado == null){
				throw new BusinessException(SeverityStatusEnum.ERROR.getCode(),"Runakuna Error","No tiene configuracion de Horario Empleado.");
			}
			
			c1.setTime(permisoEmpleado.getFecha());
			
			HorarioEmpleadoDia horarioEmpleadoDia = horarioEmpleadoDiaJpaRepository.obtenerHorarioDiaPorDiaDeSemana(horarioEmpleado.getIdHorarioEmpleado(), StringUtil.obtenerCodigoDia(c1.get(Calendar.DAY_OF_WEEK)));
			
			if(horarioEmpleadoDia == null){
				throw new BusinessException(SeverityStatusEnum.ERROR.getCode(),"Runakuna Error","No tiene configuracion de Horario Empleado Dia.");
			}
			
			Date fechaHoraIngresoPermiso =  DateUtil.parse(new SimpleDateFormat("HH:mm"),permisoEmpleado.getHoraInicio());
			Date fechaHoraSalidaPermiso=  DateUtil.parse(new SimpleDateFormat("HH:mm"),permisoEmpleado.getHoraFin());
			
			Date fechaHoraIngresoDia=  DateUtil.parse(new SimpleDateFormat("HH:mm"),horarioEmpleadoDia.getEntrada());
			Date fechaHoraSalidaDia=  DateUtil.parse(new SimpleDateFormat("HH:mm"),horarioEmpleadoDia.getSalida());
			
			if(fechaHoraIngresoPermiso.before(fechaHoraIngresoDia)){
				throw new BusinessException(SeverityStatusEnum.ERROR.getCode(),"Runakuna Error","La hora del inicio del permiso debe ser posterior a la hora de entrada del dia que solicita el permiso.");
			}
			
			if(fechaHoraSalidaPermiso.after(fechaHoraSalidaDia)){
				throw new BusinessException(SeverityStatusEnum.ERROR.getCode(),"Runakuna Error","La hora del fin del permiso debe ser inferior a la la hora de salida del dia que solicita el permiso.");
			}
			
			PermisoEmpleado entity;
			entity = mapper.map(permisoEmpleado, PermisoEmpleado.class);
			
			PeriodoEmpleado periodoEntity = periodoEmpleadoJpaRepository.findOne(permisoEmpleado.getPeriodoEmpleado().getIdPeriodoEmpleado());
			
			Empleado empleadoEntity = empleadoJpaRepository.findOne(permisoEmpleado.getIdAtendidoPor());
			
			entity.setPeriodoEmpleado(periodoEntity);
			entity.setEmpleado(empleadoEntity);
			
			//entity.setEstado("P");
			entity.setEstado("E");
			
			permisoEmpleadoJpaRepository.save(entity);
			permisoEmpleadoJpaRepository.flush();
			
			notificacion.setCodigo(1L);
			notificacion.setSeverity(SeverityStatusEnum.SUCCESS.getCode());
			notificacion.setSummary("Runakuna Success");
			notificacion.setDetail("Se registro correctamente.");
		} catch (Exception e) {
			LOGGER.info(e.getMessage(), e);
			notificacion.setCodigo(0L);
			notificacion.setSeverity("error");
			notificacion.setSummary("Runakuna Error");
			notificacion.setDetail("No es posible actualizar, "+e.getMessage());
			e.printStackTrace();
		}
		return notificacion;
	}

	@Override
	public HistoriaLaboralViewModel obtenerHistoriaLaboralActual(EmpleadoViewModel empleadoDto) {
		HistoriaLaboralViewModel dto = new HistoriaLaboralViewModel();
		
		dto = historiaLaboralRepository.obtenerHistoriaLaboralActual(empleadoDto);
		return dto;
	}
	
	@Override
	public List<HistoriaLaboralViewModel> obtenerHistoriasLaboralesActualPorEmpleado(EmpleadoViewModel empleadoDto) {
		List<HistoriaLaboralViewModel> dto = new ArrayList<>();
		
		dto = historiaLaboralRepository.obtenerHistoriasLaboralesActualPorEmpleado(empleadoDto);
		return dto;
	}

	@Override
	public PeriodoEmpleadoViewModel obtenerPeriodoEmpleadoActual(EmpleadoViewModel empleadoDto) {
		PeriodoEmpleadoViewModel dto = new PeriodoEmpleadoViewModel();
		
		dto = periodoEmpleadoRepository.obtenerPeriodoEmpleadoActual(empleadoDto);
		return dto;
	}
	
	@Override
	public  List<PermisoEmpleadoFilterViewModel> autocompleteEmpleado(String search) {
		List<PermisoEmpleadoFilterViewModel> dto = new ArrayList<>();
		
		dto = empleadoRepository.autocompleteEmpleado(search);
		return dto;
	}

	@Override
	public HistoriaLaboralViewModel obtenerHistoriaLaboralLicencia(LicenciaEmpleadoViewModel licenciaEmpleadoD) {
		HistoriaLaboralViewModel dto = new HistoriaLaboralViewModel();
		
		dto = historiaLaboralRepository.obtenerHistoriaLaboralLicencia(licenciaEmpleadoD);
		return dto;
	}

	@Override
	public List<PermisoEmpleadoResultViewModel> search(PermisoEmpleadoFilterViewModel filterViewModel) {
		return permisoEmpleadoJdbcRepository.busquedaPermisoEmpleado(filterViewModel);
	}

	@Override
	public PermisoEmpleadoViewModel findOne(Long id) {
		PermisoEmpleadoViewModel dto=new PermisoEmpleadoViewModel();
		PermisoEmpleado permisoEmpleado=permisoEmpleadoJpaRepository.findOne(id);
		mapper.map(permisoEmpleado, dto);
		PeriodoEmpleado periodoEmpleado = periodoEmpleadoJpaRepository.findOne(permisoEmpleado.getPeriodoEmpleado().getIdPeriodoEmpleado());
		dto.setIdEmpleado(periodoEmpleado.getEmpleado().getIdEmpleado());
		String estado=permisoEmpleado.getEstado();
		String estadoDescripcion="";
		switch (estado) {
		case "P" :estadoDescripcion="Pendiente"; break;
		case "E" :estadoDescripcion="Enviado"; break;
		case "A" :estadoDescripcion="Aprobado"; break;
		case "R" :estadoDescripcion="Rechazado"; break;
		default: estadoDescripcion=""; break;
		}
		dto.setEstado(estado);
		dto.setEstadoString(estadoDescripcion);
		if(permisoEmpleado.getEmpleado()!=null && dto.getEstadoString()=="Enviado"){
			dto.setIdAtendidoPor(permisoEmpleado.getEmpleado().getIdEmpleado());
		}
		
		return dto;
	}

	@Override
	public NotificacionViewModel create(PermisoEmpleadoViewModel manteinanceViewModel) {
		return null;
	}

	@Override
	public NotificacionViewModel update(PermisoEmpleadoViewModel manteinanceViewModel) {
		return null;
	}

	@Override
	public NotificacionViewModel delete(Long id) {
		return null;
	}
	
	@Override
	public List<HistoriaLaboralViewModel> obtenerHistoriasLaboralesPorEmpleado(Long idEmpleado) {
		List<HistoriaLaboralViewModel> dto = new ArrayList<>();
		
		dto = historiaLaboralRepository.obtenerHistoriasLaboralesPorEmpleado(idEmpleado);
		return dto;
	}

	@Override
	public List<PermisoEmpleadoResultViewModel> quickSearch(QuickFilterViewModel quickFilter) {
		return permisoEmpleadoJdbcRepository.busquedaRapidaPermisoEmpleado(quickFilter);
	}
	

}

package pe.com.tss.runakuna.service.impl;

import static java.util.stream.Collectors.toList;

import java.util.ArrayList;
import java.util.List;

import org.dozer.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pe.com.tss.runakuna.domain.model.entities.Alerta;
import pe.com.tss.runakuna.domain.model.entities.Empresa;
import pe.com.tss.runakuna.domain.model.entities.TablaGeneral;
import pe.com.tss.runakuna.domain.model.repository.jdbc.TablaGeneralJdbcRepository;
import pe.com.tss.runakuna.domain.model.repository.jpa.TablaGeneralJpaRepository;
import pe.com.tss.runakuna.view.model.AlertaSubscriptorViewModel;
import pe.com.tss.runakuna.view.model.AlertaViewModel;
import pe.com.tss.runakuna.view.model.HorarioDiaViewModel;
import pe.com.tss.runakuna.view.model.NotificacionViewModel;
import pe.com.tss.runakuna.view.model.TablaGeneralFilterViewModel;
import pe.com.tss.runakuna.view.model.TablaGeneralResultViewModel;
import pe.com.tss.runakuna.view.model.TablaGeneralViewModel;
import pe.com.tss.runakuna.service.TablaGeneralService;

@Service
public class TablaGeneralServiceImpl implements TablaGeneralService{

	@Autowired
    Mapper mapper;

	@Autowired
	TablaGeneralJpaRepository tablaGeneralJpaRepository;

	@Autowired
	TablaGeneralJdbcRepository tablaGeneralJdbcRepository;

	@Override
	public List<TablaGeneralViewModel> obtenerTiposDocumento() {
		List<TablaGeneral> entities;
		List<TablaGeneralViewModel> items;

		entities = tablaGeneralJpaRepository.findByGrupo("Empleado.TipoDocumento");

		items = entities.stream().map(m -> mapper.map(m, TablaGeneralViewModel.class)).collect(toList());

		return items;
	}

	@Override
	public List<TablaGeneralViewModel> obtenerEstadosCivil() {
		List<TablaGeneral> entities;
		List<TablaGeneralViewModel> items;

		entities = tablaGeneralJpaRepository.findByGrupo("Empleado.EstadoCivil");

		items = entities.stream().map(m -> mapper.map(m, TablaGeneralViewModel.class)).collect(toList());

		return items;
	}

	@Override
	public List<TablaGeneralViewModel> obtenerGruposSanguineo() {
		List<TablaGeneral> entities;
		List<TablaGeneralViewModel> items;

		entities = tablaGeneralJpaRepository.findByGrupo("Empleado.GrupoSanguineo");

		items = entities.stream().map(m -> mapper.map(m, TablaGeneralViewModel.class)).collect(toList());

		return items;
	}

	@Override
	public List<TablaGeneralViewModel> obtenerGeneros() {
		List<TablaGeneral> entities;
		List<TablaGeneralViewModel> items;

		entities = tablaGeneralJpaRepository.findByGrupo("Empleado.Generico");

		items = entities.stream().map(m -> mapper.map(m, TablaGeneralViewModel.class)).collect(toList());

		return items;
	}

	@Override
	public List<TablaGeneralViewModel> obtenerTiposDomicilio() {
		List<TablaGeneral> entities;
		List<TablaGeneralViewModel> items;

		entities = tablaGeneralJpaRepository.findByGrupo("Empleado.TipoDomicilio");

		items = entities.stream().map(m -> mapper.map(m, TablaGeneralViewModel.class)).collect(toList());

		return items;
	}

	@Override
	public List<TablaGeneralViewModel> obtenerRegimenHorario() {
		List<TablaGeneral> entities;
		List<TablaGeneralViewModel> items;

		entities = tablaGeneralJpaRepository.findByGrupo("Empleado.RegimenHorario");

		items = entities.stream().map(m -> mapper.map(m, TablaGeneralViewModel.class)).collect(toList());

		return items;
	}

	@Override
	public List<TablaGeneralViewModel> obtenerContratoTrabajo() {
		List<TablaGeneral> entities;
		List<TablaGeneralViewModel> items;

		entities = tablaGeneralJpaRepository.findByGrupo("Empleado.ContratoTrabajo");

		items = entities.stream().map(m -> mapper.map(m, TablaGeneralViewModel.class)).collect(toList());

		return items;
	}

	@Override
	public List<TablaGeneralViewModel> obtenerTipoTrabajo() {
		List<TablaGeneral> entities;
		List<TablaGeneralViewModel> items;

		entities = tablaGeneralJpaRepository.findByGrupo("Empleado.TipoTrabajo");

		items = entities.stream().map(m -> mapper.map(m, TablaGeneralViewModel.class)).collect(toList());

		return items;
	}

	@Override
	public List<TablaGeneralViewModel> obtenerRegimenLaboral() {
		List<TablaGeneral> entities;
		List<TablaGeneralViewModel> items;

		entities = tablaGeneralJpaRepository.findByGrupo("Empleado.RegimenLaboral");

		items = entities.stream().map(m -> mapper.map(m, TablaGeneralViewModel.class)).collect(toList());

		return items;
	}

	@Override
	public List<TablaGeneralViewModel> obtenerNivelEducacion() {
		List<TablaGeneral> entities;
		List<TablaGeneralViewModel> items;

		entities = tablaGeneralJpaRepository.findByGrupo("Empleado.NivelEducacion");

		items = entities.stream().map(m -> mapper.map(m, TablaGeneralViewModel.class)).collect(toList());

		return items;
	}

	@Override
	public List<TablaGeneralViewModel> obtenerTipoHorario() {
		List<TablaGeneral> entities;
		List<TablaGeneralViewModel> items;

		entities = tablaGeneralJpaRepository.findByGrupo("Horario.TipoHorario");

		items = entities.stream().map(m -> mapper.map(m, TablaGeneralViewModel.class)).collect(toList());

		return items;
	}

	@Override
	public List<HorarioDiaViewModel> obtenerDias() {
		List<TablaGeneral> entities;
		List<HorarioDiaViewModel> items;

		entities = tablaGeneralJpaRepository.findByGrupo("Dia");

		items = entities.stream().map(m -> mapper.map(m, HorarioDiaViewModel.class)).collect(toList());

		return items;
	}

	@Override
	public List<TablaGeneralViewModel> obtenerTipoEquipo() {
		List<TablaGeneral> entities;
		List<TablaGeneralViewModel> items;

		entities = tablaGeneralJpaRepository.findByGrupo("EquiposEntregados.TipoEquipo");

		items = entities.stream().map(m -> mapper.map(m, TablaGeneralViewModel.class)).collect(toList());

		return items;
	}

	@Override
	public List<TablaGeneralViewModel> obtenerEmpleadoEstado() {
		List<TablaGeneral> entities;
		List<TablaGeneralViewModel> items;

		entities = tablaGeneralJpaRepository.findByGrupo("Empleado.Estado");

		items = entities.stream().map(m -> mapper.map(m, TablaGeneralViewModel.class)).collect(toList());

		return items;
	}

	@Override
	public List<TablaGeneralViewModel> obtenerEstadoTipoEquipo() {
		List<TablaGeneral> entities;
		List<TablaGeneralViewModel> items;

		entities = tablaGeneralJpaRepository.findByGrupo("EquiposEntregados.Estado");

		items = entities.stream().map(m -> mapper.map(m, TablaGeneralViewModel.class)).collect(toList());

		return items;
	}

	@Override
	public List<TablaGeneralViewModel> obtenerRelacionContactoEmergencia() {
		List<TablaGeneral> entities;
		List<TablaGeneralViewModel> items;

		entities = tablaGeneralJpaRepository.findByGrupo("Empleado.RelacionContacto");

		items = entities.stream().map(m -> mapper.map(m, TablaGeneralViewModel.class)).collect(toList());

		return items;
	}

	@Override
	public List<TablaGeneralViewModel> obtenerMotivosPermiso() {
		List<TablaGeneral> entities;
		List<TablaGeneralViewModel> items;

		entities = tablaGeneralJpaRepository.findByGrupo("Permiso.Tipo");

		items = entities.stream().map(m -> mapper.map(m, TablaGeneralViewModel.class)).collect(toList());

		return items;
	}

	@Override
	public List<TablaGeneralViewModel> obtenerPermisoEstado() {
		List<TablaGeneral> entities;
		List<TablaGeneralViewModel> items;

		entities = tablaGeneralJpaRepository.findByGrupo("Permiso.Estado");

		items = entities.stream().map(m -> mapper.map(m, TablaGeneralViewModel.class)).collect(toList());

		return items;
	}

	@Override
	public List<TablaGeneralViewModel> obtenerRelacionDependiente() {
		List<TablaGeneral> entities;
		List<TablaGeneralViewModel> items;

		entities = tablaGeneralJpaRepository.findByGrupo("Empleado.RelacionDependiente");

		items = entities.stream().map(m -> mapper.map(m, TablaGeneralViewModel.class)).collect(toList());

		return items;
	}

	@Override
	public List<TablaGeneralViewModel> obtenerVacacionesEstados() {
		List<TablaGeneral> entities;
		List<TablaGeneralViewModel> items;

		entities = tablaGeneralJpaRepository.findByGrupo("Vacaciones.Estado");

		items = entities.stream().map(m -> mapper.map(m, TablaGeneralViewModel.class)).collect(toList());

		return items;
	}

	@Override
	public List<TablaGeneralViewModel> obtenerEstados() {
		List<TablaGeneral> entities;
		List<TablaGeneralViewModel> items;

		entities = tablaGeneralJpaRepository.findByGrupo("Estado");

		items = entities.stream().map(m -> mapper.map(m, TablaGeneralViewModel.class)).collect(toList());

		return items;
	}

	@Override
	public List<TablaGeneralViewModel> obtenerMotivoRenuncia() {
		List<TablaGeneral> entities;
		List<TablaGeneralViewModel> items;

		entities = tablaGeneralJpaRepository.findByGrupo("BajaDeEmpleado.Motivo");

		items = entities.stream().map(m -> mapper.map(m, TablaGeneralViewModel.class)).collect(toList());

		return items;
	}

	@Override
	public List<TablaGeneralViewModel> obtenerEntidadFinanciera() {
		List<TablaGeneral> entities;
		List<TablaGeneralViewModel> items;

		entities = tablaGeneralJpaRepository.findByGrupo("Entidad Financiera");

		items = entities.stream().map(m -> mapper.map(m, TablaGeneralViewModel.class)).collect(toList());

		return items;
	}

	@Override
	public List<TablaGeneralViewModel> obtenerAFP() {
		List<TablaGeneral> entities;
		List<TablaGeneralViewModel> items;

		entities = tablaGeneralJpaRepository.findByGrupo("AFP");

		items = entities.stream().map(m -> mapper.map(m, TablaGeneralViewModel.class)).collect(toList());

		return items;
	}

	@Override
	public List<TablaGeneralViewModel> obtenerEPS() {
		List<TablaGeneral> entities;
		List<TablaGeneralViewModel> items;

		entities = tablaGeneralJpaRepository.findByGrupo("EPS");

		items = entities.stream().map(m -> mapper.map(m, TablaGeneralViewModel.class)).collect(toList());

		return items;
	}

	@Override
	public List<TablaGeneralViewModel> obtenerLicenciaEstados() {
		List<TablaGeneral> entities;
		List<TablaGeneralViewModel> items;

		entities = tablaGeneralJpaRepository.findByGrupo("Licencia.Estado");

		items = entities.stream().map(m -> mapper.map(m, TablaGeneralViewModel.class)).collect(toList());

		return items;
	}

	@Override
	public List<TablaGeneralViewModel> obtenerTipoReporte() {
		List<TablaGeneral> entities;
		List<TablaGeneralViewModel> items;

		entities = tablaGeneralJpaRepository.findByGrupo("ReporteMarcaciones.TipoReporte");

		items = entities.stream().map(m -> mapper.map(m, TablaGeneralViewModel.class)).collect(toList());

		return items;
	}

	@Override
	public List<TablaGeneralViewModel> obtenerTipoNotificacion() {
		List<TablaGeneral> entities;
		List<TablaGeneralViewModel> items;

		entities = tablaGeneralJpaRepository.findByGrupo("Alerta.TipoNotificacion");

		items = entities.stream().map(m -> mapper.map(m, TablaGeneralViewModel.class)).collect(toList());

		return items;
	}

	@Override
	public List<TablaGeneralViewModel> obtenerTipoAlerta() {
		List<TablaGeneral> entities;
		List<TablaGeneralViewModel> items;

		entities = tablaGeneralJpaRepository.findByGrupo("Alerta.Tipo");

		items = entities.stream().map(m -> mapper.map(m, TablaGeneralViewModel.class)).collect(toList());

		return items;
	}

	@Override
	public List<TablaGeneralViewModel> obtenerTipoMarcacion() {
		List<TablaGeneral> entities;
		List<TablaGeneralViewModel> items;

		entities = tablaGeneralJpaRepository.findByGrupo("Dashboard.TipoMarcacion");

		items = entities.stream().map(m -> mapper.map(m, TablaGeneralViewModel.class)).collect(toList());

		return items;
	}

	@Override
	public List<TablaGeneralViewModel> buscarGrupoTablaGeneral() {
		/*List<TablaGeneral> entities;
		List<TablaGeneralViewModel> items;

		entities = tablaGeneralJpaRepository.findAll();

		items = entities.stream().map(m -> mapper.map(m, TablaGeneralViewModel.class)).collect(toList());

		return items;*/
		return tablaGeneralJdbcRepository.buscarGrupoTablaGeneral();
	}

	@Override
	public List<TablaGeneralResultViewModel> obtenerCodigosTablaGeneral(TablaGeneralFilterViewModel dto) {
		// TODO Auto-generated method stub
		return tablaGeneralJdbcRepository.obtenerCodigosTablaGeneral(dto);
	}

	@Override
	public TablaGeneralResultViewModel findOne(Long idTablaGeneral) {
		TablaGeneralResultViewModel dto=new TablaGeneralResultViewModel();
		TablaGeneral alerta=tablaGeneralJpaRepository.findOne(idTablaGeneral);
		mapper.map(alerta, dto);
		return dto;
	}

	@Override
	public NotificacionViewModel update(TablaGeneralResultViewModel tablaGeneralDto) {
		NotificacionViewModel notificacionDto=new NotificacionViewModel();
		notificacionDto= crearActualizarTablaGeneral(tablaGeneralDto);
		return notificacionDto;
	}

	private NotificacionViewModel crearActualizarTablaGeneral(TablaGeneralResultViewModel tablaGeneralDto) {
		NotificacionViewModel notificacionDto=new NotificacionViewModel();
		TablaGeneral entity=new TablaGeneral();

		try {
			mapper.map(tablaGeneralDto, entity);
			tablaGeneralJpaRepository.save(entity);
			tablaGeneralJpaRepository.flush();
			notificacionDto.setCodigo(1L);
			notificacionDto.setSeverity("success");
			notificacionDto.setSummary("Runakuna Success");
			notificacionDto.setDetail("Se actualizo correctamente");
		} catch (Exception e) {
			notificacionDto.setCodigo(0L);
			notificacionDto.setSeverity("error");
			notificacionDto.setSummary("Runakuna Error");
			notificacionDto.setDetail("No es posible actualizar, "+e.getMessage());
			e.printStackTrace();
		}
		return notificacionDto;
	}

}

package pe.com.tss.runakuna.service.impl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import org.dozer.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pe.com.tss.runakuna.domain.model.entities.Accion;
import pe.com.tss.runakuna.domain.model.entities.Modulo;
import pe.com.tss.runakuna.domain.model.repository.jdbc.ModuloRepository;
import pe.com.tss.runakuna.domain.model.repository.jpa.ModuloJpaRepository;
import pe.com.tss.runakuna.service.intf.ModuloService;
import pe.com.tss.runakuna.view.model.AccionViewModel;
import pe.com.tss.runakuna.view.model.ModuloFilterViewModel;
import pe.com.tss.runakuna.view.model.ModuloResultViewModel;
import pe.com.tss.runakuna.view.model.ModuloViewModel;
import pe.com.tss.runakuna.view.model.NotificacionViewModel;

/**
 * Created by josediaz on 25/11/2016.
 */
@Service
public class ModuloServiceImpl implements ModuloService {

    @Autowired
    private ModuloRepository moduloRepository;
    
    @Autowired
    private ModuloJpaRepository moduloJpaRepository;
    
    @Autowired
    private Mapper mapper;
    
    static final Comparator<ModuloViewModel> MODULE_ORDER = 
            new Comparator<ModuloViewModel>() {
    	public int compare(ModuloViewModel e1, ModuloViewModel e2) {
    		return e1.getOrden().compareTo(e2.getOrden());
    	}
    };

    @Override
    public List<ModuloViewModel> getModulosPermitidosPorUsuario(String cuentaUsuario) {
        List<ModuloViewModel> allowedMods = moduloRepository.getModulosPermitidosPorUsuario(cuentaUsuario);
        ModuloViewModel moduloPadre=null;
        ModuloViewModel moduloHijo=null;
        List<ModuloViewModel> result=new ArrayList<ModuloViewModel>();
        
        for(ModuloViewModel modulo:allowedMods){
        	if(modulo.getTieneSubMenu()!=null && modulo.getTieneSubMenu().equals("1")){
        		if(moduloPadre!=null && moduloPadre.getSubModulos()!=null && moduloPadre.getSubModulos().size()>0) {
        			result.add(moduloPadre);
        		}
        		moduloPadre=new ModuloViewModel();
        		mapper.map(modulo, moduloPadre);
        		moduloPadre.setSubModulos(new ArrayList<ModuloViewModel>());
        	} else {
        		moduloHijo=new ModuloViewModel();
        		mapper.map(modulo, moduloHijo);
        		moduloPadre.getSubModulos().add(moduloHijo);
        	}
        	
        }
        if(moduloPadre!=null && moduloPadre.getSubModulos()!=null && moduloPadre.getSubModulos().size()>0) {
        	result.add(moduloPadre);
        }
        
        Collections.sort(result, MODULE_ORDER);
        
        for (ModuloViewModel moduloViewModel : result) {
        	Collections.sort(moduloViewModel.getSubModulos(), MODULE_ORDER);
		}
        
        return result;
    }

	@Override
	public List<ModuloResultViewModel> search(ModuloFilterViewModel filterViewModel) {
		// TODO Auto-generated method stub
		return moduloRepository.buscarModulos(filterViewModel);
	}

	@Override
	public ModuloViewModel findOne(Long id) {
		ModuloViewModel moduloViewModel=new ModuloViewModel();
		moduloViewModel.setAcciones(new ArrayList<AccionViewModel>());
		Modulo modulo= moduloJpaRepository.findOne(id);
		mapper.map(modulo, moduloViewModel);
		moduloViewModel.setAcciones(new ArrayList<AccionViewModel>());
		if(modulo.getParent()!=null){
			moduloViewModel.setIdParent(modulo.getParent().getIdModulo());
		}
		for(Accion accion:modulo.getAcciones()) {
			AccionViewModel accionViewModel=new AccionViewModel();
			mapper.map(accion, accionViewModel);
			accionViewModel.setIdModulo(moduloViewModel.getIdModulo());
			if(accionViewModel.getAutorizacionDefecto()!=null && accionViewModel.getAutorizacionDefecto().intValue()==1) {
				accionViewModel.setAutorizacionDefectoString("SI");
			} else {
				accionViewModel.setAutorizacionDefectoString("NO");
			}
			if(accionViewModel.getEditable()!=null && accionViewModel.getEditable().intValue()==1) {
				accionViewModel.setEditableString("SI");
			} else {
				accionViewModel.setEditableString("NO");
			}
			moduloViewModel.getAcciones().add(accionViewModel);
		}
		
		return moduloViewModel;
	}

	@Override
	public NotificacionViewModel create(ModuloViewModel manteinanceViewModel) {
		NotificacionViewModel notificacionDto=new NotificacionViewModel();
		notificacionDto=crearActualizarModulo(manteinanceViewModel);
		return notificacionDto;
	}

	private NotificacionViewModel crearActualizarModulo(ModuloViewModel moduloViewModel) {
		NotificacionViewModel notificacionDto=new NotificacionViewModel();
		Modulo modulo=new Modulo();
		try{
			mapper.map(moduloViewModel, modulo);
			Modulo parent=moduloJpaRepository.findOne(moduloViewModel.getIdParent());
			modulo.setParent(parent);
			modulo.setAcciones(new ArrayList<Accion>());
			for(AccionViewModel accionViewModel:moduloViewModel.getAcciones()) {
				Accion accion=new Accion();
				mapper.map(accionViewModel, accion);
				accion.setModulo(modulo);
				modulo.getAcciones().add(accion);
			}
			moduloJpaRepository.save(modulo);
			moduloJpaRepository.flush();
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
	public NotificacionViewModel update(ModuloViewModel manteinanceViewModel) {
		NotificacionViewModel notificacionDto=new NotificacionViewModel();
		notificacionDto=crearActualizarModulo(manteinanceViewModel);
		return notificacionDto;
	}

	@Override
	public NotificacionViewModel delete(Long id) {
		// TODO Auto-generated method stub
		return null;
	}
    


	
}

package pe.com.tss.runakuna.view.model;

import java.math.BigDecimal;
import java.util.List;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import pe.com.tss.runakuna.json.JsonFlagIntegerDeserializer;
import pe.com.tss.runakuna.json.JsonFlagIntegerSerializer;

/**
 * Created by josediaz on 25/11/2016.
 */
public class ModuloViewModel extends AuditingEntityViewModel{

    protected Long idModulo;
    
    protected Long idParent;

    private String codigo;

    private String helpUrl;

    private String etiquetaMenu;

    private BigDecimal orden;

    private String nombre;

    private String url;

    private String tipoPermiso;
    
    @JsonSerialize(using=JsonFlagIntegerSerializer.class)
	@JsonDeserialize(using=JsonFlagIntegerDeserializer.class)
    private Integer visible;
    
    private String roleName;
    
    private String idEmpleado;

    private List<ModuloViewModel> subModulos;
    
    private List<AccionViewModel> acciones;
    
    private String tieneSubMenu;

    private String imageClass;

    public ModuloViewModel(){}

    public ModuloViewModel(Long idModulo, String codigo, String helpUrl, String etiquetaMenu, BigDecimal orden, String nombre, String url, String roleName, String idEmpleado) {
        this.idModulo = idModulo;
        this.codigo = codigo;
        this.helpUrl = helpUrl;
        this.etiquetaMenu = etiquetaMenu;
        this.orden = orden;
        this.nombre = nombre;
        this.url = url;
        this.roleName = roleName;
        this.idEmpleado = idEmpleado;
    }

    public Long getIdModulo() {
        return idModulo;
    }

    public void setIdModulo(Long idModulo) {
        this.idModulo = idModulo;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getHelpUrl() {
        return helpUrl;
    }

    public void setHelpUrl(String helpUrl) {
        this.helpUrl = helpUrl;
    }

    public String getEtiquetaMenu() {
        return etiquetaMenu;
    }

    public void setEtiquetaMenu(String etiquetaMenu) {
        this.etiquetaMenu = etiquetaMenu;
    }

    public BigDecimal getOrden() {
        return orden;
    }

    public void setOrden(BigDecimal orden) {
        this.orden = orden;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getTipoPermiso() {
        return tipoPermiso;
    }

    public void setTipoPermiso(String tipoPermiso) {
        this.tipoPermiso = tipoPermiso;
    }

    public Integer getVisible() {
        return visible;
    }

    public void setVisible(Integer visible) {
        this.visible = visible;
    }

    public List<ModuloViewModel> getSubModulos() {
        return subModulos;
    }

    public void setSubModulos(List<ModuloViewModel> subModulos) {
        this.subModulos = subModulos;
    }

	public String getRoleName() {
		return roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}

	public String getIdEmpleado() {
		return idEmpleado;
	}

	public void setIdEmpleado(String idEmpleado) {
		this.idEmpleado = idEmpleado;
	}

	public String getTieneSubMenu() {
		return tieneSubMenu;
	}

	public void setTieneSubMenu(String tieneSubMenu) {
		this.tieneSubMenu = tieneSubMenu;
	}

	public Long getIdParent() {
		return idParent;
	}

	public void setIdParent(Long idParent) {
		this.idParent = idParent;
	}

	public List<AccionViewModel> getAcciones() {
		return acciones;
	}

	public void setAcciones(List<AccionViewModel> acciones) {
		this.acciones = acciones;
	}

	public String getImageClass() {
		return imageClass;
	}

	public void setImageClass(String imageClass) {
		this.imageClass = imageClass;
	}
        
}


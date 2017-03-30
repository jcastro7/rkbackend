package pe.com.empresa.rk.domain.model.entities;

import pe.com.empresa.rk.domain.model.base.AuditingEntity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

/**
 * Created by josediaz on 24/11/2016.
 */
@Entity
@Table(name="Rol")
@NamedQuery(name="Rol.findAll", query="SELECT r FROM Rol r")
public class Rol extends AuditingEntity implements Serializable {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private Long idRol;
    private String nombre;
    private String descripcion;
    private Integer rolSistema;
    private String estado;
    
//    public List<Autorizacion> autorizacion;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="IdRol")
    public Long getIdRol() {
        return idRol;
    }

    public void setIdRol(Long idRol) {
        this.idRol = idRol;
    }
    @Column(name="Nombre")
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    @Column(name="Descripcion")
    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
    @Column(name = "RolSistema")
    public Integer getRolSistema() {
        return rolSistema;
    }

    public void setRolSistema(Integer rolSistema) {
        this.rolSistema = rolSistema;
    }

    @Column(name="Estado")
    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String authority() {
        return "ROLE_" + this.nombre;
    }  
	
    
    
}

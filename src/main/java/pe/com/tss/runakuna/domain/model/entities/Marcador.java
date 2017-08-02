package pe.com.tss.runakuna.domain.model.entities;

import pe.com.tss.runakuna.domain.model.base.AuditingEntity;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by josediaz on 27/03/2017.
 */

@Entity
@Table(name = "Marcador")
@NamedQuery(name = "Marcador.findAll", query = "SELECT m FROM Marcador m")
public class Marcador extends AuditingEntity implements Serializable {

    private static final long serialVersionUID = 1L;
    
    private Long idMarcador;

    private String codigo;
    private String nombre;
    private String descripcion;
    private Empresa empresa;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "IdMarcador")
    public Long getIdMarcador() {
        return idMarcador;
    }

    public void setIdMarcador(Long idMarcador) {
        this.idMarcador = idMarcador;
    }
    
    @Column(name = "Codigo")
    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }
    
    @Column(name = "Nombre")
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    
    @Column(name = "Descripcion")
    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
    
    @ManyToOne()
    @JoinColumn(name = "IdEmpresa")
	public Empresa getEmpresa() {
		return empresa;
	}

	public void setEmpresa(Empresa empresa) {
		this.empresa = empresa;
	}

}

package pe.com.tss.runakuna.view.model;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import pe.com.tss.runakuna.json.JsonDateSimpleDeserializer;
import pe.com.tss.runakuna.json.JsonDateSimpleSerializer;

import java.util.Date;

public class MarcacionQuickFilterViewModel extends QuickFilterViewModel {

    

    @JsonSerialize(using=JsonDateSimpleSerializer.class)
    @JsonDeserialize(using=JsonDateSimpleDeserializer.class)
    private Date fechaDesde;

    @JsonSerialize(using=JsonDateSimpleSerializer.class)
    @JsonDeserialize(using=JsonDateSimpleDeserializer.class)
    private Date fechaHasta;

	public Date getFechaDesde() {
		return fechaDesde;
	}

	public void setFechaDesde(Date fechaDesde) {
		this.fechaDesde = fechaDesde;
	}

	public Date getFechaHasta() {
		return fechaHasta;
	}

	public void setFechaHasta(Date fechaHasta) {
		this.fechaHasta = fechaHasta;
	}
    
    
    
}

package ec.gob.ambiente.sib_suia_ora.catalogobiodiversidad.model;

import java.io.Serializable;

import lombok.Getter;
import lombok.Setter;

public class ComparatorHigherLoadTaxa implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Getter
	@Setter
	private Integer rangoTaxonomico;
	
	@Getter
	@Setter
	private Integer parentId;
	
	@Getter
	@Setter
	private String scientificName;
	
	@Getter
	@Setter
	private String autor;
	
	@Getter
	@Setter
	private String anioPublicacion;
	
	@Getter
	@Setter
	private String nombreComun;
	
	@Getter
	@Setter
	private String codigoNomenclatural;
	
	@Getter
	@Setter
	private String observaciones;
	
	@Getter
	@Setter
	private Boolean esTaxonCorrecto;
	
	@Getter
	@Setter
	private String taxonCorrecto;
	
	@Getter
	@Setter
	private Integer idStatusTaxonomico;
	
	@Getter
	@Setter
	private String codigoGui;
	
	
}

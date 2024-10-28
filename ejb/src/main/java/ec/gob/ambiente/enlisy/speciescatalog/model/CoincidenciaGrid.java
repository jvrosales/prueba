package ec.gob.ambiente.enlisy.speciescatalog.model;

import lombok.Getter;
import lombok.Setter;

/**
 * Entidad que contiene los campos a mostrar en el grid de 
 * coincidencias por nombre cientifico
 * @author Elvis
 *
 */
public class CoincidenciaGrid {

	@Getter
	@Setter
	private String nombreCientifico;
	
	@Getter
	@Setter
	private String codigoGui;
	
		
	@Getter
	@Setter
	private String rangoTaxonomico;
	
	@Getter
	@Setter
	private String reino;
	
	@Getter
	@Setter
	private String phylum;
	
	@Getter
	@Setter
	private String clase;

	@Getter
	@Setter
	private String orden;
	
	@Getter
	@Setter
	private String familia;
	
	@Getter
	@Setter
	private String genero;
	
	@Getter
	@Setter
	private String especie;
	
	@Getter
	@Setter
	private Integer porcentaje;

}


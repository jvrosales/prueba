package ec.gob.ambiente.enlisy.speciescatalog.model;

import lombok.Getter;
import lombok.Setter;

/**
 * Entidad que contiene los campos a mostrar en el grid de actualizacion
 * de clasificacion superior
 * @author Elvis
 *
 */
public class HigherClassificationGrid {

	@Getter
	@Setter
	private String nombreCientifico;
	
	@Getter
	@Setter
	private String codigoGui;
	
	@Getter
	@Setter
	private String nombreRango;
	
	@Getter
	@Setter
	private String stausTaxonomico;
	
	@Getter
	@Setter
	private Integer id;
}

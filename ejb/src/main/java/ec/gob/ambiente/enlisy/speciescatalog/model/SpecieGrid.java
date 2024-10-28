package ec.gob.ambiente.enlisy.speciescatalog.model;

import java.io.Serializable;

import lombok.Getter;
import lombok.Setter;

/**
 * Entidad que contiene los campos a mostrar en el grid de actualizacion
 * de clasificacion superior
 * @author Elvis
 *
 */
public class SpecieGrid implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7415108322628167852L;

	@Getter
	@Setter
	private String nombreCientifico;
	
	@Getter
	@Setter
	private String codigoGui;
	
		
	@Getter
	@Setter
	private String statusTaxonomico;
	
	@Getter
	@Setter
	private Integer id;
	
	@Getter
	@Setter
	private String listaRoja;
	
	@Getter
	@Setter
	private String cites;
	
	@Getter
	@Setter
	private String grupos;
}


package ec.gob.ambiente.enlisy.speciescatalog.model;

import java.io.Serializable;

import lombok.Getter;
import lombok.Setter;

public class ValidationResult implements Serializable {

	private static final long serialVersionUID = 1L;
	
	/**
	 * Etiqueta variable en Ecuador
	 */
	@Getter
	@Setter
	private String etiquetaEnEcuador;
	
	/**
	 * Valor variable en Ecuador
	 */
	@Getter
	@Setter
	private Boolean enEcuador;
	
	/**
	 * Etiqueta variable nativa
	 */
	@Getter
	@Setter
	private String etiquetaNativa;
	
	/**
	 * Valor variable nativa
	 */
	@Getter
	@Setter
	private Boolean nativa;
	
	/**
	 * Etiqueta variable endemica
	 */
	@Getter
	@Setter
	private String etiquetaEndemica;
	
	/**
	 * Valor variable endemica
	 */
	@Getter
	@Setter
	private Boolean endemica;
	
	/**
	 * Etiqueta variable exotica
	 */
	@Getter
	@Setter
	private String etiquetaExotica;
	
	/**
	 * Valor variable exotica
	 */
	@Getter
	@Setter
	private Boolean exotica;
	
	/**
	 * Etiqueta variable invasora
	 */
	@Getter
	@Setter
	private String etiquetaInvasora;
	
	/**
	 * Valor variable invasora
	 */
	@Getter
	@Setter
	private Boolean invasora;
	
	/**
	 * Etiqueta variable domestica
	 */
	@Getter
	@Setter
	private String etiquetaDomestica;
	
	/**
	 * Valor variable domestica
	 */
	@Getter
	@Setter
	private Boolean domestica;
	
	/**
	 * Etiqueta variable migratoria
	 */
	@Getter
	@Setter
	private String etiquetaMigratoria;
	
	/**
	 * Valor variable migratoria
	 */
	@Getter
	@Setter
	private Boolean migratoria;
	
	/**
	 * Id
	 */
	@Getter
	@Setter
	private Integer id;
}

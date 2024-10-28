package ec.gob.ambiente.sib_suia_ora.catalogobiodiversidad.model;

import java.io.Serializable;
import java.util.Date;

import lombok.Getter;
import lombok.Setter;

public class HigherLoadTaxa implements Serializable {

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
	
	@Getter
	@Setter
	private Integer idTaxonCorrecto;
	
	@Getter
	@Setter
	private String hiclHigherClassification;
	
	@Getter
	@Setter
	private String hiclTaxonRankName;
	
	@Getter
	@Setter
	private Boolean hiclStatus;
	
	@Getter
	@Setter
	private String hiclUserCreate;
	
	@Getter
	@Setter
	private Date hiclDateCreate;
	
	@Getter
	@Setter
	private Boolean hiclVerifiedRecord;
	
	@Getter
	@Setter
	private String hiclHierarchyCode;
	
	@Getter
	@Setter
	private String hiclParentName;
	
	@Getter
	@Setter
	private String hiclVerifiedFor;
	
	@Getter
	@Setter
	private String hiclSearchCode;
	
	@Getter
	@Setter
	private String higherCodeAnt;
	
	@Getter
	@Setter
	private String higherCodeNuevoConHijos;
	
	@Getter
	@Setter
	private String higherCodeNuevoSinHijos;
	
	@Getter
	@Setter
	private String higherCodeNameAnt;
	
	@Getter
	@Setter
	private String higherCodeNameNuevoConHijos;
	
	@Getter
	@Setter
	private String higherCodeNameNuevoSinHijos;
	
	@Getter
	@Setter
	private Integer idCodigoNomenclatural;
	
	@Getter
	@Setter
	private Integer idFuenteTaxonomica;
	
	@Getter
	@Setter
	private String otraFuenteTaxonomica;
	
	@Getter
	@Setter
	private String urlFuenteTaxonomica;
	
}

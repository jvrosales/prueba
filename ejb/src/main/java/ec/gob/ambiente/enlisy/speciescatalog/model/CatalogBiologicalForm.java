package ec.gob.ambiente.enlisy.speciescatalog.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

/**
 * The persistent class for the catalog_biological_forms database table.
 * 
 */
@Entity
@Table(name="catalog_biological_forms", schema="biodiversity")
public class CatalogBiologicalForm implements Serializable {

	private static final long serialVersionUID = 1L;
	
	/**
	 * Clave primaria
	 */
	@Getter
	@Setter
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "CATALOG_BIOLOGICAL_FORM_GENERATOR")
    @SequenceGenerator(name = "CATALOG_BIOLOGICAL_FORM_GENERATOR", initialValue = 1, sequenceName = "seq_cabf_id", schema = "biodiversity", allocationSize = 1)
   	@Column(name="cabf_id")
	private Integer cabfId;

	
	/**
	 * Nombre de la forma
	 */
	@Getter
	@Setter
	@Column(name="cabf_name")
	private String cabfName;
	
			
	/**
	 * bandera que indica si la forma es del reino animal
	 */
	@Getter
	@Setter
	@Column(name="cabf_animal")
	private Boolean cabfAnimal;
	
	/**
	 * bandera que indica si la forma pertenece al reino plantae
	 */
	@Getter
	@Setter
	@Column(name="cabf_plantae")
	private Boolean cabfPlantae;
	
	/**
	 * bandera que indica si la forma pertenece al reino fungi
	 */
	@Getter
	@Setter
	@Column(name="cabf_fungi")
	private Boolean cabfFungi;
	
	/**
	 * bandera que indica si la forma pertenece al reino eubacteria
	 */
	@Getter
	@Setter
	@Column(name="cabf_eubacteria")
	private Boolean cabfEubacteria;

	/**
	 * bandera que indica si la forma pertenece al reino arqueobacteria
	 */
	@Getter
	@Setter
	@Column(name="cabf_archeobacteria")
	private Boolean cabfArcheobacteria;
	
	/**
	 * bandera que indica si la forma pertenece al reino protista
	 */
	@Getter
	@Setter
	@Column(name="cabf_protista")
	private Boolean cabfProtista;
	
	
	/**
	 * estado del registro
	 */
	@Getter
	@Setter
	@Column(name="cabf_status")
	private Boolean cabfStatus;
	
	/**
	 * usuario que crea el registro
	 */
	@Getter
	@Setter
	@Column(name="cabf_user_create")
	private String cabfUserCreate;
	
	/**
	 * fecha que se crea el registro
	 */
	@Getter
	@Setter
	@Column(name="cabf_date_create")
	private Date cabfDateCreate;
	
	/**
	 * fecha que se actualiza el registro
	 */
	@Getter
	@Setter
	@Column(name="cabf_date_update")
	private String cabfDateUpdate;
	
	/**
	 * usuario de actualizacion del registro
	 */
	@Getter
	@Setter
	@Column(name="cabf_user_update")
	private Date cabfUserUpdate;
	
	/**
	 * bandera que indica que el estado pertenece al reino chromista
	 */
	@Getter
	@Setter
	@Column(name="cabf_chromista")
	private Boolean cabfChromista;
	
	/**
	 * bandera que indica que el estado pertenece al reino viruses
	 */
	@Getter
	@Setter
	@Column(name="cabf_viruses")
	private Boolean cabfViruses;
	
}

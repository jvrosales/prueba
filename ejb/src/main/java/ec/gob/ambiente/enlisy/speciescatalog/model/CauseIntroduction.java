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
 * The persistent class for the cause_introduction database table.
 * 
 */
@Entity
@Table(name="cause_introduction", schema="biodiversity")
public class CauseIntroduction implements Serializable {

	private static final long serialVersionUID = 1L;
	
	/**
	 * Clave primaria
	 */
	@Getter
	@Setter
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "CAUSE_INTRODUCTION_GENERATOR")
    @SequenceGenerator(name = "CAUSE_INTRODUCTION_GENERATOR", initialValue = 1, sequenceName = "seq_cain_id", schema = "biodiversity", allocationSize = 1)
   	@Column(name="cain_id")
	private Integer cainId;

	
	/**
	 * Nombre de la causa
	 */
	@Getter
	@Setter
	@Column(name="cain_name")
	private String cainName;
	
			
	/**
	 * bandera que indica si la causa es del reino animal
	 */
	@Getter
	@Setter
	@Column(name="cain_animal")
	private Boolean cainAnimal;
	
	/**
	 * bandera que indica si la causa pertenece al reino plantae
	 */
	@Getter
	@Setter
	@Column(name="cain_plantae")
	private Boolean cainPlantae;
	
	/**
	 * bandera que indica si la causa pertenece al reino fungi
	 */
	@Getter
	@Setter
	@Column(name="cain_fungi")
	private Boolean cainFungi;
	
	/**
	 * bandera que indica si la causa pertenece al reino eubacteria
	 */
	@Getter
	@Setter
	@Column(name="cain_eubacteria")
	private Boolean cainEubacteria;

	/**
	 * bandera que indica si la causa pertenece al reino arqueobacteria
	 */
	@Getter
	@Setter
	@Column(name="cain_archeobacteria")
	private Boolean cainArcheobacteria;
	
	/**
	 * bandera que indica si la causa pertenece al reino protista
	 */
	@Getter
	@Setter
	@Column(name="cain_protista")
	private Boolean cainProtista;
	
	
	/**
	 * estado del registro
	 */
	@Getter
	@Setter
	@Column(name="cain_status")
	private Boolean cainStatus;
	
	/**
	 * usuario que crea el registro
	 */
	@Getter
	@Setter
	@Column(name="cain_user_create")
	private String cainUserCreate;
	
	/**
	 * fecha que se crea el registro
	 */
	@Getter
	@Setter
	@Column(name="cain_date_create")
	private Date cainDateCreate;
	
	/**
	 * fecha que se actualiza el registro
	 */
	@Getter
	@Setter
	@Column(name="cain_date_update")
	private String cainDateUpdate;
	
	/**
	 * usuario de actualizacion del registro
	 */
	@Getter
	@Setter
	@Column(name="cain_user_update")
	private Date cainUserUpdate;
		
	/**
	 * bandera que indica que el estado pertenece al reino chromista
	 */
	@Getter
	@Setter
	@Column(name="cain_chromista")
	private Boolean cainChromista;
	
	/**
	 * bandera que indica que el estado pertenece al reino viruses
	 */
	@Getter
	@Setter
	@Column(name="cain_viruses")
	private Boolean cainViruses;
}

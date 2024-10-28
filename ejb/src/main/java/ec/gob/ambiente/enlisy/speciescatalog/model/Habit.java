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
 * The persistent class for the artificial_groups database table.
 * 
 */
@Entity
@Table(name="habits", schema="biodiversity")
public class Habit implements Serializable {

	private static final long serialVersionUID = 1L;
	
	/**
	 * Clave primaria
	 */
	@Getter
	@Setter
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "HABITS_GENERATOR")
    @SequenceGenerator(name = "HABITS_GENERATOR", initialValue = 1, sequenceName = "seq_habi_id", schema = "biodiversity", allocationSize = 1)
   	@Column(name="habi_id")
	private Integer habiId;

	
	/**
	 * Nombre del habito
	 */
	@Getter
	@Setter
	@Column(name="habi_name")
	private String habiName;
	
	/**
	 * Codigo del habito
	 */
	@Getter
	@Setter
	@Column(name="habi_code ")
	private String habiCode ;
	
		
	/**
	 * bandera que indica si el habito es del reino animal
	 */
	@Getter
	@Setter
	@Column(name="habi_animal")
	private Boolean habiAnimal;
	
	/**
	 * bandera que indica si el habito pertenece al reino plantae
	 */
	@Getter
	@Setter
	@Column(name="habi_plantae")
	private Boolean habiPlantae;
	
	/**
	 * bandera que indica si el habito pertenece al reino fungi
	 */
	@Getter
	@Setter
	@Column(name="habi_fungi")
	private Boolean habiFungi;
	
	/**
	 * bandera que indica que el habito pertenece al reino eubacteria
	 */
	@Getter
	@Setter
	@Column(name="habi_eubacteria")
	private Boolean habiEubacteria;

	/**
	 * bandera que indica que el grupo pertenece al reino arqueobacteria
	 */
	@Getter
	@Setter
	@Column(name="habi_archeobacteria")
	private Boolean habiArcheobacteria;
	
	/**
	 * bandera que indica que el grupo pertenece al reino protista
	 */
	@Getter
	@Setter
	@Column(name="habi_protista")
	private Boolean habiProtista;
	
	
	/**
	 * estado del registro
	 */
	@Getter
	@Setter
	@Column(name="habi_status")
	private Boolean habiStatus;
	
	/**
	 * usuario que crea el registro
	 */
	@Getter
	@Setter
	@Column(name="habi_user_create")
	private String habiUserCreate;
	
	/**
	 * fecha que se crea el registro
	 */
	@Getter
	@Setter
	@Column(name="habi_date_create")
	private Date habiDateCreate;
	
	/**
	 * fecha que se actualiza el registro
	 */
	@Getter
	@Setter
	@Column(name="habi_date_update")
	private String habiDateUpdate;
	
	/**
	 * usuario de actualizacion del registro
	 */
	@Getter
	@Setter
	@Column(name="habi_user_update")
	private Date habiUserUpdate;
	
	/**
	 * bandera que indica que el estado pertenece al reino chromista
	 */
	@Getter
	@Setter
	@Column(name="habi_chromista")
	private Boolean habiChromista;
	
	/**
	 * bandera que indica que el estado pertenece al reino viruses
	 */
	@Getter
	@Setter
	@Column(name="habi_viruses")
	private Boolean habiViruses;
		
	
}

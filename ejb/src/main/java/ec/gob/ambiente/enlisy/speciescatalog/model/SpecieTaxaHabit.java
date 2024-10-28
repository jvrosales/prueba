package ec.gob.ambiente.enlisy.speciescatalog.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Transient;

import lombok.Getter;
import lombok.Setter;

/**
 * The persistent class for the species_taxa_artificial_groups database table.
 * 
 */
@Entity
@Table(name="species_taxa_habits", schema="biodiversity")
public class SpecieTaxaHabit implements Serializable {

	private static final long serialVersionUID = 1L;
	
	/**
	 * Clave primaria
	 */
	@Getter
	@Setter
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SPECIE_TAXA_HABIT_GENERATOR")
    @SequenceGenerator(name = "SPECIE_TAXA_HABIT_GENERATOR", initialValue = 1, sequenceName = "seq_stha_id", schema = "biodiversity", allocationSize = 1)
    @Column(name="stha_id")
	private Integer sthaId;

	/**
	 * Id de la especie
	 */
	@Getter
	@Setter
	@ManyToOne
	@JoinColumn(name="spta_id")
	private SpecieTaxa specieTaxa;
	
	/**
	 * Id del habito
	 */
	@Getter
	@Setter
	@ManyToOne
	@JoinColumn(name="habi_id")
	private Habit habit;
	
	/**
	 * Id de la fuente de informacion
	 */
	@Getter
	@Setter
	@ManyToOne
	@JoinColumn(name="inso_id")
	private InformationSource informationSource;
	
			
	/**
	 * Usuario que crea el registro
	 */
	@Getter
	@Setter
	@Column(name="stha_user_create")
	private String sthaUserCreate;
	
	/**
	 * Fecha de creacion del registro
	 */
	@Getter
	@Setter
	@Column(name="stha_date_create")
	private Date sthaDateCreate;
	
	/**
	 * Usuario que actualiza el registro
	 */
	@Getter
	@Setter
	@Column(name="stha_user_update")
	private String sthaUserUpdate;
	
	/**
	 * Fecha de actualizacion del registro
	 */
	@Getter
	@Setter
	@Column(name="stha_date_update")
	private Date sthaDateUpdate;
	
	/**
	 * Bandera que indica si el registro esta activo
	 */
	@Getter
	@Setter
	@Column(name="stha_status")
	private Boolean sthaStatus;
	
	
	
	@Getter
	@Setter
	@Transient
	String codigoHabito;
	
	@Getter
	@Setter
	@Transient
	String codigoFuenteInformacion;
}

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

@Entity
@Table(name="affected_species", schema="biodiversity")
public class AffectedSpecie implements Serializable {

	private static final long serialVersionUID = 1L;
	
	
	/**
	 * Clave primaria
	 */
	@Getter
	@Setter
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "AFFECTED_SPECIE_GENERATOR")
    @SequenceGenerator(name = "AFFECTED_SPECIE_GENERATOR", initialValue = 1, sequenceName = "seq_afsp_id", schema = "biodiversity", allocationSize = 1)
   	@Column(name="afsp_id")
	private Integer afspId;
	
	
	
	/**
	 * Usuario que crea el registro
	 */
	@Getter
	@Setter
	@Column(name="afsp_user_create")
	private String afspUserCreate;
	
	/**
	 * Fecha de creacion del registro
	 */
	@Getter
	@Setter
	@Column(name="afsp_date_create")
	private Date afspDateCreate;
	
	/**
	 * Usuario que actualiza el registro
	 */
	@Getter
	@Setter
	@Column(name="afsp_user_update")
	private String afspUserUpdate;
	
	/**
	 * Fecha de actualizacion del registro
	 */
	@Getter
	@Setter
	@Column(name="afsp_date_update")
	private Date afspDateUpdate;
	
	/**
	 * Bandera que indica si el registro esta activo
	 */
	@Getter
	@Setter
	@Column(name="afsp_status")
	private Boolean afspStatus;
	
		
	/**
	 * id sumario especie exotica y/o invasora
	 */
	@Getter
	@Setter
	@ManyToOne
	@JoinColumn(name="seas_id")
	private SummaryExoticAlienSpecie summaryExotic;
	
	/**
	 * id de la especie exotica
	 */
	@Getter
	@Setter
	@ManyToOne
	@JoinColumn(name="spta_id")
	private SpecieTaxa specie;
	
	/**
	 * id de la especie afectada
	 */
	@Getter
	@Setter
	@ManyToOne
	@JoinColumn(name="spta_id_affected")
	private SpecieTaxa specieAfected;
	
		
	
	@Getter
	@Setter
	@Transient
	String codigoEspecieAfectada;
	
	
}


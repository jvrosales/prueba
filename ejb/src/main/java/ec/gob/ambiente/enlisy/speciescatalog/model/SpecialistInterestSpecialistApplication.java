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

import lombok.Getter;
import lombok.Setter;

/**
 * The persistent class for the specialist_interests_specialist_applications database table.
 * 
 */
@Entity
@Table(name="specialist_interests_specialist_applications", schema="biodiversity")
public class SpecialistInterestSpecialistApplication implements Serializable {

	private static final long serialVersionUID = 1L;
	
	/**
	 * Clave primaria
	 */
	@Getter
	@Setter
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SPECIALIST_INTEREST_SPECI_APPL_GENERATOR")
    @SequenceGenerator(name = "SPECIALIST_INTEREST_SPECI_APPL_GENERATOR", initialValue = 1, sequenceName = "seq_sisa_id", schema = "biodiversity", allocationSize = 1)
   	@Column(name="sisa_id")
	private Integer sisaId;

	
	/** 
	 * Id del especialista
	 */
	@Getter
	@Setter
	@ManyToOne
	@JoinColumn(name="spap_id")
	private SpecialistApplication spapSpecialist;
	
	/** 
	 * Id del interes
	 */
	@Getter
	@Setter
	@ManyToOne
	@JoinColumn(name="spin_id")
	private SpecialistInterestCatalog specialistInterest;
	
	
	
	/**
	 * indica si el interes ha sido seleccionado
	 */
	@Getter
	@Setter
	@Column(name="sisa_selected")
	private Boolean sisaSelected;
	
	/**
	 * estado del registro
	 */
	@Getter
	@Setter
	@Column(name="sisa_status")
	private Boolean sisaStatus;
	
	/**
	 * usuario que crea el registro
	 */
	@Getter
	@Setter
	@Column(name="sisa_user_create")
	private String sisaUserCreate;
	
	/**
	 * fecha que se crea el registro
	 */
	@Getter
	@Setter
	@Column(name="sisa_date_create")
	private Date sisaDateCreate;
	
	/**
	 * fecha que se crea el registro
	 */
	@Getter
	@Setter
	@Column(name="sisa_user_update")
	private String sisaUserUpdate;
	
	/**
	 * fecha de actualizacion del registro
	 */
	@Getter
	@Setter
	@Column(name="sisa_date_update")
	private Date sisaDateUpdate;
		
	
}

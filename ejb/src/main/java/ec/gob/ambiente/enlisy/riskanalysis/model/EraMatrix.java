package ec.gob.ambiente.enlisy.riskanalysis.model;

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

import ec.gob.ambiente.enlisy.wildlifeevent.model.BiodiversityGeneralCatalog;
import lombok.Getter;
import lombok.Setter;

/**
 * The persistent class for the risk_analysis_applications_documents database table.
 * 
 */
@Entity
@Table(name="era_matrix", schema="biodiversity")
public class EraMatrix implements Serializable  {

	private static final long serialVersionUID = 1L;
	
	/**
	 * Clave primaria
	 */
	@Getter
	@Setter
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "ERA_MATRIX_GENERATOR")
    @SequenceGenerator(name = "ERA_MATRIX_GENERATOR", initialValue = 1, sequenceName = "seq_erma_id", schema = "biodiversity", allocationSize = 1)
    @Column(name="erma_id")
	private Integer ermaId;
	
	/** 
	 * Id del item
	 */
	@Getter
	@Setter
	@ManyToOne
	@JoinColumn(name="erma_item_id")
	private BiodiversityGeneralCatalog ermaItemId;
	
	/** 
	 * Id del subitem
	 */
	@Getter
	@Setter
	@ManyToOne
	@JoinColumn(name="erma_subitem_id")
	private BiodiversityGeneralCatalog ermaSubitemId;
	
	
	/**
	 *Valor del item o subitem
	 */
	@Getter
	@Setter
	@Column(name="erma_value")
	private Double ermaValue;
	
	/**
	 *Nombre del item
	 */
	@Getter
	@Setter
	@Column(name="erma_name")
	private String ermaName;
	
	
		
	/**
	 * Usuario que crea el registro
	 */
	@Getter
	@Setter
	@Column(name="erma_user_create")
	private String ermaUserCreate;
	
	/**
	 * Fecha de creacion del registro
	 */
	@Getter
	@Setter
	@Column(name="erma_date_create")
	private Date ermaDateCreate;
	
	/**
	 * Usuario que actualiza el registro
	 */
	@Getter
	@Setter
	@Column(name="erma_user_update")
	private String ermaUserUpdate;
	
	/**
	 * Fecha de actualizacion del registro
	 */
	@Getter
	@Setter
	@Column(name="erma_date_update")
	private Date ermaDateUpdate;
	
	/**
	 * Bandera que indica si el registro esta activo
	 */
	@Getter
	@Setter
	@Column(name="erma_status")
	private Boolean ermaStatus;
	
	
	

	
}

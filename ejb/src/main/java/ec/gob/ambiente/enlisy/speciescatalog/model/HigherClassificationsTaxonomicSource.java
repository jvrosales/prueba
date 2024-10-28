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
 * The persistent class for the cites database table.
 * 
 */
@Entity
@Table(name="higher_classifications_taxonomic_sources", schema="biodiversity")
public class HigherClassificationsTaxonomicSource implements Serializable {

	private static final long serialVersionUID = 1L;
	
	/**
	 * Clave primaria
	 */
	@Getter
	@Setter
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "HIGHER_CLASS_TAX_SOU_GENERATOR")
    @SequenceGenerator(name = "HIGHER_CLASS_TAX_SOU_GENERATOR", initialValue = 1, sequenceName = "seq_hita_id", schema = "biodiversity", allocationSize = 1)
   	@Column(name="hita_id")
	private Integer hitaId;

	/**
	 * Id de la clasificacion superior
	 */
	@Getter
	@Setter
	@ManyToOne
	@JoinColumn(name="hicl_id")
	private HigherClassification higherClassification;
	
	/**
	 * Id de la fuente taxonomica
	 */
	@Getter
	@Setter
	@ManyToOne
	@JoinColumn(name="taso_id")
	private TaxonomicSource taxonomicSource;
	
		
	/**
	 * Usuario que crea el registro
	 */
	@Getter
	@Setter
	@Column(name="hita_user_create")
	private String hitaUserCreate;
	
	/**
	 * Fecha de creacion del registro
	 */
	@Getter
	@Setter
	@Column(name="hita_date_create")
	private Date hitaDateCreate;
	
	/**
	 * Usuario que actualiza el registro
	 */
	@Getter
	@Setter
	@Column(name="hita_user_update")
	private String hitaUserUpdate;
	
	/**
	 * Fecha de actualizacion del registro
	 */
	@Getter
	@Setter
	@Column(name="hita_date_update")
	private Date hitaDateUpdate;
	
	/**
	 * Bandera que indica si el registro esta activo
	 */
	@Getter
	@Setter
	@Column(name="hita_status")
	private Boolean hitaStatus;
}

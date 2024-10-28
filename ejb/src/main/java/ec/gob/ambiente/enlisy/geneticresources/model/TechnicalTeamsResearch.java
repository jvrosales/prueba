package ec.gob.ambiente.enlisy.geneticresources.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Transient;

import lombok.Getter;
import lombok.Setter;
import ec.gob.ambiente.enlisy.model.People;


/**
 * The persistent class for the technical_researchers database table.
 * 
 */
@Entity
@Table(name="technical_teams_research", schema="biodiversity")
@NamedQuery(name="TechnicalTeamsResearch.findAll", query="SELECT o FROM TechnicalTeamsResearch o where o.tetrStatus = true")
public class TechnicalTeamsResearch implements Serializable,Cloneable{
	private static final long serialVersionUID = 1L;
	
	public TechnicalTeamsResearch clone(ProposedCollection proposedCollection) throws CloneNotSupportedException {		
		TechnicalTeamsResearch clone= (TechnicalTeamsResearch)super.clone();
		clone.setTetrId(null);
		clone.setTetrUserUpdate(null);
		clone.setTetrDateUpdate(null);
		clone.setProposedCollection(proposedCollection);
		return clone;
	}
	
	public TechnicalTeamsResearch() {		
	}
	
	public TechnicalTeamsResearch(ProposedCollection proposedCollection) {
		this.proposedCollection=proposedCollection;
		this.people=new People();
	}

	@Getter
	@Setter
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="tetr_id")
	private Integer tetrId;

	@Getter
	@Setter
	@Column(name="tetr_senescyt_number")
	private String tetrSenescytNumber;	
	
	@Getter
	@Setter
	@Column(name="tetr_experience")
	private String tetrExperience;
	
	
	@Getter
	@Setter
	@Column(name="tetr_affiliation")
	private String tetrAffiliation;
	
	@Getter
	@Setter
	@ManyToOne
	@JoinColumn(name="grca_id_function")
	private GeneticResourcesCatalog geneticResourcesCatalog;
	
	@Getter
	@Setter
	@ManyToOne
	@JoinColumn(name="grca_id_autoidentifacation")
	private GeneticResourcesCatalog geneticResourcesCatalogAutoidentificacion;
	
	@Getter
	@Setter
	@Column(name="tetr_status")
	private Boolean tetrStatus;
	
	@Getter
	@Setter
	@Column(name="tetr_user_create")
	private String tetrUserCreate;
	
	@Getter
	@Setter
	@Column(name="tetr_date_create")
	private Date tetrDateCreate;
	
	@Getter
	@Setter
	@Column(name="tetr_user_update")
	private String tetrUserUpdate;
	
	@Getter
	@Setter
	@Column(name="tetr_date_update")
	private Date tetrDateUpdate;
	
	@Getter
	@Setter
	@ManyToOne
	@JoinColumn(name="prco_id")
	private ProposedCollection proposedCollection;
	
	@Getter
	@Setter
	@ManyToOne
	@JoinColumn(name="peop_id")
	private People people;
	
	@Getter
	@Setter
	@Column(name="tetr_biological_groups")
	private String tetrBiologicalGroups;
	
	@Getter
	@Setter
	@Column(name="tetr_study_area")
	private String tetrStudyArea;
	
	@Getter
	@Setter
	@ManyToOne
	@JoinColumn(name="grca_id")
	private GeneticResourcesCatalog geneticResourcesCatalogNivelAcademico;

	@Getter
	@Setter
	@Transient
	private ProposedDocument documentoCartaIntencion;
	
	@Getter
	@Setter
	@Transient
	private ProposedDocument documentoCopiaPasaporte;
	
	@Getter
	@Setter
	@Transient
	private ProposedDocument documentoCertificacionSenecyt;
	
	@Getter
	@Setter
	@Column(name="doc_id_letter")
	private Integer docIdLetter;
	
	@Getter
	@Setter
	@Column(name="doc_id_passport")
	private Integer docIdPassport;
	
	@Getter
	@Setter
	@Column(name="doc_id_accreditation")
	private Integer docIdAccreditation;
	
	@Getter
	@Setter
	@Column(name="tetr_original_record")
	private Boolean tetrOriginalRecord;
	
	@Getter
	@Setter
	@Column(name="tetr_elimination_justification")
	private String tetrEliminationJustification;
}

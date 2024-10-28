package ec.gob.ambiente.enlisy.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

import org.hibernate.annotations.Filter;

import ec.gob.ambiente.suia.domain.base.EntidadBase;
import lombok.Getter;
import lombok.Setter;


/**
 * The persistent class for the application database table.
 *
 */
@Entity
@Table(name = "patent_form_comments", schema = "biodiversity_mcm")
@NamedQuery(name = "PatentFormComments.findAll", query = "SELECT o FROM PatentFormComments o")
@AttributeOverrides({
	@AttributeOverride(name = "fechaCreacion", column = @Column(name = "pafo_creation_date")),
    @AttributeOverride(name = "fechaModificacion", column = @Column(name = "pafo_date_update")),
    @AttributeOverride(name = "usuarioCreacion", column = @Column(name = "pafo_creator_user")),
    @AttributeOverride(name = "usuarioModificacion", column = @Column(name = "pafo_user_update")),
    @AttributeOverride(name = "observacionesBD", column = @Column(name = "pafo_observation_bd")) })
@Filter(name = EntidadBase.FILTER_ACTIVE, condition = "pafo_status = 'TRUE'")

public class PatentFormComments implements Serializable{
    private static final long serialVersionUID = 1L;
    
    @Getter
    @Setter
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "pafo_id")
    private Integer id;
    
    @Getter
    @Setter
    @Column(name = "pafo_field")
    private String field;
    
    @Getter
    @Setter
    @Column(name = "pafo_description")
    private String description;
    
    @Getter
    @Setter
    @Column(name = "pafo_class_name")
    private String className;
    
    @Getter
    @Setter
    @Column(name = "pafo_id_class")
    private Integer idClass;
    
    @Getter
    @Setter
    @Column(name = "pafo_section_form")
    private String sectionForm;
    
    @Getter
    @Setter
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
    
    @Getter
    @Setter
    @Column(name = "pafo_date")
    private Date date;
    
    @Getter
    @Setter
    @Column(name = "pafo_observation_corrected")
    private Boolean observationCorrected;
    
    @Getter
    @Setter
    @Column(name = "taskid")
    private Integer taskId;
    
    @Getter
    @Setter
    @Column(name = "pafo_answer")
    private String answer;
    
    @Getter
    @Setter
    @Column(name = "pafo_observations")
    private String observations;
    
    @Getter
    @Setter
    @Column(name = "pafo_status")
    private Boolean status;
    
    @Getter
    @Setter
    @Column(name = "pafo_observation_type")
    private Integer pafoObservationType;
    
}
package ec.gob.ambiente.sib_suia_ora.indexcollectiongbif.model;

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

import ec.gob.ambiente.enlisy.model.PatentRequest;
import lombok.Getter;
import lombok.Setter;

/**
 * The persistent class for the dataset_ipt database table.
 *
 */
@Entity
@Table(name="dataset_ipt", schema="biodiversity_mcm")
@NamedQuery(name="DatasetIpt.findAll", query="SELECT o FROM DatasetIpt o")
public class DatasetIpt implements Serializable {
    
    private static final long serialVersionUID = 1L;
    
    /**
     * Clave primaria
     */
    @Getter
    @Setter
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="dsipt_id")
    private Integer dsiptId;
    
    /**
     * Id de la patente
     */
    @Getter
    @Setter
    @ManyToOne
    @JoinColumn(name="pare_id")
    private PatentRequest patentRequest;
    
    /**
     * Identificador digital del dataset en el IPT
     * * Toma el valor del catalogo : results.#item.doi
     */
    @Getter
    @Setter
    @Column(name="dsipt_doi")
    private String dsiptDoi;
    
    /**
     * Enlace a la página del dataset del IPT
     * Toma el valor del catalogo : results.#iitem.identifiers.type_url.identifier
     */
    @Getter
    @Setter
    @Column(name="dsipt_url")
    private String dsiptUrl;
    
    /**
     * Identificador unico del dataset en el IPT
     * Toma el valor del catalogo : results.#item.key
     */
    @Getter
    @Setter
    @Column(name="dsipt_key")
    private String dsiptKey;
    
    /**
     * Version actual del dataset en el IPT
     * Toma el valor del catalogo : results.#item.version
     */
    @Getter
    @Setter
    @Column(name="dsipt_version")
    private String dsiptVersion;
    
    /**
     * Para colocar el titulo dado al dataset
     * Toma el valor del catalogo : results.#item.title
     */
    @Getter
    @Setter
    @Column(name="dsipt_title")
    private String dsiptTitle;
    
    /**
     * Fecha de la ultima versión modificada
     * Toma el valor del catalogo : results.#item.modified
     */
    @Getter
    @Setter
    @Column(name = "dsipt_modified")
    private Date dsiptModified;
    
    /**
     * Bandera que indica si el proceso de indexación del DOI esta activo
     */
    @Getter
    @Setter
    @Column(name = "dsipt_execution_status")
    private Boolean dsiptExecutionStatus;
    
    /**
     * Usuario que crea el registro
     */
    @Getter
    @Setter
    @Column(name = "dsipt_user_create")
    private String dsiptUserCreate;

    /**
     * Fecha de creacion del registro
     */
    @Getter
    @Setter
    @Column(name = "dsipt_date_create")
    private Date dsiptDateCreate;

    /**
     * Usuario que actualiza el registro
     */
    @Getter
    @Setter
    @Column(name = "dsipt_user_update")
    private String dsiptUserUpdate;

    /**
     * Fecha de actualizacion del registro
     */
    @Getter
    @Setter
    @Column(name = "dsipt_date_update")
    private Date dsiptDateUpdate;

    /**
     * Bandera que indica si el registro esta activo
     */
    @Getter
    @Setter
    @Column(name = "dsipt_status")
    private Boolean dsiptStatus;
}
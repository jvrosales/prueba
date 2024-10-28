package ec.gob.ambiente.enlisy.geneticresources.model;

import java.io.Serializable;

import javax.persistence.*;

import java.sql.Timestamp;

import lombok.Getter;
import lombok.Setter;


/**
 * The persistent class for the process_catalog database table.
 * 
 */
@Entity
@Table(name="process_catalog", schema="biodiversity")
@NamedQuery(name="ProcessCatalog.findAll", query="SELECT p FROM ProcessCatalog p")
public class ProcessCatalog implements Serializable {
	private static final long serialVersionUID = 1L;

	@Getter
	@Setter
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="prca_id")
	private Integer prcaId;

	@Getter
	@Setter
	@Column(name="prca_date_create")
	private Timestamp prcaDateCreate;

	@Getter
	@Setter
	@Column(name="prca_date_update")
	private Timestamp prcaDateUpdate;

	@Getter
	@Setter
	@Column(name="prca_status")
	private Boolean prcaStatus;

	@Getter
	@Setter
	@Column(name="prca_user_create")
	private String prcaUserCreate;

	@Getter
	@Setter
	@Column(name="prca_user_update")
	private String prcaUserUpdate;

	@Getter
	@Setter
	@Column(name="prca_value_bpm")
	private String prcaValueBpm;

	@Getter
	@Setter
	@Column(name="prca_value_change")
	private String prcaValueChange;

	public ProcessCatalog() {
	}

}
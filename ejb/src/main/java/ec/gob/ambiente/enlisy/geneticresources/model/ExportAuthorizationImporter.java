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

import ec.gob.ambiente.enlisy.model.Nationality;
import lombok.Getter;
import lombok.Setter;

/**
 * The persistent class for the export_authorization database table.
 * 
 */
@Entity
@Table(name = "export_authorization_importer", schema = "biodiversity")
@NamedQuery(name = "ExportAuthorizationImporter.findAll", query = "SELECT o FROM ExportAuthorizationImporter o where o.exaiStatus = true")
public class ExportAuthorizationImporter implements Serializable, Cloneable {
	private static final long serialVersionUID = 1L;

	@Override
	public ExportAuthorizationImporter clone() throws CloneNotSupportedException {

		ExportAuthorizationImporter clone = (ExportAuthorizationImporter) super.clone();
		clone.setExaiId(null);
		clone.setExaiUserUpdate(null);
		clone.setExaiDateUpdate(null);
		return clone;
	}
	
	@Getter
	@Setter
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "exai_id")
	private Integer exaiId;
	
	@Getter
	@Setter	
	@Column(name="exai_identification")
	private String exaiIdentification;
	
	@Getter
	@Setter	
	@Column(name="exai_name")
	private String exaiName;
	
	@Getter
	@Setter	
	@Column(name="exai_phone")
	private String exaiPhone;
	
	@Getter
	@Setter	
	@Column(name="exai_email")
	private String exaiEmail;
	
	@Getter
	@Setter
	@Column(name = "exai_name_institution")
	private String exaiNameInstitution;

	@Getter
	@Setter
	@Column(name = "exai_address_institution")
	private String exaiAddressInstitution;
	
	@Getter
	@Setter
	@Column(name = "exai_is_historical")
	private Boolean exaiIsHistorical;
	
	@Getter
	@Setter
	@Column(name="exai_status")
	private Boolean exaiStatus;
	
	@Getter
	@Setter
	@Column(name="exai_user_create")
	private String exaiUserCreate;
	
	@Getter
	@Setter
	@Column(name="exai_date_create")
	private Date exaiDateCreate;
	
	@Getter
	@Setter
	@Column(name="exai_user_update")
	private String exaiUserUpdate;
	
	@Getter
	@Setter
	@Column(name="exai_date_update")
	private Date exaiDateUpdate;
	
	@Getter
	@Setter
	@ManyToOne
	@JoinColumn(name="nati_id")
	private Nationality nationality;
	
	@Getter
	@Setter
	@ManyToOne
	@JoinColumn(name="exau_id")
	private ExportAuthorization exportAuthorization;
}

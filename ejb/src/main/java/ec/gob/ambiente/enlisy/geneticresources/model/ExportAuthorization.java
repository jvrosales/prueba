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

import ec.gob.ambiente.enlisy.model.GeographicalLocation;
import lombok.Getter;
import lombok.Setter;

/**
 * The persistent class for the export_authorization database table.
 * 
 */
@Entity
@Table(name = "export_authorization", schema = "biodiversity")
@NamedQuery(name = "ExportAuthorization.findAll", query = "SELECT o FROM ExportAuthorization o where o.exauStatus = true")
public class ExportAuthorization implements Serializable, Cloneable {
	private static final long serialVersionUID = 1L;

	@Override
	public ExportAuthorization clone() throws CloneNotSupportedException {

		ExportAuthorization clone = (ExportAuthorization) super.clone();
		clone.setExauId(null);
		clone.setExauUserUpdate(null);
		clone.setExauDateUpdate(null);
		return clone;
	}

	@Getter
	@Setter
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "exau_id")
	private Integer exauId;
	
	@Getter
	@Setter
	@Column(name = "exau_application_code")
	private String exauApplicationCode;

	@Getter
	@Setter
	@Column(name = "exau_authorization_code")
	private String exauAuthorizationCode;

	@Getter
	@Setter
	@Column(name = "exau_date_start")
	private Date exauDateStart;

	@Getter
	@Setter
	@Column(name = "exau_date_end")
	private Date exauDateEnd;

	@Getter
	@Setter
	@Column(name = "exau_status_authorization")
	private String exauStatusAuthorization;
	
	@Getter
	@Setter
	@Column(name = "exau_destination_place")
	private String exauDestinationPlace;

	@Getter
	@Setter
	@Column(name = "exau_use_biological_material")
	private String exauUseBiologicalMaterial;

	@Getter
	@Setter
	@Column(name = "exau_carrier_identification")
	private String exauCarrierIdentification;
	
	@Getter
	@Setter
	@Column(name = "exau_carrier_name")
	private String exauCarrierName;
	
	@Getter
	@Setter
	@Column(name = "exau_carrier_phone")
	private String exauCarrierPhone;
	
	@Getter
	@Setter
	@Column(name = "exau_carrier_email")
	private String exauCarrierEmail;
	
	@Getter
	@Setter
	@Column(name = "exau_transport_company")
	private String exauTransportCompany;

	@Getter
	@Setter
	@Column(name = "exau_transport_number")
	private String exauTransportNumber;
	
	@Getter
	@Setter
	@Column(name = "exau_port_shipment")
	private String exauPortShipment;
	
	@Getter
	@Setter
	@Column(name = "exau_shipping_way")
	private String exaShippingWay;

	@Getter
	@Setter
	@Column(name = "exau_date_shipment")
	private Date exauDateShipment;
	
	@Getter
	@Setter
	@Column(name = "exau_is_historical")
	private Boolean exauIsHistorical;

	@Getter
	@Setter
	@Column(name = "exau_status")
	private Boolean exauStatus;

	@Getter
	@Setter
	@Column(name = "exau_user_create")
	private String exauUserCreate;

	@Getter
	@Setter
	@Column(name = "exau_date_create")
	private Date exauDateCreate;

	@Getter
	@Setter
	@Column(name = "exau_user_update")
	private String exauUserUpdate;

	@Getter
	@Setter
	@Column(name = "exau_date_update")
	private Date exauDateUpdate;

	@Getter
	@Setter
	@ManyToOne
	@JoinColumn(name = "prco_id")
	private ProposedCollection proposedCollection;

	@Getter
	@Setter
	@ManyToOne
	@JoinColumn(name = "grca_id_means_transport")
	private GeneticResourcesCatalog meansTransport;
	
	@Getter
	@Setter
	@ManyToOne
	@JoinColumn(name = "grca_id_fate_biological_material")
	private GeneticResourcesCatalog fateBiologicalMaterial;

	@Getter
	@Setter
	@Column(name = "exau_gelo_origin")
	private String exauGeloOrigin;
	
	@Getter
	@Setter
	@ManyToOne
	@JoinColumn(name = "exau_destination_country_id")
	private GeographicalLocation destinationCountry;
	
}

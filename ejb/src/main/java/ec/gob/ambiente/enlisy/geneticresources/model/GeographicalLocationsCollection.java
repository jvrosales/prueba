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
import ec.gob.ambiente.enlisy.model.GeographicalLocation;


/**
 * The persistent class for the technical_researchers database table.
 * 
 */
@Entity
@Table(name="geographical_locations_collection", schema="biodiversity")
@NamedQuery(name="GeographicalLocationsCollection.findAll", query="SELECT o FROM GeographicalLocationsCollection o where o.gelcStatus = true")
public class GeographicalLocationsCollection implements Serializable,Cloneable {
	private static final long serialVersionUID = 1L;
	
	public GeographicalLocationsCollection clone(ProposedCollection proposedCollection) throws CloneNotSupportedException {
		GeographicalLocationsCollection clone= (GeographicalLocationsCollection)super.clone();
		clone.setGelcId(null);
		clone.setGelcUserUpdate(null);
		clone.setGelcDateUpdate(null);
		clone.setProposedCollection(proposedCollection);
		return clone;
	}
	
	public GeographicalLocationsCollection()
	{
		
	}
	
	public GeographicalLocationsCollection(GeographicalLocation id)
	{
		this.geographicalLocation = id;
	}
	
	public GeographicalLocationsCollection(ProposedCollection proposedCollection)
	{
		this.proposedCollection=proposedCollection;
	}

	@Getter
	@Setter
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="gelc_id")
	private Integer gelcId;
	
	@Getter
	@Setter
	@Column(name="snap_gid")
	private Integer snapGid;
	
	@Getter
	@Setter
	@Column(name="snap_name")
	private String snapName;
	
	@Getter
	@Setter
	@Column(name="bosque_gid")
	private Integer bosqueGid;
	
	@Getter
	@Setter
	@Column(name="bosque_name")
	private String bosqueName;
	
	@Getter
	@Setter
	@Column(name="gelc_snap_high")
	private Boolean gelcSnapHigh;
	
	@Getter
	@Setter
	@Column(name="gelc_snap_low")
	private Boolean gelcSnapLow;
	
	@Getter
	@Setter
	@Column(name="gelc_snap_south")
	private Boolean gelcSnapSouth;
	
	@Getter
	@Setter
	@Column(name="gelc_snap_north")
	private Boolean gelcSnapNorth;
	
	@Getter
	@Setter
	@Column(name="gelc_status")
	private Boolean gelcStatus;
	
	@Getter
	@Setter
	@Column(name="gelc_user_create")
	private String gelcUserCreate;
	
	@Getter
	@Setter
	@Column(name="gelc_date_create")
	private Date gelcDateCreate;
	
	@Getter
	@Setter
	@Column(name="gelc_user_update")
	private String gelcUserUpdate;
	
	@Getter
	@Setter
	@Column(name="gelc_date_update")
	private Date gelcDateUpdate;

	//bi-directional many-to-one association to ProposedCollection
	@Getter
	@Setter
	@ManyToOne
	@JoinColumn(name="prco_id")
	private ProposedCollection proposedCollection;
	
	//bi-directional many-to-one association to GeographicalLocation
	@Getter
	@Setter
	@ManyToOne
	@JoinColumn(name="gelo_id")
	private GeographicalLocation geographicalLocation;	
	
	@Getter
	@Setter
	@Column(name = "gelc_docu_id")
	private Integer gelcDocuId;
	
	@Getter
	@Setter
	@Transient
	private ProposedDocument documentoAceptacionPredio;
	
	//gelc_is_private_property
	@Getter
	@Setter
	@Column(name = "gelc_is_private_property")
	private Boolean gelcPrivateProperty;
	
	@Getter
	@Setter
	@Transient
	private String sitiosGalapagos;
	

	
}

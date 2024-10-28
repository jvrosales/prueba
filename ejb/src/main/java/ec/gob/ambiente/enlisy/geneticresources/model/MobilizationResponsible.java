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
import javax.persistence.Table;

import ec.gob.ambiente.enlisy.model.Nationality;
import lombok.Getter;
import lombok.Setter;


/**
 * The persistent class for the mobilization_responsible database table.
 * 
 */
@Entity
@Table(name="mobilization_responsible", schema="biodiversity")
public class MobilizationResponsible implements Serializable {
	private static final long serialVersionUID = 1L;	

	public MobilizationResponsible() {		
	}
	
	public MobilizationResponsible(MobilizationGuide mobilizationGuide,String identification,String name, Nationality nationality) {
		this.mobilizationGuide = mobilizationGuide;
		this.morePeopIdentification=identification;
		this.morePeopName=name;
		this.nationality =nationality;
	}

	@Getter
	@Setter
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="more_id")
	private Integer moreId;

	@Getter
	@Setter
	@ManyToOne
	@JoinColumn(name="mogu_id")
	private MobilizationGuide mobilizationGuide;

	@Getter
	@Setter
	@ManyToOne
	@JoinColumn(name="nati_id")
	private Nationality nationality;

	@Getter
	@Setter	
	@Column(name="more_peop_identification")
	private String morePeopIdentification;

	@Getter
	@Setter
	@Column(name="more_peop_name")
	private String morePeopName;	
	
	@Getter
	@Setter
	@Column(name="more_transport")
	private Boolean moreTransport;
	
	@Getter
	@Setter
	@Column(name="more_status")
	private Boolean moreStatus;
	
	@Getter
	@Setter
	@Column(name="more_user_create")
	private String moreUserCreate;
	
	@Getter
	@Setter
	@Column(name="more_date_create")
	private Date moreDateCreate;
	
	@Getter
	@Setter
	@Column(name="more_user_update")
	private String moreUserUpdate;
	
	@Getter
	@Setter
	@Column(name="more_date_update")
	private Date moreDateUpdate;

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((moreId == null) ? 0 : moreId.hashCode());
		result = prime
				* result
				+ ((morePeopIdentification == null) ? 0
						: morePeopIdentification.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		MobilizationResponsible other = (MobilizationResponsible) obj;
		if (moreId == null) {
			if (other.moreId != null)
				return false;
		} else if (!moreId.equals(other.moreId))
			return false;
		if (morePeopIdentification == null) {
			if (other.morePeopIdentification != null)
				return false;
		} else if (!morePeopIdentification.equals(other.morePeopIdentification))
			return false;
		return true;
	}
	
	

	
	
}
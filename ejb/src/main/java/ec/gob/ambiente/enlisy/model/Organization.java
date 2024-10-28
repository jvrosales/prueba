package ec.gob.ambiente.enlisy.model;

import java.io.Serializable;

import javax.persistence.*;

import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;


import ec.gob.ambiente.suia.domain.base.EntidadBase;

import java.util.Date;
import java.util.List;

import lombok.Getter;
import lombok.Setter;


/**
 * The persistent class for the organizations database table.
 * 
 */
@Entity
@Table(name="organizations", schema="public")
@AttributeOverrides({ @AttributeOverride(name = "estado", column = @Column(name = "orga_status")) })
@NamedQueries({
	@NamedQuery(name = Organization.FIND_BY_PERSON, query = "SELECT o FROM Organization o WHERE o.people = :persona"),
	@NamedQuery(name = Organization.FIND_BY_PERSON_NAME, query = "SELECT o FROM Organization o WHERE o.people = :persona and o.orgaRuc = :nombre"),
@NamedQuery(name="Organization.findAll", query="SELECT o FROM Organization o")
})
public class Organization extends EntidadBase implements Serializable {
	private static final long serialVersionUID = 1L;

	public static final String FIND_BY_PERSON = "ec.com.magmasoft.business.domain.Organizacion.findByPerson";
	
	public static final String FIND_BY_PERSON_NAME = "ec.com.magmasoft.business.domain.Organizacion.findByPersonName";

	@Id
	@SequenceGenerator(name = "ORGANIZATIONS_GENERATOR", initialValue = 1, sequenceName = "seq_orga_id", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "ORGANIZATIONS_GENERATOR")
	@Column(name="orga_id")
	private Integer orgaId;

	@Column(name="gelo_id")
	private Integer geloId;

	@Column(name="orga_charge_legal_representative")
	private String orgaChargeLegalRepresentative;

	@Column(name="orga_name_organization")
	private String orgaNameOrganization;

	@Column(name="orga_ruc")
	private String orgaRuc;

	@Column(name="orga_state_participation")
	private String orgaStateParticipation;
	
	@Getter
	@Setter
	@Column(name="orga_activity")
	private String orgaActivity;

//	@Column(name="orga_status")
//	private Boolean orgaStatus;
	
	@Column(name="orga_user_create")
	private String orgaUserCreate;
	
	@Column(name="orga_date_create")
	private Date orgaDateCreate;
	
	@Column(name="orga_user_update")
	private String orgaUserUpdate;
	
	@Column(name="orga_date_update")
	private Date orgaDateUpdate;
	
	@Column(name="orga_ip_create")
	private String orgaIpCreate;
	
	@Column(name="orga_ip_update")
	private String orgaIpUpdate;

	//bi-directional many-to-one association to People
	@ManyToOne
	@JoinColumn(name="peop_id")
	private People people;

	//bi-directional many-to-one association to Contact	
	@OneToMany(cascade = CascadeType.PERSIST, mappedBy = "organization")
	@LazyCollection(LazyCollectionOption.FALSE)
	private List<Contact> contacts;
	
	//bi-directional many-to-one association to OrganizationsType
	@ManyToOne
	@JoinColumn(name="tyor_id")
	private OrganizationsType organizationsType;

	public Organization() {
	}

	public Integer getOrgaId() {
		return this.orgaId;
	}

	public void setOrgaId(Integer orgaId) {
		this.orgaId = orgaId;
	}

	public Integer getGeloId() {
		return this.geloId;
	}

	public void setGeloId(Integer geloId) {
		this.geloId = geloId;
	}

	public String getOrgaChargeLegalRepresentative() {
		return this.orgaChargeLegalRepresentative;
	}

	public void setOrgaChargeLegalRepresentative(String orgaChargeLegalRepresentative) {
		this.orgaChargeLegalRepresentative = orgaChargeLegalRepresentative;
	}

	public String getOrgaNameOrganization() {
		return this.orgaNameOrganization;
	}

	public void setOrgaNameOrganization(String orgaNameOrganization) {
		this.orgaNameOrganization = orgaNameOrganization;
	}

	public String getOrgaRuc() {
		return this.orgaRuc;
	}

	public void setOrgaRuc(String orgaRuc) {
		this.orgaRuc = orgaRuc;
	}

	public String getOrgaStateParticipation() {
		return this.orgaStateParticipation;
	}

	public void setOrgaStateParticipation(String orgaStateParticipation) {
		this.orgaStateParticipation = orgaStateParticipation;
	}

//	public Boolean getOrgaStatus() {
//		return this.orgaStatus;
//	}
//
//	public void setOrgaStatus(Boolean orgaStatus) {
//		this.orgaStatus = orgaStatus;
//	}

	public People getPeople() {
		return this.people;
	}

	public void setPeople(People people) {
		this.people = people;
	}

	public List<Contact> getContacts() {
		return this.contacts;
	}

	public void setContacts(List<Contact> contacts) {
		this.contacts = contacts;
	}

	public Contact addContact(Contact contact) {
		getContacts().add(contact);
		contact.setOrganization(this);

		return contact;
	}

	public Contact removeContact(Contact contact) {
		getContacts().remove(contact);
		contact.setOrganization(null);

		return contact;
	}

	public OrganizationsType getOrganizationsType() {
		return organizationsType;
	}

	public void setOrganizationsType(OrganizationsType organizationsType) {
		this.organizationsType = organizationsType;
	}
	
	public String getEmail()
	{	
		try {
			for (Contact contact : getContacts()) {
				if(contact.getContactsForm().getCofoId()==ContactsForm.EMAIL && !contact.getValue().isEmpty())					
					return contact.getValue();
			}
		
		} catch (Exception e) {
			System.out.println("No se encontro email de "+orgaNameOrganization);
		}
		return null;
	}

	public String getOrgaUserCreate() {
		return orgaUserCreate;
	}

	public void setOrgaUserCreate(String orgaUserCreate) {
		this.orgaUserCreate = orgaUserCreate;
	}

	public Date getOrgaDateCreate() {
		return orgaDateCreate;
	}

	public void setOrgaDateCreate(Date orgaDateCreate) {
		this.orgaDateCreate = orgaDateCreate;
	}

	public String getOrgaUserUpdate() {
		return orgaUserUpdate;
	}

	public void setOrgaUserUpdate(String orgaUserUpdate) {
		this.orgaUserUpdate = orgaUserUpdate;
	}

	public Date getOrgaDateUpdate() {
		return orgaDateUpdate;
	}

	public void setOrgaDateUpdate(Date orgaDateUpdate) {
		this.orgaDateUpdate = orgaDateUpdate;
	}

	public String getOrgaIpCreate() {
		return orgaIpCreate;
	}

	public void setOrgaIpCreate(String orgaIpCreate) {
		this.orgaIpCreate = orgaIpCreate;
	}

	public String getOrgaIpUpdate() {
		return orgaIpUpdate;
	}

	public void setOrgaIpUpdate(String orgaIpUpdate) {
		this.orgaIpUpdate = orgaIpUpdate;
	}
	
	@Override
	public Integer getId() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setId(Integer id) {
		// TODO Auto-generated method stub
		
	}
	
	
}
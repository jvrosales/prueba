package ec.gob.ambiente.enlisy.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.*;


/**
 * The persistent class for the contacts database table.
 * 
 */
@Entity
@Table(name="contacts", schema="public")
@NamedQuery(name="Contact.findAll", query="SELECT c FROM Contact c")
public class Contact implements Serializable {
	private static final long serialVersionUID = 1L;
	public static final String FIND_BY_EMAIL = "ec.com.magmasoft.business.domain.Contacto.findByEmail";

	@Id
	@SequenceGenerator(name = "CONTACTS_GENERATOR", initialValue = 1, sequenceName = "seq_cont_id", schema = "public", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "CONTACTS_GENERATOR")
	@Column(name="cont_id")
	private Long contId;

	@Column(name="cont_status")
	private Boolean status;

	@Column(name="cont_value")
	private String value;
	
	@Column(name="cont_user_create")
	private String contUserCreate;
	
	@Column(name="cont_date_create")
	private Date contDateCreate;
	
	@Column(name="cont_user_update")
	private String contUserUpdate;
	
	@Column(name="cont_date_update")
	private Date contDateUpdate;
	
	@Column(name="cont_ip_create")
	private String contIpCreate;
	
	@Column(name="cont_ip_update")
	private String contIpUpdate;

	//bi-directional many-to-one association to ContactsForm
	@ManyToOne
	@JoinColumn(name="cofo_id")
	private ContactsForm contactsForm;

	//bi-directional many-to-one association to Organization
	@ManyToOne
	@JoinColumn(name="orga_id")
	private Organization organization;

	//bi-directional many-to-one association to People	
	@ManyToOne
	@JoinColumn(name = "pers_id", referencedColumnName = "peop_id")
	private People people;

	public Contact() {		
	}
	
	public Contact(ContactsForm contactsForm) {
		setContactsForm(contactsForm);
		setStatus(true);
	}

	public Long getContId() {
		return this.contId;
	}

	public void setContId(Long contId) {
		this.contId = contId;
	}

	public Boolean getStatus() {
		return status;
	}

	public void setStatus(Boolean status) {
		this.status = status;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public ContactsForm getContactsForm() {
		return this.contactsForm;
	}

	public void setContactsForm(ContactsForm contactsForm) {
		this.contactsForm = contactsForm;
	}

	public Organization getOrganization() {
		return this.organization;
	}

	public void setOrganization(Organization organization) {
		this.organization = organization;
	}

	public People getPeople() {
		return this.people;
	}

	public void setPeople(People people) {
		this.people = people;
	}

	public String getContUserCreate() {
		return contUserCreate;
	}

	public void setContUserCreate(String contUserCreate) {
		this.contUserCreate = contUserCreate;
	}

	public Date getContDateCreate() {
		return contDateCreate;
	}

	public void setContDateCreate(Date contDateCreate) {
		this.contDateCreate = contDateCreate;
	}

	public String getContUserUpdate() {
		return contUserUpdate;
	}

	public void setContUserUpdate(String contUserUpdate) {
		this.contUserUpdate = contUserUpdate;
	}

	public Date getContDateUpdate() {
		return contDateUpdate;
	}

	public void setContDateUpdate(Date contDateUpdate) {
		this.contDateUpdate = contDateUpdate;
	}

	public String getContIpCreate() {
		return contIpCreate;
	}

	public void setContIpCreate(String contIpCreate) {
		this.contIpCreate = contIpCreate;
	}

	public String getContIpUpdate() {
		return contIpUpdate;
	}

	public void setContIpUpdate(String contIpUpdate) {
		this.contIpUpdate = contIpUpdate;
	}
	
}

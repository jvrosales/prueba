package ec.gob.ambiente.enlisy.services;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.persistence.NoResultException;
import javax.persistence.Query;

import ec.gob.ambiente.enlisy.dao.AbstractFacade;
import ec.gob.ambiente.enlisy.model.Contact;
import ec.gob.ambiente.enlisy.model.ContactsForm;
import ec.gob.ambiente.enlisy.model.Organization;
import ec.gob.ambiente.enlisy.model.People;
import ec.gob.ambiente.enlisy.model.User;
import ec.gob.ambiente.exceptions.ServiceException;
import ec.gob.ambiente.suia.crud.service.CrudServiceBean;
import ec.gob.ambiente.suia.domain.base.EntidadBase;



@Stateless
public class ContactFacade  extends AbstractFacade<Contact, Integer> implements Serializable{

	private static final long serialVersionUID = 3960693287587773578L;

	public ContactFacade() {
		super(Contact.class, Integer.class);	
	}

	
	
	@EJB
	private ContactsFormFacade contactsFormFacade;
	@EJB
	private CrudServiceBean crudServiceBean;
	
	/**
	 * Busca los contactos que coincidan con el valor ingresado, el nombre de
	 * usuario que le pertenece y el tipo
	 * 
	 * @param userName
	 * @param contactValue
	 * @param contactType
	 * @return
	 */
	@SuppressWarnings("unchecked")
	private List<Contact> findContactByUser(String userName,String contactValue,Integer contactType) {

	Query q;
	q = getEntityManager().createQuery(
	"SELECT c"
	+ " FROM Contact c,User u"
	+ " where c.status=true"
	+ " and c.people.peopId = u.people.peopId"
	+ " and c.contactsForm.cofoId = :contactType"
	+ " and c.value = :contactValue"
	+ " and u.userName = :userName");
	q.setParameter("contactValue", contactValue);
	q.setParameter("contactType", contactType);
	q.setParameter("userName", userName);
	return (List<Contact>) q.getResultList();
	}
	
	/**
	 * Busca los contactos que pertenezcan al nombre de
	 * usuario y el tipo
	 * por persona
	 * @param userName
	 * @param contactType
	 * @return
	 */
	@SuppressWarnings("unchecked")
	private List<Contact> findContactByUserPeople(String userName,Integer contactType) {

	Query q;
	q = getEntityManager().createQuery(
	"SELECT c"
	+ " FROM Contact c,User u"
	+ " where c.status=true"
	+ " and c.people.peopId = u.people.peopId"
	+ " and c.contactsForm.cofoId = :contactType"	
	+ " and u.userName = :userName"
	+ " order by 1");
	
	q.setParameter("contactType", contactType);
	q.setParameter("userName", userName);
	return (List<Contact>) q.getResultList();
	}
	
	/**
	 * Busca los contactos que pertenezcan al nombre de
	 * usuario y el tipo
	 * por Organizacion
	 * @param userName
	 * @param contactType
	 * @return
	 */
	@SuppressWarnings({ "unchecked", "unused" })
	private List<Contact> findContactByUserOrganization(String userName,Integer contactType) {

	Query q;
	q = getEntityManager().createQuery(
	"SELECT c"
	+ " FROM Contact c,User u,Organization o"
	+ " where c.status=true"
	+ " and c.organization.orgaId = o.orgaId"
	+ " and c.organization.orgaRuc = u.userName"
	+ " and c.contactsForm.cofoId = :contactType"	
	+ " and u.userName = :userName"	
	+ " order by 1");
	
	q.setParameter("contactType", contactType);
	q.setParameter("userName", userName);
	return (List<Contact>) q.getResultList();
	}
	
	/**
	 * Sobrecarga al metodo:
	 * List<Contacts> findContactByUser(String userName,String contactValue,Integer contactType)
	 * Envia un Usuario y el mail y busca con el tipo 5(Email)
	 * @param user
	 * @param email
	 * @return
	 */
	public List<Contact> findEmailByUser(User user,String email) {
		return findContactByUser(user.getUserName(), email,ContactsForm.EMAIL);		
	}
	
	/**
	 * Sobrecarga al metodo:
	 * List<Contacts> findContactByUser(String userName,Integer contactType)
	 * Envia un Usuario y busca con el tipo 5(Email)
	 * @param user
	 * @param email
	 * @return
	 */
	public List<Contact> findEmailByUser(User user) {
		List<Contact> result=findContactByOrganization(user.getUserName(), ContactsForm.EMAIL);
		if(result!=null&&!result.isEmpty())
			return result;
		else
			return findContactByUserPeople(user.getUserName(), ContactsForm.EMAIL);		
	}
	
	
	/**
	 * Busca los contactos que coincidan con el valor ingresado, el nombre de
	 * usuario de la organizacion que le pertenece y el tipo
	 * 
	 * @param userName
	 * @param contactValue
	 * @param contactType
	 * @return
	 */
	@SuppressWarnings("unchecked")
	private List<Contact> findContactByOrganization(String userName,String contactValue,Integer contactType) {

	Query q;
	q = getEntityManager().createQuery(
	"SELECT c FROM Contact c "
	+ "where c.status=true"
	+ " and c.contactsForm.cofoId = :contactType and c.value = :contactValue and (c.organization.orgaRuc=:userName"
	+ " or c.organization.people.peopPin=:userName)");	
	q.setParameter("contactValue", contactValue);
	q.setParameter("contactType", contactType);
	q.setParameter("userName", userName);
	return (List<Contact>) q.getResultList();
	}	
	
	
	/**
	 * Busca los contactos que pertenezcan al nombre de
	 * usuario de la organizacion y el tipo
	 * 
	 * @param userName
	 * @param contactType
	 * @return
	 */
	@SuppressWarnings("unchecked")
	private List<Contact> findContactByOrganization(String userName,Integer contactType) {

	Query q;
	q = getEntityManager().createQuery(
	"SELECT c"
	+ " FROM Contact c,Organization o"
	+ " where c.status=true"
	+ " and c.organization.orgaId = o.orgaId"
	+ " and c.contactsForm.cofoId = :contactType"	
	+ " and o.orgaRuc = :userName order by 1");
	
	q.setParameter("contactType", contactType);
	q.setParameter("userName", userName);
	return (List<Contact>) q.getResultList();
	}

	/**
	 * Sobrecarga al metodo:
	 * private List<Contacts> findContactByOrganization(String userName,String contactValue,Integer contactType)
	 * Envia un Usuario de organizacion y el mail y busca con el tipo 5(Email)
	 * @param user Organization
	 * @param email
	 * @return
	 */
	public List<Contact> findEmailByOrganization(User user,String email) {
		return findContactByOrganization(user.getUserName(), email,ContactsForm.EMAIL);
		
	}
	
	/**
	 * Sobrecarga al metodo:
	 * private List<Contacts> findContactByOrganization(String userName,Integer contactType) {
	 * Envia un Usuario de organizacion y el mail y busca con el tipo 5(Email)
	 * @param user Organization
	 * @param email
	 * @return
	 */
	public List<Contact> findEmailByOrganization(User user) {
		return findContactByOrganization(user.getUserName(),ContactsForm.EMAIL);		
	}
	
	/**
	 * Obtener contactos por persona, ordenados por ingreso
	 * @param people
	 * @return List<Contact>
	 * @throws ServiceException
	 */
	@SuppressWarnings("unchecked")
	public List<Contact> findByPeople(final People people)
			throws ServiceException {
		List<Contact> contacts = null;
		if(people!=null)
		try {
			Query query =  super.getEntityManager().createQuery("SELECT c FROM Contact c WHERE c.people.peopId = :peopId AND c.status=true ORDER BY c.contId ASC");
			query.setParameter("peopId", people.getPeopId());
			contacts = (List<Contact>) query.getResultList();
			
		} catch (RuntimeException e) {
			throw new ServiceException(e);
		}
		return contacts;
	}
	
	/**
	 * Buscar Contactos por organizacion
	 * @param organization
	 * @return
	 * @throws ServiceException
	 */
	@SuppressWarnings("unchecked")
	public List<Contact> findByOrganization(final Organization organization){		
		try {		
			Query query =  super.getEntityManager().createQuery("SELECT o FROM Contact o WHERE o.organization.orgaId = :orgaId AND o.status=true ORDER BY o.contId ASC");
			query.setParameter("orgaId", organization.getOrgaId());
			List<Contact> contacts = (List<Contact>) query.getResultList();
			return contacts;
			
		} catch (NoResultException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	/**
	 * Se consulta si existe el mail registrado
	 * 
	 * @param nombre
	 * @return
	 */
	public List<Contact> searchMail(String valor) throws ServiceException {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("emailCode", ContactsForm.EMAIL);
		params.put("value", valor != null ? valor.trim() : "");
		return (List<Contact>) super.findByCreateQueryPaginado("SELECT c FROM Contact c WHERE c.status=true and c.contactsForm.cofoId = :emailCode and c.value = :value", params, 0, 1);
	}
	
	/**
	 * Guardar Contacto
	 * @param contact
	 */
	@Deprecated
	public void save(Contact contact) {
		if(contact.getContId()==null)
			create(contact);
		else
			edit(contact);
	}
	
	/**
	 * Guardar Contacto
	 * @param contact
	 */
	public void save(Contact contact,User user) {
		if(contact.getContId()==null){
			contact.setContUserCreate(user.getUserName());
			contact.setContDateCreate(new Date());
			create(contact);
		}
		else{
			contact.setContUserUpdate(user.getUserName());
			contact.setContDateUpdate(new Date());
			edit(contact);
		}
	}

	@SuppressWarnings("unchecked")
	public List<Contact> buscarUsuarioNativeQuery(final String userName) throws ServiceException {
		List<Contact> listaContacto = new ArrayList<Contact>();
		try {

			listaContacto = (List<Contact>) crudServiceBean.getEntityManager().createQuery(
					"SELECT c FROM Contact c WHERE c.status=true and c.organization.orgaRuc= :userName ORDER BY c.contId ASC")
					.setParameter("userName", userName).getResultList();

			if (listaContacto.isEmpty())
				listaContacto = (List<Contact>) crudServiceBean.getEntityManager().createQuery(
						"SELECT c FROM Contact c WHERE c.status=true and c.people.peopPin= :userName ORDER BY c.contId ASC")
						.setParameter("userName", userName).getResultList();

		} catch (RuntimeException e) {
			throw new ServiceException(e);
		}
		return listaContacto;
	}

	/**
	 * Se consulta si existe el mail registrado
	 * 
	 * @param nombre
	 * @return
	 */
	public List<? extends EntidadBase> buscarMail(String valor) throws ServiceException {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("codigoEmail", ContactsForm.EMAIL);
		params.put("valor", valor != null ? valor.trim() : "");
		return crudServiceBean.findByNamedQueryPaginados(Contact.FIND_BY_EMAIL, params, 0, 10);

	}

}
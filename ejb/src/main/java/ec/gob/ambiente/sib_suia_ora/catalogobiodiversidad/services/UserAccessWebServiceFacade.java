package ec.gob.ambiente.sib_suia_ora.catalogobiodiversidad.services;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.persistence.NoResultException;
import javax.persistence.Query;

import ec.gob.ambiente.enlisy.dao.AbstractFacade;
import ec.gob.ambiente.enlisy.model.Contact;
import ec.gob.ambiente.enlisy.model.Organization;
import ec.gob.ambiente.enlisy.model.People;
import ec.gob.ambiente.enlisy.model.User;
import ec.gob.ambiente.enlisy.services.ContactFacade;
import ec.gob.ambiente.enlisy.services.OrganizationFacade;
import ec.gob.ambiente.enlisy.services.RoleFacade;
import ec.gob.ambiente.enlisy.services.RolesUserFacade;
import ec.gob.ambiente.enlisy.services.UserFacade;
import ec.gob.ambiente.exceptions.ServiceException;
import ec.gob.ambiente.sib_suia_ora.catalogobiodiversidad.model.UserAccessWebService;
import ec.gob.ambiente.vo.NotificacionesMails;

/**
 * Servicios para administracion de la solicitud de registro de especialista
 * 
 * @author EXCO
 *
 */
@Stateless
public class UserAccessWebServiceFacade extends AbstractFacade<UserAccessWebService, Integer> {

	public UserAccessWebServiceFacade() {
		super(UserAccessWebService.class, Integer.class);
	}

	@EJB
	private UserFacade userFacade;

	@EJB
	private RolesUserFacade rolesUserFacade;

	@EJB
	private RoleFacade roleFacade;

	@EJB
	private UserAccessWebServiceFacade userAccessWebServicefacades;

	@EJB
	private ContactFacade contactFacade;

	@EJB
	private OrganizationFacade organizationFacade;

	/**
	 * Buscar userAcces
	 * 
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<UserAccessWebService> findUsersAccess() throws ServiceException{
		try {
			Query query = super.getEntityManager()
					.createQuery("SELECT o FROM UserAccessWebService o where o.uawsStatus = true");
			List<UserAccessWebService> result = (List<UserAccessWebService>) query.getResultList();
			if (result.size() > 0)
				return result;

		} catch (NoResultException e) {
			return new ArrayList<UserAccessWebService>();
		}
		return new ArrayList<UserAccessWebService>();
	}

	/**
	 * Metodo para guardar usuario y acceso a servicios web
	 * 
	 * @param user
	 * @param notificacionesMail
	 * @param usuarioExterno
	 * @param rolServicioWeb
	 * @param wsCatalog
	 * @param wsSummary
	 * @throws ServiceException
	 */
	public void saveAll(User user, NotificacionesMails notificacionesMail, boolean usuarioExterno,
			String rolServicioWeb, Boolean wsCatalog, Boolean wsSummary) throws ServiceException {

		if (user.getUserId() != null) {
			userFacade.edit(user);
		} else {
			userFacade.create(user);
		}

		if (usuarioExterno) {
			rolesUserFacade.save(user, roleFacade.findByName(rolServicioWeb));
		}

		UserAccessWebService userAccess = new UserAccessWebService();
		userAccess.setUserId(user.getUserId());
		userAccess.setUawsAccessStatus("REGISTRADO");
		userAccess.setUawsDateCreate(new Date());
		userAccess.setUawsWsCatalogTaxonomic(wsCatalog);
		userAccess.setUawsWsSummary(wsSummary);
		userAccess.setUawsStatus(true);
		userAccessWebServicefacades.create(userAccess);
	}

	/**
	 * Metodo para guardar usuario persona juridica y accesos a servicios web
	 * 
	 * @param user
	 * @param people
	 * @param organization
	 * @param notificacionesMail
	 * @param usuarioExterno
	 * @param rolServicioWeb
	 * @param wsCatalog
	 * @param wsSummary
	 * @throws ServiceException
	 */
	public void saveUser(User user, People people, Organization organization, NotificacionesMails notificacionesMail,
			boolean usuarioExterno, String rolServicioWeb, Boolean wsCatalog, Boolean wsSummary)
			throws ServiceException {

		if (user.getUserId() != null) {
			userFacade.edit(user);
		} else {
			// cambio para guardar

			userFacade.create(user);
			if (user.getUserId() != null) {
				user.setPeople(people);
				userFacade.edit(user);
			}

			for (Contact contact : organization.getContacts()) {
				if (contact.getContId() == null)
					contactFacade.create(contact);
				else
					contactFacade.edit(contact);
			}

			if (organization != null && organization.getOrgaId() != null)
				organizationFacade.edit(organization);

		}

		if (usuarioExterno) {
			rolesUserFacade.save(user, roleFacade.findByName(rolServicioWeb));
		}

		UserAccessWebService userAccess = new UserAccessWebService();
		userAccess.setUserId(user.getUserId());
		userAccess.setUawsAccessStatus("REGISTRADO");
		userAccess.setUawsDateCreate(new Date());
		userAccess.setUawsWsCatalogTaxonomic(wsCatalog);
		userAccess.setUawsWsSummary(wsSummary);
		userAccess.setUawsStatus(true);
		userAccessWebServicefacades.create(userAccess);
	}
	
	/**
	 * Metodo que guarda los accesos a servicios web solicitados por un usuario
	 * @param user
	 * @param wsCatalog
	 * @param wsSummary
	 * @throws ServiceException
	 */
	public void saveAccessServicioWeb(User user, Boolean wsCatalog, Boolean wsSummary)
			throws ServiceException {
	
		UserAccessWebService userAccess = new UserAccessWebService();
		userAccess.setUserId(user.getUserId());
		userAccess.setUawsAccessStatus("REGISTRADO");
		userAccess.setUawsDateCreate(new Date());
		userAccess.setUawsWsCatalogTaxonomic(wsCatalog);
		userAccess.setUawsWsSummary(wsSummary);
		userAccess.setUawsStatus(true);
		userAccessWebServicefacades.create(userAccess);
	}

	
	@SuppressWarnings("unchecked")
	public List<UserAccessWebService> findUsersAccessByUser(Integer idUser) {
		try {
			Query query = super.getEntityManager()
					.createQuery("SELECT o FROM UserAccessWebService o where o.uawsStatus = true and o.userId =:idUser");
			query.setParameter("idUser", idUser);
			List<UserAccessWebService> result = (List<UserAccessWebService>) query.getResultList();
			if (result.size() > 0)
				return result;

		} catch (NoResultException e) {
			return new ArrayList<UserAccessWebService>();
		}
		return new ArrayList<UserAccessWebService>();
	}
}

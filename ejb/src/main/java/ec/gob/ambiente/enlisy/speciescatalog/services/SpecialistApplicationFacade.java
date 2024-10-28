package ec.gob.ambiente.enlisy.speciescatalog.services;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.persistence.NoResultException;
import javax.persistence.Query;

import ec.gob.ambiente.enlisy.dao.AbstractFacade;
import ec.gob.ambiente.enlisy.model.Contact;
import ec.gob.ambiente.enlisy.model.ContactsForm;
import ec.gob.ambiente.enlisy.model.User;
import ec.gob.ambiente.enlisy.services.ContactFacade;
import ec.gob.ambiente.enlisy.speciescatalog.model.SpecialistApplication;
import ec.gob.ambiente.exceptions.ServiceException;

/**
 * Servicios para administracion de la solicitud de registro de especialista
 * 
 * @author EXCO
 *
 */
@Stateless
public class SpecialistApplicationFacade extends AbstractFacade<SpecialistApplication, Integer> {
	
	@EJB
	ContactFacade contactFacade;

	public SpecialistApplicationFacade() {
		super(SpecialistApplication.class, Integer.class);
	}

	/**
	 * Guardar Solicitud de Especialista
	 * 
	 * @param solicitudEspecialista
	 * @param usuario
	 * @return
	 */
	public SpecialistApplication save(SpecialistApplication specialistApplication, User user) {
		SpecialistApplication application = null;
		try {
			if (specialistApplication.getSpapId() == null) {

				specialistApplication.setSpapStatus(true);
				specialistApplication.setSpapDateCreate(new Date());
				specialistApplication.setSpapUserCreate(user.getUserName());
				application = create(specialistApplication);

			} else {
				specialistApplication.setSpapDateUpdate(new Date());
				specialistApplication.setSpapUserUpdate(user.getUserName());
				application = edit(specialistApplication);
			}

			return application;
		} catch (Exception ex) {
			return application;
		}
	}

	/**
	 * Recuperar Solicitud de Especialista por numero Tramite
	 * 
	 * @param solicitudEspecialista
	 * @param usuario
	 * @return
	 */
	public SpecialistApplication findByCode(String code) {
		try {
			Query query = super.getEntityManager().createQuery(
					"SELECT o FROM SpecialistApplication o where o.spapCode= :codigo and o.spapStatus = true");
			query.setParameter("codigo", code);

			SpecialistApplication result = (SpecialistApplication) query.getSingleResult();
			if (result != null)
				return result;

		} catch (NoResultException e) {
			return null;
		}
		return null;
	}

	public SpecialistApplication findByUser(String code) {

		try {
			Query query = super.getEntityManager().createQuery(
					"SELECT o FROM SpecialistApplication o where o.user.people.peopPin= :codigo and o.spapStatus = true and (o.spapStatusApplication= :elEstado or o.spapStatusApplication= :elEstadoDos) ");
			query.setParameter("codigo", code);
			query.setParameter("elEstado", "APROBADO");
			query.setParameter("elEstadoDos", "INGRESADO");
			

			SpecialistApplication result = (SpecialistApplication) query.getSingleResult();
			if (result != null)
				return result;

		} catch (NoResultException e) {
			return null;
		}
		return null;
	}
	
	public SpecialistApplication findByUserTramite(String code,String numeroTramite) {

		try {
			Query query = super.getEntityManager().createQuery(
					"SELECT o FROM SpecialistApplication o where o.user.people.peopPin= :codigo and o.spapStatus = true and o.spapStatusApplication= :elEstado and o.spapCode= :elTramite ");
			query.setParameter("codigo", code);
			query.setParameter("elEstado", "APROBADO");
			query.setParameter("elTramite", numeroTramite);
			
			

			SpecialistApplication result = (SpecialistApplication) query.getSingleResult();
			if (result != null)
				return result;

		} catch (NoResultException e) {
			return null;
		}
		return null;
	}

	
	@SuppressWarnings("unchecked")
	public List<SpecialistApplication> findByRedListEnabled() {
		try
		{
			String sql = "SELECT SISA.spapSpecialist FROM SpecialistInterestSpecialistApplication SISA "
					+ "WHERE SISA.sisaStatus=true AND SISA.sisaSelected=true "
					+ "AND SISA.spapSpecialist.spapStatus=true "
					+ "AND SISA.spapSpecialist.spapStatusApplication='APROBADO'" 
				    //+ "AND SISA.spapStatusApplication.user.userStatus=true "
					+ "AND SISA.spapSpecialist.user.people.peopStatus=true "
					+ "AND SISA.specialistInterest.spinStatus=true "
					+ "AND SISA.specialistInterest.spinId=3 ";
			Query query = super.getEntityManager().createQuery(sql);
			return query.getResultList();
			
		}catch(Exception e)
		{
			e.printStackTrace();
			return null;
		}
	}
	
	
	public SpecialistApplication findByUserEspecialistaAprobado(String code) {

		try {
			Query query = super.getEntityManager().createQuery(
					"SELECT o FROM SpecialistApplication o where o.user.people.peopPin= :codigo and o.spapStatus = true and (o.spapStatusApplication= :elEstado) ");
			query.setParameter("codigo", code);
			query.setParameter("elEstado", "APROBADO");
						

			SpecialistApplication result = (SpecialistApplication) query.getSingleResult();
			if (result != null)
				return result;

		} catch (NoResultException e) {
			return null;
		}
		return null;
	}
	
	/**
	 * Obtener la Lista de Especialistas aprobados
	 * @return
	 */
	
	public List<SpecialistApplication> findByAprobado() {

		try {
			Query query = super.getEntityManager().createQuery(
					"SELECT o FROM SpecialistApplication o where o.spapStatus = true and (o.spapStatusApplication= :elEstado) ");
			query.setParameter("elEstado", "APROBADO");
						

			@SuppressWarnings("unchecked")
			List<SpecialistApplication> result =  query.getResultList();
			if (!result.isEmpty())
			{
				for(SpecialistApplication sol:result)
				{
					if(sol.getUser()!=null&&sol.getUser().getPeople()!=null)
					{
						
							sol.setNombresApellidos(sol.getUser().getPeople().getPeopName());
							sol.setIdentificacion(sol.getUser().getPeople().getPeopPin());
							
							List<Contact> contactosList = contactFacade.findByPeople(sol.getUser().getPeople());
							for (Contact contact : contactosList) {
								int tipoContacto = Integer.valueOf(contact.getContactsForm().getCofoId().intValue());

								switch (tipoContacto) {
								case ContactsForm.CELULAR:
									break;
								case ContactsForm.TELEFONO:
									sol.setTelefono(contact.getValue());
									break;

								case ContactsForm.EMAIL:
									sol.setEmail(contact.getValue());
									break;

								case ContactsForm.URL:
									break;

								}
							}
					}
				}
				return result;
			}

		} catch (NoResultException e) {
			return new ArrayList<SpecialistApplication>();
		} catch (ServiceException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return new ArrayList<SpecialistApplication>();
		}
		return new ArrayList<SpecialistApplication>();
	}
	
	/**
	 * Buscar un especialista con todos los datos asociados
	 */
	
	public SpecialistApplication findAll(Integer id) {

		try {
			
			SpecialistApplication result =  find(id);
			if (result!=null)
			{
				
					if(result.getUser()!=null&&result.getUser().getPeople()!=null)
					{
						
						result.setNombresApellidos(result.getUser().getPeople().getPeopName());
						result.setIdentificacion(result.getUser().getPeople().getPeopPin());
							
							List<Contact> contactosList = contactFacade.findByPeople(result.getUser().getPeople());
							for (Contact contact : contactosList) {
								int tipoContacto = Integer.valueOf(contact.getContactsForm().getCofoId().intValue());

								switch (tipoContacto) {
								case ContactsForm.CELULAR:
									break;
								case ContactsForm.TELEFONO:
									result.setTelefono(contact.getValue());
									break;

								case ContactsForm.EMAIL:
									result.setEmail(contact.getValue());
									break;

								case ContactsForm.URL:
									break;

								}
							}
					}
				
				return result;
			}

		} catch (NoResultException e) {
			return new SpecialistApplication();
		} catch (ServiceException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return new SpecialistApplication();
		}
		return new SpecialistApplication();
	}
	
}

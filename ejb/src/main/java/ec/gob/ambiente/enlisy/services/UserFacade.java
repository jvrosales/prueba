package ec.gob.ambiente.enlisy.services;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.persistence.NoResultException;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import ec.gob.ambiente.enlisy.dao.AbstractFacade;
import ec.gob.ambiente.enlisy.geneticresources.services.GeneticResourcesCatalogFacade;
import ec.gob.ambiente.enlisy.model.Area;
import ec.gob.ambiente.enlisy.model.Contact;
import ec.gob.ambiente.enlisy.model.Organization;
import ec.gob.ambiente.enlisy.model.People;
import ec.gob.ambiente.enlisy.model.User;
import ec.gob.ambiente.exceptions.ServiceException;
import ec.gob.ambiente.suia.crud.service.CrudServiceBean;
import ec.gob.ambiente.tareas.programadas.EnvioMailCliente;
import ec.gob.ambiente.vo.NotificacionesMails;

@Stateless
public class UserFacade extends AbstractFacade<User, Integer> implements Serializable {
	
	private static final long serialVersionUID = -4594424897085245484L;


	public UserFacade() {
		super(User.class, Integer.class);		
	}
	
	
	@EJB
	private UserFacade userfacade;
	
	@EJB
	private RolesUserFacade rolesUserFacade;
	
	@EJB
	private RoleFacade roleFacade;
	
	@EJB
	private EnvioMailCliente envioMailCliente;
	
	@EJB
	private GeneticResourcesCatalogFacade catalogFacade;
		
	@EJB
	private OrganizationFacade organizationFacade;

	@EJB
	private PeopleFacade peopleFacade;

	@EJB
	private GeographicalLocationFacade geographicalLocationFacade;
	
	@EJB
	private ContactFacade contactFacade;
	
	@EJB
	private CrudServiceBean crudServiceBean;

	public User getUserById(Integer userId)
	{
		//return userfacade.find(userId);
		try {
			Query query = getEntityManager().createQuery(" SELECT u FROM User u where u.userId= :userId");		
			query.setParameter("userId",	userId);
			return (User) query.getSingleResult();			
		} catch (NoResultException e) {
			return null;
		}
	}
	
	public List<User> buscarUsuarios(String rol,String descripcion) {
		@SuppressWarnings("unchecked")
		List<User> usuarios = crudServiceBean
				.getEntityManager()
				.createQuery("SELECT ru.usuario FROM RolUsuario ru"
						+ " WHERE ru.estado=true AND ru.rol.estado=true AND ru.usuario.estado=true"
						+ " AND ru.rol.nombre =:rol" 
						+ " AND ru.descripcion LIKE :descripcion" )
				.setParameter("rol", rol).setParameter("descripcion", descripcion).getResultList();

		return usuarios;
	}
	
	public User buscarUsuario(String nombre) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("name", nombre != null ? nombre.trim() : "");
		try {
			User usuario = (User) crudServiceBean.findByNamedQuerySingleResult(User.FIND_BY_USER, params);
			if (usuario != null) {
				usuario.getRolesUsers().size();
				usuario.getPeople();
				
//				usuario.getArea();
			}
			return usuario;
		} catch (NoResultException ex) {
			return null;
		}
	}
	
	public void save(User user, NotificacionesMails notificacionesMail, boolean usuarioExterno, String rolProponente) throws ServiceException {
        
        if(user.getUserId() != null)
        {
        	userfacade.edit(user);
        }
        else
        {
        	userfacade.create(user);
        }        
              
        
        if (usuarioExterno) {            
            rolesUserFacade.save(user,roleFacade.findByName(rolProponente));
        }
    }
	
	/**
	 * Actualizar datos de perfil de usuario
	 * @param user
	 */
	public void updateUserData(User user,Organization organization) {

		//Actualizar Usuario
		//Se actualiza en cascada la tabla personas
		userfacade.edit(user);
		
		//Actualizar, crear o remover contactos
		//ContactService contactService=new ContactService();
		for (Contact contact : user.getPeople().getContacts()) {
			if(contact.getContId()==null)				
				contactFacade.create(contact);							
			else
				contactFacade.edit(contact);
		}
		
		if(organization!=null&&organization.getOrgaId()!=null)
			organizationFacade.edit(organization);

		
		
	}
	
	public Organization getOrganizationByPeopleAndRuc(People people,String orgaRuc)
	{
		return organizationFacade.findByPeopleAndRuc(people,orgaRuc);
	}
	
	public People getPeopleByPin(String peopPin) {
	
		return peopleFacade.findByPin(peopPin);
	}
	
	
	
	
	public String getCompleteNameByUserName(String userName)
	{
		Organization orga=organizationFacade.findByRuc(userName);
		if(orga!=null&&orga.getOrgaNameOrganization()!=null)
			return orga.getOrgaNameOrganization();
		
		TypedQuery<User> query = super.getEntityManager().createQuery("select u from User u where u.userName = :userName", User.class);
		
		query.setParameter("userName", userName);
		
		String completeName = "";
		if(query.getResultList().size() > 0)
		{
			completeName = query.getResultList().get(0).getPeople().getPeopName();
		}
		
		return completeName;
	}
	
	public User findByUserName(String userName)
	{
		TypedQuery<User> query = super.getEntityManager().createQuery("select u from User u "
				+ "where u.userName = :userName and u.userStatus = true", User.class);
		
		query.setParameter("userName", userName);
		
		User user = new User();
		
		if(query.getResultList().size() > 0)
		{
			user = query.getResultList().get(0);
		}
		
		return user;
	}
	
	public User findByUserNameAll(String userName)
	{
		TypedQuery<User> query = super.getEntityManager().createQuery("select u from User u "
				+ "where u.userName = :userName", User.class);
		
		query.setParameter("userName", userName);
		
		User user = new User();
		
		if(query.getResultList().size() > 0)
		{
			user = query.getResultList().get(0);
		}
		
		return user;
	}
	
	public User findByUserNameDisabled(String userName)
	{
		TypedQuery<User> query = super.getEntityManager().createQuery("select u from User u "
				+ "where u.userName = :userName and u.userStatus = false", User.class);
		
		query.setParameter("userName", userName);
		
		User user = new User();
		
		if(query.getResultList().size() > 0)
		{
			user = query.getResultList().get(0);
		}
		
		return user;
	}
	
	public User findByArea(Area area)
	{
		TypedQuery<User> query = super.getEntityManager().createQuery("select u from User u where u.area.id = :area and u.userStatus = true", User.class);
		
		query.setParameter("area", area.getId());
		
		User user = new User();
		
		if(query.getResultList().size() > 0)
		{
			user = query.getResultList().get(0);
		}
		
		return user;
	}
	
	public List<User> find_all(){		
		return findAll();
	}
	
	public User updateUser(User usuario){		
		return edit(usuario);
	}
	
	public void saveUser(User user, People people, Organization organization, NotificacionesMails notificacionesMail, boolean usuarioExterno, String rolProponente) throws ServiceException {
        
        if(user.getUserId() != null)
        {
        	userfacade.edit(user);
        }
        else
        {
        	//cambio para guardar
        	
        	userfacade.create(user);
        	if(user.getUserId() != null){
        		user.setPeople(people);
        		userfacade.edit(user);
        	}
        	
        	for (Contact contact : organization.getContacts()) {
    			if(contact.getContId()==null)				
    				contactFacade.create(contact);							
    			else
    				contactFacade.edit(contact);
    		}
    		
    		if(organization!=null&&organization.getOrgaId()!=null)
    			organizationFacade.edit(organization);
        	
        }              
        
        if (usuarioExterno) {            
            rolesUserFacade.save(user,roleFacade.findByName(rolProponente));
        }
    }

	public User userByPeople(Integer peopleId)
	{
		//return userfacade.find(userId);
		try {
			Query query = getEntityManager().createQuery(" SELECT u FROM User u where u.people.peopId = :peopleId");		
			query.setParameter("peopleId",	peopleId);
			return (User) query.getSingleResult();			
		} catch (NoResultException e) {
			return null;
		}
	}
	
}
package ec.gob.ambiente.enlisy.services;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.TypedQuery;

import ec.gob.ambiente.enlisy.dao.AbstractFacade;
import ec.gob.ambiente.enlisy.model.PatentInformation;
import ec.gob.ambiente.enlisy.model.User;
import ec.gob.ambiente.enlisy.model.UsuarioAsociado;

@Stateless
public class PatentInformationFacade extends AbstractFacade<PatentInformation, Integer> implements Serializable {

	private static final long serialVersionUID = -4594424897085245484L;

	public PatentInformationFacade() {
		super(PatentInformation.class, Integer.class);
	}

	/**
	 * Actualizar datos de perfil de usuario
	 * 
	 * @param user
	 */

	public PatentInformation findByPreliminary(String preliminary) {
		TypedQuery<PatentInformation> query = super.getEntityManager().createQuery(
				"select u from PatentInformation u " + "where u.preliminary = :preliminary ", PatentInformation.class);
		query.setParameter("preliminary", preliminary);

		PatentInformation patentInformation = new PatentInformation();
		if (query.getResultList().size() > 0) {
			patentInformation = query.getResultList().get(0);
		}
		return patentInformation;
	}

	
	public PatentInformation findByMax(String preliminary)
	{
		TypedQuery<PatentInformation> query = super.getEntityManager().createQuery(
			"select u from PatentInformation u "
				+ "where u.preliminary = :preliminary "
				+ "and u.status = true and u.secuenceInt="
				+ "(select max(o.secuenceInt) from PatentInformation o "
				+ "where o.preliminary = :preliminary)", PatentInformation.class);
		query.setParameter("preliminary", preliminary);
		PatentInformation patentInformation = new PatentInformation();
		if(query.getResultList().size() > 0)
		{
			patentInformation = query.getResultList().get(0);
		}
		return patentInformation;
	}
	
	public boolean saveUpdate(PatentInformation patentInformation, User user ) {
		try
		{			
			if(patentInformation.getId() == null)
			{
				patentInformation.setCreationDate(new Date());
				patentInformation.setCreatorUser(user.getUserName());
				create(patentInformation);
			}
			else
			{
				patentInformation.setDateUpdate(new Date());
				patentInformation.setUserUpdate(user.getUserName());
				edit(patentInformation);
			}
			return true;
		}
		catch(Exception ex)
		{
			System.out.println(ex.toString());
			return false;
		}
	}

	public PatentInformation findById(Integer id) {
		TypedQuery<PatentInformation> query = super.getEntityManager()
				.createQuery("select u from PatentInformation u " + "where u.id = :id and u.status = true", PatentInformation.class);
		query.setParameter("id", id);

		PatentInformation patentInformation = new PatentInformation();
		if (query.getResultList().size() > 0) {
			patentInformation = query.getResultList().get(0);
		}
		return patentInformation;
	}
	
	public List<PatentInformation> findByCategoria(UsuarioAsociado usarioAsociado) {
		TypedQuery<PatentInformation> query = super.getEntityManager()
				.createQuery("select u from PatentInformation u where u.usuarioAsociado = :usarioAsociado and u.status = true", PatentInformation.class);

		query.setParameter("usarioAsociado", usarioAsociado);
		List<PatentInformation> patentInformation = new ArrayList<PatentInformation>();
		if (query.getResultList().size() > 0) {
			patentInformation = query.getResultList();
		}
		return patentInformation;
	}
	
	public List<PatentInformation> findByListPatent(String codigoProyecto) {
		TypedQuery<PatentInformation> query = super.getEntityManager()
				.createQuery("select u from PatentInformation u where u.code = :codigoProyecto and u.status = true order by 1 desc", PatentInformation.class);
		query.setParameter("codigoProyecto", codigoProyecto);
		List<PatentInformation> patentInformation = new ArrayList<PatentInformation>();
		if (query.getResultList().size() > 0) {
			patentInformation = query.getResultList();
		}
		return patentInformation;
	}
	
	public PatentInformation findByCode(String code) {
		TypedQuery<PatentInformation> query = super.getEntityManager()
				.createQuery("select u from PatentInformation u " + "where u.code = :code and u.status = true order by u.Id DESC", PatentInformation.class);
		query.setParameter("code", code);

		PatentInformation patentInformation = new PatentInformation();
		if (query.getResultList().size() > 0) {
			patentInformation = query.getResultList().get(0);
		}
		return patentInformation;
	}
	
	public PatentInformation findByCodeRenovation(String code) {
		TypedQuery<PatentInformation> query = super.getEntityManager()
				.createQuery("select u from PatentInformation u " + "where u.code = :code and u.status = true order by u.Id asc ", PatentInformation.class);
		query.setParameter("code", code);
		
		PatentInformation patentInformation = new PatentInformation();
		if (query.getResultList().size() > 0) {
			patentInformation = query.getResultList().get(0);
		}
		return patentInformation;
	}
	
	public void delete(PatentInformation patentInformation, User user){
		patentInformation.setStatus(false);
		patentInformation.setDateUpdate(new Date());
		patentInformation.setUserUpdate(user.getUserName());
		edit(patentInformation);
	}

}
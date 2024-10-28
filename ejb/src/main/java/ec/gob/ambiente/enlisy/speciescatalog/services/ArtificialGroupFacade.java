package ec.gob.ambiente.enlisy.speciescatalog.services;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.NoResultException;
import javax.persistence.Query;

import ec.gob.ambiente.enlisy.dao.AbstractFacade;
import ec.gob.ambiente.enlisy.model.User;
import ec.gob.ambiente.enlisy.speciescatalog.model.ArtificialGroup;


/**
 * Servicios para la administracion de grupos artificiales
 * @author EXCO
 *
 */
@Stateless
public class ArtificialGroupFacade extends AbstractFacade<ArtificialGroup, Integer>{

	public ArtificialGroupFacade() {
		super(ArtificialGroup.class, Integer.class);		
	}
	
		
	/**
	 * Recuperar lista de grupos reino animal
	 * @return
	 */
	
	@SuppressWarnings("unchecked")
	public List<ArtificialGroup> findGroupsAnimal()
	{
		
		try
		{
			Query query = super.getEntityManager().createQuery("SELECT o FROM ArtificialGroup o where o.argrStatus = true and o.argrAnimal=true order by argrName");
			List<ArtificialGroup> result=(List<ArtificialGroup>)query.getResultList();
			if(result.size()>0)
				return result;
			
		}catch(NoResultException e)
		{
			return new ArrayList<ArtificialGroup>();
		}
		return new ArrayList<ArtificialGroup>();
	}
	
	/**
	 * Recuperar lista de grupos reino plantae
	 * @return
	 */
	
	@SuppressWarnings("unchecked")
	public List<ArtificialGroup> findGroupsPlantae()
	{
		
		try
		{
			Query query = super.getEntityManager().createQuery("SELECT o FROM ArtificialGroup o where o.argrStatus = true and o.argrPlantae=true order by argrName");
			List<ArtificialGroup> result=(List<ArtificialGroup>)query.getResultList();
			if(result.size()>0)
				return result;
			
		}catch(NoResultException e)
		{
			return new ArrayList<ArtificialGroup>();
		}
		return new ArrayList<ArtificialGroup>();
	}
	
	/**
	 * Recuperar lista de grupos reino fungi
	 * @return
	 */
	
	@SuppressWarnings("unchecked")
	public List<ArtificialGroup> findGroupsFungi()
	{
		
		try
		{
			Query query = super.getEntityManager().createQuery("SELECT o FROM ArtificialGroup o where o.argrStatus = true and o.argrFungi=true order by argrName");
			List<ArtificialGroup> result=(List<ArtificialGroup>)query.getResultList();
			if(result.size()>0)
				return result;
			
		}catch(NoResultException e)
		{
			return new ArrayList<ArtificialGroup>();
		}
		return new ArrayList<ArtificialGroup>();
	}
	
	/**
	 * Recuperar lista de grupos reino eubacteria
	 * @return
	 */
	
	@SuppressWarnings("unchecked")
	public List<ArtificialGroup> findGroupsEubacteria()
	{
		
		try
		{
			Query query = super.getEntityManager().createQuery("SELECT o FROM ArtificialGroup o where o.argrStatus = true and o.argrEubacteria=true");
			List<ArtificialGroup> result=(List<ArtificialGroup>)query.getResultList();
			if(result.size()>0)
				return result;
			
		}catch(NoResultException e)
		{
			return new ArrayList<ArtificialGroup>();
		}
		return new ArrayList<ArtificialGroup>();
	}
	
	/**
	 * Recuperar lista de grupos reino arqueobacteria
	 * @return
	 */
	
	@SuppressWarnings("unchecked")
	public List<ArtificialGroup> findGroupsArqueobacteria()
	{
		
		try
		{
			Query query = super.getEntityManager().createQuery("SELECT o FROM ArtificialGroup o where o.argrStatus = true and o.argrArcheobacteria=true order by argrName");
			List<ArtificialGroup> result=(List<ArtificialGroup>)query.getResultList();
			if(result.size()>0)
				return result;
			
		}catch(NoResultException e)
		{
			return new ArrayList<ArtificialGroup>();
		}
		return new ArrayList<ArtificialGroup>();
	}
	
	/**
	 * Recuperar lista de grupos reino protista
	 * @return
	 */
	
	@SuppressWarnings("unchecked")
	public List<ArtificialGroup> findGroupsProtista()
	{
		
		try
		{
			Query query = super.getEntityManager().createQuery("SELECT o FROM ArtificialGroup o where o.argrStatus = true and o.argrProtista=true order by argrName");
			List<ArtificialGroup> result=(List<ArtificialGroup>)query.getResultList();
			if(result.size()>0)
				return result;
			
		}catch(NoResultException e)
		{
			return new ArrayList<ArtificialGroup>();
		}
		return new ArrayList<ArtificialGroup>();
	}
	
	
	/**
	 * Recuperar lista de grupos reino chromista
	 * @return
	 */
	
	@SuppressWarnings("unchecked")
	public List<ArtificialGroup> findGroupsChromista()
	{
		
		try
		{
			Query query = super.getEntityManager().createQuery("SELECT o FROM ArtificialGroup o where o.argrStatus = true and o.argrChromista=true order by argrName");
			List<ArtificialGroup> result=(List<ArtificialGroup>)query.getResultList();
			if(result.size()>0)
				return result;
			
		}catch(NoResultException e)
		{
			return new ArrayList<ArtificialGroup>();
		}
		return new ArrayList<ArtificialGroup>();
	}
	
	/**
	 * Recuperar lista de grupos reino viruses
	 * @return
	 */
	
	@SuppressWarnings("unchecked")
	public List<ArtificialGroup> findGroupsViruses()
	{
		
		try
		{
			Query query = super.getEntityManager().createQuery("SELECT o FROM ArtificialGroup o where o.argrStatus = true and o.argrViruses=true order by argrName");
			List<ArtificialGroup> result=(List<ArtificialGroup>)query.getResultList();
			if(result.size()>0)
				return result;
			
		}catch(NoResultException e)
		{
			return new ArrayList<ArtificialGroup>();
		}
		return new ArrayList<ArtificialGroup>();
	}

	@SuppressWarnings("unchecked")
	public ArtificialGroup getGroupByNameAndCode(String name, String code){
		try{
			Query query = super.getEntityManager().createQuery(
					"SELECT o FROM ArtificialGroup o where o.argrStatus = true and o.argrName=:name and o.argrCode=:code");
			query.setParameter("name",name);
			query.setParameter("code",code);
			List<ArtificialGroup> result=(List<ArtificialGroup>)query.getResultList();
			if(result!=null && !result.isEmpty()){
				return result.get(0);
			}else{
				return new ArtificialGroup();
			}
		}catch (NoResultException e){
			return new ArtificialGroup();
		}
	}
	
	/**
	 * Obtener lista de grupos artificiales activos
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<ArtificialGroup> findArtificialGroups()
	{
		
		try
		{
			Query query = super.getEntityManager().createQuery("SELECT o FROM ArtificialGroup o where o.argrStatus = true order by argrName");
			List<ArtificialGroup> result=(List<ArtificialGroup>)query.getResultList();
			if(!result.isEmpty())
			{
				for (ArtificialGroup item : result) {
					StringBuilder concatenacion = new StringBuilder();
					String reinos="";
					concatenacion.append("");
					if (item.getArgrAnimal() != null) {
						if (item.getArgrAnimal()) {
							concatenacion.append("AN,");
						}

					} 
					
					if (item.getArgrPlantae() != null) {
						if (item.getArgrPlantae()) {
							concatenacion.append("PL,");
						}

					} 
					
					if (item.getArgrChromista() != null) {
						if (item.getArgrChromista()) {
							concatenacion.append("CH,");
						}

					} 
					
					if (item.getArgrEubacteria() != null) {
						if (item.getArgrEubacteria()) {
							concatenacion.append("BA,");
						}

					} 
					if (item.getArgrArcheobacteria() != null) {
						if (item.getArgrArcheobacteria()) {
							concatenacion.append("AR,");
						}

					} 
					if (item.getArgrProtista() != null) {
						if (item.getArgrProtista()) {
							concatenacion.append("PR,");
						}

					} 
					if (item.getArgrFungi() != null) {
						if (item.getArgrFungi()) {
							concatenacion.append("FU,");
						}

					} 
					if (item.getArgrViruses() != null) {
						if (item.getArgrViruses()) {
							concatenacion.append("VI,");
						}

					}
					if(!concatenacion.toString().equals(""))
					{
					reinos= concatenacion.toString().substring(0, concatenacion.toString().length()-1);
					}
					item.setReinos(reinos);
				}
				return result;
			}
			
		}catch(NoResultException e)
		{
			return new ArrayList<ArtificialGroup>();
		}
		return new ArrayList<ArtificialGroup>();
	}
	
	/**
	 * Obtener registros de especies que tengan asignado el grupo artificial
	 * @param idArtificialGroup
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<Object[]> getSpeciesTaxaByArtificialGroup(Integer idArtificialGroup) {
		List<Object[]> result = new ArrayList<>();
		try {
			Query q;
			q = getEntityManager().createNativeQuery(
					"select stag_id, argr_id from biodiversity.species_taxa_artificial_groups where argr_id = ?1 and stag_status = true");
			q.setParameter(1, idArtificialGroup);
			result = (List<Object[]>) q.getResultList();
			if (q.getResultList() == null) {
				result = new ArrayList<Object[]>();
			}
		} catch (Exception ex) {
			return new ArrayList<Object[]>();
		}
		return result;
	}
	
	/**
	 * Guardar un grupo artificial
	 * @param artificialGroup
	 * @param user
	 * @return
	 */
	public ArtificialGroup save(ArtificialGroup artificialGroup, User user) {
		ArtificialGroup artificial = null;
		try {
			if (artificialGroup.getArgrId() == null) {

				artificialGroup.setArgrStatus(true);
				artificialGroup.setArgrDateCreate(new Date());
				artificialGroup.setArgrUserCreate(user.getUserName());
				artificial = create(artificialGroup);

			} else {
				artificialGroup.setArgrDateUpdate(new Date());
				artificialGroup.setArgrUserUpdate(user.getUserName());
				artificial = edit(artificialGroup);
			}

			return artificial;
		} catch (Exception ex) {
			return artificial;
		}
	}
	
	/**
	 * Buscar un grupo artificial por nombre
	 * @param name
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<ArtificialGroup> findArtificialGroupByName(String name) {
		try {
			Query query = super.getEntityManager()
					.createQuery("SELECT o FROM ArtificialGroup o where o.argrStatus = true and UPPER(o.argrName) = :name");
			query.setParameter("name", name.toUpperCase());
			List<ArtificialGroup> result = (List<ArtificialGroup>) query.getResultList();
			if (!result.isEmpty()) {
				
				return result;
			}

		} catch (NoResultException e) {
			return new ArrayList<ArtificialGroup>();
		}
		return new ArrayList<ArtificialGroup>();
	}
	
	/**
	 * Buscar un grupo artificial por codigo
	 * @param code
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<ArtificialGroup> findArtificialGroupByCode(String code) {
		try {
			Query query = super.getEntityManager()
					.createQuery("SELECT o FROM ArtificialGroup o where o.argrStatus = true and UPPER(o.argrCode) = :code");
			query.setParameter("code", code.toUpperCase());
			List<ArtificialGroup> result = (List<ArtificialGroup>) query.getResultList();
			if (!result.isEmpty()) {
				
				return result;
			}

		} catch (NoResultException e) {
			return new ArrayList<ArtificialGroup>();
		}
		return new ArrayList<ArtificialGroup>();
	}
}

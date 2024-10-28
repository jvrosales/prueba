package ec.gob.ambiente.enlisy.services;

import java.io.Serializable;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.persistence.NoResultException;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import ec.gob.ambiente.alfresco.service.AlfrescoServiceBean;
import ec.gob.ambiente.enlisy.dao.AbstractFacade;
import ec.gob.ambiente.enlisy.model.CatalogoPatentes;
import ec.gob.ambiente.enlisy.model.PatentRequest;
import ec.gob.ambiente.exceptions.CmisAlfrescoException;
import ec.gob.ambiente.util.Constant;

@Stateless
public class PatentCatalogFacade extends AbstractFacade<CatalogoPatentes, Integer> implements Serializable {
	
	private static final long serialVersionUID = -4594424897085245484L;

	@EJB(lookup = Constant.ALFRESCO_SERVICE_BEAN)
	private AlfrescoServiceBean alfrescoServiceBean;
	
	public PatentCatalogFacade() {
		super(CatalogoPatentes.class, Integer.class);		
	}
	
	/**
	 * Actualizar datos de perfil de usuario
	 * @param user
	 */

	@SuppressWarnings("unchecked")
	public List<CatalogoPatentes> findLikeCode(String ctpaName)
	{
		try {
			Query query = super.getEntityManager().createQuery("select o from CatalogoPatentes o where o.ctpaName like :ctpaName and o.ctpaStatus = true order by 1");
			query.setParameter("ctpaName", ctpaName);
			List<CatalogoPatentes> catalogs = (List<CatalogoPatentes>) query.getResultList();	
			
			if(catalogs.size() > 0)
			{					
				return catalogs;
			}
		}catch (NoResultException e) {
			e.printStackTrace();
		}
				
		return null;
		
	}
	
	public CatalogoPatentes findByParent(String name) {
		TypedQuery<CatalogoPatentes> query = super.getEntityManager().createQuery(
				"select u from CatalogoPatentes u " + "where u.ctpaName= :name ", CatalogoPatentes.class);
		query.setParameter("name", name);
		return query.getSingleResult();
	}
	
	public CatalogoPatentes findByParent1(int padre,String name) {
		TypedQuery<CatalogoPatentes> query = super.getEntityManager().createQuery(
				"select u from CatalogoPatentes u where u.ctpaName= :name and u.parentId = :padre ", CatalogoPatentes.class);
		query.setParameter("name", name);
		query.setParameter("padre", padre);
		return query.getSingleResult();
	}
	
	public CatalogoPatentes findByParentZone(int id) {
		TypedQuery<CatalogoPatentes> query = super.getEntityManager().createQuery(
				"select u from CatalogoPatentes u " + "where u.id= :id ", CatalogoPatentes.class);
		query.setParameter("id", id);
		return query.getSingleResult();
	}
	
	@SuppressWarnings("unchecked")
	public List<CatalogoPatentes> findLikeParent(Integer id)
	{
		try {
			Query query = super.getEntityManager().createQuery("select o from CatalogoPatentes o where o.parentId= :parentId and o.ctpaStatus = true order by 1");
			query.setParameter("parentId", id);
						
			List<CatalogoPatentes> catalogs = (List<CatalogoPatentes>) query.getResultList();	
			
			if(catalogs.size() > 0)
			{					
				return catalogs;
			}
		}catch (NoResultException e) {
			e.printStackTrace();
		}
				
		return null;
		
	}
	
	@SuppressWarnings("unchecked")
	public List<CatalogoPatentes> findLikeParentAnimal(Integer id)
	{
		try {
			Query query = super.getEntityManager().createQuery("select o from CatalogoPatentes o where o.parentId= :parentId and id in (55,56,57,58,59,60,61,62,63,65,66,67,68,69,70,71,72,73) and o.ctpaStatus = true order by 1");
			query.setParameter("parentId", id);
						
			List<CatalogoPatentes> catalogs = (List<CatalogoPatentes>) query.getResultList();	
			
			if(catalogs.size() > 0)
			{					
				return catalogs;
			}
		}catch (NoResultException e) {
			e.printStackTrace();
		}
				
		return null;
		
	}
	
	@SuppressWarnings("unchecked")
	public List<CatalogoPatentes> findLikeParentPlantae(Integer id)
	{
		try {
			Query query = super.getEntityManager().createQuery("select o from CatalogoPatentes o where o.parentId= :parentId and id in (75,76,77,78,63,68,71,119) and o.ctpaStatus = true order by 1 desc");
			query.setParameter("parentId", id);
						
			List<CatalogoPatentes> catalogs = (List<CatalogoPatentes>) query.getResultList();	
			
			if(catalogs.size() > 0)
			{					
				return catalogs;
			}
		}catch (NoResultException e) {
			e.printStackTrace();
		}
				
		return null;
		
	}
	
	public byte[] descargarDocumentoPorId(String id) {
		try {
			return alfrescoServiceBean.downloadDocumentById(id);
		} catch (CmisAlfrescoException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	public CatalogoPatentes findByDescription(String ctpaName, String code)
	{
		try {
			Query query = super.getEntityManager().createQuery("select o from CatalogoPatentes o where o.ctpaName = :ctpaName and o.ctpaStatus = true");
			query.setParameter("ctpaName", ctpaName);
			if(query.getResultList().size() > 0)
			{
				CatalogoPatentes catalog = (CatalogoPatentes) query.getResultList().get(0);	
				return catalog;
			}
		} catch (NoResultException e) {
			e.printStackTrace();
		}
				
		return null;
	}

	public List<CatalogoPatentes> findByWildlifeManagementCenter(PatentRequest centroTenenciaManejo) {
		try
		{
			Query query = super.getEntityManager().createQuery("SELECT o FROM CatalogoPatentes o where o.patentInformation.id= :id and o.corsStatus = true");
			query.setParameter("id", centroTenenciaManejo.getId());
			@SuppressWarnings("unchecked")
			List<CatalogoPatentes> result=(List<CatalogoPatentes>)query.getResultList();
			if(result.size()>0)
				return result;
			
		}catch(NoResultException e)
		{
			return null;
		}
		return null;
	}
	@SuppressWarnings("unchecked")
	public CatalogoPatentes findByTypeByPatentApplication(String ctpaName) {
		try {
			Query query = super
					.getEntityManager()
					.createQuery( "SELECT p FROM CatalogoPatentes p where  p.ctpaName= :ctpaName and p.ctpaStatus= true order by 1 desc", CatalogoPatentes.class);					
			query.setParameter("ctpaName", ctpaName);
			List<CatalogoPatentes> result= (List<CatalogoPatentes>) query.getResultList();
			if(result.size()>0)
			{
				CatalogoPatentes ret=result.get(0);				
				ret.setContent(descargarDocumentoPorId(ret.getCtpaUrl()));
				return ret;
			}
				
		}catch(NoResultException e)
		{
			return null;
		}
		return null;			
	}
	
	
	
	
	
}

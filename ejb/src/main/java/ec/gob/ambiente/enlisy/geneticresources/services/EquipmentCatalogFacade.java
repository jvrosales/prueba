package ec.gob.ambiente.enlisy.geneticresources.services;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.NoResultException;
import javax.persistence.Query;

import ec.gob.ambiente.enlisy.dao.AbstractFacade;
import ec.gob.ambiente.enlisy.geneticresources.model.EquipmentCatalog;
import ec.gob.ambiente.enlisy.model.User;

@Stateless
public class EquipmentCatalogFacade extends AbstractFacade<EquipmentCatalog, Integer> implements Serializable{	

	private static final long serialVersionUID = 8852726480903658111L;
	public EquipmentCatalogFacade() {
		super(EquipmentCatalog.class, Integer.class);		
	}
	
	public EquipmentCatalog findById(Integer id)
	{
		try {
			Query query = super.getEntityManager().createQuery("select o from EquipmentCatalog o where o.eqcaId = :id and o.eqcaStatus = true");
			query.setParameter("id", id);
			
			return (EquipmentCatalog) query.getSingleResult();	
		} catch (NoResultException e) {
			return null;
		}
		
	}
	
	public EquipmentCatalog findByDescription(String description)
	{
		try {
			Query query = super.getEntityManager().createQuery("select o from EquipmentCatalog o where upper(o.eqcaDescription) = :description and o.eqcaStatus = true");
			query.setParameter("description", description.toUpperCase());
			return (EquipmentCatalog) query.getSingleResult();	
		} catch (NoResultException e) {
			return null;
		}
		
	}
	
	@SuppressWarnings("unchecked")
	public List<EquipmentCatalog> findLikeDescription(String description)
	{
		try {
			Query query = super.getEntityManager().createQuery("select o from EquipmentCatalog o where upper(o.eqcaDescription) like :description and o.eqcaStatus = true");
			query.setParameter("description", description.toUpperCase());
			List<EquipmentCatalog> result=(List<EquipmentCatalog>)query.getResultList();
			if(result.size() > 0)					
				return result;
		} catch (NoResultException e) {
			return null;
		}
		return null;
	}
	
	
	/**
	 * Guardar  Equipos 
	 * @param EquipmentCatalog
	 * @param user
	 * @return
	 */
	public boolean save(EquipmentCatalog equipmentCatalog, User user)
	{
		try
		{			
			//Guarda Siempre en mayusculas
			equipmentCatalog.setEqcaDescription(equipmentCatalog.getEqcaDescription().toUpperCase());
			
			if(equipmentCatalog.getEqcaId() == null)
			{	
				
				equipmentCatalog.setEqcaStatus(true);
				equipmentCatalog.setEqcaDateCreate(new Date());
				equipmentCatalog.setEqcaUserCreate(user.getUserName());
				create(equipmentCatalog);
				return false;
				
			}
			/*else
			{
				equipmentCatalog.setEqcaDateUpdate(new Date());	
				equipmentCatalog.setEqcaUserUpdate(user.getUserName());
				edit(equipmentCatalog);
			}*/
			
			return false;
		}
		catch(Exception ex)
		{
			return false;
		}
	}
	
	/*public void delete(EquipmentCatalog equipmentCatalog, User user){
		equipmentCatalog.setEqcaStatus(false);
		equipmentCatalog.setEqcaDateUpdate(new Date());	
		equipmentCatalog.setEqcaUserUpdate(user.getUserName());
		edit(equipmentCatalog);
	}*/
	
}
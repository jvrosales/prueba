package ec.gob.ambiente.enlisy.geneticresources.services;

import java.util.Date;
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.NoResultException;
import javax.persistence.Query;

import ec.gob.ambiente.enlisy.dao.AbstractFacade;
import ec.gob.ambiente.enlisy.geneticresources.model.ExportAuthorization;
import ec.gob.ambiente.enlisy.geneticresources.model.ProposedCollection;
import ec.gob.ambiente.enlisy.geneticresources.model.GeneticResourcesCatalog;
import ec.gob.ambiente.enlisy.geneticresources.model.ProposedCollectionData;
import ec.gob.ambiente.enlisy.model.User;

@Stateless
public class ProposedCollectionDataFacade extends AbstractFacade<ProposedCollectionData, Integer>{

	public ProposedCollectionDataFacade() {
		super(ProposedCollectionData.class, Integer.class);
	}
		
	/**
	 * Obtener Datos de la Propuesta de recoleccion por id
	 * @param id
	 * @return
	 */
	public ProposedCollectionData findById(Integer id)
	{
		try
		{
			Query query = super.getEntityManager().createQuery("SELECT o FROM ProposedCollectionData o where o.prcdId= :id and o.prcdStatus = true");
			query.setParameter("id", id);
			
			return (ProposedCollectionData) query.getSingleResult();
			
		}catch(NoResultException e)
		{
			return null;
		}
	}
	
	/**
	 * Obtener Datos Propuesta de recoleccion por id
	 * @param id
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<ProposedCollectionData> findByProposedCollection(ProposedCollection proposedCollection)
	{
		try
		{
			Query query = super.getEntityManager().createQuery("SELECT o FROM ProposedCollectionData o where o.proposedCollection.prcoId= :id and o.prcdStatus = true order by o.prcdId desc ");
			query.setParameter("id", proposedCollection.getPrcoId());
			
			List<ProposedCollectionData> result=(List<ProposedCollectionData>)query.getResultList();
			if(result.size()>0)
				return result;
			
		}catch(NoResultException e)
		{
			return null;
		}
		return null;
	}
	
	/**
	 * Obtener Datos Propuesta de recoleccion por id
	 * @param id
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<ProposedCollectionData> findByProposedCollectionAndType(ProposedCollection proposedCollection, GeneticResourcesCatalog geneticResourcesCatalog)
	{
		try
		{
			Query query = super.getEntityManager().createQuery("SELECT o FROM ProposedCollectionData o where o.proposedCollection.prcoId= :id and o.typeInformation.grcaId= :tipo and o.prcdStatus = true");
			query.setParameter("id", proposedCollection.getPrcoId());
			query.setParameter("tipo", geneticResourcesCatalog.getGrcaId());
			
			List<ProposedCollectionData> result=(List<ProposedCollectionData>)query.getResultList();
			if(result.size()>0)
				return result;
			
		}catch(NoResultException e)
		{
			return null;
		}
		return null;
	}
	
	/**
	 * Guardar Datos Propuesta de recoleccion
	 * @param ProposedCollectionData
	 * @param user
	 * @return
	 */
	public boolean save(ProposedCollectionData ProposedCollectionData, User user)
	{
		try
		{			
			if(ProposedCollectionData.getPrcdId() == null)
			{	
				
				ProposedCollectionData.setPrcdStatus(true);
				ProposedCollectionData.setPrcdDateCreate(new Date());
				ProposedCollectionData.setPrcdUserCreate(user.getUserName());
				create(ProposedCollectionData);
				
			}
			else
			{
				ProposedCollectionData.setPrcdDateUpdate(new Date());	
				ProposedCollectionData.setPrcdUserUpdate(user.getUserName());
				edit(ProposedCollectionData);
			}
			
			
			
			
			
			return true;
		}
		catch(Exception ex)
		{
			return false;
		}
	}
	
	public void delete(ProposedCollectionData ProposedCollectionData, User user){
		ProposedCollectionData.setPrcdStatus(false);
		ProposedCollectionData.setPrcdDateUpdate(new Date());	
		ProposedCollectionData.setPrcdUserUpdate(user.getUserName());
		edit(ProposedCollectionData);
	}

	/**
	 * Obtener negativa de autorizacion por id propuesta y autorizacion de exportacion
	 * @param id
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<ProposedCollectionData> findByTypeByProposedByAuthorization(int typeInformation, ProposedCollection proposedCollection, ExportAuthorization exportAuthorization) {
		try {
			Query query = super.getEntityManager()
					.createQuery(
							"SELECT o FROM ProposedCollectionData o where o.proposedCollection.prcoId= :id and "
							+ "o.exportAuthorization.exauId= :idAuthorization and "
							+ "o.typeInformation.grcaId = :typeInformation and "
							+ "o.prcdStatus = true");
			query.setParameter("id", proposedCollection.getPrcoId());
			query.setParameter("idAuthorization", exportAuthorization.getExauId());
			query.setParameter("typeInformation", typeInformation);

			List<ProposedCollectionData> result = (List<ProposedCollectionData>) query
					.getResultList();
			if (result.size() > 0)
				return result;

		} catch (NoResultException e) {
			return null;
		}
		return null;
	}
	
	/**
	 * Obtener Datos Propuesta de recoleccion por id propuesta y numero de tramite de autorizacion de exportacion
	 * @param id
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<ProposedCollectionData> findByProposedByCodeAuthorization(ProposedCollection proposedCollection, ExportAuthorization exportAuthorization, Integer type) {
		try {
			Query query = super.getEntityManager()
					.createQuery(
							"SELECT o FROM ProposedCollectionData o where o.proposedCollection.prcoId= :id and "
							+ "o.exportAuthorization.exauApplicationCode= :codeAuthorization and o.prcdStatus = true and "
							+ "o.typeInformation.grcaId= :type");
			query.setParameter("id", proposedCollection.getPrcoId());
			query.setParameter("codeAuthorization", exportAuthorization.getExauApplicationCode());
			query.setParameter("type", type);

			List<ProposedCollectionData> result = (List<ProposedCollectionData>) query
					.getResultList();
			if (result.size() > 0)
				return result;

		} catch (NoResultException e) {
			return null;
		}
		return null;
	}
}

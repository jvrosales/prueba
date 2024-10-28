package ec.gob.ambiente.enlisy.geneticresources.services;

import java.util.Date;
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.NoResultException;
import javax.persistence.Query;

import ec.gob.ambiente.enlisy.dao.AbstractFacade;
import ec.gob.ambiente.enlisy.geneticresources.model.ExportAuthorization;
import ec.gob.ambiente.enlisy.geneticresources.model.ExportAuthorizationSpecies;
import ec.gob.ambiente.enlisy.geneticresources.model.ProposedCollectionSpecies;
import ec.gob.ambiente.enlisy.geneticresources.model.UnidentifiedSpecies;
import ec.gob.ambiente.enlisy.model.User;

@Stateless
public class ExportAuthorizationSpeciesFacade extends AbstractFacade<ExportAuthorizationSpecies, Integer>{
	public ExportAuthorizationSpeciesFacade() {
		super(ExportAuthorizationSpecies.class, Integer.class);		
	}
	
	/**
	 * Obtener listado de especies por autorizacion de exportacion
	 * @param idAuthorization
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<ExportAuthorizationSpecies> findSpeciesByAuthorization(int idAuthorization)
	{
		try
		{
			Query query = super.getEntityManager().createQuery("SELECT o FROM ExportAuthorizationSpecies o "
					+ "where o.exportAuthorization.exauId= :id and o.exasStatus = true "
					+ "order by  exasId asc");
			query.setParameter("id", idAuthorization);
			
			List<ExportAuthorizationSpecies> result=(List<ExportAuthorizationSpecies>)query.getResultList();
			if (result != null && !result.isEmpty())
				return result;
			
		}catch(NoResultException e)
		{
			return null;
		}
		return null;
	}
	
	/**
	 * Obtener ultima especie autorizada por autorizacion de exportacion
	 * @param id
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public ExportAuthorizationSpecies findLastSpecieByAuthorization(ExportAuthorization authorization)
	{
		try
		{
			Query query = super.getEntityManager().createQuery("SELECT o FROM ExportAuthorizationSpecies o "
					+ "where o.exportAuthorization.exauId= :id and o.exasStatus = true "
					+ "order by  exasId desc");
			query.setParameter("id", authorization.getExauId());
			
			List<ExportAuthorizationSpecies> result=(List<ExportAuthorizationSpecies>)query.getResultList();
			if (result != null && !result.isEmpty())
				return result.get(0);
			
		}catch(NoResultException e)
		{
			return null;
		}
		return null;
	}
	
	/**
	 * Obtener primer registro de especie por tramite de autorizacion y especie de propuesta
	 * @param id
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public ExportAuthorizationSpecies findSpecieBySpecieByAuthorization(ProposedCollectionSpecies specie,	ExportAuthorization authorization) {
		try {
			Query query = super.getEntityManager().createQuery(
					"SELECT o FROM ExportAuthorizationSpecies o " + "where o.exportAuthorization.exauApplicationCode= :code "
							+ "and o.proposedCollectionSpecies.prcsId= :idSpecie  and o.exasStatus = false and o.exasIsHistorical = true  "
							+ "order by exasId asc");
			query.setParameter("code", authorization.getExauApplicationCode());
			query.setParameter("idSpecie", specie.getPrcsId());

			List<ExportAuthorizationSpecies> result = (List<ExportAuthorizationSpecies>) query
					.getResultList();
			if (result != null && !result.isEmpty())
				return result.get(0);

		} catch (NoResultException e) {
			return null;
		}
		return null;
	}
	
	/**
	 * Obtener primer registro de especie por tramite de autorizacion y especie no identificada
	 * @param id
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public ExportAuthorizationSpecies findSpecieByUnidentifiedSpecieByInitAuthorization(UnidentifiedSpecies specie,	ExportAuthorization authorization) {
		try {
			Query query = super.getEntityManager().createQuery(
					"SELECT o FROM ExportAuthorizationSpecies o " + "where o.exportAuthorization.exauId= :idAuthorization "
							+ "and o.unidentifiedSpecies.unspId= :idSpecie  and o.exasStatus = false "
							+ "and o.exasIsHistorical = true "
							+ "order by exasId asc");
			query.setParameter("idAuthorization", authorization.getExauId());
			query.setParameter("idSpecie", specie.getUnspIdInitialSpecie());

			List<ExportAuthorizationSpecies> result = (List<ExportAuthorizationSpecies>) query
					.getResultList();
			if (result != null && !result.isEmpty())
				return result.get(0);

		} catch (NoResultException e) {
			return null;
		}
		return null;
	}
	
	/**
	 * Guardar especie de la autorizacion
	 * @param exportAuthorization
	 * @param user
	 */
	public boolean save(ExportAuthorizationSpecies exportAuthorizationSpecies, User user) {
		try {
			if (exportAuthorizationSpecies.getExasId() == null) {

				exportAuthorizationSpecies.setExasStatus(true);
				exportAuthorizationSpecies.setExasIsHistorical(false);
				exportAuthorizationSpecies.setExasDateCreate(new Date());
				exportAuthorizationSpecies.setExasUserCreate(user.getUserName());
				create(exportAuthorizationSpecies);

			} else {
				exportAuthorizationSpecies.setExasDateUpdate(new Date());
				exportAuthorizationSpecies.setExasUserUpdate(user.getUserName());
				edit(exportAuthorizationSpecies);
			}

			return true;
		} catch (Exception ex) {
			return false;
		}
	}
}

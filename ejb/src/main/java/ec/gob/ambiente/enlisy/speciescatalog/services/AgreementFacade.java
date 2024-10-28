package ec.gob.ambiente.enlisy.speciescatalog.services;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.NoResultException;
import javax.persistence.Query;

import ec.gob.ambiente.enlisy.dao.AbstractFacade;
import ec.gob.ambiente.enlisy.model.User;
import ec.gob.ambiente.enlisy.speciescatalog.model.Agreement;


/**
 * Servicios para la administracion de convenios
 * @author EXCO
 *
 */
@Stateless
public class AgreementFacade extends AbstractFacade<Agreement, Integer>{

	public AgreementFacade() {
		super(Agreement.class, Integer.class);		
	}
	
		
	/**
	 * Obtener lista de convenios activos
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<Agreement> findAgreements()
	{
		
		try
		{
			Query query = super.getEntityManager().createQuery("SELECT o FROM Agreement o where o.agreStatus = true order by o.agreName");
			List<Agreement> result=(List<Agreement>)query.getResultList();
			if(!result.isEmpty())
			{
				
				return result;
			}
			
		}catch(NoResultException e)
		{
			return new ArrayList<Agreement>();
		}
		return new ArrayList<Agreement>();
	}
	
	/**
	 * Obtener registros de especies que tengan asignado el convenio
	 * @param idArtificialGroup
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<Object[]> getSpeciesTaxaByAgreement(Integer idAgreement) {
		List<Object[]> result = new ArrayList<>();
		try {
			Query q;
			q = getEntityManager().createNativeQuery(
					"select sagr_id, agre_id from biodiversity.species_taxa_agreements where agre_id = ?1 and sagr_status = true");
			q.setParameter(1, idAgreement);
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
	 * Guardar un convenio
	 * @param artificialGroup
	 * @param user
	 * @return
	 */
	public Agreement save(Agreement agreement, User user) {
		Agreement agre = null;
		try {
			if (agreement.getAgreId() == null) {

				agreement.setAgreStatus(true);
				agreement.setAgreDateCreate(new Date());
				agreement.setAgreUserCreate(user.getUserName());
				agre = create(agreement);

			} else {
				agreement.setAgreDateUpdate(new Date());
				agreement.setAgreUserUpdate(user.getUserName());
				agre = edit(agreement);
			}

			return agre;
		} catch (Exception ex) {
			return agre;
		}
	}
	
	/**
	 * Buscar un convenio por nombre
	 * @param name
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<Agreement> findAgreementByName(String name) {
		try {
			Query query = super.getEntityManager()
					.createQuery("SELECT o FROM Agreement o where o.agreStatus = true and UPPER(o.agreName) = :name");
			query.setParameter("name", name.toUpperCase());
			List<Agreement> result = (List<Agreement>) query.getResultList();
			if (!result.isEmpty()) {
				
				return result;
			}

		} catch (NoResultException e) {
			return new ArrayList<Agreement>();
		}
		return new ArrayList<Agreement>();
	}
}

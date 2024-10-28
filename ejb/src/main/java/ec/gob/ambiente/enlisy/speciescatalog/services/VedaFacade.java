package ec.gob.ambiente.enlisy.speciescatalog.services;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.NoResultException;
import javax.persistence.Query;

import ec.gob.ambiente.enlisy.dao.AbstractFacade;
import ec.gob.ambiente.enlisy.model.User;
import ec.gob.ambiente.enlisy.speciescatalog.model.Veda;


/**
 * Servicios para la administracion de vedas
 * @author EXCO
 *
 */
@Stateless
public class VedaFacade extends AbstractFacade<Veda, Integer>{

	public VedaFacade() {
		super(Veda.class, Integer.class);		
	}
	
		
	/**
	 * Obtener lista de convenios activos
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<Veda> findVedas()
	{
		
		try
		{
			Query query = super.getEntityManager().createQuery("SELECT o FROM Veda o where o.vedaStatus = true order by o.vedaName");
			List<Veda> result=(List<Veda>)query.getResultList();
			if(!result.isEmpty())
			{
				
				return result;
			}
			
		}catch(NoResultException e)
		{
			return new ArrayList<Veda>();
		}
		return new ArrayList<Veda>();
	}
	
	/**
	 * Obtener registros de especies que tengan asignada la veda
	 * @param idArtificialGroup
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<Object[]> getSpeciesTaxaByVeda(Integer idAVeda) {
		List<Object[]> result = new ArrayList<>();
		try {
			Query q;
			q = getEntityManager().createNativeQuery(
					"select spve_id, veda_id from biodiversity.species_taxa_vedas where veda_id = ?1 and spve_status = true");
			q.setParameter(1, idAVeda);
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
	 * Guardar una veda
	 * @param veda
	 * @param user
	 * @return
	 */
	public Veda save(Veda veda, User user) {
		Veda ved = null;
		try {
			if (veda.getVedaId() == null) {

				veda.setVedaStatus(true);
				veda.setVedaDateCreate(new Date());
				veda.setVedaUserCreate(user.getUserName());
				ved = create(veda);

			} else {
				veda.setVedaDateUpdate(new Date());
				veda.setVedaUserUpdate(user.getUserName());
				ved = edit(veda);
			}

			return ved;
		} catch (Exception ex) {
			return ved;
		}
	}
	
	/**
	 * Buscar una veda por nombre
	 * @param name
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<Veda> findVedaByName(String name) {
		try {
			Query query = super.getEntityManager()
					.createQuery("SELECT o FROM Veda o where o.vedaStatus = true and UPPER(o.vedaName) = :name");
			query.setParameter("name", name.toUpperCase());
			List<Veda> result = (List<Veda>) query.getResultList();
			if (!result.isEmpty()) {
				
				return result;
			}

		} catch (NoResultException e) {
			return new ArrayList<Veda>();
		}
		return new ArrayList<Veda>();
	}
}

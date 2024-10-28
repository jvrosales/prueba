package ec.gob.ambiente.enlisy.speciescatalog.services;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.Query;

import ec.gob.ambiente.enlisy.dao.AbstractFacade;
import ec.gob.ambiente.enlisy.model.User;
import ec.gob.ambiente.enlisy.speciescatalog.model.DispersionRoute;




/**
 * Servicios para la administracion de la
	dispersion de una especie exotica
 * @author EXCO
 *
 */
@Stateless
public class DispersionRouteFacade extends AbstractFacade<DispersionRoute, Integer>{

	public DispersionRouteFacade() {
		super(DispersionRoute.class, Integer.class);		
	}

	/**
	 * Guardar especie-dispersion route
	 * @param DispersionRoute
	 * @param user
	 * @return
	 */
	
	
	public DispersionRoute save(DispersionRoute dispersionRoute, User user)
	{
		DispersionRoute dispersionRouteReturn=null;
		try
		{			
			if(dispersionRoute.getDiroId() == null)
			{				
				
				dispersionRoute.setDiroStatus(true);
				dispersionRoute.setDiroDateCreate(new Date());
				dispersionRoute.setDiroUserCreate(user.getUserName());
				dispersionRouteReturn=create(dispersionRoute);
				
			}
			else
			{
				dispersionRoute.setDiroDateUpdate(new Date());
				dispersionRoute.setDiroUserUpdate(user.getUserName());
				dispersionRouteReturn=edit(dispersionRoute);
			}
					
			
			return dispersionRouteReturn;
		}
		catch(Exception ex)
		{
			ex.printStackTrace();
			return dispersionRouteReturn;
		}
	}
	
	/**
	 * Listar rutas de dispersion de una especie
	 * @param idSpecie
	 * @return
	 */
	
	@SuppressWarnings("unchecked")
	public List<DispersionRoute> getDispersionsBySumary(Integer idSummary) {
		
		List<DispersionRoute> result=new ArrayList<DispersionRoute>();
		try
		{
			Query query = super.getEntityManager().createQuery("select o from DispersionRoute o where o.summaryExotic.seasId = :elCodigo and o.diroStatus = true");
			query.setParameter("elCodigo", idSummary);
			result=(List<DispersionRoute>)query.getResultList();
			
		}
		catch(Exception ex)
		{
			ex.printStackTrace();
			return new ArrayList<DispersionRoute>();
			
			
		}
		return result;
		}
	
	
	
		
	
}

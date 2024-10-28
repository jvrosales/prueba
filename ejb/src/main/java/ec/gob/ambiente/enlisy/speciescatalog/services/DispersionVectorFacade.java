package ec.gob.ambiente.enlisy.speciescatalog.services;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.Query;

import ec.gob.ambiente.enlisy.dao.AbstractFacade;
import ec.gob.ambiente.enlisy.model.User;
import ec.gob.ambiente.enlisy.speciescatalog.model.DispersionVector;




/**
 * Servicios para la administracion de la
	vectores de dispersion de una especie exotica
 * @author EXCO
 *
 */
@Stateless
public class DispersionVectorFacade extends AbstractFacade<DispersionVector, Integer>{

	public DispersionVectorFacade() {
		super(DispersionVector.class, Integer.class);		
	}

	/**
	 * Guardar especie-dispersion vector
	 * @param DispersionVector
	 * @param user
	 * @return
	 */
	
	
	public DispersionVector save(DispersionVector dispersionVector, User user)
	{
		DispersionVector dispersionVectorReturn=null;
		try
		{			
			if(dispersionVector.getDiveId() == null)
			{				
				
				dispersionVector.setDiveStatus(true);
				dispersionVector.setDiveDateCreate(new Date());
				dispersionVector.setDiveUserCreate(user.getUserName());
				dispersionVectorReturn=create(dispersionVector);
				
			}
			else
			{
				dispersionVector.setDiveDateUpdate(new Date());
				dispersionVector.setDiveUserUpdate(user.getUserName());
				dispersionVectorReturn=edit(dispersionVector);
			}
					
			
			return dispersionVectorReturn;
		}
		catch(Exception ex)
		{
			ex.printStackTrace();
			return dispersionVectorReturn;
		}
	}
	
	/**
	 * Listar vectores de dispersion de una especie
	 * @param idSpecie
	 * @return
	 */
	
	@SuppressWarnings("unchecked")
	public List<DispersionVector> getVectorsBySumary(Integer idSummary) {
		
		List<DispersionVector> result=new ArrayList<DispersionVector>();
		try
		{
			Query query = super.getEntityManager().createQuery("select o from DispersionVector o where o.summaryExotic.seasId = :elCodigo and o.diveStatus = true");
			query.setParameter("elCodigo", idSummary);
			result=(List<DispersionVector>)query.getResultList();
			
		}
		catch(Exception ex)
		{
			ex.printStackTrace();
			return new ArrayList<DispersionVector>();
			
			
		}
		return result;
		}
	
	
	
		
	
}

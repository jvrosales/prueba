package ec.gob.ambiente.enlisy.speciescatalog.services;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.Query;

import ec.gob.ambiente.enlisy.dao.AbstractFacade;
import ec.gob.ambiente.enlisy.model.User;
import ec.gob.ambiente.enlisy.speciescatalog.model.AffectedSpecie;






/**
 * Servicios para la administracion de 
 * las especies afectadas
 * @author EXCO
 *
 */
@Stateless
public class AffectedSpecieFacade extends AbstractFacade<AffectedSpecie, Integer>{

	public AffectedSpecieFacade() {
		super(AffectedSpecie.class, Integer.class);		
	}

	/**
	 * Guardar especie-afectada
	 * @param BiologicalForm
	 * @param user
	 * @return
	 */
	
	
	public AffectedSpecie save(AffectedSpecie afected, User user)
	{
		AffectedSpecie afectedReturn=null;
		try
		{			
			if(afected.getAfspId() == null)
			{				
				
				afected.setAfspStatus(true);
				afected.setAfspDateCreate(new Date());
				afected.setAfspUserCreate(user.getUserName());
				afectedReturn=create(afected);
				
			}
			else
			{
				afected.setAfspDateUpdate(new Date());
				afected.setAfspUserUpdate(user.getUserName());
				afectedReturn=edit(afected);
			}
					
			
			return afectedReturn;
		}
		catch(Exception ex)
		{
			ex.printStackTrace();
			return afectedReturn;
		}
	}
	
	/**
	 * Listar especies afectadas
	 * @param idSpecie
	 * @return
	 */
	
	@SuppressWarnings("unchecked")
	public List<AffectedSpecie> getSpeciesBySumary(Integer idSummary) {
		
		List<AffectedSpecie> result=new ArrayList<AffectedSpecie>();
		try
		{
			Query query = super.getEntityManager().createQuery("select o from AffectedSpecie o where o.summaryExotic.seasId = :elCodigo and o.afspStatus = true");
			query.setParameter("elCodigo", idSummary);
			result=(List<AffectedSpecie>)query.getResultList();
			
		}
		catch(Exception ex)
		{
			ex.printStackTrace();
			return new ArrayList<AffectedSpecie>();
			
			
		}
		return result;
		}
	
	
	
		
	
}

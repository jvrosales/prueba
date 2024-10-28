package ec.gob.ambiente.enlisy.speciescatalog.services;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.Query;

import ec.gob.ambiente.enlisy.dao.AbstractFacade;
import ec.gob.ambiente.enlisy.model.User;
import ec.gob.ambiente.enlisy.speciescatalog.model.InvasionEnvironment;




/**
 * Servicios para la administracion de los
	ambientes de invasion de una especie exotica
 * @author EXCO
 *
 */
@Stateless
public class InvasionEnvironmentFacade extends AbstractFacade<InvasionEnvironment, Integer>{

	public InvasionEnvironmentFacade() {
		super(InvasionEnvironment.class, Integer.class);		
	}

	/**
	 * Guardar especie-ambiente
	 * @param UseExoticAlienSpecie
	 * @param user
	 * @return
	 */
	
	
	public InvasionEnvironment save(InvasionEnvironment environmentEspecie, User user)
	{
		InvasionEnvironment environmentEspecieReturn=null;
		try
		{			
			if(environmentEspecie.getInenId() == null)
			{				
				
				environmentEspecie.setInenStatus(true);
				environmentEspecie.setInenDateCreate(new Date());
				environmentEspecie.setInenUserCreate(user.getUserName());
				environmentEspecieReturn=create(environmentEspecie);
				
			}
			else
			{
				environmentEspecie.setInenDateUpdate(new Date());
				environmentEspecie.setInenUserUpdate(user.getUserName());
				environmentEspecieReturn=edit(environmentEspecie);
			}
					
			
			return environmentEspecieReturn;
		}
		catch(Exception ex)
		{
			ex.printStackTrace();
			return environmentEspecieReturn;
		}
	}
	
	/**
	 * Listar ambientes invasion de una especie
	 * @param idSummary
	 * @return
	 */
	
	@SuppressWarnings("unchecked")
	public List<InvasionEnvironment> getEnvironmentsBySumary(Integer idSummary) {
		
		List<InvasionEnvironment> result=new ArrayList<InvasionEnvironment>();
		try
		{
			Query query = super.getEntityManager().createQuery("select o from InvasionEnvironment o where o.summaryExotic.seasId = :elCodigo and o.inenStatus = true");
			query.setParameter("elCodigo", idSummary);
			result=(List<InvasionEnvironment>)query.getResultList();
			
		}
		catch(Exception ex)
		{
			ex.printStackTrace();
			return new ArrayList<InvasionEnvironment>();
			
			
		}
		return result;
		}
	
	
	
		
	
}

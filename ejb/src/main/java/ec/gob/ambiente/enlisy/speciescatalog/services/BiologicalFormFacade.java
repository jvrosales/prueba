package ec.gob.ambiente.enlisy.speciescatalog.services;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.Query;

import ec.gob.ambiente.enlisy.dao.AbstractFacade;
import ec.gob.ambiente.enlisy.model.User;
import ec.gob.ambiente.enlisy.speciescatalog.model.BiologicalForm;





/**
 * Servicios para la administracion de la
	formas biologicas de una especie exotica
 * @author EXCO
 *
 */
@Stateless
public class BiologicalFormFacade extends AbstractFacade<BiologicalForm, Integer>{

	public BiologicalFormFacade() {
		super(BiologicalForm.class, Integer.class);		
	}

	/**
	 * Guardar especie-biological form
	 * @param BiologicalForm
	 * @param user
	 * @return
	 */
	
	
	public BiologicalForm save(BiologicalForm biologicalForm, User user)
	{
		BiologicalForm biologicalFormReturn=null;
		try
		{			
			if(biologicalForm.getBifoId() == null)
			{				
				
				biologicalForm.setBifoStatus(true);
				biologicalForm.setBifoDateCreate(new Date());
				biologicalForm.setBifoUserCreate(user.getUserName());
				biologicalFormReturn=create(biologicalForm);
				
			}
			else
			{
				biologicalForm.setBifoDateUpdate(new Date());
				biologicalForm.setBifoUserUpdate(user.getUserName());
				biologicalFormReturn=edit(biologicalForm);
			}
					
			
			return biologicalFormReturn;
		}
		catch(Exception ex)
		{
			ex.printStackTrace();
			return biologicalFormReturn;
		}
	}
	
	/**
	 * Listar formas biologicas de una especie
	 * @param idSpecie
	 * @return
	 */
	
	@SuppressWarnings("unchecked")
	public List<BiologicalForm> getFormsBySumary(Integer idSummary) {
		
		List<BiologicalForm> result=new ArrayList<BiologicalForm>();
		try
		{
			Query query = super.getEntityManager().createQuery("select o from BiologicalForm o where o.summaryExotic.seasId = :elCodigo and o.bifoStatus = true");
			query.setParameter("elCodigo", idSummary);
			result=(List<BiologicalForm>)query.getResultList();
			
		}
		catch(Exception ex)
		{
			ex.printStackTrace();
			return new ArrayList<BiologicalForm>();
			
			
		}
		return result;
		}
	
	
	
		
	
}

package ec.gob.ambiente.enlisy.geneticresources.services;

import javax.ejb.Stateless;
import javax.persistence.NoResultException;
import javax.persistence.Query;

import ec.gob.ambiente.enlisy.dao.AbstractFacade;
import ec.gob.ambiente.enlisy.geneticresources.model.ProcessCatalog;

@Stateless
public class ProcessCatalogFacade extends AbstractFacade<ProcessCatalog, Integer>{
	
	public ProcessCatalogFacade(){
		super(ProcessCatalog.class, Integer.class);
	}
	
	public ProcessCatalog findByTask(String task){
		
		try{
			Query query = super.getEntityManager().createQuery("SELECT p FROM ProcessCatalog p where p.prcaValueBpm = :task and p.prcaStatus = true");
			query.setParameter("task", task);
			
			ProcessCatalog proceso = (ProcessCatalog)query.getSingleResult();
			return proceso;
			
		}catch(NoResultException e){
			return null;
		}	
		
	}

}

package ec.gob.ambiente.enlisy.speciescatalog.services;

import java.util.Date;
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.NoResultException;
import javax.persistence.Query;

import ec.gob.ambiente.enlisy.dao.AbstractFacade;
import ec.gob.ambiente.enlisy.model.User;
import ec.gob.ambiente.enlisy.speciescatalog.model.ProposedTaxa;
import ec.gob.ambiente.enlisy.speciescatalog.model.SpecieApplication;

/**
 * Servicios para la administracion de las publicaciones del especialista
 * @author EXCO
 *
 */
@Stateless
public class ProposedTaxaFacade extends AbstractFacade<ProposedTaxa, Integer>{

	public ProposedTaxaFacade() {
		super(ProposedTaxa.class, Integer.class);		
	}
	
		
	/**
	 * Guardar Publicacion
	 * @param solicitudEspecialista
	 * @param usuario
	 * @return
	 */
	public ProposedTaxa save(ProposedTaxa proposedTaxa, User user)
	{
		ProposedTaxa proposed=null;
		try
		{			
			if(proposedTaxa.getPrtaId() == null)
			{				
				
				proposedTaxa.setPrtaStatus(true);
				proposedTaxa.setPrtaDateCreate(new Date());
				proposedTaxa.setPrtaUserCreate(user.getUserName());
				proposed=create(proposedTaxa);
				
			}
			else
			{
				proposedTaxa.setPrtaDateUpdate(new Date());
				proposedTaxa.setPrtaUserUpdate(user.getUserName());
				proposed=edit(proposedTaxa);
			}
					
			
			return proposed;
		}
		catch(Exception ex)
		{
			ex.printStackTrace();
			return proposed;
		}
	}
	
		
	/**
	 * Eliminar publicaciones de una solicitud
	 * @param specieApplication
	 * @param usuario
	 * @return
	 */
	public void deleteBySpecieApplication(SpecieApplication specieApplication, User user)
	{
		
		try
		{
			Query query = super.getEntityManager().createQuery("update ProposedTaxa o set o.prtaStatus = false, o.prtaDateUpdate = :laFecha , o.prtaUserUpdate = :elUsuario  where o.specieApplication.sappId = :codigoSolicitud");
			query.setParameter("laFecha", new Date());
			query.setParameter("elUsuario", user.getUserName());
			query.setParameter("codigoSolicitud", specieApplication.getSappId());
			query.executeUpdate();
							
			
		}catch(NoResultException e)
		{
			
		}
	}
	
	@SuppressWarnings("unchecked")
	public List<ProposedTaxa> findBySpecieApplication(SpecieApplication specieApplication,String statusApplication)
	{
		
		try
		{
			Query query = super.getEntityManager().createQuery("SELECT o FROM ProposedTaxa o where o.specieApplication.sappId= :id and o.prtaStatus = true and o.prtaStatusApplication= :estado");
			query.setParameter("id", specieApplication.getSappId());
			query.setParameter("estado",statusApplication);
			
			
			List<ProposedTaxa> result=(List<ProposedTaxa>)query.getResultList();
			if(result.size()>0)
				return result;
			
		}catch(NoResultException e)
		{
			return null;
		}
		return null;
	}
}

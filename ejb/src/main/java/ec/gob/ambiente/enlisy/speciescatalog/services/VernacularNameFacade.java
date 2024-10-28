package ec.gob.ambiente.enlisy.speciescatalog.services;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.NoResultException;
import javax.persistence.Query;

import ec.gob.ambiente.enlisy.dao.AbstractFacade;
import ec.gob.ambiente.enlisy.model.User;
import ec.gob.ambiente.enlisy.speciescatalog.model.SpecieTaxa;
import ec.gob.ambiente.enlisy.speciescatalog.model.VernacularName;

/**
 * Servicios para la administracion de nombres comunes
 * @author EXCO
 *
 */
@Stateless
public class VernacularNameFacade extends AbstractFacade<VernacularName, Integer>{

	public VernacularNameFacade() {
		super(VernacularName.class, Integer.class);		
	}
	
	/**
	 * Guardar nombre comun
	 * @param vernacularName
	 * @param user
	 * @return
	 */
		
	
	public VernacularName save(VernacularName vernacularName, User user)
	{
		VernacularName name=null;
		try
		{			
			if(vernacularName.getVenaId() == null)
			{				
				
				vernacularName.setVenaStatus(true);
				vernacularName.setVenaDateCreate(new Date());
				vernacularName.setVenaUserCreate(user.getUserName());
				name=create(vernacularName);
				
			}
			else
			{
				vernacularName.setVenaDateUpdate(new Date());
				vernacularName.setVenaUserUpdate(user.getUserName());
				name=edit(vernacularName);
			}
					
			
			return name;
		}
		catch(Exception ex)
		{
			ex.printStackTrace();
			return name;
		}
	}
	
	
	/**
	 * Retorna la lista de grupos artificiales de una especie
	 * @param idSpecie
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<VernacularName> getVernacularnamesBySpecie(Integer idSpecie) {
		
		List<VernacularName> result=new ArrayList<VernacularName>();
		try
		{
			Query query = super.getEntityManager().createQuery("select o from VernacularName o where o.specieTaxa.sptaId = :elCodigo and o.venaStatus = true");
			query.setParameter("elCodigo", idSpecie);
			result=(List<VernacularName>)query.getResultList();
			
		}
		catch(Exception ex)
		{
			ex.printStackTrace();
			
		}
		return result;
		}
	

	/**
	 * Eliminar nombres comunes
	 * @param specie
	 * @param user
	 */
	public void deleteBySpecie(SpecieTaxa specie, User user)
	{
		
		try
		{
			Query query = super.getEntityManager().createQuery("delete from VernacularName o  where o.specieTaxa.sptaId = :codigoEspecie and o.venaStatus = true");
			query.setParameter("codigoEspecie", specie.getSptaId());
			query.executeUpdate();
							
			
		}catch(NoResultException e)
		{
			
		}
	}
	
	
}

package ec.gob.ambiente.enlisy.speciescatalog.services;


import javax.ejb.Stateless;
import javax.persistence.NoResultException;
import javax.persistence.Query;

import ec.gob.ambiente.enlisy.dao.AbstractFacade;
import ec.gob.ambiente.enlisy.speciescatalog.model.BiodiversityCatalogParameter;



/**
 * Servicios para administracion de parametros del catalogo de biodiversidad
 * @author EXCO
 *
 */
@Stateless
public class BiodiversityCatalogParameterFacade extends AbstractFacade<BiodiversityCatalogParameter, Integer>{

	public BiodiversityCatalogParameterFacade() {
		super(BiodiversityCatalogParameter.class, Integer.class);		
	}
	
	public BiodiversityCatalogParameter findByCode(String bccaCode)
	{
		try {
			Query query = super.getEntityManager().createQuery("select o from BiodiversityCatalogParameter o where o.bccaCode = :bccaCode and o.bccaStatus = true");
			query.setParameter("bccaCode", bccaCode);
			
			if(query.getResultList().size() > 0)
			{
				BiodiversityCatalogParameter catalog = (BiodiversityCatalogParameter) query.getResultList().get(0);	
				return catalog;
			}
		} catch (NoResultException e) {
			e.printStackTrace();
		}
				
		return null;
	}
	
	
}

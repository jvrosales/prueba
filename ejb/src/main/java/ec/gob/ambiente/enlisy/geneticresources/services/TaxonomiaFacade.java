package ec.gob.ambiente.enlisy.geneticresources.services;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.NoResultException;
import javax.persistence.Query;

import ec.gob.ambiente.enlisy.dao.AbstractFacade;
import ec.gob.ambiente.enlisy.fiscalia.model.TbTaxaFiscalia;

@Stateless
public class TaxonomiaFacade extends AbstractFacade<TbTaxaFiscalia, Integer>{


	public TaxonomiaFacade() {
		super(TbTaxaFiscalia.class, Integer.class);		
	}
	
	@SuppressWarnings("unchecked")
	public TbTaxaFiscalia findByEspecie(String especie) {
		try{
			Query query = super.getEntityManager().createQuery(
					"SELECT o FROM TbTaxaFiscalia o where o.especie = :especie");
			query.setParameter("especie", especie);
			List<TbTaxaFiscalia> result= (List<TbTaxaFiscalia>) query.getResultList();
			if(result.size()>0)
				return result.get(0);
		}catch(NoResultException e)
		{
			return null;
		}
		return null;
		
	}
	
	@SuppressWarnings("unchecked")
	public TbTaxaFiscalia findByCode(String code) {
		try{
			Query query = super.getEntityManager().createQuery(
					"SELECT o FROM TbTaxaFiscalia o where o.gui = :code");
			query.setParameter("code", code);
			List<TbTaxaFiscalia> result= (List<TbTaxaFiscalia>) query.getResultList();
			if(result.size()>0)
				return result.get(0);
		}catch(NoResultException e)
		{
			return null;
		}
		return null;
		
	}
	
	@SuppressWarnings("unchecked")
	public List<String> listDistinctColumn2ByColumn1(String column1,String column2,String valueColumn1) {
		try
		{
			Query query = super.getEntityManager().createQuery(
					"SELECT distinct(o."+column2+") FROM TbTaxaFiscalia o where o."+column1+" = :valueColumn1");
			query.setParameter("valueColumn1", valueColumn1);
			return (List<String>) query.getResultList();
		}catch(Exception e)
		{
			e.printStackTrace();
		}
		return null;
	}
	
	
}
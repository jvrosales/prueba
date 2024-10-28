package ec.gob.ambiente.suia.recaudaciones.facade;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.persistence.NoResultException;
import javax.persistence.Query;

import ec.gob.ambiente.enlisy.model.CatalogoGeneralCoa;
import ec.gob.ambiente.enums.CatalogoTipoCoaEnum;
import ec.gob.ambiente.suia.crud.service.CrudServiceBean;


@Stateless
public class CatalogoCoaFacade {

	@EJB
	private CrudServiceBean crudServiceBean;	
	
	@SuppressWarnings("unchecked")
	public List<CatalogoGeneralCoa> obtenerCatalogo(CatalogoTipoCoaEnum tipo) {
		
		List<CatalogoGeneralCoa> resultList=new ArrayList<CatalogoGeneralCoa>();
		try {
			Query query = crudServiceBean.getEntityManager().createQuery("SELECT o FROM CatalogoGeneralCoa o WHERE o.estado=true and o.catalogoTipoCoa.id=:idTipo ORDER BY 1");
			query.setParameter("idTipo", tipo.getId());			
			resultList=(List<CatalogoGeneralCoa>)query.getResultList();
		} catch (NoResultException nre) {
			//TODO
		} catch (Exception e) {
			e.printStackTrace();
			
		}		
		return resultList;
	}
	
	public CatalogoGeneralCoa obtenerCatalogoById(CatalogoTipoCoaEnum id) {
		try {
			Query query = crudServiceBean.getEntityManager().createQuery("SELECT o FROM CatalogoGeneralCoa o WHERE o.estado=true and o.id=:id");
			query.setParameter("id", id.getId());			
			return (CatalogoGeneralCoa)query.getSingleResult();
		} catch (Exception e) {
			return null;
		}		
	}

	public CatalogoGeneralCoa obtenerCatalogoPorId(Integer id) {
		try {
			Query query = crudServiceBean.getEntityManager().createQuery("SELECT o FROM CatalogoGeneralCoa o WHERE o.estado=true and o.id=:id");
			query.setParameter("id", id);			
			return (CatalogoGeneralCoa)query.getSingleResult();
		} catch (Exception e) {
			return null;
		}		
	}

	@SuppressWarnings("unchecked")
	public List<CatalogoGeneralCoa> obtenerCatalogoOrden(CatalogoTipoCoaEnum tipo) {
		
		List<CatalogoGeneralCoa> resultList=new ArrayList<CatalogoGeneralCoa>();
		try {
			Query query = crudServiceBean.getEntityManager().createQuery("SELECT o FROM CatalogoGeneralCoa o WHERE o.estado=true and o.catalogoTipoCoa.id=:idTipo ORDER BY orden");
			query.setParameter("idTipo", tipo.getId());			
			resultList=(List<CatalogoGeneralCoa>)query.getResultList();
		} catch (NoResultException nre) {
			//TODO
		} catch (Exception e) {
			e.printStackTrace();
			
		}		
		return resultList;
	}
	
	public CatalogoGeneralCoa obtenerCatalogoPorTipoYOrden(CatalogoTipoCoaEnum tipo, Integer orden) {
		try {
			Query query = crudServiceBean.getEntityManager().createQuery("SELECT o FROM CatalogoGeneralCoa o WHERE o.estado=true and o.catalogoTipoCoa.id=:idTipo and o.orden=:orden");
			query.setParameter("idTipo", tipo.getId());
			query.setParameter("orden", orden);	
			return  (CatalogoGeneralCoa)query.getSingleResult();
		} catch (NoResultException nre) {
			//TODO
		} catch (Exception e) {
			e.printStackTrace();
			
		}		
		return null;
	}
	
	public CatalogoGeneralCoa obtenerCatalogoPorCodigo(String codigo) {
		try {
			Query query = crudServiceBean.getEntityManager().createQuery("SELECT o FROM CatalogoGeneralCoa o WHERE o.estado=true and o.codigo=:codigo");
			query.setParameter("codigo", codigo);			
			return (CatalogoGeneralCoa)query.getSingleResult();
		} catch (Exception e) {
			return null;
		}		
	}

}

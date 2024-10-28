package ec.gob.ambiente.enlisy.services;

import java.io.Serializable;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.persistence.Query;

import ec.gob.ambiente.enlisy.dao.AbstractFacade;
import ec.gob.ambiente.enlisy.model.Area;
import ec.gob.ambiente.enlisy.model.GeographicalLocation;

@Stateless
public class AreaFacade extends AbstractFacade<Area, Integer> implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@EJB
	private GeographicalLocationFacade geographicalLocationFacade;
	
	public AreaFacade() {
		super(Area.class, Integer.class);	
	}
	
	
	public Area findByGeoIdAreaType(GeographicalLocation place){
		
		try {
			Area areaDireccion = null;
			
			Query query = super.getEntityManager().createQuery("select a from Area a where a.ubicacionesGeografica = :place and a.artyId = 2 order by 1");
			query.setParameter("place", place);
			
			if(query.getResultList().size() > 0){
				Area area = (Area) query.getResultList().get(0);
				return area;
			}
			
			return areaDireccion;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}		
	}
	
	public Area findByCanton(GeographicalLocation ubicacion){		
		try {			
			
			Area areaDireccion = null;
			
			Query query = super.getEntityManager().createQuery("select a from Area a where a.id = :id order by 1");
			query.setParameter("id", ubicacion.getAreaZonalId());
			
			if(query.getResultList().size() > 0){
				Area area = (Area) query.getResultList().get(0);
				return area;
			}
			
			return areaDireccion;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}		
	}
	
	public Area findSiglasOTbyCanton(GeographicalLocation canton) {
	   	 try {

	   		 Area siglasOT = null;
	   		 // select * from areas where gelo_id_canton = 217 and entity_type = 'ZONAL';
	   		 Query query = super.getEntityManager()
	   				 .createQuery("select a from Area a where a.idCanton = :id and a.tipoEnteAcreditado is not null");
	   		 query.setParameter("id", canton.getGeloId());

	   		 if (query.getResultList().size() > 0) {
	   			 Area area = (Area) query.getResultList().get(0);
	   			 return area;
	   		 }

	   		 return siglasOT;
	   	 } catch (Exception e) {
	   		 e.printStackTrace();
	   		 return null;
	   	 }
	    }


}

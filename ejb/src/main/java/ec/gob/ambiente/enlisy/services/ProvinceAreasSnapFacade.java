package ec.gob.ambiente.enlisy.services;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.Query;

import ec.gob.ambiente.enlisy.dao.AbstractFacade;
import ec.gob.ambiente.enlisy.model.ProvinceAreasSnap;

@Stateless
public class ProvinceAreasSnapFacade extends AbstractFacade<ProvinceAreasSnap, Integer> implements Serializable{
		
	private static final long serialVersionUID = -7785530046621980400L;

	public ProvinceAreasSnapFacade(){
		super(ProvinceAreasSnap.class, Integer.class);		
	}
	
	
	@SuppressWarnings("unchecked")
	public List<ProvinceAreasSnap> consultarAreasPorProvincia(String provincia){
		
		List<ProvinceAreasSnap> lista = new ArrayList<>();
		try {
			
			Query query = super.getEntityManager().createQuery("select p from ProvinceAreasSnap p where "
					+ "upper(translate (p.prarProvince,'áéíóúÁÉÍÓÚäëïöüÄËÏÖÜñ', 'aeiouAEIOUaeiouAEIOUÑ')) like translate(:provincia,'áéíóúÁÉÍÓÚäëïöüÄËÏÖÜñ', 'aeiouAEIOUaeiouAEIOUÑ') "
					+ "and p.prarStatus = true order by 1");
			
			query.setParameter("provincia", provincia);
						
			lista = (ArrayList<ProvinceAreasSnap>)query.getResultList();			
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return lista;
	}
	
	@SuppressWarnings("unchecked")
	public List<ProvinceAreasSnap> consultarAreasPorProvincias(String provincia){
		List<ProvinceAreasSnap> lista = new ArrayList<>();
		try {
			StringBuilder sqlSelect = new StringBuilder();
			sqlSelect.append("select p from ProvinceAreasSnap p where p.prarStatus = true and (");
			String[] provincias = provincia.split(",");
			for (int i=0;  provincias.length > i; i++) {
				if(i > 0)
					sqlSelect.append(" or ");
				sqlSelect.append("upper(translate (p.prarProvince,'áéíóúÁÉÍÓÚäëïöüÄËÏÖÜñ', 'aeiouAEIOUaeiouAEIOUÑ')) like translate("+provincias[i]+",'áéíóúÁÉÍÓÚäëïöüÄËÏÖÜñ', 'aeiouAEIOUaeiouAEIOUÑ') ");
			}
			sqlSelect.append(") order by 1 ");
			
			Query query = super.getEntityManager().createQuery(sqlSelect.toString());
			lista = (ArrayList<ProvinceAreasSnap>)query.getResultList();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return lista;
	}
	
	@SuppressWarnings("unchecked")
	public ProvinceAreasSnap consultarPorId(Integer id){
		try {
			
			Query query = getEntityManager().createQuery("select p from ProvinceAreasSnap p where "
					+ "p.prarGidSnap = :idGid and p.prarStatus = true order by 1");
			
			query.setParameter("idGid", id);
			
			List<ProvinceAreasSnap> lista = query.getResultList();
			
			if(lista!=null && !lista.isEmpty()){
				return lista.get(0);
			}	
		} catch (Exception e) {
			e.printStackTrace();
		}	
		return null;
	}
	
	@SuppressWarnings("unchecked")
	public ProvinceAreasSnap consultarPorIdPorIdProvincia(Integer id, Integer idProvincia){
		try {
			
			Query query = getEntityManager().createQuery("select p from ProvinceAreasSnap p where "
					+ "p.prarGidSnap = :idGid and p.geloId = :geloId and p.prarStatus = true order by 1");

			query.setParameter("idGid", id);
			query.setParameter("geloId", idProvincia);
			
			List<ProvinceAreasSnap> lista = query.getResultList();
			
			if(lista!=null && !lista.isEmpty()){
				return lista.get(0);
			}	
		} catch (Exception e) {
			e.printStackTrace();
		}	
		return null;
	}
}
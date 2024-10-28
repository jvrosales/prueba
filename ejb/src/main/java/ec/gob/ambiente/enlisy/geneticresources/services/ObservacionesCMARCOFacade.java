package ec.gob.ambiente.enlisy.geneticresources.services;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import ec.gob.ambiente.enlisy.dao.AbstractFacade;
import ec.gob.ambiente.enlisy.geneticresources.model.ObservacionesCMARCO;
import ec.gob.ambiente.suia.crud.service.CrudServiceBean;
import ec.gob.ambiente.suia.exceptions.ServiceException;

@Stateless
public class ObservacionesCMARCOFacade extends AbstractFacade<ObservacionesCMARCO, Integer>{
	
    @EJB
    private CrudServiceBean crudServiceBean;
    
	public ObservacionesCMARCOFacade() {
		super(ObservacionesCMARCO.class, Integer.class);		
	}
	
    public void guardar(List<ObservacionesCMARCO> listaObservaciones) throws ServiceException {
        try {
            crudServiceBean.saveOrUpdate(listaObservaciones);
        } catch (RuntimeException e) {
            throw new ServiceException(e);
        }
    }
	
    public List<ObservacionesCMARCO> listarPorIdClaseNombreClase(Integer idClase, String nombreClase, String... secciones) throws ServiceException {
        try {
            if (secciones.length == 1 && secciones[0] != null && secciones[0].equals("*")) {
                return listarPorIdClaseNombreClase(idClase, nombreClase);
            }
            Map<String, Object> params = new HashMap<String, Object>();
            params.put("idClase", idClase);
            params.put("nombreClase", nombreClase);
            params.put("seccion", Arrays.asList(secciones));
            
            @SuppressWarnings("unchecked")
			List<ObservacionesCMARCO> lista = (List<ObservacionesCMARCO>) crudServiceBean.findByNamedQuery(ObservacionesCMARCO.LISTAR_POR_ID_CLASE_NOMBRE_CLASE_SECCION, params);
            for (ObservacionesCMARCO ob : lista) {
                ob.setDisabled(true);
            }
            return lista;
        } catch (RuntimeException e) {
        	return null;
        }
    }
    
    public List<ObservacionesCMARCO> listarPorIdClaseNombreClaseNoCorregidas(Integer idClase, String nombreClase) throws ServiceException {
        try {
            Map<String, Object> params = new HashMap<String, Object>();
            params.put("idClase", idClase);
            params.put("nombreClase", nombreClase);
            @SuppressWarnings("unchecked")
			List<ObservacionesCMARCO> lista = (List<ObservacionesCMARCO>) crudServiceBean.findByNamedQuery(ObservacionesCMARCO.LISTAR_POR_ID_CLASE_NOMBRE_CLASE_NO_CORREGIDAS, params);
            for (ObservacionesCMARCO ob : lista) {
                ob.setDisabled(true);
            }
            return lista;
        } catch (RuntimeException e) {
            throw new ServiceException(e);
        }
    }
}
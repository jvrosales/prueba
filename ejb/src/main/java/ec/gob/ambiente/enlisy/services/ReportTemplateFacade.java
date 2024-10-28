package ec.gob.ambiente.enlisy.services;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import ec.gob.ambiente.enlisy.dao.AbstractFacade;
import ec.gob.ambiente.enlisy.model.ReportTemplate;
import ec.gob.ambiente.enums.TipoDocumentoSistema;
import ec.gob.ambiente.suia.crud.service.CrudServiceBean;

@Stateless
public class ReportTemplateFacade extends AbstractFacade<ReportTemplate, Integer> implements Serializable{

    private static final long serialVersionUID = 1164242243166182670L;
    
    @EJB
    private CrudServiceBean crudServiceBean;
    
    public ReportTemplateFacade() {
        super(ReportTemplate.class, Integer.class);
    }

    public ReportTemplate getPlantillaReporte(TipoDocumentoSistema tipoDocumento) {
       
        Map<String, Object> parameters = new HashMap<String, Object>();
        parameters.put("p_tipoDocumentoId", tipoDocumento.getIdTipoDocumento());
        return crudServiceBean.findByNamedQuerySingleResult(ReportTemplate.OBTENER_PLANTILLA_POR_INFORME, parameters);
    }


}
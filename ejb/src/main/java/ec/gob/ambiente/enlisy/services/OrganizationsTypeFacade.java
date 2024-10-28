package ec.gob.ambiente.enlisy.services;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.Query;

import ec.gob.ambiente.enlisy.dao.AbstractFacade;
import ec.gob.ambiente.enlisy.model.OrganizationsType;
import ec.gob.ambiente.enums.TipoOrganizacionesPublicasPrivadas;
import ec.gob.ambiente.exceptions.ServiceException;

@Stateless
public class OrganizationsTypeFacade extends AbstractFacade<OrganizationsType, Integer> {

	public OrganizationsTypeFacade() {
		super(OrganizationsType.class, Integer.class);		
	}
	
	@SuppressWarnings("unchecked")
	public List<OrganizationsType> findByStatus(boolean status) throws ServiceException {
        List<OrganizationsType> organizationsTypes = null;
        try {
        	List<Integer> types = new ArrayList<Integer>();
        	types.add(TipoOrganizacionesPublicasPrivadas.EMPRESA_PRIVADA.getIdTipoEmpresa());
        	types.add(TipoOrganizacionesPublicasPrivadas.EMPRESA_PUBLICA.getIdTipoEmpresa());
        	Query query = super.getEntityManager().createQuery("select ot from OrganizationsType ot where ot.ortyStatus = :status and ot.ortyId in :types");
        	query.setParameter("status", status);
        	query.setParameter("types", types);
        	
        	organizationsTypes = (List<OrganizationsType>) query.getResultList();
        } catch (RuntimeException e) {
            throw new ServiceException(e);
        }
        return organizationsTypes;
    }
}

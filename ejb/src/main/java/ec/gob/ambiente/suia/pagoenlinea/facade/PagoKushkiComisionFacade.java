package ec.gob.ambiente.suia.pagoenlinea.facade;

import java.math.BigDecimal;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.persistence.Query;

import ec.gob.ambiente.suia.crud.service.CrudServiceBean;
import ec.gob.ambiente.suia.pagoenlinea.model.PagoKushkiComision;


@Stateless
public class PagoKushkiComisionFacade {

	@EJB
	private CrudServiceBean crudServiceBean;
	
	public PagoKushkiComision obtenerValoresComision(Double valor) {
		try {
			Query query = crudServiceBean.getEntityManager().createQuery("SELECT p FROM PagoKushkiComision p WHERE :valor between p.pacoMinimumValue and p.pacoMaximumValue and p.pacostatus=true");
			query.setParameter("valor", new BigDecimal(valor));		
			return (PagoKushkiComision)query.getSingleResult();
		} catch (Exception e) {
			return null;
		}		
	}
	
	public PagoKushkiComision obtenerValoresComisionMaxima(Double valor) {
		try {
			Query query = crudServiceBean.getEntityManager().createQuery("SELECT p FROM PagoKushkiComision p WHERE p.pacoMinimumValue >= :valor and p.pacostatus=true");
			query.setParameter("valor", new BigDecimal(valor));		
			return (PagoKushkiComision)query.getSingleResult();
		} catch (Exception e) {
			return null;
		}		
	}	
	
}

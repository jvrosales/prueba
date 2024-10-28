package ec.gob.ambiente.enlisy.redlist.services;

import javax.ejb.Stateless;

import ec.gob.ambiente.enlisy.dao.AbstractFacadeModel;
import ec.gob.ambiente.enlisy.redlist.model.RedListTracking;
 
@Stateless
public class RedlistTrackingFacade extends AbstractFacadeModel<RedListTracking> {

	private static final long serialVersionUID = 1L;
	
	public RedlistTrackingFacade() {
		super(RedListTracking.class);
	}

	@Override
	public void configure(String filter) {
		initialize();
	}
}

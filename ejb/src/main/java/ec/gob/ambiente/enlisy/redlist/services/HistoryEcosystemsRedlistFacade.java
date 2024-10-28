package ec.gob.ambiente.enlisy.redlist.services;

import javax.ejb.Stateless;

import ec.gob.ambiente.enlisy.dao.AbstractFacadeModel;
import ec.gob.ambiente.enlisy.redlist.model.HistoryEcosystemsRedlist;
 
@Stateless
public class HistoryEcosystemsRedlistFacade extends AbstractFacadeModel<HistoryEcosystemsRedlist> {

	private static final long serialVersionUID = 1L;
	
	public HistoryEcosystemsRedlistFacade() {
		super(HistoryEcosystemsRedlist.class);
	}

	@Override
	public void configure(String filter) {
		initialize();
	}
}

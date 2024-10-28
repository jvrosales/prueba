package ec.gob.ambiente.enlisy.redlist.services;

import javax.ejb.Stateless;

import ec.gob.ambiente.enlisy.dao.AbstractFacadeModel;
import ec.gob.ambiente.enlisy.redlist.model.HistorySpeciesRedlist;
 
@Stateless
public class HistorySpeciesRedlistFacade extends AbstractFacadeModel<HistorySpeciesRedlist> {

	private static final long serialVersionUID = 1L;

	public HistorySpeciesRedlistFacade() {
		super(HistorySpeciesRedlist.class);
	}

	@Override
	public void configure(String filter) {
		initialize();
	}

}

package ec.gob.ambiente.enlisy.exportauthorization.services;

import javax.ejb.Stateless;

import ec.gob.ambiente.enlisy.dao.AbstractFacadeModel;
import ec.gob.ambiente.enlisy.exportauthorization.model.ExportAuthVueProduct;

@Stateless
public class ExportAuthVueProductFacade extends AbstractFacadeModel<ExportAuthVueProduct>{

	private static final long serialVersionUID = 1L;
	
	public ExportAuthVueProductFacade() {
		super(ExportAuthVueProduct.class);
	}
	
	@Override
	public void configure(String filter) {
		initialize();
	}
}
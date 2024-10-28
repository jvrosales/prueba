package ec.gob.ambiente.enlisy.geneticresources.services;

import java.util.Date;

import javax.ejb.Stateless;

import ec.gob.ambiente.enlisy.dao.AbstractFacade;
import ec.gob.ambiente.enlisy.geneticresources.model.UnidentifiedSpecies;
import ec.gob.ambiente.enlisy.model.User;

@Stateless
public class UnidentifiedSpeciesFacade  extends AbstractFacade<UnidentifiedSpecies, Integer> {

	public UnidentifiedSpeciesFacade() {
		super(UnidentifiedSpecies.class, Integer.class);		
	}
	
	/**
	 * Guardar especie no identificada de la autorizacion
	 * @param unidentifiedSpecies
	 * @param user
	 */
	public boolean save(UnidentifiedSpecies unidentifiedSpecies, User user) {
		try {
			if (unidentifiedSpecies.getUnspId() == null) {

				unidentifiedSpecies.setUnspStatus(true);
				unidentifiedSpecies.setUnspIsHistorical(false);
				unidentifiedSpecies.setUnspDateCreate(new Date());
				unidentifiedSpecies.setUnspUserCreate(user.getUserName());
				create(unidentifiedSpecies);

			} else {
				unidentifiedSpecies.setUnspDateUpdate(new Date());
				unidentifiedSpecies.setUnspUserUpdate(user.getUserName());
				edit(unidentifiedSpecies);
			}

			return true;
		} catch (Exception ex) {
			return false;
		}
	}
}

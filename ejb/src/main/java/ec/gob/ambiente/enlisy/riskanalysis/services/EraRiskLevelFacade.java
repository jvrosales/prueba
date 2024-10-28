package ec.gob.ambiente.enlisy.riskanalysis.services;

import java.util.Date;
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.Query;

import ec.gob.ambiente.enlisy.dao.AbstractFacade;
import ec.gob.ambiente.enlisy.model.User;
import ec.gob.ambiente.enlisy.riskanalysis.model.EraRiskLevel;


/**
 * Servicios para la administracion de los valores maximos y minimos 
 * para determinar el nivel de riesgo
 * @author EXCO
 *
 */
@Stateless
public class EraRiskLevelFacade extends AbstractFacade<EraRiskLevel, Integer>{

	public EraRiskLevelFacade() {
		super(EraRiskLevel.class, Integer.class);		
	}
	
	/**
	 * Guardar valores de maximo y minimo de un nivel
	 * @param eraRiskAnalysisApplication
	 * @param user
	 * @return
	 */
		
	public EraRiskLevel save(EraRiskLevel eraRiskLevel, User user) {
		EraRiskLevel era = null;
		try {
			if (eraRiskLevel.getErrlId() == null) {

				eraRiskLevel.setErrlStatus(true);
				eraRiskLevel.setErrlDateCreate(new Date());
				eraRiskLevel.setErrlUserCreate(user.getUserName());
				era = create(eraRiskLevel);

			} else {
				eraRiskLevel.setErrlDateUpdate(new Date());
				eraRiskLevel.setErrlUserUpdate(user.getUserName());
				era = edit(eraRiskLevel);
			}

			return era;
		} catch (Exception ex) {
			return era;
		}
	}
	
	/**
	 * Obtener la lista de niveles
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<EraRiskLevel> getLevels()
	{
		try {
			Query query = super.getEntityManager().createQuery(
					"SELECT o FROM EraRiskLevel o where  o.errlStatus = true");
			

			List<EraRiskLevel> result = (List<EraRiskLevel>) query.getResultList();
			if (!result.isEmpty())
				return result;

		} catch (Exception e) {
			return null;
		}
		return null;
	}
	
	
	
}

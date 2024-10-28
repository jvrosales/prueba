package ec.gob.ambiente.enlisy.riskanalysis.services;

import java.util.Date;
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.Query;

import ec.gob.ambiente.enlisy.dao.AbstractFacade;
import ec.gob.ambiente.enlisy.model.User;
import ec.gob.ambiente.enlisy.riskanalysis.model.EraQuestionUncertainty;


/**
 * Servicios para la administracion de los valores de preguntas, incertidumbres
 * @author EXCO
 *
 */
@Stateless
public class EraQuestionUncertaintyFacade extends AbstractFacade<EraQuestionUncertainty, Integer>{

	public EraQuestionUncertaintyFacade() {
		super(EraQuestionUncertainty.class, Integer.class);		
	}
	
	/**
	 * Guardar valores de una pregunta o incertidumbre
	 * @param eraRiskAnalysisApplication
	 * @param user
	 * @return
	 */
		
	public EraQuestionUncertainty save(EraQuestionUncertainty eraQuestionUncertainty, User user) {
		EraQuestionUncertainty eraQuestion= null;
		try {
			if (eraQuestionUncertainty.getErquId() == null) {

				eraQuestionUncertainty.setErquStatus(true);
				eraQuestionUncertainty.setErquDateCreate(new Date());
				eraQuestionUncertainty.setErquUserCreate(user.getUserName());
				eraQuestion = create(eraQuestionUncertainty);

			} else {
				eraQuestionUncertainty.setErquDateUpdate(new Date());
				eraQuestionUncertainty.setErquUserUpdate(user.getUserName());
				eraQuestion = edit(eraQuestionUncertainty);
			}

			return eraQuestion;
		} catch (Exception ex) {
			return eraQuestion;
		}
	}
	
	/**
	 * Obtener una lista de registros segun catalogo
	 * @param id
	 * @return
	 */
	
	@SuppressWarnings("unchecked")
	public List<EraQuestionUncertainty>findByGeneralCatalog(Integer id)
	{
		try {
			Query query = super.getEntityManager().createQuery(
					"SELECT o FROM EraQuestionUncertainty o where o.typeId.bigcId= :codigo and o.erquStatus = true order by o.typeId.bigcCode");
			query.setParameter("codigo", id);

			List<EraQuestionUncertainty> result = (List<EraQuestionUncertainty>) query.getResultList();
			if (!result.isEmpty())
				return result;

		} catch (Exception e) {
			return null;
		}
		return null;
	}
	
	/**
	 * Obtener un registro de respuesta por codigo
	 * @param code
	 * @return
	 */
	public EraQuestionUncertainty findAnswerByCode(String code)
	{
		try {
			Query query = super.getEntityManager().createQuery(
					"SELECT o FROM EraQuestionUncertainty o where o.typeId.bigcCode= :tipo and o.erquStatus = true and o.erquCode= :codigo");
			query.setParameter("tipo", "tipo_pregunta");
			query.setParameter("codigo", code);

			EraQuestionUncertainty result = (EraQuestionUncertainty) query.getSingleResult();
			if (result != null)
				return result;

		} catch (Exception e) {
			return null;
		}
		return null;
	}
	
	/**
	 * Obtener un registro de incertidumbre por codigo
	 * @param code
	 * @return
	 */
	public EraQuestionUncertainty findUncertaintyByCode(String code)
	{
		try {
			Query query = super.getEntityManager().createQuery(
					"SELECT o FROM EraQuestionUncertainty o where o.typeId.bigcCode= :tipo and o.erquStatus = true and o.erquCode= :codigo");
			query.setParameter("tipo", "tipo_incertidumbre");
			query.setParameter("codigo", code);

			EraQuestionUncertainty result = (EraQuestionUncertainty) query.getSingleResult();
			if (result != null)
				return result;

		} catch (Exception e) {
			return null;
		}
		return null;
	}
	
	
}

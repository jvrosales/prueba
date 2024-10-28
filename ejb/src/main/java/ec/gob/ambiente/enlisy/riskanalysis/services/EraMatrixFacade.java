package ec.gob.ambiente.enlisy.riskanalysis.services;

import java.util.Date;

import javax.ejb.Stateless;
import javax.persistence.Query;

import ec.gob.ambiente.enlisy.dao.AbstractFacade;
import ec.gob.ambiente.enlisy.model.User;
import ec.gob.ambiente.enlisy.riskanalysis.model.EraMatrix;


/**
 * Servicios para la administracion de los valores de la matriz era
 * @author EXCO
 *
 */
@Stateless
public class EraMatrixFacade extends AbstractFacade<EraMatrix, Integer>{

	public EraMatrixFacade() {
		super(EraMatrix.class, Integer.class);		
	}
	
	/**
	 * Guardar valores de una matriz de era
	 * @param eraRiskAnalysisApplication
	 * @param user
	 * @return
	 */
		
	public EraMatrix save(EraMatrix eraMatrix, User user) {
		EraMatrix era = null;
		try {
			if (eraMatrix.getErmaId() == null) {

				eraMatrix.setErmaStatus(true);
				eraMatrix.setErmaDateCreate(new Date());
				eraMatrix.setErmaUserCreate(user.getUserName());
				era = create(eraMatrix);

			} else {
				eraMatrix.setErmaDateUpdate(new Date());
				eraMatrix.setErmaUserUpdate(user.getUserName());
				era = edit(eraMatrix);
			}

			return era;
		} catch (Exception ex) {
			return era;
		}
	}
	
	/**
	 * Obtener un registro por item, subitem y nombre
	 * @param codeItem
	 * @param subitemCode
	 * @param name
	 * @return
	 */
	
	public EraMatrix findByItemSubitemYNombre(String codeItem, String subitemCode, String name)
	{
		try
		{
			Query query = super.getEntityManager().createQuery(
					"SELECT o FROM EraMatrix o where o.ermaItemId.bigcCode= :itemCode and o.ermaSubitemId.bigcCode= :subitemCode and o.ermaStatus = true and o.ermaName= :nombre");
			query.setParameter("itemCode", codeItem);
			query.setParameter("subitemCode", subitemCode);
			query.setParameter("nombre", name);

			EraMatrix result = (EraMatrix) query.getSingleResult();
			if (result != null)
				return result;
		}
		catch(Exception e)
		{
			return null;
		}
		return null;
	}
	
	
}

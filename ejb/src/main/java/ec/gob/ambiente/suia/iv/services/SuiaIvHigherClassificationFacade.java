package ec.gob.ambiente.suia.iv.services;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import ec.gob.ambiente.enlisy.speciescatalog.model.HigherClassification;
import ec.gob.ambiente.suia.dto.ReinoTo;
import ec.gob.ambiente.suia.iv.dao.SuiaIvHigherClassificationDao;

/**
 * @author avfp
 *
 */
@Stateless
public class SuiaIvHigherClassificationFacade {

	@EJB
	private SuiaIvHigherClassificationDao suiaIvHigherClassificationDao;

	
	

	/**
	 * busca los reinos todos
	 * 
	 * 
	 * @return
	 */
	public List<ReinoTo> buscarMetasPorPlanAccion(Long codigoPlanAccion) {
		List<ReinoTo> reinoToL = new ArrayList<>();
		List<HigherClassification> higherClassificationL = suiaIvHigherClassificationDao.buscarTodos();
		if (null != higherClassificationL && higherClassificationL.size() > 0) {
			for (HigherClassification higherClass : higherClassificationL) {
				reinoToL.add(covertirAReino(higherClass));
			}
		}
		return reinoToL;
	}

	
	public ReinoTo covertirAReino(HigherClassification higherClass) {
		ReinoTo reinoTo = new ReinoTo();
		reinoTo.setId(higherClass.getHiclId());
		reinoTo.setNombre(higherClass.getHiclScientificName());
		return reinoTo;

	}

}

package ec.gob.ambiente.suia.iv.services;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import ec.gob.ambiente.enlisy.geneticresources.model.GoalsCatalog;
import ec.gob.ambiente.suia.dto.PoliticaTo;
import ec.gob.ambiente.suia.iv.dao.GoalsCatalogDao;


/**
 * @author avfp
 *
 */
@Stateless
public class SuiaIvPoliticaFacade {

	@EJB
	private GoalsCatalogDao goalsCatalogDao;

	public List<PoliticaTo> buscarPorCodigoPadre(String code, boolean onlyEnableds) throws Exception {
		List<PoliticaTo> politicasToL = new ArrayList<>();
		List<GoalsCatalog> suiaIvPoliticasL = goalsCatalogDao.findByCodeParent(code, onlyEnableds);
		for (GoalsCatalog suiaIvPolitica : suiaIvPoliticasL) {
			politicasToL.add(covertirAPoliticaTo(suiaIvPolitica));
		}
		return politicasToL;
	}

	public PoliticaTo buscarPorId(Integer id) {
		return covertirAPoliticaTo(goalsCatalogDao.findById(id));
	}

	public PoliticaTo buscarPorCodigo(String code, boolean onlyEnableds) {
		return covertirAPoliticaTo(goalsCatalogDao.findByCode(code, onlyEnableds));
	}

	public PoliticaTo covertirAPoliticaTo(GoalsCatalog goalsCatalog) {
		PoliticaTo politicaTo = new PoliticaTo();
		politicaTo.setId(goalsCatalog.getGocaId());
		politicaTo.setCodigoObjetivoCatalogo(goalsCatalog.getGocaCode());
		politicaTo.setNombre(goalsCatalog.getGocaDescription());
		politicaTo.setDescripcion(goalsCatalog.getGocaDescription());
		politicaTo.setEstado(goalsCatalog.getGocaEnable());
		return politicaTo;

	}

}

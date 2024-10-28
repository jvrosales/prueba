package ec.gob.ambiente.enlisy.services;

import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.persistence.LockModeType;

import ec.gob.ambiente.enlisy.model.SecuenciaDedicada;
import ec.gob.ambiente.suia.crud.service.CrudServiceBean;

@Stateless
public class SecuenciasFacade {

	@EJB
	private CrudServiceBean crudServiceBean;

	/**
	 * Retorna el valor numérico de la secuencia utilizando como key
	 * 'sequenceName'.
	 * 
	 * @param sequenceName
	 * @return
	 * @throws Exception
	 */
	public Long getNextValueDedicateSequence(String sequenceName) throws Exception {
		SecuenciaDedicada secuenciaDedicada = getSequenciaDedicadaUnlock(sequenceName);
		crudServiceBean.getEntityManager().lock(secuenciaDedicada, LockModeType.PESSIMISTIC_READ);
		Long sequence = secuenciaDedicada.incrementSequence();
		crudServiceBean.saveOrUpdate(secuenciaDedicada);
		return sequence;
	}

	/**
	 * Retorna el valor numérico de la secuencia utilizando como key
	 * 'sequenceName'.
	 * 
	 * @param sequenceName
	 * @return
	 * @throws Exception
	 */
	public String getNextValueDedicateSequence(String sequenceName, int minDigits) throws Exception {
		SecuenciaDedicada secuenciaDedicada = getSequenciaDedicadaUnlock(sequenceName);
		crudServiceBean.getEntityManager().lock(secuenciaDedicada, LockModeType.PESSIMISTIC_READ);
		Long sequence = secuenciaDedicada.incrementSequence();
		crudServiceBean.saveOrUpdate(secuenciaDedicada);
		String sequenceString = sequence.toString();
		for (int i = sequenceString.length(); i < minDigits; i++) {
			sequenceString = "0" + sequenceString;
		}
		return sequenceString;
	}

	/**
	 * Retorna el valor numérico de una secuencia creada en la base de datos.
	 * 
	 * @param name
	 * @param schema
	 * @return
	 * @throws Exception
	 */
	public Long getSecuenceNextValue(String name, String schema) {
		return Long.parseLong(crudServiceBean.getSecuenceNextValue(name, schema).toString());
	}
	

	/**
	 * Retorna como un String el año actual. Ej: Para el año 2015, retorna
	 * '2015'.
	 * 
	 * @return
	 */
	public String getCurrentYear() {
		return Calendar.getInstance().get(Calendar.YEAR) + "";
	}

	/**
	 * Retorna como un String el mes actual. Ej: Para el mes de mayo, retorna
	 * '05'.
	 * 
	 * @return
	 */
	public String getCurrentMonth() {
		String monthFormat = "";
		int month = Calendar.getInstance().get(Calendar.MONTH) + 1;
		if (month < 10)
			monthFormat = "0" + month;
		else
			monthFormat = month + "";
		return monthFormat;
	}

	private SecuenciaDedicada getSequenciaDedicadaUnlock(String sequenceName) throws Exception {
		int repeat = 0;
		SecuenciaDedicada secuenciaDedicada = null;
		while (repeat < 3) {
			try {
				secuenciaDedicada = getSequenciaDedicada(sequenceName);
				return secuenciaDedicada;
			} catch (Exception e) {
				Thread.sleep(2000);
				repeat++;
			}
		}
		return null;
	}

	private SecuenciaDedicada getSequenciaDedicada(String sequenceName) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("nombre", sequenceName);
		SecuenciaDedicada secuenciaDedicada = crudServiceBean.findByNamedQuerySingleResult(
				SecuenciaDedicada.FIND_BY_NAME, params);
		if (secuenciaDedicada == null) {
			secuenciaDedicada = new SecuenciaDedicada();
			secuenciaDedicada.setNombre(sequenceName);
			crudServiceBean.saveOrUpdate(secuenciaDedicada);
		}
		return secuenciaDedicada;
	}

}

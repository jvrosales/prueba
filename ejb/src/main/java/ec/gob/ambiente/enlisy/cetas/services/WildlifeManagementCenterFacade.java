package ec.gob.ambiente.enlisy.cetas.services;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.NoResultException;
import javax.persistence.Query;

import ec.gob.ambiente.enlisy.dao.AbstractFacade;
import ec.gob.ambiente.enlisy.cetas.model.WildlifeManagementCenter;
import ec.gob.ambiente.enlisy.model.User;
import ec.gob.ambiente.util.Constant;

@Stateless
public class WildlifeManagementCenterFacade extends AbstractFacade<WildlifeManagementCenter, Integer>{
	
	public WildlifeManagementCenterFacade() {
		super(WildlifeManagementCenter.class, Integer.class);		
	}
		
	public Integer procedureNumberGenerated(String sequenceName) {
		Query q;
		q = getEntityManager()
				.createNativeQuery("select nextval('"+sequenceName.toLowerCase()+"');");
		java.math.BigInteger result= (java.math.BigInteger)q.getSingleResult();
		return Integer.valueOf(result.intValue());
	}
	
	/**
	 * Obtiene los datos de una solicitud buscados por código de trámite
	 * @param code Código de Trámite
	 * @return Application
	 */
	/*
	public WildlifeManagementCenter findByCode(String code)
	{
		try
		{
			Query query = super.getEntityManager().createQuery("SELECT o FROM WildlifeManagementCenter o where o.wmceCode= :code and o.wmceStatus = true");
			query.setParameter("code", code);
			
			return (WildlifeManagementCenter) query.getSingleResult();
			
		}catch(NoResultException e)
		{
			return null;
		}
	}
	*/
	
	
	/**
	 * Obtiene true or false dependiendo si existe un tramite de un determinado Centro de Tenencia en proceso. 
	 * @param id Centro Tenencia
	 * @return true / false
	 */
	public boolean findProcessStartedByCTM(String idCentro) {
		try {
			Query q;
			q = getEntityManager()
					.createNativeQuery("select status from dblink('"+Constant.getDblinkBpmsBiodiversidad()+"','select status from processinstancelog where processinstanceid in(select processinstanceid from variableinstancelog where processid=''Biodiversidad.PatentesCentrosTenenciaManejo'' and variableid  = ''id_centro_tenencia'' and value=''"+idCentro+"'') and status=1')	as (status integer);");

			if(q.getResultList().size() > 0)
			{
				return true;
			}else{
				return false;
			}

		}catch(NoResultException e)
		{
			return false;
		}
	}

	
	/**
	 * Obtener todas las solicitudes por solicitante
	 * @param people
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<WildlifeManagementCenter> findByUser(User user)
	{
		Query query = super.getEntityManager().createQuery("SELECT o FROM WildlifeManagementCenter o where o.user.userId = :userId and o.wmceStatus = true order by o.wmceDateUpdate desc");
		query.setParameter("userId", user.getUserId());
		
		
		return (List<WildlifeManagementCenter>)query.getResultList();
	}
	
	
	/**
	 * Guardar Centro de Tenencia y Manejo
	 * @param wildlifeManagementCenter
	 * @param user
	 * @return
	 */
	public boolean save(WildlifeManagementCenter wildlifeManagementCenter, User user)
	{
		try
		{			
			if(wildlifeManagementCenter.getWmceId() == null)
			{				
				wildlifeManagementCenter.setUser(user);
				wildlifeManagementCenter.setWmceStatus(true);
				wildlifeManagementCenter.setWmceDateCreate(new Date());
				wildlifeManagementCenter.setWmceUserCreate(user.getUserName());
				create(wildlifeManagementCenter);
			}
			else
			{
				wildlifeManagementCenter.setWmceDateUpdate(new Date());
				wildlifeManagementCenter.setWmceUserUpdate(user.getUserName());
				edit(wildlifeManagementCenter);
			}
			
			return true;
		}
		catch(Exception ex)
		{
			return false;
		}
	}
	
	public void delete(WildlifeManagementCenter wildlifeManagementCenter, User user){
		wildlifeManagementCenter.setWmceStatus(false);
		wildlifeManagementCenter.setWmceDateUpdate(new Date());
		wildlifeManagementCenter.setUser(user);
		edit(wildlifeManagementCenter);
	}
	
	@SuppressWarnings("unchecked")
	public List<WildlifeManagementCenter> getManagementCenterToLiberation(){
		try {
			Query query = super.getEntityManager().createQuery(
					" select pa.wildlifeManagementCenter from PatentApplication pa "
				+   " where current_date between pa.paapStartDate and pa.paapEndDate and pa.paapStatusPatent = 'EMITIDO' "
				+   " and pa.wildlifeManagementCenter.wmceStatus = 't' ");
			List<WildlifeManagementCenter> result = (List<WildlifeManagementCenter>) query.getResultList();
			if (result.size() > 0) {
				return result;
			}
		} catch (NoResultException e) {
			return new ArrayList<WildlifeManagementCenter>();
		}
		return new ArrayList<WildlifeManagementCenter>();
	}
}
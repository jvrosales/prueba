package ec.gob.ambiente.enlisy.geneticresources.services;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.NoResultException;
import javax.persistence.Query;

import ec.gob.ambiente.enlisy.dao.AbstractFacade;
import ec.gob.ambiente.enlisy.geneticresources.model.ProposedCollection;
import ec.gob.ambiente.enlisy.model.PatentInformation;
import ec.gob.ambiente.enlisy.model.User;

@Stateless
public class ProposedCollectionFacade extends AbstractFacade<ProposedCollection, Integer>{
	
	public ProposedCollectionFacade() {
		super(ProposedCollection.class, Integer.class);		
	}
		
	/**
	 * Obtiene los datos de una propuesta de recoleccion buscados por código de trámite
	 * @param code Código de Trámite
	 * @return Application
	 */
	@SuppressWarnings("unchecked")
	public ProposedCollection findByCode(String code)
	{
		try
		{
			Query query = super.getEntityManager().createQuery("SELECT o FROM ProposedCollection o,ResearchApplication a where o.researchApplication.reapId=a.reapId and a.reapCode= :code and o.prcoStatus = true and a.reapStatus = true order by 1");
			query.setParameter("code", code);
			
			List<ProposedCollection> resultList=query.getResultList();
			
			if(resultList!=null&&!resultList.isEmpty())
				return resultList.get(0);			 
			
			
		}catch(NoResultException e)
		{
			return null;
		}
		
		return null;
	}
	

	@SuppressWarnings("unchecked")
	public ProposedCollection findByCodeModificacion(String code)
	{
		try
		{
			Query query = super.getEntityManager().createQuery("SELECT o FROM ProposedCollection o,ResearchApplication a where o.researchApplication.reapId=a.reapId and o.prcoCode= :code and o.prcoStatus = true and a.reapStatus = true order by 1");
			query.setParameter("code", code);
			List<ProposedCollection> resultList=query.getResultList();
			if(resultList!=null&&!resultList.isEmpty())
				return resultList.get(0);	
		}catch(NoResultException e)
		{
			return null;
		}	
		return null;
	}
	
	@SuppressWarnings("unchecked")
	public PatentInformation findPatentByCode(String code)
	{
		try
		{
			Query query = super.getEntityManager().createQuery("SELECT o FROM PatentInformation o WHERE o.code = :code AND o.status = true order by 1");
			query.setParameter("code", code);
			
			List<PatentInformation> resultList=query.getResultList();
			
			if(resultList!=null&&!resultList.isEmpty())
				return resultList.get(0);			 
			
			
		}catch(NoResultException e)
		{
			return null;
		}
		
		return null;
	}
	
	@SuppressWarnings("unchecked")
	public ProposedCollection findByCode(String code,String status)
	{
		try
		{
			Query query = super.getEntityManager().createQuery("SELECT o FROM ProposedCollection o where o.prcoCode= :code and o.prcoStatusProposed=:status and o.prcoStatus = true and o.prcoIdPrevious is not null order by 1 desc");
			query.setParameter("code", code);
			query.setParameter("status", status);
			List<ProposedCollection> resultList=query.getResultList();
			if(resultList!=null&&!resultList.isEmpty())
				return resultList.get(0);
		}catch(NoResultException e)
		{
			return null;
		}
		return null;
	}
	
	@SuppressWarnings("unchecked")
	public ProposedCollection findById(Integer proyectoId)
	{
		try
		{
			Query query = super.getEntityManager().createQuery("SELECT o FROM ProposedCollection o where o.prcoId= :code and o.prcoStatus = true order by 1 desc");
			query.setParameter("code", proyectoId);
			List<ProposedCollection> resultList=query.getResultList();
			if(resultList!=null&&!resultList.isEmpty())
				return resultList.get(0);
		}catch(NoResultException e)
		{
			return null;
		}
		return null;
	}
	
	/**
	 * Obtiene los proyectos de un programa
	 * @param proposedCollectionParent Propuesta del Programa
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<ProposedCollection> findByParent(ProposedCollection proposedCollectionParent)
	{
		try
		{
			Query query = super.getEntityManager().createQuery("SELECT o FROM ProposedCollection o "
					+ "where o.proposedCollection.prcoId = :prcoId and o.proposedCollection.prcoStatus = true and "
					+ "o.proposedCollection.prcoProgram = true and o.prcoStatus = true order by 1");
			query.setParameter("prcoId", proposedCollectionParent.getPrcoId().intValue());
			
			List<ProposedCollection> resultList=query.getResultList();
			
			if(resultList!=null&&!resultList.isEmpty())
				return resultList;			 
			
			
		}catch(NoResultException e)
		{
			return null;
		}
		
		return null;
	}
	
	@SuppressWarnings("unchecked")
	public List<ProposedCollection> findByPrevious(ProposedCollection proposedCollectionPrevious)
	{
		try
		{	
			
			Query query = super.getEntityManager().createQuery("SELECT o FROM ProposedCollection o where "
					+ "o.prcoIdPrevious = :prcoIdPrevious and o.prcoStatus = true order by 1 desc");
			
			query.setParameter("prcoIdPrevious", proposedCollectionPrevious.getPrcoId().intValue());
			
			
			List<ProposedCollection> resultList=query.getResultList();
			
			if(resultList!=null&&!resultList.isEmpty())
				return resultList;			 
			
			
		}catch(NoResultException e)
		{
			return null;
		}
		
		return null;
	}
	
	
	@SuppressWarnings("unchecked")
	public List<ProposedCollection> findProjectRenovarUpdate(String user)
	{
		try
		{
			Query query = super.getEntityManager().createQuery(
					"SELECT o FROM ProposedCollection o where o.prcoStatus = true  and o.prcoUserCreate= :user ");// and o.prcoDateEnd
			query.setParameter("user", user);
			List<ProposedCollection> result = (List<ProposedCollection>) query.getResultList();
			if (result.size() > 0)
				return result;

		} catch (NoResultException e) {
			return null;
		}
		return null;
	}
	
	
	/**
	 * Guardar Propuesta de recoleccion
	 * @param proposedCollection
	 * @param user
	 * @return
	 */
	public boolean save(ProposedCollection proposedCollection, User user)
	{
		try
		{			
			if(proposedCollection.getPrcoId() == null)
			{	
				
				proposedCollection.setPrcoStatus(true);
				proposedCollection.setPrcoDateCreate(new Date());
				proposedCollection.setPrcoUserCreate(user.getUserName());
				create(proposedCollection);
				
			}
			else
			{
				proposedCollection.setPrcoDateUpdate(new Date());
				proposedCollection.setPrcoUserUpdate(user.getUserName());
				edit(proposedCollection);
			}
			
			
			
			
			
			return true;
		}
		catch(Exception ex)
		{
			return false;
		}
	}
	
	public void delete(ProposedCollection proposedCollection, User user){
		proposedCollection.setPrcoStatus(false);
		proposedCollection.setPrcoDateUpdate(new Date());
		proposedCollection.setPrcoUserUpdate(user.getUserName());
		edit(proposedCollection);
	}
	
	@SuppressWarnings("unchecked")
	public List<ProposedCollection> findPropuestasPorUsuario(String user){
		try
		{
			Query query = super.getEntityManager().createQuery(
					"SELECT o FROM ProposedCollection o where o.prcoStatus = true and "
					+ "o.prcoStatusProposed is not null and o.prcoStatusProposed =:status and o.prcoUserCreate= :user and "
					+ "o.prcoDateEnd > :date and o.prcoJustificationChange is null order by 1 desc");
			query.setParameter("user", user);
			query.setParameter("date", new Date());
			query.setParameter("status", "APROBADO");
			List<ProposedCollection> result = (List<ProposedCollection>) query.getResultList();
			if (result.size() > 0)
				return result;

		} catch (NoResultException e) {
			return null;
		}
		return null;
	}
	
	@SuppressWarnings("unchecked")
	public ProposedCollection findByTramite(String code)
	{
		try
		{
			Query query = super.getEntityManager().createQuery("SELECT o FROM ProposedCollection o where o.prcoCode= :code and o.prcoStatus = true order by 1");
			query.setParameter("code", code);
			
			List<ProposedCollection> resultList=query.getResultList();
			
			if(resultList!=null&&!resultList.isEmpty())
				return resultList.get(0);			 
			
			
		}catch(NoResultException e)
		{
			return null;
		}
		
		return null;
	}
	
	@SuppressWarnings("unchecked")
	public List<ProposedCollection> findProject(String user)
	{
		try
		{
			Query query = super.getEntityManager().createQuery(
					"SELECT o FROM ProposedCollection o where o.prcoStatus = true and o.prcoProgram=true and "
					+ " o.prcoUserCreate= :user and o.prcoJustificationChange is null and o.prcoStatusProposed is not null "
					+ " and o.prcoStatusProposed = :status");// and o.prcoDateEnd
			query.setParameter("user", user);
			query.setParameter("status", "APROBADO");
			List<ProposedCollection> result = (List<ProposedCollection>) query.getResultList();
			if (result.size() > 0)
				return result;

		} catch (NoResultException e) {
			return null;
		}
		return null;
	}
	
	//control y seguimiento
	
	@SuppressWarnings("unchecked")
	public List<ProposedCollection> findByActivitiesDate(){
		
		try {
			
			Query query = super.getEntityManager().createQuery(
					"SELECT o FROM ProposedCollection o where o.prcoCollectionActivitiesDateStart <= :startDate "
					+ "and o.prcoCollectionActivitiesDateEnd >= :endDate and o.prcoStatusProposed = :status "
					+ "and o.prcoStatus = true and o.prcoControl is null");
						
			query.setParameter("startDate", new Date());
			query.setParameter("endDate", new Date());
			query.setParameter("status", "APROBADO");
			
			List<ProposedCollection> result = (List<ProposedCollection>) query.getResultList();
			if (result.size() > 0)
				return result;
			
		} catch (NoResultException e) {
			return null;
		}
		return null;
	}
	
	@SuppressWarnings("unchecked")
	public List<ProposedCollection> findByTypeProposedAndStatus(String type, String status){
		
		try {
			
			Query query = super.getEntityManager().createQuery(
					"SELECT o FROM ProposedCollection o where o.researchApplication.typeApplication.grcaCode = :type "
					+ "and o.prcoStatusProposed = :status and o.prcoControl is null and o.prcoIdPrevious is null "
					+ "and o.prcoStatus = true ");						
			
			query.setParameter("status", status);
			query.setParameter("type", type);
			
			List<ProposedCollection> result = (List<ProposedCollection>) query.getResultList();
			if (result.size() > 0)
				return result;
			
		} catch (NoResultException e) {
			return null;
		}
		return null;
	}	
	
	@SuppressWarnings("unchecked")
	public List<ProposedCollection> findByPreviousAndStatus(ProposedCollection proposedCollectionPrevious, String status)
	{
		try
		{	
			
			Query query = super.getEntityManager().createQuery("SELECT o FROM ProposedCollection o "
					+ "where o.prcoIdPrevious = :prcoIdPrevious and "
					+ "o.prcoStatusProposed = :status and "
					+ "o.prcoStatus = true order by 1 desc");
			query.setParameter("prcoIdPrevious", proposedCollectionPrevious.getPrcoId().intValue());
			query.setParameter("status", status);
			
			List<ProposedCollection> resultList=query.getResultList();
			
			if(resultList!=null&&!resultList.isEmpty())
				return resultList;			 
			
			
		}catch(NoResultException e)
		{
			return null;
		}
		
		return null;
	}
	
	
	
	@SuppressWarnings({ "unused", "unchecked" })
	private ProposedCollection findByDaysAndNotification(ProposedCollection propuesta, int diasRestantes){
		
		try {
			
			Query query = super.getEntityManager().createQuery(
					"SELECT o FROM ProposedCollection o where "
					+ "o.prcoId = :prcoId "
					+ "and extract(days from (o.prcoDateEnd - now())) <= :diasRestantes");
			query.setParameter("prcoId", propuesta.getPrcoId());
			query.setParameter("diasRestantes", diasRestantes);			
			
			List<ProposedCollection> result = (List<ProposedCollection>) query.getResultList();
			if (result.size() > 0){
				return result.get(0);
			}			
		} catch (NoResultException e) {
			return null;
		}
		return null;
		
	}	

	/**
	 * Obtiene las propuestas originales (no renovacion ni modificatorio) que han sido aprobadas por usuario
	 * @param user
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<ProposedCollection> findPropuestasAprobadasPorUsuario(String user){
		try
		{
			Query query = super.getEntityManager().createQuery(
					"SELECT o FROM ProposedCollection o, ResearchApplication a where o.prcoStatus = true and "
					+ "o.researchApplication.reapId=a.reapId and a.reapStatus = true and "
					+ "o.prcoStatusProposed is not null and o.prcoUserCreate= :user and "
					+ "o.prcoCode is null and "
					+ "o.prcoStatusProposed= :status "
					+ "order by 1 desc");
			query.setParameter("user", user);
			query.setParameter("status", "APROBADO");
			List<ProposedCollection> result = (List<ProposedCollection>) query.getResultList();
			if (result.size() > 0)
				return result;

		} catch (NoResultException e) {
			return null;
		}
		return null;
	}
	
	/**
	 * Busqueda de propuestas que tienen un periodo vigente
	 * @param user
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<ProposedCollection> findPropuestasAprobadasPorUsuarioVigentes(String user){
		try
		{
			Query query = super.getEntityManager().createQuery(
					"SELECT o FROM ProposedCollection o, ResearchApplication a where o.prcoStatus = true and "
					+ "o.researchApplication.reapId=a.reapId and a.reapStatus = true and "
					+ "o.prcoStatusProposed is not null and o.prcoUserCreate= :user and "
					+ "o.prcoCode is null and "
					+ "o.prcoStatusProposed= :status and "
					+ "o.prcoDateEnd > now()"
					+ "order by 1 ");
			query.setParameter("user", user);
			query.setParameter("status", "APROBADO");
			List<ProposedCollection> result = (List<ProposedCollection>) query.getResultList();
			if (result.size() > 0)
				return result;

		} catch (NoResultException e) {
			return null;
		}
		return null;
	}
	
	@SuppressWarnings("unchecked")
	public List<ProposedCollection> findProjectRenovarUpdateDesc(String user)
	{
		try
		{
			Query query = super.getEntityManager().createQuery(
					"SELECT o FROM ProposedCollection o where o.prcoStatus = true  and o.prcoUserCreate= :user order by 1 desc");// and o.prcoDateEnd
			query.setParameter("user", user);
			List<ProposedCollection> result = (List<ProposedCollection>) query.getResultList();
			if (result.size() > 0)
				return result;

		} catch (NoResultException e) {
			return null;
		}
		return null;
	}
	
	/**
	 * Obtiene los proyectos de un programa
	 * @param proposedCollectionParent Propuesta del Programa
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<Object> findByInfoProjetctParent(ProposedCollection proposedCollectionParent)
	{
		try
		{
			List<Object> lista = getEntityManager().createNativeQuery(
					"select a.prco_id, a.prco_title, c.peop_name, c.peop_id "
					+ "from biodiversity.proposed_collection a inner join  biodiversity.technical_teams_research  b on a.prco_id = b.prco_id "
					+ "inner join people c on b.peop_id = c.peop_id "
					+ "	inner join biodiversity.genetic_resources_catalogs d on b.grca_id_function = d.grca_id and  grca_code = 'funcion_investigacion.investigador principal' "
					+ "where a.prco_status = true and prco_id_parent = "+proposedCollectionParent.getPrcoId()+ " order by a.prco_id asc " ).getResultList() ;

			if(lista!=null&&!lista.isEmpty())
				return lista;
		}catch(NoResultException e)
		{
			return null;
		}
		return null;
	}
	
	/**
	 * Obtiene los proyectos o programas modificados de un proyecto o programa principal
	 * @param proposedCollectionParent Propuesta del Programa
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<ProposedCollection> findByUserByProposed(String usuario, String codigotramite)
	{
		try
		{
			Query query = super.getEntityManager().createQuery(
					"SELECT o FROM ProposedCollection o where o.prcoStatus = true and "
					+ " o.prcoUserCreate= :user and o.prcoJustificationChange is null "
					+ " and o.researchApplication.reapCode = :codigo ");
			query.setParameter("user", usuario);
			query.setParameter("codigo", codigotramite);
			List<ProposedCollection> result = (List<ProposedCollection>) query.getResultList();
			if (result.size() > 0)
				return result;
		} catch (NoResultException e) {
			return null;
		}
		return null;
	}
	
	/***
	 *  Obtiene los proyectos del usuario que pueden ser modificada la fecha para ampliacion de plazo
	 * @param usuario
	 * @param esPrograma
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<ProposedCollection> findByUserPlazoAmpliacion(String usuario, boolean esPrograma){
		try {
			Query query = super.getEntityManager().createQuery(
					"SELECT o FROM ProposedCollection o where o.prcoDateEnd >= :startDate "
					+ "and o.researchApplication.user.userName =:usuario "
					+ "and o.researchApplication.reapStatus = true "
					+ "and o.prcoDateEnd <= :endDate and o.prcoStatusProposed = :status "
					+ "and o.prcoStatus = true and o.prcoControl is null");

			Date fechaInicio = sumarDiasFecha(new Date(), 15);
			Date fechaFin = sumarDiasFecha(new Date(), 60);
			query.setParameter("startDate", fechaInicio);
			query.setParameter("endDate", fechaFin);
			query.setParameter("usuario", usuario);
			query.setParameter("status", "APROBADO");
			
			List<ProposedCollection> result = (List<ProposedCollection>) query.getResultList();
			if (result.size() > 0)
				return result;
		} catch (NoResultException e) {
			return null;
		}
		return null;
	}
	
	 public Date sumarDiasFecha(Date fecha, int dias){
	       Calendar calendar = Calendar.getInstance();
	       calendar.setTime(fecha); // Configuramos la fecha que se recibe
	       calendar.add(Calendar.DAY_OF_YEAR, dias);  // numero de días a añadir
	       return calendar.getTime(); // Devuelve el objeto Date con los nuevos días añadidos
	 }
}

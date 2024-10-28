package ec.gob.ambiente.enlisy.services;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.persistence.NoResultException;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import ec.gob.ambiente.enlisy.dao.AbstractFacade;
import ec.gob.ambiente.enlisy.model.Area;
import ec.gob.ambiente.enlisy.model.GeographicalLocation;
import ec.gob.ambiente.enlisy.model.Role;
import ec.gob.ambiente.enlisy.model.RolesUser;
import ec.gob.ambiente.enlisy.model.User;
import ec.gob.ambiente.suia.crud.service.CrudServiceBean;
import ec.gob.ambiente.util.Constant;

@Stateless
public class RolesUserFacade extends AbstractFacade<RolesUser, Integer> implements Serializable {

	private static final long serialVersionUID = 5978766190183771960L;
	
	@EJB
	private AreaFacade areaFacade;
	
	@EJB
	private CrudServiceBean crudServiceBean;

	public RolesUserFacade() {
		super(RolesUser.class, Integer.class);		
	}
	
	/**
	 * Buscar Roles por usuario
	 * @param userName
	 * @param roleName
	 * @return Devuelve null si no encuntra ningun registro
	 */
	public List<RolesUser> findByUserNameAndRoleName(String userName,String roleName)
	{
		TypedQuery<RolesUser> query = super.getEntityManager().createQuery(""
				+ "select o from RolesUser o where o.user.userStatus=true and o.user.userName = :userName and (o.role.roleName='admin' or o.role.roleName ='sujeto de control' or o.role.roleName ='AUTORIDAD AMBIENTAL' or o.role.roleName like :role) and o.rousStatus = true ", RolesUser.class);
		query.setParameter("userName", userName);
		query.setParameter("role", roleName);		
		List<RolesUser> result= (List<RolesUser>) query.getResultList();
		if(result.size()>0)
			return result;
		return null;
	}
	
	/**
	 * Agregar un rol a un usuario
	 * @param user
	 * @param role
	 */
	public void save(User user,Role role)
	{
		RolesUser ru=new RolesUser();
		ru.setRousStatus(true);
		ru.setUser(user);
		ru.setRole(role);		
		create(ru);
	}

	/**
	 * Buscar Usuarios por nombre de rol
	 * @param roleName
	 * @return Devuelve null si no encuntra ningun registro
	 */
	public List<User> findByRoleName(String roleName)
	{
		try {
			TypedQuery<User> query = super.getEntityManager().createQuery(""
					+ "select u from RolesUser o,User u where o.user.userId=u.userId and o.role.roleName like :role and o.rousStatus = true ", User.class);
			query.setParameter("role", roleName);		
			List<User> result= (List<User>) query.getResultList();
			if(result.size()>0)
				return result;
		} catch (NoResultException e) {
			e.printStackTrace();
		}

		return null;
	}

	public List<User> findByRolesNameAreas(String roleName, Integer idArea) {
		try {
			TypedQuery<User> query = super.getEntityManager().createQuery(
					"select u from RolesUser o,User u, GeographicalLocation g2"
							+ ", Area a where o.user.userId=u.userId and o.role.roleName like :role and o.rousStatus = true and g2.geloId = :idArea",
					User.class);
			query.setParameter("role", roleName);
			query.setParameter("idArea", idArea);
			List<User> result = (List<User>) query.getResultList();
			if (result.size() > 0)
				return result;
		} catch (NoResultException e) {
			e.printStackTrace();
		}

		return null;
	}

	/**
	 * Buscar Usuario por nombre de rol
	 * 
	 * @param roleName
	 * @return Devuelve null si no encuntra ningun registro
	 */
	public User findUniqueByRoleName(String roleName)
	{
		List<User> listUsers=findByRoleName(roleName);
		if(listUsers!=null)
			return listUsers.get(0);
		return null;
	}
	
	//control y seguimiento
	public List<User> findByRoleNameArea(String roleName, GeographicalLocation ubicacion)
	{
		try {
			TypedQuery<User> query = super.getEntityManager().createQuery(""
					+ "select u from RolesUser o,User u where o.user.userId=u.userId and "
					+ "o.role.roleName like :role and o.rousStatus = true and o.user.area.ubicacionesGeografica = :ubicacion ",
					User.class);
			query.setParameter("role", roleName);
			query.setParameter("ubicacion", ubicacion);
			List<User> result = (List<User>) query.getResultList();
			if (result.size() > 0)
				return result;
		} catch (NoResultException e) {
			e.printStackTrace();
		}

		return null;
	}
		
	public List<User> findByRoleDescriptionAndLocation(String roleDescription, GeographicalLocation lugar)
	{
		try {
			TypedQuery<User> query = super.getEntityManager()
					.createQuery(
							"" + "select u from RolesUser o,User u where o.user.userId=u.userId and "
									+ "o.role.roleDescription like :role and o.rousStatus = true and "
									+ "o.user.area.ubicacionesGeografica = :ubicacion and o.user.area.artyId = 2",
							User.class);
			query.setParameter("role", roleDescription);
			query.setParameter("ubicacion", lugar);
			List<User> result = (List<User>) query.getResultList();
			if (result.size() > 0)
				return result;
		} catch (NoResultException e) {
			e.printStackTrace();
		}

		return null;
	}

	public List<RolesUser> findByUserRole(String userName, String roleName) {

		try {
			TypedQuery<RolesUser> query = super.getEntityManager().createQuery(""
					+ "select o from RolesUser o where o.user.userStatus=true and o.user.userName = :userName and o.role.roleName like :role and o.rousStatus = true ",
					RolesUser.class);
			query.setParameter("userName", userName);
			query.setParameter("role", roleName);
			List<RolesUser> result = (List<RolesUser>) query.getResultList();
			if (result.size() > 0)
				return result;

		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * Buscar usuarios por nombre de rol
	 * 
	 * @param roleName
	 * @return Devuelve null si no encuntra ningun registro
	 */
	public List<User> findUsersByRoleName(String roleName)
	{
		List<User> listUsers=findByRoleName(roleName);
		if(listUsers!=null)
			return listUsers;
		return null;
	}
	
	
	/**
	 * <b> Buscar Usuario por rol y provincia. Si no hay usuarios retorna null. </b>
	 * 
	 * @author Daniel Balarezo
	 *         <p>
	 *         [Fecha: 14/09/2018]
	 *         </p>
	 * @return
	 */

	public User findUserByRoleAndProvince(String roleName, String provinciaCentro, Integer tipoArea) {

		List<User> usuariosList = null;
		List<User> usuarios = findByRoleNameArea(roleName, tipoArea);

		if (usuarios != null && !usuarios.isEmpty()) {
			usuariosList = new ArrayList<User>();
			for (User user : usuarios) {
				if (user.getArea() != null && user.getArea().getUbicacionesGeografica() != null
						&& user.getArea().getUbicacionesGeografica().getGeloCodificationInec() != null) {
					String inecProvinciaUsuario = user.getArea().getUbicacionesGeografica().getGeloCodificationInec()
							.substring(0, 2);
					if (inecProvinciaUsuario.equalsIgnoreCase(provinciaCentro)) {
						usuariosList.add(user);
					}
				}
			}
		}

		if (usuariosList == null || usuariosList.isEmpty()) {
			return null;
		}
		else{
			//devuelve el primer usuario encontrado
			return usuariosList.get(0);
		}
	}
	
	
	/**
	 * <b> Buscar todos los Usuarios por rol y provincia. Si no hay usuarios retorna
	 * null. </b>
	 * 
	 * @author Daniel Balarezo
	 *         <p>
	 *         [Fecha: 01/02/2019]
	 *         </p>
	 * @return
	 */

	public List<User> findAllUserByRoleAndProvince(String roleName, String provinciaCentro, Integer tipoArea) {

		List<User> usuariosList = null;
		List<User> usuarios = findByRoleNameArea(roleName, tipoArea);

		if(usuarios!=null && !usuarios.isEmpty())
		{
			usuariosList=new ArrayList<User>();
			for (User user : usuarios) {
				if (user.getArea() != null && user.getArea().getUbicacionesGeografica() != null
						&& user.getArea().getUbicacionesGeografica().getGeloCodificationInec() != null) {
					String inecProvinciaUsuario = user.getArea().getUbicacionesGeografica().getGeloCodificationInec()
							.substring(0, 2);
					if (inecProvinciaUsuario.equalsIgnoreCase(provinciaCentro)) {
						usuariosList.add(user);
					}
				}
			}
		}

		if (usuariosList == null || usuariosList.isEmpty()) {
			return null;
		}
		else{
			//devuelve el primer usuario encontrado
			return usuariosList;
		}
	}

	/**
	 * Buscar Usuario por nombre de rol y area
	 * 
	 * @param roleName tipoArea
	 * @author Daniel Balarezo
	 *         <p>
	 *         [Fecha: 04/01/2019]
	 *         </p>
	 */
	public User findUniqueByRoleNameArea(String roleName, Integer tipoArea) {
		List<User> listUsers = findByRoleNameArea(roleName, tipoArea);
		if (listUsers != null)
			return listUsers.get(0);
		return null;
	}

	/**
	 * <b> Buscar Usuario por rol y provincia. Si no hay usuarios retorna null. </b>
	 * 
	 * @author Daniel Balarezo
	 *         <p>
	 *         [Fecha: 28/12/2018]
	 *         </p>
	 * @return
	 */
	public List<User> findByRoleNameArea(String roleName, Integer tipoArea) {
		try {
			TypedQuery<User> query = super.getEntityManager().createQuery(""
					+ "select u from RolesUser o,User u where o.user.userId=u.userId and o.role.roleName like :role and o.rousStatus = true "
					+ "and o.user.userStatus=true and o.user.area.artyId = :tipoArea and o.user.area.area is null",
					User.class);
			query.setParameter("role", roleName);
			query.setParameter("tipoArea", tipoArea);
			List<User> result = (List<User>) query.getResultList();
			if (result.size() > 0)
				return result;
		} catch (NoResultException e) {
			e.printStackTrace();
		}

		return null;
	}

	public List<User> findAllUserByRoleAndCantonOficinaTecnica(String roleName, GeographicalLocation canton) {

		Area area = areaFacade.findByCanton(canton);

		List<User> usuarios = buscarUsuariosPorRolYArea(roleName, area);
		if (usuarios == null || usuarios.isEmpty()) {
			return null;
		} else {
			return usuarios;
		}
	}

	public List<User> buscarUsuariosPorRolYArea(String rol, Area area) {Query q;
	String aux = "";
	// Busqueda del usuario RUIZ MOREIRA CARLOS GIOVANNI para Responsable de OT Los Bancos
	if(area.getId().equals(1100) && rol.equals("Biodiversidad-Responsable de Oficina Técnica")) {
		aux = "and u.user_id = 5404";
	}
	q = getEntityManager().createNativeQuery("select u.* from suia_iii.roles_users ru" + 
			" inner join public.users u on ru.user_id = u.user_id" + 
			" inner join public.areas_users au on au.user_id = u.user_id" + 
			" inner join suia_iii.roles r on r.role_id = ru.role_id" + 
			" inner join public.areas a on a.area_id = au.area_id" + 
			" where r.role_name = :rol and a.area_id = :area and ru.rous_status = true" +
			" " + aux + " " +
			" and ru.rous_status = true" + 
			" and au.arus_status = true;", User.class);
	q.setParameter("rol", rol).setParameter("area", area.getId());
	
	@SuppressWarnings("unchecked")
	List<User> result = (List<User>) q.getResultList();
	return result;
	}

	@SuppressWarnings("unchecked")
	public List<User> getUsersByRoleAndCantonOficinaTecnica(String roleName, Integer geloIdCanton){
		List<User> result = new ArrayList<>();
		List<Integer> resultSQL;
		String sql =
				"select ru.user_id " +
				"from suia_iii.roles_users ru, " +
				"     suia_iii.roles ro, " +
				"     public.areas a, " +
				"     public.areas_users au, " +
				"     public.users u " +
				"where ru.role_id = ro.role_id " +
				"  and ro.role_name = '"+roleName+"' " +
				"  and ro.role_status = true " +
				"  and ru.rous_status = true " +
				"  and a.area_id = au.area_id " +
				"  and a.area_status = true " +
				"  and au.arus_status = true " +
				"  and ru.user_id = au.user_id " +
				"  and ru.user_id = u.user_id " +
				"  and u.user_status = true " +
				"  and a.area_id = (select gl.area_id_zonal from public.geographical_locations gl where gl.gelo_id = "+geloIdCanton+")";

		Query query = super.getEntityManager().createNativeQuery(sql);
		resultSQL = (List<Integer>) query.getResultList();
		if (resultSQL.isEmpty()) {
			return new ArrayList<>();
		}else{
			for (Integer r : resultSQL) {
				List<User> users =
						super.getEntityManager().createQuery("SELECT u FROM User u where u.userId="+r+ " and u.userStatus=true").getResultList();
				if (!users.isEmpty()){
					result.add(users.get(0));
				}
			}
			return result;
		}
	}
	
	public List<RolesUser> findByUserNameAndRoleNameAcces(String userName,String roleName)
	{
		TypedQuery<RolesUser> query = super.getEntityManager().createQuery(""
				+ "select o from RolesUser o where o.user.userStatus=true and o.user.userName = :userName and (o.role.roleName='admin' or o.role.roleName ='sujeto de control' or o.role.roleName ='AUTORIDAD AMBIENTAL' or o.role.roleName like :role) and o.rousStatus = true and o.role.roleName != :roleAcc", RolesUser.class);
		query.setParameter("userName", userName);
		query.setParameter("role", roleName);	
		query.setParameter("roleAcc", "Biodiversidad-Servicios Web SIB");	
		List<RolesUser> result= (List<RolesUser>) query.getResultList();
		if(result.size()>0)
			return result;
		return null;
	}

/**
 * funcion para obtener el usuario de menor carga laboral 
 * @param roleName
 * @return
 */
	public User buscarUsuarioMenorCargaLaboralPorRol(String roleName) {
		List<User> listUsers = findByRoleName(roleName);
		Integer pesoTareasUsuario;
		Integer pesoTotal = 0;
        	List<User> usuariosYCargas = new ArrayList<User>();
	        if(listUsers == null || listUsers.isEmpty())
        		return null;
		for (User objUser : listUsers) {
			pesoTareasUsuario = 0;
			Query sqlProcesos = super.getEntityManager().createNativeQuery(
							"select * from  dblink('"
									+ Constant.getDblinkBpmsBiodiversidad()
									+ "',"
									+ "'select count(actualowner_id) as tareas from task where actualowner_id in(''"
									+ objUser.getUserName()
									+ "'') and status in(''InProgress'',''Reserved'',''Created'',''Ready'') group by actualowner_id order by tareas') as (tareas integer)");
			try {
				Object result = sqlProcesos.getSingleResult();
				pesoTareasUsuario = (Integer) result;
			} catch (Exception e) {
			}
			pesoTotal += pesoTareasUsuario;
			// Se guardan los datos de las tareas en el usuario y se agrega el usuario a la lista para retornar
			objUser.setNumeroTramites(pesoTareasUsuario);
			objUser.setPesoTotalTareas(pesoTareasUsuario);
			usuariosYCargas.add(objUser);
		}
		//Se agrega el % de carga a cada usuario
		for (User userWorkload : usuariosYCargas) {
		    if(pesoTotal != 0)
		        userWorkload.setCarga(userWorkload.getPesoTotalTareas() * 100d / pesoTotal);
		    else
		        userWorkload.setCarga(0d);
		}

		Comparator<User> comparadorUsuario = new Comparator<User>() {
		    @Override
		    public int compare(User usuario, User t1) {
		        if(usuario.getCarga() == t1.getCarga()) {
		        	if(usuario.getUserWorkPerformanceRatio() == null || t1.getUserWorkPerformanceRatio() ==null){
		                return 0;
		            }
		            double resta = usuario.getUserWorkPerformanceRatio() - t1.getUserWorkPerformanceRatio();
		            if(resta > 0)
		                return -1;
		            else
		                return 1;
		        }
		        else
		        	return (int) (usuario.getCarga() - t1.getCarga());
		    }
		};
		Collections.sort(usuariosYCargas, comparadorUsuario);
		return usuariosYCargas.get(0);
	}
}
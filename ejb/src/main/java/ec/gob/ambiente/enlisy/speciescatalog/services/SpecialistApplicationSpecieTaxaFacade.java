package ec.gob.ambiente.enlisy.speciescatalog.services;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.sql.DataSource;

import ec.gob.ambiente.enlisy.dao.AbstractFacade;
import ec.gob.ambiente.enlisy.model.User;
import ec.gob.ambiente.enlisy.services.ContactFacade;
import ec.gob.ambiente.enlisy.speciescatalog.model.SpecialistApplicationSpecieTaxa;
import ec.gob.ambiente.enlisy.speciescatalog.model.SpecieGrid;

/**
 * Servicios para administracion de la solicitud de registro de especialista
 * 
 * @author EXCO
 *
 */
@Stateless
public class SpecialistApplicationSpecieTaxaFacade extends AbstractFacade<SpecialistApplicationSpecieTaxa, Integer> {

	@EJB
	ContactFacade contactFacade;

	public SpecialistApplicationSpecieTaxaFacade() {
		super(SpecialistApplicationSpecieTaxa.class, Integer.class);
	}

	/**
	 * Guardar Especialista Specie Taxa
	 * 
	 * @param solicitudEspecialista
	 * @param usuario
	 * @return
	 */
	public SpecialistApplicationSpecieTaxa save(SpecialistApplicationSpecieTaxa specialistApplicationSpecieTaxa,
			User user) {
		SpecialistApplicationSpecieTaxa application = null;
		try {
			if (specialistApplicationSpecieTaxa.getSastId() == null) {

				specialistApplicationSpecieTaxa.setSastStatus(true);
				specialistApplicationSpecieTaxa.setSastDateCreate(new Date());
				specialistApplicationSpecieTaxa.setSastUserCreate(user.getUserName());
				application = create(specialistApplicationSpecieTaxa);

			} else {
				specialistApplicationSpecieTaxa.setSastDateUpdate(new Date());
				specialistApplicationSpecieTaxa.setSastUserUpdate(user.getUserName());
				application = edit(specialistApplicationSpecieTaxa);
			}

			return application;
		} catch (Exception ex) {
			return application;
		}
	}

	/**
	 * Metodo para guardar o actualizar la lista de especies asignadas a un
	 * especialista
	 * 
	 * @param taxa
	 */

	public void insertUpdate(List<SpecieGrid> taxa, Integer idEspecialista, User user) {
		PreparedStatement pstmt = null;
		PreparedStatement pstmtEliminar = null;

		try {
			Connection con = getConnection();
			if (con != null) {

				pstmtEliminar = con
						.prepareStatement("delete from biodiversity.specialist_applications_species_taxa where spap_id = ?");
				pstmtEliminar.setInt(1, idEspecialista);
				pstmtEliminar.executeUpdate();

				pstmt = con.prepareStatement(
						"insert into biodiversity.specialist_applications_species_taxa (spta_id,spap_id,sast_status,sast_user_create,sast_date_create)  values (?, ?, ?, ? , ?)");

				System.out.println("Inicio batch.");
				long start = System.currentTimeMillis();

				java.sql.Date sDate = new java.sql.Date(new Date().getTime());

				for (SpecieGrid spst : taxa) {

					pstmt.setInt(1, spst.getId());
					pstmt.setInt(2, idEspecialista);
					pstmt.setBoolean(3, true);
					pstmt.setString(4, user.getUserName());
					pstmt.setDate(5, sDate);
					pstmt.addBatch();

				}
				
				pstmt.executeBatch();

				long end = System.currentTimeMillis();
				System.out.println("Fin batch(insert en): " + (end - start) + " milliseconds.");

			} else {
				System.out.println("Error de conexión ");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				if (pstmt != null) {
					pstmt.close();
				}
				if (pstmtEliminar != null) {
					pstmtEliminar.close();
				}

			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	private Connection getConnection() {

		Connection connMY = null;
		try {

			Context ctx = new InitialContext();
			DataSource ds = (DataSource) ctx.lookup("java:jboss/datasources/SuiaDS");
			connMY = ds.getConnection();
		}

		catch (Exception e) {
			e.printStackTrace();

			// TODO: handle exception
		}

		return connMY;

	}

	@SuppressWarnings("unchecked")
	public List<SpecieGrid> getByIdSpecialist(Integer idEspecialista) {
		List<Object[]> result = new ArrayList<>();
		List<SpecieGrid> resultObj = new ArrayList<>();
		Query q;
		EntityManager en = super.getEntityManager();
		q = en.createNativeQuery(
				"SELECT biodiversity.species_taxa.spta_id, spta_scientific_name, spta_red_list_ec, spta_cites_name, spta_taxonomic_status_name from biodiversity.species_taxa, biodiversity.specialist_applications_species_taxa where biodiversity.specialist_applications_species_taxa.spta_id= biodiversity.species_taxa.spta_id and sast_status = true and spap_id = ?1 ");
		q.setParameter(1, idEspecialista);
		result = q.getResultList();
		if (result != null && !result.isEmpty()) {
			for (Object[] row : result) {
				
				SpecieGrid specie= new SpecieGrid();
				specie.setId((Integer)row[0]);
				specie.setNombreCientifico((String)row[1]);
				specie.setListaRoja((String)row[2]);
				specie.setCites((String)row[3]);
				specie.setStatusTaxonomico((String)row[4]);
				resultObj.add(specie);
			}
		}
		return resultObj;
	}

}

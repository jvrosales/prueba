package ec.gob.ambiente.sib_suia_ora.catalogobiodiversidad.services;

import ec.gob.ambiente.enlisy.dao.AbstractFacade;
import ec.gob.ambiente.enlisy.exportauthorization.model.ExportAuthorizationVue;
import ec.gob.ambiente.sib_suia_ora.catalogobiodiversidad.model.CitesDataResponse;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.ArrayList;
import java.util.List;

@Stateless
public class CitesReportWebServiceFacade extends AbstractFacade<ExportAuthorizationVue, Integer> {

    public CitesReportWebServiceFacade() {
        super(ExportAuthorizationVue.class, Integer.class);
    }

    @SuppressWarnings("unchecked")
	public List<CitesDataResponse> getCitesReport(String year, String country){
        List<CitesDataResponse> listResult = new ArrayList<>();
        StringBuilder sql = new StringBuilder();
        sql.append( "SELECT " +
                    "eavp.eavp_appendix_origin_number as apendice, " +
                    "eavp_species_name as especie, " +
                    "eavp_description_terms_catalog as codigo_comercio, " +
                    "eavp_sample_description as descripcion_especimen, " +
                    "eavp_sample_amount as cantidad, " +
                    "eavp_quantity_unit_catalog as unidad, " +
                    "eavu_importer_country_code as pais_destino, " +
                    "eav.eavu_certificate_number as permiso_export, " +
                    "eavu_exporter_country_code as pais_origen, " +
                    "eavu_transaction_obj_catalog as proposito, " +
                    "eavp_origin_code_id_catalog as origen, " +
                    "EXTRACT(YEAR from eavu_certificate_issue_date) as anio, " +
                    "eavu_level_approv_observations as observaciones " +
                    "FROM " +
                    "biodiversity.export_auth_vue_products eavp " +
                    "join biodiversity.export_authorization_vue eav " +
                    "on eav.eavu_id = eavp.eavu_id and eavp_status = true " +
                    "WHERE " +
                    "eavu_request_type_name = 'Exportación' " +
                    "and  eavu_status = true "
        );
        if (year!=null && !year.equals("")){
            sql.append(" and  EXTRACT(YEAR from eavu_certificate_issue_date) between ");
            sql.append(year);
            sql.append(" and ");
            sql.append(year);
        }
        if (country!=null && !country.equals("")){
            sql.append(" and (eavu_importer_country_code = '");
            sql.append(country);
            sql.append("') ");
        }
        sql.append(" ORDER BY anio,permiso_export ");

        List<Object[]> result;
        Query q;
        EntityManager en = super.getEntityManager();
        q = en.createNativeQuery(sql.toString());
        result = q.getResultList();
        if (result!=null && !result.isEmpty()){
            for (Object[] row : result){
                CitesDataResponse obj = new CitesDataResponse();
                if (row[0]!=null){
                    obj.setApendice(row[0].toString());
                }
                if (row[1]!=null){
                    obj.setEspecie(row[1].toString());
                }
                if (row[2]!=null){
                    obj.setCodigoComercio(row[2].toString());
                }
                if (row[3]!=null){
                    obj.setDescripcionEspecimen(row[3].toString());
                }
                if (row[4]!=null){
                    obj.setCantidad(row[4].toString());
                }
                if (row[5]!=null){
                    obj.setUnidad(row[5].toString());
                }
                if (row[6]!=null){
                    obj.setPaisDestino(row[6].toString());
                }
                if (row[7]!=null){
                    obj.setPermisoExport(row[7].toString());
                }
                if (row[8]!=null){
                    obj.setPaisOrigen(row[8].toString());
                }

                obj.setPermisoReexport("N/A");

                if (row[9]!=null){
                    obj.setProposito(row[9].toString());
                }
                if (row[10]!=null){
                    obj.setOrigen(row[10].toString());
                }
                if (row[11]!=null){
                    obj.setAnio(row[11].toString());
                }
                if (row[12]!=null){
                    obj.setObservaciones(row[12].toString());
                }
                listResult.add(obj);
            }
        }
        return listResult;
    }
}
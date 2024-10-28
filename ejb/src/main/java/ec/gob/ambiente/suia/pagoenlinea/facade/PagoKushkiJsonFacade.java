package ec.gob.ambiente.suia.pagoenlinea.facade;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import ec.gob.ambiente.enlisy.model.User;
import ec.gob.ambiente.suia.crud.service.CrudServiceBean;
import ec.gob.ambiente.suia.pagoenlinea.model.PagoKushkiJson;

@Stateless
public class PagoKushkiJsonFacade {

	@EJB
    private CrudServiceBean crudServiceBean;
	
	public void save(PagoKushkiJson obj, User usuario){
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss.SSS");
		String DateToStoreInDataBase= sdf.format(new Date()); // java.util.Date
	    Timestamp ts = Timestamp.valueOf(DateToStoreInDataBase);			
		if(obj.getId_PagoJson()==null){
			obj.setUsuarioCreacion(usuario.getUserName());
			obj.setFechaCreacion(ts);			
		}
		else{
			obj.setUsuarioModificacion(usuario.getUserName());
			obj.setFechaModificacion(ts);			
		}
		crudServiceBean.saveOrUpdate(obj);
	}
	
	
}
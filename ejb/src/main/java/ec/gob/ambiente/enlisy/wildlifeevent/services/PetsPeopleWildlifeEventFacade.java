package ec.gob.ambiente.enlisy.wildlifeevent.services;

import java.sql.Timestamp;
import java.util.List;
import javax.ejb.Stateless;

import ec.gob.ambiente.common.JPAResponse;
import ec.gob.ambiente.enlisy.dao.AbstractFacadeModel;
import ec.gob.ambiente.enlisy.wildlifeevent.model.PetsPeopleWildlifeEvent;

@Stateless
public class PetsPeopleWildlifeEventFacade extends AbstractFacadeModel<PetsPeopleWildlifeEvent>{

	
	public PetsPeopleWildlifeEventFacade() {
		super(PetsPeopleWildlifeEvent.class);
	}

	private static final long serialVersionUID = 1L;

	@Override
	public void configure(String filter) {
		initialize();
	}
	
	public JPAResponse saveOrUpdate(List<PetsPeopleWildlifeEvent> list, String user, Timestamp date) {
		JPAResponse response = new JPAResponse();
		try {
			if (!list.isEmpty()) {
				for (PetsPeopleWildlifeEvent p:list) {
					if (isTransient(p)) {
						p.setPepeUserCreate(user);
						p.setPepeDateCreate(date);
						p.setPepeUserUpdate(user);
						p.setPepeDateUpdate(date);
						saveUncommit(p);
					}else {
						p.setPepeUserUpdate(user);
						p.setPepeDateUpdate(date);
						saveOrUpdateUncommit(p);
					}
				}
				flush();
			}
		}catch(Exception e) {
			response.setSuccessful(false);
			response.setException(e);
		}
		return response;
	}

}

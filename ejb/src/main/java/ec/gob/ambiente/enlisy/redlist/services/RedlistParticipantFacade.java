package ec.gob.ambiente.enlisy.redlist.services;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.NoResultException;
import javax.persistence.Query;

import ec.gob.ambiente.common.JPAResponse;
import ec.gob.ambiente.enlisy.dao.AbstractFacadeModel;
import ec.gob.ambiente.enlisy.redlist.model.RedlistParticipant;
 
@Stateless
public class RedlistParticipantFacade extends AbstractFacadeModel<RedlistParticipant> {

	private static final long serialVersionUID = 1L;

	public RedlistParticipantFacade() {
		super(RedlistParticipant.class);
	}

	@Override
	public void configure(String filter) {
		initialize();
	}

	@SuppressWarnings("unchecked")
	public List<RedlistParticipant> findByCallRedListId(int id) {
		try {
			Query query = super.getEntityManager()
					.createQuery("select s from RedlistParticipant s where s.callRedList.careId=:parentId and s.rspaStatus='t' ");
			query.setParameter("parentId", id);
			List<RedlistParticipant> result = (List<RedlistParticipant>) query.getResultList();
			if (result.size() > 0) {
				return result;
			}
		} catch (NoResultException e) {
			return new ArrayList<RedlistParticipant>();
		}
		return new ArrayList<RedlistParticipant>();
	}

	public JPAResponse saveOrUpdate(List<RedlistParticipant> list, String user, Timestamp date) {
		JPAResponse response = new JPAResponse();
		try {
			if (!list.isEmpty()) {
				for (RedlistParticipant p : list) {
					if (isTransient(p)) {
						p.setRspaUserCreate(user);
						p.setRspaDateCreate(date);
						p.setRspaUserUpdate(user);
						p.setRspaDateUpdate(date);
						saveUncommit(p);
					} else {
						p.setRspaUserUpdate(user);
						p.setRspaDateUpdate(date);
						saveOrUpdateUncommit(p);
					}
				}
				flush();
			}
		} catch (Exception e) {
			response.setSuccessful(false);
			response.setException(e);
		}
		return response;
	}
}

package ec.gob.ambiente.enlisy.redlist.services;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.NoResultException;
import javax.persistence.Query;

import ec.gob.ambiente.common.JPAResponse;
import ec.gob.ambiente.enlisy.dao.AbstractFacadeModel;
import ec.gob.ambiente.enlisy.redlist.model.RedlistSpecy;
 
@Stateless
public class RedlistSpecyFacade extends AbstractFacadeModel<RedlistSpecy> {

	private static final long serialVersionUID = 1L;

	public RedlistSpecyFacade() {
		super(RedlistSpecy.class);
	}

	@Override
	public void configure(String filter) {
		initialize();
	}

	@SuppressWarnings("unchecked")
	public List<RedlistSpecy> findByCallRedListId(int id) {
		try {
			Query query = super.getEntityManager()
					.createQuery("select s from RedlistSpecy s where s.callRedList.careId=:parentId");
			query.setParameter("parentId", id);
			List<RedlistSpecy> result = (List<RedlistSpecy>) query.getResultList();
			if (result.size() > 0) {
				return result;
			}
		} catch (NoResultException e) {
			return new ArrayList<RedlistSpecy>();
		}
		return new ArrayList<RedlistSpecy>();
	}

	public JPAResponse saveOrUpdate(List<RedlistSpecy> list, String user, Timestamp date) {
		JPAResponse response = new JPAResponse();
		try {
			if (!list.isEmpty()) {
				for (RedlistSpecy p : list) {
					if (isTransient(p)) {
						p.setRlspUserCreate(user);
						p.setRlspDateCreate(date);
						p.setRlspUserUpdate(user);
						p.setRlspDateUpdate(date);
						saveUncommit(p);
					} else {
						p.setRlspUserUpdate(user);
						p.setRlspDateUpdate(date);
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

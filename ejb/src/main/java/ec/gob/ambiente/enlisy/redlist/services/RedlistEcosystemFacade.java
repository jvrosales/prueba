package ec.gob.ambiente.enlisy.redlist.services;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.NoResultException;
import javax.persistence.Query;

import ec.gob.ambiente.common.JPAResponse;
import ec.gob.ambiente.enlisy.dao.AbstractFacadeModel;
import ec.gob.ambiente.enlisy.redlist.model.RedlistEcosystem;
 
@Stateless
public class RedlistEcosystemFacade extends AbstractFacadeModel<RedlistEcosystem> {

	private static final long serialVersionUID = 1L;

	public RedlistEcosystemFacade() {
		super(RedlistEcosystem.class);
	}

	@Override
	public void configure(String filter) {
		initialize();
	}

	@SuppressWarnings("unchecked")
	public List<RedlistEcosystem> findByCallRedListId(int id) {
		try {
			Query query = super.getEntityManager()
					.createQuery("select s from RedlistEcosystem s where s.callRedList.careId=:parentId and s.rlesStatus='t' ");
			query.setParameter("parentId", id);
			List<RedlistEcosystem> result = (List<RedlistEcosystem>) query.getResultList();
			if (result.size() > 0) {
				return result;
			}
		} catch (NoResultException e) {
			return new ArrayList<RedlistEcosystem>();
		}
		return new ArrayList<RedlistEcosystem>();
	}

	public JPAResponse saveOrUpdate(List<RedlistEcosystem> list, String user, Timestamp date) {
		JPAResponse response = new JPAResponse();
		try {
			if (!list.isEmpty()) {
				for (RedlistEcosystem p : list) {
					if (isTransient(p)) {
						p.setRlesUserCreate(user);
						p.setRlesDateCreate(date);
						p.setRlesUserUpdate(user);
						p.setRlesDateUpdate(date);
						saveUncommit(p);
					} else {
						p.setRlesUserUpdate(user);
						p.setRlesDateUpdate(date);
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

package ec.gob.ambiente.enlisy.redlist.services;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.NoResultException;
import javax.persistence.Query;

import ec.gob.ambiente.enlisy.dao.AbstractFacadeModel;
import ec.gob.ambiente.enlisy.redlist.model.MeetingAgreement;
import ec.gob.ambiente.enlisy.redlist.model.MeetingLog;

@Stateless
public class MeetingAgreementFacade extends AbstractFacadeModel<MeetingAgreement> {

	private static final long serialVersionUID = 1L;
	
	public MeetingAgreementFacade() {
		super(MeetingAgreement.class);
	}
	
	@Override
	public void configure(String filter) {
		 initialize();
	}

	@SuppressWarnings("unchecked")
	public List<MeetingAgreement> findByMeetingLog(MeetingLog meetingLog) {
		try {
			Query query = super.getEntityManager().createQuery("SELECT M FROM MeetingAgreement M "
					+ "WHERE M.meetingLog.meloId=" + meetingLog.getMeloId() + " ORDER BY M.meagDateCreate ");
			List<MeetingAgreement> result = (List<MeetingAgreement>) query.getResultList();
			if (result.size() > 0)
				return result;

		} catch (NoResultException e) {
			return new ArrayList<MeetingAgreement>();
		}
		return new ArrayList<MeetingAgreement>();
	}
}

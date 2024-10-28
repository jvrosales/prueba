package ec.gob.ambiente.enlisy.services;

import java.io.Serializable;
import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.persistence.TypedQuery;

import ec.gob.ambiente.enlisy.dao.AbstractFacade;
import ec.gob.ambiente.suia.crud.service.CrudServiceBean;
import ec.gob.ambiente.suia.domain.base.TransaccionFinanciera;
import ec.gob.ambiente.suia.domain.base.TransaccionFinancieraLog;
import ec.gob.ambiente.suia.web.services.financial.financialws.controller.PaymentInVO;
import ec.gob.ambiente.suia.web.services.financial.financialws.controller.PaymentResponseVO;
import ec.gob.ambiente.suia.web.services.financial.financialws.controller.PaymentServiceInternal;
import ec.gob.ambiente.suia.web.services.financial.financialws.controller.PaymentServiceInternal_Service;

@Stateless
public class TransaccionFinancieraFacade extends AbstractFacade<TransaccionFinanciera, Integer> implements Serializable{
	
	 private static final long serialVersionUID = -4594424897085245484L;
	
	 public TransaccionFinancieraFacade() {
	        super(TransaccionFinanciera.class, Integer.class);
	    }
	
	@EJB
	private CrudServiceBean crudServiceBean;	
	
	@SuppressWarnings("unchecked")
	public List<TransaccionFinanciera> cargarTransacciones(String identificadorMotivo) {
		return crudServiceBean.getEntityManager()
				.createQuery("From TransaccionFinanciera t where t.identificadorMotivo = :identificadorMotivo and t.tipodePago = 10 order by 1 desc")
				.setParameter("identificadorMotivo", identificadorMotivo).getResultList();
	}


	@SuppressWarnings("unchecked")
	public List<TransaccionFinanciera> cargarTransaccionesPat(Integer identificadorMotivo) {
		return crudServiceBean.getEntityManager()
				.createQuery("From TransaccionFinanciera t where t.codigoProyecto = :identificadorMotivo and t.tipodePago = 10 order by 1 desc")
				.setParameter("identificadorMotivo", identificadorMotivo).getResultList();
	}
	
	@SuppressWarnings("unchecked")
	public List<TransaccionFinanciera> cargarTransaccionesPatentes(Integer identificadorMotivo) {
		return crudServiceBean.getEntityManager()
				.createQuery("From TransaccionFinanciera t where t.codigoProyecto = :identificadorMotivo  and t.tipodePago = 11 order by 1 desc")
				.setParameter("identificadorMotivo", identificadorMotivo).getResultList();
	}
	
	public List<TransaccionFinanciera> findByNumber(String identificadorMotivo) {
        TypedQuery<TransaccionFinanciera> query = super.getEntityManager().createQuery(
                "select t from TransaccionFinanciera t where t.identificadorMotivo = :identificadorMotivo order by 1 desc", TransaccionFinanciera.class);
        query.setParameter("identificadorMotivo", identificadorMotivo);
        List<TransaccionFinanciera> transaccionFinanciera = new ArrayList<TransaccionFinanciera>();
        if (query.getResultList().size() > 0) {
        	transaccionFinanciera = query.getResultList();
        }
        return transaccionFinanciera;
    }
	
	public void guardarTransacciones(List<TransaccionFinanciera> transacciones, long idTarea, String nombreTarea,
                                     long idInstanciaProceso, String idProceso, String nombreProceso) {
		for (TransaccionFinanciera transaccion : transacciones) {
            //Se guarda la transaccion
			crudServiceBean.saveOrUpdate(transaccion);
            //Se guarda el log de la transaccion
            TransaccionFinancieraLog log = new TransaccionFinancieraLog();
            log.setIdInstanciaProceso(idInstanciaProceso);
            log.setIdTarea(idTarea);
            log.setNombreTarea(nombreTarea);
            log.setIdProceso(idProceso);
            log.setNombreProceso(nombreProceso);
            log.setTransaccionFinanciera(transaccion);
            crudServiceBean.saveOrUpdate(log);
		}
	}

	public double consultarSaldo(String codigoEntidadFinanciera, String numeroTransaccion) {
 		PaymentServiceInternal_Service service;
		try {
			service = new PaymentServiceInternal_Service();
			PaymentServiceInternal port = service.getPaymentServiceInternalPort();
			PaymentResponseVO response = port.consultarSaldo(codigoEntidadFinanciera, numeroTransaccion);
			if (response.getCode().trim().equals("1")) {
				return response.getBalance();
			} else {
				return -1;
			}
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return 0;
	}

	public boolean realizarPago(double valorPago, List<TransaccionFinanciera> transacciones,
			String identificadorMotivo) {
		try {
			List<PaymentInVO> transactionsIn = new ArrayList<PaymentInVO>();
			for (TransaccionFinanciera transaccion : transacciones) {
				PaymentInVO in = new PaymentInVO();
				in.setBankEntityCode(transaccion.getInstitucionFinanciera().getCodigoInstitucion());
				in.setTransactionNumber(transaccion.getNumeroTransaccion());
				transactionsIn.add(in);
			}

			PaymentServiceInternal_Service service = new PaymentServiceInternal_Service();
			PaymentServiceInternal port = service.getPaymentServiceInternalPort();
			port.realizarPago(transactionsIn, valorPago, identificadorMotivo);
		} catch (Exception e) {
			return false;
		}
		return true;
	}
	
	@SuppressWarnings("unchecked")	
	public List<TransaccionFinanciera> cargarTransacciones(long ProcessInstanceId, long TaskId) {
		return crudServiceBean.getEntityManager()
				.createQuery("SELECT t From TransaccionFinancieraLog l inner join l.transaccionFinanciera t where l.idTarea = :idTarea and l.idInstanciaProceso = :idInstanciaProceso")
				.setParameter("idTarea", TaskId).setParameter("idInstanciaProceso", ProcessInstanceId).getResultList();
	}
}

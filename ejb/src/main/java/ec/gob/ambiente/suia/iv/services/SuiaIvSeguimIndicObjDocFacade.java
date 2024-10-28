package ec.gob.ambiente.suia.iv.services;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import ec.gob.ambiente.enlisy.actionplan.enumerador.EstadoDocumentoEnum;
import ec.gob.ambiente.suia.dto.DocumentoTo;
import ec.gob.ambiente.suia.iv.dao.SuiaIvSeguimIndicObjDocDao;
import ec.gob.ambiente.suia.iv.model.SuiaIvSeguimIndicObjDoc;

/**
 * @author avfp
 *
 */
@Stateless
public class SuiaIvSeguimIndicObjDocFacade {


	@EJB
	private SuiaIvSeguimIndicObjDocDao suiaIvSeguimIndicObjDocDao;

	

	/**
	 * cambia el estado documento de seguimiento documento 
	 * @return
	 */
	public DocumentoTo cambiarEstadoDocumento(DocumentoTo documentoTo) throws Exception {
		SuiaIvSeguimIndicObjDoc seguimientoDcoumento = suiaIvSeguimIndicObjDocDao.find(documentoTo.getId());
		seguimientoDcoumento.setEstadoDocumento(documentoTo.getEstadoDocumento().getCodigo());
		seguimientoDcoumento.setUsuarioActualiza(documentoTo.getUsuario());	
		seguimientoDcoumento.setFechaActualiza(new Date());
		suiaIvSeguimIndicObjDocDao.edit(seguimientoDcoumento);
		return covertirASeguimientoDocumentoTo(suiaIvSeguimIndicObjDocDao.edit(seguimientoDcoumento));
	}

	
	 public List<DocumentoTo> buscaPorSeguimiento(Long codigoSeguimiento) throws Exception {
		 List<DocumentoTo> seguimientoDocumentoL = new ArrayList<DocumentoTo>();
			List<SuiaIvSeguimIndicObjDoc> documentoL = suiaIvSeguimIndicObjDocDao.buscaPorSeguimiento(codigoSeguimiento);
			if (null != documentoL && documentoL.size() > 0) {
				for (SuiaIvSeguimIndicObjDoc suiaIvSeguimientoDocumento : documentoL) {
					seguimientoDocumentoL.add(covertirASeguimientoDocumentoTo(suiaIvSeguimientoDocumento));
				}
			}
			return seguimientoDocumentoL;
	 }
			
	

	/**
	 * Convierte a TO el seguimiento documento
	 * 
	 * @param suiaIvSeguimiento
	 * @return
	 */
	public DocumentoTo covertirASeguimientoDocumentoTo(SuiaIvSeguimIndicObjDoc seguimientoDcoumento ) {
		DocumentoTo documentoTo = new DocumentoTo();
		documentoTo.setId(seguimientoDcoumento.getCodigo());
		documentoTo.setRuta(seguimientoDcoumento.getRutaDocumento());
		documentoTo.setName(seguimientoDcoumento.getNombreDocumento());
		documentoTo.setExtension(seguimientoDcoumento.getFormatoDocumento());
		documentoTo.setEstadoDocumento(EstadoDocumentoEnum.convertStringToEnum(seguimientoDcoumento.getEstadoDocumento()));
		return documentoTo;
	} ;

}

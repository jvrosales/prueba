package ec.gob.ambiente.enums;

import lombok.Getter;

/**
 * @author carlos.pupo Almacena los tipos de documentos
 */
public enum TipoDocumentoSistema {


	// PRONUNCIAMIENTO DE ARCHIVO DE SOLICITUD DE PATENTE
	MCMES_PRONUNCIAMIENTO_ARCHIVO_SOLICITUD_PATENTE(5905),
	
	// DOCUMENTOS DE SUBSANACION
	MCMES_DOCUMENTO_SUBSANACION(3235),

	// PRONUNCIAMIENTO DE ARCHIVO DE SOLICITUD DE PATENTE
	MCMES_PRONUNCIAMIENTO_NEGACION_PATENTE(5906),
    
	// INFORME TECNICO
	MCMES_INFORME_TECNICO(5907),

	// PATENTE PROVISIONAL
    MCMES_PATENTE_PROVISIONAL(5908),
    
    // PATENTE
    MCMES_PATENTE(5909),

    // DOCUMENTO EmiRenCenResRehJarBotVivOrqZoo
    MCMES_EmiRenCenResRehJarBotVivOrqZoo(5910),
    
	// DOCUMENTO EmiPerNatCatMusHerCep
    MCMES_EmiPerNatCatMusHerCep(5911),

	// DOCUMENTO EmiRenCenResRehJarBotVivOrqZooAdm
	MCMES_EmiRenCenResRehJarBotVivOrqZooAdm(5912),

	// DOCUMENTO EmiMusHerCepSinAdm
	MCMES_EmiMusHerCepSinAdm(5913),

	// DOCUMENTO EmiCenResRehJarBotVivOrqZooAdm
	MCMES_EmiCenResRehJarBotVivOrqZooAdm(5914),
	
	//NUT PAGOS --PROCESO RECAUDACIONES
	RECAUDACIONES_NUT_PAGOS_XBANCO(3599),
	RECAUDACIONES_NUT_PAGOS(3600),
	COMPROBANTE_PAGO_NUT(3601),	
	
	// DOCUMENTO EmiMusHerCepAdm
	MCMES_EmiMusHerCepAdm(5915);
	
	
	
	
	@Getter
	private int idTipoDocumento;

	private TipoDocumentoSistema(int idTipoDocumento) {
  	  this.idTipoDocumento = idTipoDocumento;
	}
}



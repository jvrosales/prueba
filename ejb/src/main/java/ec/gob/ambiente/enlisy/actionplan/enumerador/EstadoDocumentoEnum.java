package ec.gob.ambiente.enlisy.actionplan.enumerador;

import lombok.Getter;

/**
 * Estados que se manejaran en los planes de acción
 * @author EXCO
 *
 */
public enum EstadoDocumentoEnum {
	PUBLICO("PUB","ACCESO PÚBLICO"),
	PRIVADO("PRI","ACCESO PRIVADO"),
	COMPARTIDO("COM","CON AUTORIZACIÓN");
		
	@Getter
    private String codigo;
	
	@Getter
    private String valorEstado;//Estado

    private EstadoDocumentoEnum(String codigo,String valorEstado) {            
       this.codigo=codigo;
       this.valorEstado=valorEstado;
    }
    
	 public static EstadoDocumentoEnum convertStringToEnum(String texto) {
	    	for(EstadoDocumentoEnum tipo: EstadoDocumentoEnum.values()) {
	    		if(tipo.getCodigo().equals(texto)) {
	    			return tipo;
	    		}
	    	}
	    	return null;
	    }
}

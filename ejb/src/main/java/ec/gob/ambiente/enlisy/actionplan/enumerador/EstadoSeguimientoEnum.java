package ec.gob.ambiente.enlisy.actionplan.enumerador;

import lombok.Getter;

/**
 * Estados que se manejaran en los planes de acción
 * @author EXCO
 *
 */
public enum EstadoSeguimientoEnum {
	INGRESADO("ING","Ingresado"),
	APROBADO("APR","Aprobado");
		
	@Getter
    private String codigo;
	
	@Getter
    private String valorEstado;

    private EstadoSeguimientoEnum(String codigo,String valorEstado) {            
       this.codigo=codigo;
       this.valorEstado=valorEstado;
    }
    
	 public static EstadoSeguimientoEnum convertStringToEnum(String texto) {
	    	for(EstadoSeguimientoEnum tipo: EstadoSeguimientoEnum.values()) {
	    		if(tipo.getCodigo().equals(texto)) {
	    			return tipo;
	    		}
	    	}
	    	return null;
	    }
}

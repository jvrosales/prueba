package ec.gob.ambiente.enlisy.actionplan.enumerador;

import lombok.Getter;

/**
 * Estados que se manejaran en los planes de acción
 * @author EXCO
 *
 */
public enum EstadoPlanAccionEnum {
	
	INGRESADO("ING","Ingresado"),
	ELIMINADO("ELI","Eliminado"),
	PUBLICADO("PUB","Publicado"),
	FINALIZADO("FIN","Finalizado");
		
	@Getter
    private String codigo;
	
	@Getter
    private String valorEstado;//Estado

    private EstadoPlanAccionEnum(String codigo,String valorEstado) {            
       this.codigo=codigo;
       this.valorEstado=valorEstado;
    }
    
	 public static EstadoPlanAccionEnum convertStringToEnum(String texto) {
	    	for(EstadoPlanAccionEnum tipo: EstadoPlanAccionEnum.values()) {
	    		if(tipo.getCodigo().equals(texto)) {
	    			return tipo;
	    		}
	    	}
	    	return null;
	    }
}

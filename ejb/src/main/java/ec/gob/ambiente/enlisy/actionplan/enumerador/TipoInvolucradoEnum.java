package ec.gob.ambiente.enlisy.actionplan.enumerador;

import lombok.Getter;

/**
 * Estados que se manejaran en los planes de acción
 * @author EXCO
 *
 */
public enum TipoInvolucradoEnum {
	ONG("ONG","ONG"),
	PERSONA_NATURAL("PER","Persona Natural"),
	PERSONA_JURIDICA("PER","Persona Jurídica"),
	PUBLICO("PUB","Empresa Pública"),
	PRIVADO("PRI","Empresa Privada"), 
	OTROS("OTR","Otro"),;
		
	@Getter
    private String codigo;
	
	@Getter
    private String valorEstado;

    private TipoInvolucradoEnum(String codigo,String valorEstado) {            
       this.codigo=codigo;
       this.valorEstado=valorEstado;
    }
    
	 public static TipoInvolucradoEnum convertStringToEnum(String texto) {
	    	for(TipoInvolucradoEnum tipo: TipoInvolucradoEnum.values()) {
	    		if(tipo.getCodigo().equals(texto)) {
	    			return tipo;
	    		}
	    	}
	    	return null;
	    }
}

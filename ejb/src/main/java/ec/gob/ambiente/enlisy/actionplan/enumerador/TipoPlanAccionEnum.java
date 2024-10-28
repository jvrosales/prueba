package ec.gob.ambiente.enlisy.actionplan.enumerador;

import lombok.Getter;

/**
 * Estados que se manejaran en los planes de acción
 * @author EXCO
 *
 */
public enum TipoPlanAccionEnum {

	TIPO_ESPECIE("ESP","Especie"),
	TIPO_GRUPO("GRP","Grupo");
		
	@Getter
    private String codigo;
	
	@Getter
    private String valorEstado;//Estado

    private TipoPlanAccionEnum(String codigo,String valorEstado) {            
       this.codigo=codigo;
       this.valorEstado=valorEstado;
    }
    
    public static TipoPlanAccionEnum convertStringToEnum(String texto) {
    	for(TipoPlanAccionEnum tipo: TipoPlanAccionEnum.values()) {
    		if(tipo.getCodigo().equals(texto)) {
    			return tipo;
    		}
    	}
    	return null;
    }
}

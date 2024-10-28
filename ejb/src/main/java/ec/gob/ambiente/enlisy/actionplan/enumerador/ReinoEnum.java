package ec.gob.ambiente.enlisy.actionplan.enumerador;

import lombok.Getter;

/**
 * Estados que se manejaran en los planes de acción
 * @author EXCO
 *
 */
public enum ReinoEnum {
	BACTERIA(45538,"BACTERIA","ARGR_EUBACTERIA"),
	ARCHAEA(45549,"ARCHAEA","ARGR_ARCHEOBACTERIA"),
	VIRUSES(57110,"VIRUSES","ARGR_VIRUSES"),
	CHROMISTA(43621,"CHROMISTA","ARGR_CHROMISTA"),
	PROTOZOA(45588,"PROTOZOA","ARGR_PROTISTA"),
	FUNGI(51228,"FUNGI","ARGR_FUNGI"),
	PLANTAE(1,"PLANTAE","ARGR_PLANTAE"),
	ANIMAL(42931,"ANIMAL","ARGR_ANIMAL");
		
	
	@Getter
    private Integer codigo;
	
	@Getter
    private String nombre;
	
	@Getter
    private String tipo;

    private ReinoEnum(Integer codigo,String nombre, String tipo) {                    
        this.codigo=codigo;
        this.nombre=nombre;
        this.tipo = tipo;

    }
    
	 public static ReinoEnum convertIntToEnum(Integer codigo) {
	    	for(ReinoEnum tipo: ReinoEnum.values()) {
	    		if(tipo.getCodigo().equals(codigo)) {
	    			return tipo;
	    		}
	    	}
	    	return null;
	    }

	 public static String obtenerNombre(Integer codigo) {
	    	for(ReinoEnum tipo: ReinoEnum.values()) {
	    		if(tipo.getCodigo().equals(codigo)) {
	    			return tipo.getNombre();
	    		}
	    	}
	    	return null;
	    }
}

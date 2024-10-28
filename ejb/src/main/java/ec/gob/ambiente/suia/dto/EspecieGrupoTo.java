package ec.gob.ambiente.suia.dto;

import lombok.Getter;
import lombok.Setter;

public class EspecieGrupoTo {
	
	@Getter
	@Setter
	private Integer id;
	
	@Getter
	@Setter
	private String nombre;
	
	@Getter
	@Setter
	private String cientifico;
	
	@Getter
	@Setter
	private String descripcion;
	
	@Getter
	@Setter
	private Boolean seleccionado;
	
	@Getter
	@Setter
	private String sptaScientificGui;

}

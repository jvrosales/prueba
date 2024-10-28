package ec.gob.ambiente.suia.dto;

import lombok.Getter;
import lombok.Setter;

public class PoliticaTo {
	
	@Getter
	@Setter
	private Integer id;
	
	@Getter
	@Setter
	private String codigoObjetivoCatalogo;
	
	@Getter
	@Setter
	private String nombre;
	
	@Getter
	@Setter
	private String descripcion;
	
	@Getter
	@Setter
	private String usuario;

	@Getter
	@Setter
	private Boolean estado;
}

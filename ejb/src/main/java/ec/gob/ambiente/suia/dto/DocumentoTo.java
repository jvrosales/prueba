package ec.gob.ambiente.suia.dto;

import ec.gob.ambiente.enlisy.actionplan.enumerador.EstadoDocumentoEnum;
import lombok.Getter;
import lombok.Setter;

public class DocumentoTo {
	
	@Getter
	@Setter
	private Long id;
	
	@Getter
	@Setter
	private byte[] content;
	
	@Getter
	@Setter
	private String mime;
	
	@Getter
	@Setter
	private String name;
	
	@Getter
	@Setter
	private String extension;
	
	@Getter
	@Setter
	private String ruta;
	
	@Getter
	@Setter
	private String usuario;
	
	
	@Getter
	@Setter
	private EstadoDocumentoEnum estadoDocumento;
	
	
	

}

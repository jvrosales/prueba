package ec.gob.ambiente.sib_suia_ora.summaries.model;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;

import lombok.Getter;
import lombok.Setter;

@XmlAccessorType(value = javax.xml.bind.annotation.XmlAccessType.FIELD)
public class AccessRights implements Serializable {

	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Getter
	@Setter
	@XmlAttribute(name = "comment")
	private String comment = "Declaración de responsabilidad como autoridad ambiental para uso oficial de la información contenida en el sumario";
	
	@Getter
	@Setter
	@XmlAttribute(name = "value")
    private String value="El Ministerio del Ambiente certifica que esta ficha, y la validez de su contenido, fue elaborada en ejercicio de su facultad legal como organismo rector en materia ambiental, el mismo que puede ser consultado en el sitio web del Sistema Único de Información Ambiental";
}

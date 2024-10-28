package ec.gob.ambiente.sib_suia_ora.summaries.model;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;

import lombok.Getter;
import lombok.Setter;

@XmlAccessorType(value = javax.xml.bind.annotation.XmlAccessType.FIELD)
public class Collaborator implements Serializable {

	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Getter
	@Setter
	@XmlAttribute(name = "comment")
	private String comment = "Colaborador. Persona que trabajó con el o los autores en la realización del sumario sin aportar conocimiento sino esfuerzo en sistematizar y digitalizar la información";
	
	@Getter
	@Setter
	@XmlAttribute(name = "value")
    private String value;
}

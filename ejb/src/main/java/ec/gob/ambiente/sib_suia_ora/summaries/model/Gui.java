package ec.gob.ambiente.sib_suia_ora.summaries.model;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;

import lombok.Getter;
import lombok.Setter;

@XmlAccessorType(value = javax.xml.bind.annotation.XmlAccessType.FIELD)
public class Gui implements Serializable {

	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Getter
	@Setter
	@XmlAttribute(name = "comment")
	private String comment = "Global Unique Identifier. Código único nacional del Catálogo Nacional de Objetos Biológicos";
	
	@Getter
	@Setter
	@XmlAttribute(name = "value")
    private String value;
}

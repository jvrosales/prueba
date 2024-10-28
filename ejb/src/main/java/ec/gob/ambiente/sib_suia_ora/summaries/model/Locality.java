package ec.gob.ambiente.sib_suia_ora.summaries.model;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;

import lombok.Getter;
import lombok.Setter;

@XmlAccessorType(value = javax.xml.bind.annotation.XmlAccessType.FIELD)
public class Locality implements Serializable {

	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Getter
	@Setter
	@XmlAttribute(name = "comment")
	private String comment = "Etnia o descripción específica del área donde se usa originalmente el nombre común o vernáculo";
	
	@Getter
	@Setter
	@XmlAttribute(name = "value")
    private String value;
}

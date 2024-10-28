package ec.gob.ambiente.sib_suia_ora.summaries.model;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;

import lombok.Getter;
import lombok.Setter;

@XmlAccessorType(value = javax.xml.bind.annotation.XmlAccessType.FIELD)
public class Image implements Serializable {

	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Getter
	@Setter
	@XmlElement(name = "type")
    private TypeIma type;
	
	@Getter
	@Setter
	@XmlElement(name = "title")
    private Title title;
	
	@Getter
	@Setter
	@XmlElement(name = "creator")
    private Creator creator;
	
	@Getter
	@Setter
	@XmlElement(name = "description")
    private DescriptionIma description;
	
	
	@Getter
	@Setter
	@XmlElement(name = "identifier")
    private Identifier identifier;
	
	
}

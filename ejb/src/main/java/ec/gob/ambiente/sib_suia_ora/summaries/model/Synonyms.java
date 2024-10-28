package ec.gob.ambiente.sib_suia_ora.summaries.model;

import java.io.Serializable;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;

import lombok.Getter;
import lombok.Setter;

@XmlAccessorType(value = javax.xml.bind.annotation.XmlAccessType.FIELD)
public class Synonyms implements Serializable {

	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	
	@Getter
	@Setter
	@XmlElement
    private List<Synonym> synonym;
	
	@Getter
	@Setter
	@XmlAttribute(name = "comment")
	private String comment = "Lista de scientificNames sinónimos o relacionados al scientificName actual. Terms: synonymName, synonymGUI";
}

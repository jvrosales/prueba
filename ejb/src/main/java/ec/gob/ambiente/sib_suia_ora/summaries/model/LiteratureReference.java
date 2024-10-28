package ec.gob.ambiente.sib_suia_ora.summaries.model;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;

import lombok.Getter;
import lombok.Setter;

@XmlAccessorType(value = javax.xml.bind.annotation.XmlAccessType.FIELD)
public class LiteratureReference implements Serializable {

	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Getter
	@Setter
	@XmlElement(name = "type")
    private Type type;
	
	@Getter
	@Setter
	@XmlElement(name = "bibliographicCitation")
    private BibliographicCitation bibliographicCitation;
	
	@Getter
	@Setter
	@XmlElement(name = "url")
    private Url url;
	
	@Getter
	@Setter
	@XmlElement(name = "Principal")
    private Principal Principal;
	
	
}

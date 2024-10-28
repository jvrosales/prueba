package ec.gob.ambiente.sib_suia_ora.summaries.model;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;

import lombok.Getter;
import lombok.Setter;

@XmlAccessorType(value = javax.xml.bind.annotation.XmlAccessType.FIELD)
public class VernacularN implements Serializable {

	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Getter
	@Setter
	@XmlElement(name = "vernacularName")
    private VernacularName vernacularName;
	
	@Getter
	@Setter
	@XmlElement(name = "locality")
    private Locality locality;
	
	@Getter
	@Setter
	@XmlElement(name = "language")
    private LanguageV language;
	
	@Getter
	@Setter
	@XmlElement(name = "source")
    private Source source;
}

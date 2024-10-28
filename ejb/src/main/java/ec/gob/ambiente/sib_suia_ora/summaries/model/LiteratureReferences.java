package ec.gob.ambiente.sib_suia_ora.summaries.model;

import java.io.Serializable;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;

import lombok.Getter;
import lombok.Setter;

@XmlAccessorType(value = javax.xml.bind.annotation.XmlAccessType.FIELD)
public class LiteratureReferences implements Serializable {

	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	
	@Getter
	@Setter
	@XmlElement
    private List<LiteratureReference> literatureReference;
	
	@Getter
	@Setter
	@XmlAttribute(name = "comment")
	private String comment = "Lista de citas bibliográficas referidas en el sumario. Terms: Type, bibliographicCitation, url";
	
}

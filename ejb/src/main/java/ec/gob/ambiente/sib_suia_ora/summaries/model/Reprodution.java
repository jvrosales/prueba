package ec.gob.ambiente.sib_suia_ora.summaries.model;

import java.io.Serializable;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;

import lombok.Getter;
import lombok.Setter;

@XmlAccessorType(value = javax.xml.bind.annotation.XmlAccessType.FIELD)
public class Reprodution implements Serializable {

	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Getter
	@Setter
	@XmlAttribute(name="comment")
    private String comment="Reproducción";
	
	@Getter
	@Setter
	@XmlElement
    private List<ReproductionValue> reproduction;
}

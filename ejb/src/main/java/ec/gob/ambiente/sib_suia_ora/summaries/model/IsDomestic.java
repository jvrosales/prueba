package ec.gob.ambiente.sib_suia_ora.summaries.model;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;

import lombok.Getter;
import lombok.Setter;

@XmlAccessorType(value = javax.xml.bind.annotation.XmlAccessType.FIELD)
public class IsDomestic implements Serializable {

	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Getter
	@Setter
	@XmlAttribute(name = "comment")
	private String comment = "En caso de flora y hongos, el término utilizado es cultivada. Doméstico aplica para animales";
	
	@Getter
	@Setter
	@XmlAttribute(name = "value")
    private Boolean value;
}

package ec.gob.ambiente.sib_suia_ora.summaries.model;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;

import lombok.Getter;
import lombok.Setter;

@XmlAccessorType(value = javax.xml.bind.annotation.XmlAccessType.FIELD)
public class RadialCutSawnWood implements Serializable {

	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Getter
	@Setter
	@XmlAttribute(name = "comment")
	private String comment = "Corte radial en madera aserrada";
	
	@Getter
	@Setter
	@XmlElement(name = "veined")
	private Veined veined;
	
	@Getter
	@Setter
	@XmlElement(name = "glossShine")
	private GlossShine glossShine;
	
		
	

}

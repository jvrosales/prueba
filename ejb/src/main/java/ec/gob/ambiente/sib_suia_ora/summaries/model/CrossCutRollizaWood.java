package ec.gob.ambiente.sib_suia_ora.summaries.model;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;

import lombok.Getter;
import lombok.Setter;

@XmlAccessorType(value = javax.xml.bind.annotation.XmlAccessType.FIELD)
public class CrossCutRollizaWood implements Serializable {

	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Getter
	@Setter
	@XmlAttribute(name = "comment")
	private String comment = "Corte transversal en madera rolliza";
	
	@Getter
	@Setter
	@XmlElement(name = "colorHeartwood")
	private ColorHeartwood colorHeartwood;
	
	@Getter
	@Setter
	@XmlElement(name = "odor")
	private Odor odor;
	
	@Getter
	@Setter
	@XmlElement(name = "taste")
	private Taste taste;
	
	@Getter
	@Setter
	@XmlElement(name = "latexResin")
	private LatexResin latexResin;
	
	@Getter
	@Setter
	@XmlElement(name = "outerCortex")
	private OuterCortex outerCortex;


}

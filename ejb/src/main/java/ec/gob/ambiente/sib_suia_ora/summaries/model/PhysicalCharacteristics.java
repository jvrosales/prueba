package ec.gob.ambiente.sib_suia_ora.summaries.model;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;

import lombok.Getter;
import lombok.Setter;

@XmlAccessorType(value = javax.xml.bind.annotation.XmlAccessType.FIELD)
public class PhysicalCharacteristics implements Serializable {

	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Getter
	@Setter
	@XmlAttribute(name = "comment")
	private String comment = "Características Físicas";
	
	@Getter
	@Setter
	@XmlElement(name = "density")
	private Density density;
	
	@Getter
	@Setter
	@XmlElement(name = "normalContraction")
	private NormalContraction normalContraction;
	
	@Getter
	@Setter
	@XmlElement(name = "totalContraction")
	private TotalContraction totalContraction;
	
	

}

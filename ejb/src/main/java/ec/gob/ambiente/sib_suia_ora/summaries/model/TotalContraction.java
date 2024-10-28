package ec.gob.ambiente.sib_suia_ora.summaries.model;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;

import lombok.Getter;
import lombok.Setter;

@XmlAccessorType(value = javax.xml.bind.annotation.XmlAccessType.FIELD)
public class TotalContraction implements Serializable {

	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Getter
	@Setter
	@XmlAttribute(name = "comment")
	private String comment = "Contración total en %";
	
	@Getter
	@Setter
	@XmlElement(name = "radial")
	private Radial radial;
	
	@Getter
	@Setter
	@XmlElement(name = "tangential")
	private Tangential tangential;
	
	@Getter
	@Setter
	@XmlElement(name = "volumetric")
	private Volumetric volumetric;
	
	@Getter
	@Setter
	@XmlElement(name = "trRatio")
	private TrRatio trRatio;
	
		
	

}

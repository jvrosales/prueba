package ec.gob.ambiente.sib_suia_ora.summaries.model;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;

import lombok.Getter;
import lombok.Setter;

@XmlAccessorType(value = javax.xml.bind.annotation.XmlAccessType.FIELD)
public class Density implements Serializable {

	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Getter
	@Setter
	@XmlAttribute(name = "comment")
	private String comment = "Densidad en g/cm3";
	
	@Getter
	@Setter
	@XmlElement(name = "inGreen")
	private InGreen inGreen;
	
	@Getter
	@Setter
	@XmlElement(name = "airDry")
	private AirDry airDry;
	
	@Getter
	@Setter
	@XmlElement(name = "anhydrous")
	private Anhydrous anhydrous;
	
	@Getter
	@Setter
	@XmlElement(name = "basic")
	private  Basic basic;
	
		
	

}

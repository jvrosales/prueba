package ec.gob.ambiente.sib_suia_ora.summaries.model;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;

import lombok.Getter;
import lombok.Setter;

@XmlAccessorType(value = javax.xml.bind.annotation.XmlAccessType.FIELD)
public class OrganolepticCharacteristics implements Serializable {

	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Getter
	@Setter
	@XmlAttribute(name = "comment")
	private String comment = "Características organolépticas";
	
	
	
	@Getter
	@Setter
	@XmlElement(name = "hardnessOrWeight")
	private HardnessOrWeight hardnessOrWeight;
	
	@Getter
	@Setter
	@XmlElement(name = "crossCutRollizaWood")
	private CrossCutRollizaWood crossCutRollizaWood;
	
	@Getter
	@Setter
	@XmlElement(name = "tangentalCutSawnWood")
	private TangentalCutSawnWood tangentalCutSawnWood;
	
	@Getter
	@Setter
	@XmlElement(name = "radialCutSawnWood")
	private RadialCutSawnWood radialCutSawnWood;
	
	
	

}

package ec.gob.ambiente.sib_suia_ora.summaries.model;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;

import lombok.Getter;
import lombok.Setter;

@XmlAccessorType(value = javax.xml.bind.annotation.XmlAccessType.FIELD)
public class CrossCutSawnWood implements Serializable {

	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Getter
	@Setter
	@XmlAttribute(name = "comment")
	private String comment = "Corte transversal en madera aserrada";
	
	@Getter
	@Setter
	@XmlElement(name = "growthRings")
	private GrowthRings growthRings;
	
	@Getter
	@Setter
	@XmlElement(name = "pores")
	private Pores pores;
	
	@Getter
	@Setter
	@XmlElement(name = "axialParenchyma")
	private AxialParenchyma axialParenchyma;
	
	@Getter
	@Setter
	@XmlElement(name = "radialParenchyma")
	private RadialParenchyma radialParenchyma;
	
		
	

}

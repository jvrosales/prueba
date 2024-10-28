package ec.gob.ambiente.sib_suia_ora.summaries.model;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;

import lombok.Getter;
import lombok.Setter;

@XmlAccessorType(value = javax.xml.bind.annotation.XmlAccessType.FIELD)
public class Control implements Serializable {

	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	
	
	@Getter
	@Setter
	@XmlElement(name = "physicalControl")
    private PhysicalControl physicalControl;
	
	@Getter
	@Setter
	@XmlElement(name = "chemicalControl")
    private ChemicalControl chemicalControl;
	
	@Getter
	@Setter
	@XmlElement(name = "biologicalControl")
    private BiologicalControl biologicalControl;
	
	@Getter
	@Setter
	@XmlElement(name = "preventionMeasure")
    private PreventionMeasure preventionMeasure;
	
	@Getter
	@Setter
	@XmlElement(name = "riskAnalysis")
    private RiskAnalysis riskAnalysis;
}

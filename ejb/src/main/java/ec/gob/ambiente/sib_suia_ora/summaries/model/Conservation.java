package ec.gob.ambiente.sib_suia_ora.summaries.model;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;

import lombok.Getter;
import lombok.Setter;

@XmlAccessorType(value = javax.xml.bind.annotation.XmlAccessType.FIELD)
public class Conservation implements Serializable {

	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Getter
	@Setter
	@XmlAttribute(name = "comment")
	private String comment = "Descripción sobre el estado de conservación y los esfuerzos relizados para una especie";
	
	@Getter
	@Setter
	@XmlElement(name = "threatStatus")
    private ThreatStatus threatStatus;
	
	@Getter
	@Setter
	@XmlElement(name = "redListEcCriteria")
    private RedListEcCriteria redListEcCriteria;
	
	@Getter
	@Setter
	@XmlElement(name = "appendixCITES")
    private AppendixCITES appendixCITES;
	
	@Getter
	@Setter
	@XmlElement(name = "conservationMeasure")
    private ConservationMeasure conservationMeasure;
}

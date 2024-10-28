package ec.gob.ambiente.sib_suia_ora.summaries.model;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;

import lombok.Getter;
import lombok.Setter;

@XmlAccessorType(value = javax.xml.bind.annotation.XmlAccessType.FIELD)
public class Impacts implements Serializable {

	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	
	@Getter
	@Setter
	@XmlElement(name = "speciesAffected")
    private SpeciesAffected speciesAffected;
	
	@Getter
	@Setter
	@XmlElement(name = "ecologicalImpact")
    private EcologicalImpact ecologicalImpact;
	
	@Getter
	@Setter
	@XmlElement(name = "economicImpact")
    private EconomicImpact economicImpact;
	
	@Getter
	@Setter
	@XmlElement(name = "socialImpact")
    private SocialImpact socialImpact;
	
	@Getter
	@Setter
	@XmlElement(name = "healthImpact")
    private HealthImpact healthImpact;
	
	
	
}

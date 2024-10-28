package ec.gob.ambiente.sib_suia_ora.summaries.model;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;

import lombok.Getter;
import lombok.Setter;

@XmlAccessorType(value = javax.xml.bind.annotation.XmlAccessType.FIELD)
public class Distribution implements Serializable {

	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Getter
	@Setter
	@XmlElement(name = "distribution")
    private DistributionDis distribution;
	
	@Getter
	@Setter
	@XmlElement(name = "altitudeRange")
    private AltitudeRange altitudeRange;
	
	@Getter
	@Setter
	@XmlElement(name = "provinces")
    private Provinces provinces;
	
	@Getter
	@Setter
	@XmlElement(name = "protectedAreas")
    private ProtectedAreas protectedAreas;
	
	@Getter
	@Setter
	@XmlElement(name = "ecosystems")
    private Ecosystems ecosystems;
	
	
}

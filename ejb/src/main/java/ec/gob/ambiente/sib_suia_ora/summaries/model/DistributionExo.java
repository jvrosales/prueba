package ec.gob.ambiente.sib_suia_ora.summaries.model;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;

import lombok.Getter;
import lombok.Setter;

@XmlAccessorType(value = javax.xml.bind.annotation.XmlAccessType.FIELD)
public class DistributionExo implements Serializable {

	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Getter
	@Setter
	@XmlElement(name = "nativeDistributionArea")
    private NativeDistributionArea nativeDistributionArea;
	
	@Getter
	@Setter
	@XmlElement(name = "naturalEnvironment")
    private NaturalEnvironment naturalEnvironment;
	
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

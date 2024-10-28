package ec.gob.ambiente.sib_suia_ora.summaries.model;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;

import lombok.Getter;
import lombok.Setter;

@XmlAccessorType(value = javax.xml.bind.annotation.XmlAccessType.FIELD)
public class DescriptionForestal implements Serializable {

	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Getter
	@Setter
	@XmlElement(name = "general")
    private General general;
	
	@Getter
	@Setter
	@XmlElement(name = "botanicalDescription")
    private BotanicalDescription botanicalDescription;
	
	@Getter
	@Setter
	@XmlElement(name = "conditionalUse")
    private ConditionalUse conditionalUse;
	
	@Getter
	@Setter
	@XmlElement(name = "similarSpecies")
    private SimilarSpecies similarSpecies;
	
	@Getter
	@Setter
	@XmlElement(name = "ecology")
    private Ecology ecology;

	
	@Getter
	@Setter
	@XmlElement(name = "uses")
    private Uses uses;
	
	@Getter
	@Setter
	@XmlElement(name = "volumenSafAnalysis")
    private VolumenSafAnalysis volumenSafAnalysis;


	@Getter
	@Setter
	@XmlElement(name = "botanicalWoodCollections")
    private BotanicalWoodCollections botanicalWoodCollections;
	
}

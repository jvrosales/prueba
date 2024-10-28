package ec.gob.ambiente.sib_suia_ora.summaries.model;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import lombok.Getter;
import lombok.Setter;

@XmlRootElement(name = "SumForeSpResult")
@XmlAccessorType(value = javax.xml.bind.annotation.XmlAccessType.FIELD)
public class SumForeSpResult implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

		
	@Getter
	@Setter
	@XmlElement(name = "metadata")
    private Metadata metadata;
	
	@Getter
	@Setter
	@XmlElement(name = "taxon")
    private Taxon taxon;
	
	@Getter
	@Setter
	@XmlElement(name = "description")
    private DescriptionForestal description;
	
	@Getter
	@Setter
	@XmlElement(name = "organolepticCharacteristics")
    private OrganolepticCharacteristics organolepticCharacteristics;
	
	@Getter
	@Setter
	@XmlElement(name = "anatomicalCharacteristics")
    private AnatomicalCharacteristics anatomicalCharacteristics;
	
	@Getter
	@Setter
	@XmlElement(name = "physicalCharacteristics")
    private PhysicalCharacteristics physicalCharacteristics;
	

	@Getter
	@Setter
	@XmlElement(name = "vernacularNames")
    private VernacularNames vernacularNames;
	
	@Getter
	@Setter
	@XmlElement(name = "synonyms")
    private Synonyms synonyms;
	
	@Getter
	@Setter
	@XmlElement(name = "habits")
    private Habits habits;
	
	@Getter
	@Setter
	@XmlElement(name = "distribution")
    private Distribution distribution;
	
	@Getter
	@Setter
	@XmlElement(name = "conservation")
    private Conservation conservation;
	
	@Getter
	@Setter
	@XmlElement(name = "literatureReferences")
    private LiteratureReferences literatureReferences;
	
	@Getter
	@Setter
	@XmlElement(name = "images")
    private Images images;
}

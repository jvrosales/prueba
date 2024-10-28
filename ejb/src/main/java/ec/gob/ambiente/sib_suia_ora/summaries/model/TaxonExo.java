package ec.gob.ambiente.sib_suia_ora.summaries.model;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;

import lombok.Getter;
import lombok.Setter;

@XmlAccessorType(value = javax.xml.bind.annotation.XmlAccessType.FIELD)
public class TaxonExo implements Serializable {

	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Getter
	@Setter
	@XmlElement(name = "gui")
    private Gui gui;
	
	@Getter
	@Setter
	@XmlElement(name = "kingdom")
    private Kingdom kingdom;
	
	@Getter
	@Setter
	@XmlElement(name = "phylum")
    private Phylum phylum;
	
	@Getter
	@Setter
	@XmlElement(name = "clas")
    private Clas clas;
	
	@Getter
	@Setter
	@XmlElement(name = "order")
    private Order order;
	
	@Getter
	@Setter
	@XmlElement(name = "family")
    private Family family;
	
	@Getter
	@Setter
	@XmlElement(name = "genus")
    private Genus genus;
	
	@Getter
	@Setter
	@XmlElement(name = "specificInfraspecificEpithet")
    private SpecificInfraspecificEpithet specificInfraspecificEpithet;
	
	@Getter
	@Setter
	@XmlElement(name = "scientificName")
    private ScientificName scientificName;
	
	@Getter
	@Setter
	@XmlElement(name = "taxonRank")
    private TaxonRank taxonRank;
	
	@Getter
	@Setter
	@XmlElement(name = "scientificNameAuthorship")
    private ScientificNameAuthorship scientificNameAuthorship;
	
	@Getter
	@Setter
	@XmlElement(name = "namePublishedInYear")
    private NamePublishedInYear namePublishedInYear;
	
	@Getter
	@Setter
	@XmlElement(name = "inEcuador")
    private InEcuador inEcuador;
	
	@Getter
	@Setter
	@XmlElement(name = "isNative")
    private IsNative isNative;
	
	@Getter
	@Setter
	@XmlElement(name = "isEndemic")
    private IsEndemic isEndemic;
	
	@Getter
	@Setter
	@XmlElement(name = "isExotic")
    private IsExotic isExotic;
	
	@Getter
	@Setter
	@XmlElement(name = "isInvasive")
    private IsInvasive isInvasive;
	
	@Getter
	@Setter
	@XmlElement(name = "isDomestic")
    private IsDomestic isDomestic;
	
	@Getter
	@Setter
	@XmlElement(name = "taxonomicStatus")
    private TaxonomicStatus taxonomicStatus;
	
	@Getter
	@Setter
	@XmlElement(name = "isCorrectName")
    private IsCorrectName isCorrectName;
	
	@Getter
	@Setter
	@XmlElement(name = "correctScientificName")
    private CorrectScientificName correctScientificName;
	
}

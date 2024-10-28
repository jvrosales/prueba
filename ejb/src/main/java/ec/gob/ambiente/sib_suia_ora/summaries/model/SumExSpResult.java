package ec.gob.ambiente.sib_suia_ora.summaries.model;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import lombok.Getter;
import lombok.Setter;

/**
 * Objeto que contiene la informacion taxonomica
 * de un nombre o nombres Cientificos enviados como parametros
 * en el servicio web
 * @author CristinaFactos
 *
 */
@XmlRootElement(name = "SumExSpResult")
@XmlAccessorType(value = javax.xml.bind.annotation.XmlAccessType.FIELD)
public class SumExSpResult implements Serializable {

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
    private TaxonExo taxon;
	
	@Getter
	@Setter
	@XmlElement(name = "description")
    private DescriptionExotica description;
	
	@Getter
	@Setter
	@XmlElement(name = "characteristics")
    private Characteristics characteristics;
	
	@Getter
	@Setter
	@XmlElement(name = "impacts")
    private Impacts impacts;
	
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
	@XmlElement(name = "introduction")
    private Introduction introduction;
	
	@Getter
	@Setter
	@XmlElement(name = "control")
    private Control control;
	
	@Getter
	@Setter
	@XmlElement(name = "distribution")
    private DistributionExo distribution;
	
	@Getter
	@Setter
	@XmlElement(name = "literatureReferences")
    private LiteratureReferences literatureReferences;
	
	@Getter
	@Setter
	@XmlElement(name = "images")
    private Images images;
	
	
	
	


}

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
@XmlRootElement(name = "SumGSpResult")
@XmlAccessorType(value = javax.xml.bind.annotation.XmlAccessType.FIELD)
public class SumGSpResult implements Serializable {

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
    private Description description;
	
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
	@XmlElement(name = "Conservation")
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

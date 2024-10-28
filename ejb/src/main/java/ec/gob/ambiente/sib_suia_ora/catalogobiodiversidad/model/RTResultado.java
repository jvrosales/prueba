package ec.gob.ambiente.sib_suia_ora.catalogobiodiversidad.model;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

import lombok.Getter;
import lombok.Setter;

/**
 * Objeto que contiene la informacion taxonomica
 * de un nombre o nombres Cientificos enviados como parametros
 * en el servicio web
 * @author CristinaFactos
 *
 */
@XmlRootElement(name = "RTResultado")
@XmlAccessorType(value = javax.xml.bind.annotation.XmlAccessType.FIELD)
public class RTResultado implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

		
	@Getter
	@Setter
	@XmlElement(name = "scientificName")
	private String scientificName;
	
		
	@Getter
	@Setter
	@XmlElement(name = "taxonID")
	private String taxonID;
	
	@Getter
	@Setter
	@XmlElement(name = "taxonRank")
	private String taxonRank;
	
	
	@Getter
	@Setter
	@XmlElement(name = "scientificNameAuthorship")
	private String scientificNameAuthorship;
	
	@Getter
	@Setter
	@XmlElement(name = "namePublishedInYear")
	private String namePublishedInYear;
	
	@Getter
	@Setter
	@XmlElement(name = "isAcceptedName")
	private Boolean isAcceptedName;
	
	
	
	@Getter
	@Setter
	@XmlElement(name = "taxonomicStatus")
	private String taxonomicStatus;

	

	
	@Getter
	@Setter
	@XmlElement(name = "correctName")
	private String correctName;
	
	@Getter
	@Setter
	@XmlElement(name = "nationalRedList")
	String nationalRedList;
	
	@Getter
	@Setter
	@XmlElement(name = "cites")
	private String cites;
	
	@Getter
	@Setter
	@XmlElement(name = "isEndemic")
	private Boolean isEndemic;
	
		
	@Getter
	@Setter
	@XmlElement(name = "isExotic")
	private Boolean isExotic;
	
	@Getter
	@Setter
	@XmlElement(name = "isDomestic")
	private Boolean isDomestic;
	
	@Getter
	@Setter
	@XmlElement(name = "isNative")
	private Boolean isNative;
	
	@Getter
	@Setter
	@XmlElement(name = "isMigratory")
	private Boolean isMigratory;
	
	@Getter
	@Setter
	@XmlElement(name = "isInvasive")
	private Boolean isInvasive;
	
	@Getter
	@Setter
	@XmlElement(name = "kingdom")
	private String kingdom;
	
	@Getter
	@Setter
	@XmlElement(name = "phylum")
	private String phylum;
	
	
	@Getter
	@Setter
	@XmlElement(name = "clas")
	private String clas;
	
	
	@Getter
	@Setter
	@XmlElement(name = "order")
	private String order;
	
	@Getter
	@Setter
	@XmlElement(name = "family")
	private String family;
	
	
	@Getter
	@Setter
	@XmlElement(name = "genus")
	private String genus;
	
		
	@Getter
	@Setter
	@XmlElement(name = "specificInfraspecifcEpit")
	private String specificInfraspecifcEpit;
	
	@Getter
	@Setter
	@XmlElement(name = "artificialGroup")
	private String artificialGroup;
	
	@Getter
	@Setter
	@XmlElement(name = "error")
	private String error;
	
	@Getter
	@Setter
	@XmlTransient
	private Integer id;
	
	
	@Getter
	@Setter
	@XmlTransient
	private Integer idRank;

}

package ec.gob.ambiente.sib_suia_ora.summaries.model;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;

import lombok.Getter;
import lombok.Setter;

@XmlAccessorType(value = javax.xml.bind.annotation.XmlAccessType.FIELD)
public class IntroductionProjectValue implements Serializable {

	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	
	@Getter
	@Setter
	@XmlElement(name = "type")
    private TypeInt type;
	
	@Getter
	@Setter
	@XmlElement(name = "introductionCause")
    private IntroductionCause introductionCause;
	
	@Getter
	@Setter
	@XmlElement(name = "eventDate")
    private EventDate eventDate;
	
	@Getter
	@Setter
	@XmlElement(name = "locality")
    private LocalityExo locality;
	
	@Getter
	@Setter
	@XmlElement(name = "author")
    private AuthorExo author;
	
	@Getter
	@Setter
	@XmlElement(name = "title")
    private TitleExo title;
	
	@Getter
	@Setter
	@XmlElement(name = "invasionRiskCategory")
    private InvasionRiskCategory invasionRiskCategory;
	
	@Getter
	@Setter
	@XmlElement(name = "isExoticInRegion")
    private IsExoticInRegion isExoticInRegion;
	
	@Getter
	@Setter
	@XmlElement(name = "introductionDescription")
    private IntroductionDescription introductionDescription;
}

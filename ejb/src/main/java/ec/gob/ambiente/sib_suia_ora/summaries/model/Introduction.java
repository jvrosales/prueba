package ec.gob.ambiente.sib_suia_ora.summaries.model;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;

import lombok.Getter;
import lombok.Setter;

@XmlAccessorType(value = javax.xml.bind.annotation.XmlAccessType.FIELD)
public class Introduction implements Serializable {

	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	
	
	@Getter
	@Setter
	@XmlElement(name = "introductionYear")
    private IntroductionYear introductionYear;
	
	@Getter
	@Setter
	@XmlElement(name = "introductionReason")
    private IntroductionReason introductionReason;
	
	@Getter
	@Setter
	@XmlElement(name = "introductionProjects")
    private IntroductionProject introductionProjects;
}

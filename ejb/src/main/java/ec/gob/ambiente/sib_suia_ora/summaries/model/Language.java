package ec.gob.ambiente.sib_suia_ora.summaries.model;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;

import lombok.Getter;
import lombok.Setter;

@XmlAccessorType(value = javax.xml.bind.annotation.XmlAccessType.FIELD)
public class Language implements Serializable {

	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Getter
	@Setter
	@XmlAttribute(name = "comment")
	private String comment = "Idioma utilizado para escribir la información del sumario de la especie, se utiliza el código ISO 639-2 (https://www.loc.gov/standards/iso639-2/php/code_list.php)";
	
	@Getter
	@Setter
	@XmlAttribute(name = "value")
    private String value="SPA";
}

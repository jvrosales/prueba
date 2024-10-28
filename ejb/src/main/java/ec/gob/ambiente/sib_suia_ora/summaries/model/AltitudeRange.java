package ec.gob.ambiente.sib_suia_ora.summaries.model;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;

import lombok.Getter;
import lombok.Setter;

@XmlAccessorType(value = javax.xml.bind.annotation.XmlAccessType.FIELD)
public class AltitudeRange implements Serializable {

	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Getter
	@Setter
	@XmlAttribute(name = "comment")
	private String comment = "Texto descriptivo del rango altitudinal de la distribución de la especie";
	
	@Getter
	@Setter
	@XmlAttribute(name = "value")
    private String value;
}

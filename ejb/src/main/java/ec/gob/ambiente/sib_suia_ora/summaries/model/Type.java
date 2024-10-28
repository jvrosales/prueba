package ec.gob.ambiente.sib_suia_ora.summaries.model;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;

import lombok.Getter;
import lombok.Setter;

@XmlAccessorType(value = javax.xml.bind.annotation.XmlAccessType.FIELD)
public class Type implements Serializable {

	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Getter
	@Setter
	@XmlAttribute(name = "comment")
	private String comment = "Indica la categoría a la que corresponde la cita bibliográfica. Se utiliza un vocabulario controlado que puede tomar los siguiente valores: Sumario electrónico, Blog, Pdf, Servicio Web, Colección HHNN, Vidioteca, Audioteca, Fototeca, Banco genes";
	
	@Getter
	@Setter
	@XmlAttribute(name = "value")
    private String value;
}

package ec.gob.ambiente.sib_suia_ora.taxonomicresolution.model;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlValue;

import lombok.Getter;
import lombok.Setter;


@XmlRootElement(name = "nameSubmitted")
@XmlAccessorType(value = javax.xml.bind.annotation.XmlAccessType.FIELD)
public class NameSubmitted implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Getter
	@Setter
	@XmlAttribute(name = "comment")
	private String comment = "Nombre científico enviado en la consulta";

	@Getter
	@Setter
	@XmlValue
	private String nombreEnviado;

}

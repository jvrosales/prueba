package ec.gob.ambiente.sib_suia_ora.catalogobiodiversidad.model;

import lombok.Getter;
import lombok.Setter;

import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;

@XmlRootElement(name = "TariffSubheadingResponse")
@XmlAccessorType(value = javax.xml.bind.annotation.XmlAccessType.FIELD)
public class TariffSubheadingResponse implements Serializable {

    /**
	 * 
	 */
	private static final long serialVersionUID = -7777905099930321997L;

	@Getter
    @Setter
    @XmlElement(name = "codigo_subpartida")
    private String codigoSubpartida;

    @Getter
    @Setter
    @XmlElement(name = "descripcion")
    private String descripcion;

    @Getter
    @Setter
    @XmlElement(name = "codigo_complementario")
    private String codigoComplementario;

    @Getter
    @Setter
    @XmlElement(name = "codigo_suplementario")
    private String codigoSuplementario;

    @Getter
    @Setter
    @XmlElement(name = "error")
    private String error;
}
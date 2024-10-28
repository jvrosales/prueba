package ec.gob.ambiente.sib_suia_ora.catalogobiodiversidad.model;

import lombok.Getter;
import lombok.Setter;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;

@XmlRootElement(name = "CitesDataResponse")
@XmlAccessorType(value = javax.xml.bind.annotation.XmlAccessType.FIELD)
public class CitesDataResponse implements Serializable {


    /**
	 * 
	 */
	private static final long serialVersionUID = -4799097192163440193L;

	@Getter
    @Setter
    @XmlElement(name = "apendice")
    private String apendice;

    @Getter
    @Setter
    @XmlElement(name = "especie")
    private String especie;

    @Getter
    @Setter
    @XmlElement(name = "codigo_comercio")
    private String codigoComercio;

    @Getter
    @Setter
    @XmlElement(name = "descripcion_especimen")
    private String descripcionEspecimen;

    @Getter
    @Setter
    @XmlElement(name = "cantidad")
    private String cantidad;

    @Getter
    @Setter
    @XmlElement(name = "unidad")
    private String unidad;

    @Getter
    @Setter
    @XmlElement(name = "pais_destino")
    private String paisDestino;

    @Getter
    @Setter
    @XmlElement(name = "permiso_export")
    private String permisoExport;

    @Getter
    @Setter
    @XmlElement(name = "pais_origen")
    private String paisOrigen;

    @Getter
    @Setter
    @XmlElement(name = "permiso_reexport")
    private String permisoReexport;

    @Getter
    @Setter
    @XmlElement(name = "proposito")
    private String proposito;

    @Getter
    @Setter
    @XmlElement(name = "origen")
    private String origen;

    @Getter
    @Setter
    @XmlElement(name = "anio")
    private String anio;

    @Getter
    @Setter
    @XmlElement(name = "observaciones")
    private String observaciones;

    @Getter
    @Setter
    @XmlElement(name = "error")
    private String error;
}
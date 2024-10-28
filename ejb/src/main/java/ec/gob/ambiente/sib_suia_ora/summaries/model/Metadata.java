package ec.gob.ambiente.sib_suia_ora.summaries.model;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;

import lombok.Getter;
import lombok.Setter;

@XmlAccessorType(value = javax.xml.bind.annotation.XmlAccessType.FIELD)
public class Metadata implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Getter
	@Setter
	@XmlAttribute(name = "comment")
	private String comment = "El Ministerio del Ambiente y Agua se reserva el derecho de modificar sin previo aviso el listado de especies con información disponible, el contenido del sumario, y/o la estructura del servicio web. Para información actualizada se puede consultar en http://suia.ambiente.gob.ec";
	
	@Getter
	@Setter
	@XmlElement(name = "author")
    private Author author;
	
	@Getter
	@Setter
	@XmlElement(name = "reviewer")
    private Reviewer reviewer;
	
	@Getter
	@Setter
	@XmlElement(name = "editor")
    private Editor editor;
	
	@Getter
	@Setter
	@XmlElement(name = "collaborator")
    private Collaborator collaborator;
	
	@Getter
	@Setter
	@XmlElement(name = "publicationYear")
    private PublicationYear publicationYear;
	
	@Getter
	@Setter
	@XmlElement(name = "language")
    private Language language;
	
	@Getter
	@Setter
	@XmlElement(name = "rightsHolder")
    private RightsHolder rightsHolder;
	
	@Getter
	@Setter
	@XmlElement(name = "accessRights")
    private AccessRights accessRights;
	
	@Getter
	@Setter
	@XmlElement(name = "license")
    private License license;
	
	@Getter
	@Setter
	@XmlElement(name = "version")
    private Version version;
}

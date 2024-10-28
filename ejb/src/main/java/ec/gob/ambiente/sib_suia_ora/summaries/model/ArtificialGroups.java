package ec.gob.ambiente.sib_suia_ora.summaries.model;

import java.io.Serializable;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;

import lombok.Getter;
import lombok.Setter;

@XmlAccessorType(value = javax.xml.bind.annotation.XmlAccessType.FIELD)
public class ArtificialGroups implements Serializable {

	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Getter
	@Setter
	@XmlAttribute(name = "comment")
    private String comment="Lista de grupos artificiales de clasificación o agrupación a la que pertenece la especie. Forma de asociar grupos de especies por alguna categoría de manejo, reconocimiento, conservación, uso o gestión administrativa";
	
	@Getter
	@Setter
	@XmlElement
    private List<ArtificialGroup> artificialGroup;
}

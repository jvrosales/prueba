package ec.gob.ambiente.sib_suia_ora.summaries.model;

import java.util.List;

import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;

import lombok.Getter;
import lombok.Setter;

@XmlAccessorType(value = javax.xml.bind.annotation.XmlAccessType.FIELD)
public class ProtectedAreas {

	@Getter
	@Setter
	@XmlAttribute(name = "comment")
	private String comment = "Distribución en áreas protegidas. En caso de especies exóticas se refiere a áreas protegidas afectadas";
	
	
	@Getter
	@Setter
	@XmlElement
    private List<ProtectedArea>protectedArea;
}

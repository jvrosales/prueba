package ec.gob.ambiente.sib_suia_ora.summaries.model;

import java.io.Serializable;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;

import lombok.Getter;
import lombok.Setter;

@XmlAccessorType(value = javax.xml.bind.annotation.XmlAccessType.FIELD)
public class Images implements Serializable {

	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	
	@Getter
	@Setter
	@XmlElement
    private List<Image> image;
	
	@Getter
	@Setter
	@XmlAttribute(name = "comment")
	private String comment = "Lista de imágenes de la especie. Los posibles valores de type son: principal (imagen principal del sumario), secundaria (imagen descriptiva adicional en otras situaciones o aspectos de uso, aprovechamiento o conservación de la especie), mapa (imagen relacionada a un mapa de distribución actual, potencial u otra temática referida a la presencia espacial de la especie), forestal (imagen relacionada al análisis estadístico de aprovechamiento forestal de la especie del Sistema de Administración Forsetal - SAF), trasnversal (imagen relacionada a la característica organoléptica de un corte transversal en madera rolliza), tangencial (imagen relacionada a la característica organoléptica de un corte tangencial en madera aserrada), radial (imagen relacionada a la característica organoléptica de un corte radial en madera aserrada), anatomica (imagen relacionada a las características físicas de un corte transversal en madera aserrada)";
}

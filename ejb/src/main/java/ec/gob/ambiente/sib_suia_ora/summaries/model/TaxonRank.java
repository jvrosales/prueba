package ec.gob.ambiente.sib_suia_ora.summaries.model;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;

import lombok.Getter;
import lombok.Setter;

@XmlAccessorType(value = javax.xml.bind.annotation.XmlAccessType.FIELD)
public class TaxonRank implements Serializable {

	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Getter
	@Setter
	@XmlAttribute(name = "comment")
	private String comment = "El rango taxonómico del nombre más específico provisto en el campo scientificName. Se usa vocabulario controlado (Reino, Phylum, Clase, Orden, Familia, Genero, especie, subespecie, variedad, forma)";
	
	@Getter
	@Setter
	@XmlAttribute(name = "value")
    private String value;
}

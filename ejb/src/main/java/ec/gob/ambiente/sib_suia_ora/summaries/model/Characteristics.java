package ec.gob.ambiente.sib_suia_ora.summaries.model;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;

import lombok.Getter;
import lombok.Setter;

@XmlAccessorType(value = javax.xml.bind.annotation.XmlAccessType.FIELD)
public class Characteristics implements Serializable {

	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Getter
	@Setter
	@XmlAttribute(name = "comment")
    private String comment="Características de la especie exótica / invasora";
	
	@Getter
	@Setter
	@XmlElement(name = "dispersion")
    private Dispersion dispersion;
	
	@Getter
	@Setter
	@XmlElement(name = "diet")
    private Diet diet;
	
	@Getter
	@Setter
	@XmlElement(name = "dispersionRoutes")
    private DispersionRoutes dispersionRoutes;
	
	@Getter
	@Setter
	@XmlElement(name = "reproduction")
    private Reprodution reproduction;
	
	@Getter
	@Setter
	@XmlElement(name = "dispertionVectors")
    private DispertionVectors dispertionVectors;
	
	
	@Getter
	@Setter
	@XmlElement(name = "biologicalForm")
    private BiologicalForm biologicalForm;
	
	@Getter
	@Setter
	@XmlElement(name = "uses")
    private UsesExo uses;
	
	@Getter
	@Setter
	@XmlElement(name = "preferentialInvasionEnvironments")
    private PreferentialInvasionEnvironments preferentialInvasionEnvironments;
	
	
	
	
	
}

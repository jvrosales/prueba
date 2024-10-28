package ec.gob.ambiente.sib_suia_ora.summaries.model;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;

import lombok.Getter;
import lombok.Setter;

@XmlAccessorType(value = javax.xml.bind.annotation.XmlAccessType.FIELD)
public class Habit implements Serializable {

	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	
	
	
	@Getter
	@Setter
	@XmlElement(name = "habit")
    private HabitName habit;
	
	@Getter
	@Setter
	@XmlElement(name = "source")
    private SourceHab source;
}

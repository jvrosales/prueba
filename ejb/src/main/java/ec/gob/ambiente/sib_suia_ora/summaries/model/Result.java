package ec.gob.ambiente.sib_suia_ora.summaries.model;

import java.io.Serializable;
import java.util.List;

import lombok.Getter;
import lombok.Setter;

public class Result implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Getter
	@Setter
	private String error;
	
	@Getter
	@Setter
	
	private List<SumGSpResult> sumGSpResult;
	
	@Getter
	@Setter
	private List<SumForeSpResult> sumForeSpResult;
	
	@Getter
	@Setter
	private List<SumExSpResult> sumExSpResult;
	
	
}

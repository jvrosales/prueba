package ec.gob.ambiente.vo;

import java.io.Serializable;

public class ProponentVO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8247231437102138917L;
	
	private String name;
	private String legalRepresentative;
	private String cedula;
	private String ruc;

	public ProponentVO() {
		super();
	}



	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getLegalRepresentative() {
		return legalRepresentative;
	}

	public void setLegalRepresentative(String legalRepresentative) {
		this.legalRepresentative = legalRepresentative;
	}

	public String getCedula() {
		return cedula;
	}

	public void setCedula(String cedula) {
		this.cedula = cedula;
	}

	public String getRuc() {
		return ruc;
	}

	public void setRuc(String ruc) {
		this.ruc = ruc;
	}	
}
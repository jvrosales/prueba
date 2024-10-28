package ec.gob.ambiente.enlisy.fiscalia.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;


/**
 * The persistent class for the TbTaxaFiscalia database table.
 * 
 */

@Entity
@Table(name="tb_taxa_fiscalia", schema="suia_fiscalia")
public class TbTaxaFiscalia implements Serializable {
	private static final long serialVersionUID = 1L;

	@Getter
	@Setter
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="secuencial")
	private Integer secuencial;
	
	@Getter
	@Setter
	@Column(name="gui")
	private String gui;
	
	@Getter
	@Setter
	@Column(name="reino")
	private String reino;
	
	@Getter
	@Setter
	@Column(name="phylum")
	private String phylum;
	
	@Getter
	@Setter
	@Column(name="clase")
	private String clase;
	
	@Getter
	@Setter
	@Column(name="orden")
	private String orden;
	
	@Getter
	@Setter
	@Column(name="familia")
	private String familia;
	
	@Getter
	@Setter
	@Column(name="genero")
	private String genero;
	
	@Getter
	@Setter
	@Column(name="especie")
	private String especie;
	
	@Getter
	@Setter
	@Column(name="nombrecientifico")
	private String nombrecientifico;
	
	
	@Getter
	@Setter
	@Column(name="uicn")
	private String uicn;
	
	@Getter
	@Setter
	@Column(name="cites")
	private String cites;
	
	@Getter
	@Setter
	@Column(name="sinonimos")
	private String sinonimos;

	@Getter
	@Setter
	@Column(name="autor")
	private String autor;
	
	@Getter
	@Setter
	@Column(name="codigobusqueda")
	private String codigobusqueda;
	
	@Getter
	@Setter
	@Column(name="ecuador")
	private Integer ecuador;
	
	@Getter
	@Setter
	@Column(name="endemica")
	private Integer endemica;
	
	@Getter
	@Setter
	@Column(name="nativa")
	private Integer nativa;
	
	@Getter
	@Setter
	@Column(name="forestal")
	private Integer forestal;
		
	@Getter
	@Setter
	@Column(name="orquidea")
	private Integer orquidea;
	
	@Getter
	@Setter
	@Column(name="anfibio")
	private Integer anfibio;
	
	@Getter
	@Setter
	@Column(name="ave")
	private Integer ave;
	
	@Getter
	@Setter
	@Column(name="mamifero")
	private Integer mamifero;
	
	@Getter
	@Setter
	@Column(name="pez")
	private Integer pez;
	
	@Getter
	@Setter
	@Column(name="reptil")
	private Integer reptil;
	
	  
	@Getter
	@Setter
	@Column(name="introducida")
	private Integer introducida;
	
	@Getter
	@Setter
	@Column(name="comercial")
	private Integer comercial;	  
	   

}
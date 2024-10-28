package ec.gob.ambiente.enlisy.speciescatalog.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "vw_lstespecies", schema = "biodiversity")
public class VistaEspecies implements Serializable {
	
	private static final long serialVersionUID = 7983025632702435432L;
	
	@Getter
	@Setter
	@Id
	@Column(name="id_reino")
	private Integer id_reino;
	
	@Getter
	@Setter
	@Column(name="gui_reino")
	private String gui_reino;
	
	@Getter
	@Setter
	@Column(name="reino")
	private String reino;
	
	@Getter
	@Setter
	@Column(name="id_phylum")
	private Integer id_phylum;
	
	@Getter
	@Setter
	@Column(name="gui_phylum")
	private String gui_phylum;
	
	@Getter
	@Setter
	@Column(name="phylum")
	private String phylum;
	
	@Getter
	@Setter
	@Column(name="id_clase")
	private Integer id_clase;
	
	@Getter
	@Setter
	@Column(name="gui_clase")
	private String gui_clase;
	
	@Getter
	@Setter
	@Column(name="clase")
	private String clase;
	
	@Getter
	@Setter
	@Column(name="id_orden")
	private Integer id_orden;
	
	@Getter
	@Setter
	@Column(name="gui_orden")
	private String gui_orden;
	
	@Getter
	@Setter
	@Column(name="orden")
	private String orden;
	
	@Getter
	@Setter
	@Column(name="id_familia")
	private Integer id_familia;
	
	@Getter
	@Setter
	@Column(name="gui_familia")
	private String gui_familia;
	
	@Getter
	@Setter
	@Column(name="familia")
	private String familia;
	
	@Getter
	@Setter
	@Column(name="id_genero")
	private Integer id_genero;
	
	@Getter
	@Setter
	@Column(name="gui_genero")
	private String gui_genero;
	
	@Getter
	@Setter
	@Column(name="genero")
	private String genero;
	
	@Getter
	@Setter
	@Column(name="id_especie")
	private Integer id_especie;
	
	@Getter
	@Setter
	@Column(name="gui_especie")
	private String gui_especie;
	
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
	@Column(name="autor")
	private String autor;
	
	@Getter
	@Setter
	@Column(name="ano")
	private String ano;
	
	@Getter
	@Setter
	@Column(name="rango")
	private String rango;
	
	@Getter
	@Setter
	@Column(name="codigobuscar")
	private String codigobuscar;
	
	@Getter
	@Setter
	@Column(name="listarojaec")
	private String listarojaec;
	
	@Getter
	@Setter
	@Column(name="listarojauicn")
	private String listarojauicn;
	
	@Getter
	@Setter
	@Column(name="cites")
	private String cites;
	
	@Getter
	@Setter
	@Column(name="cites_criterio")
	private String cites_criterio;
	
	@Getter
	@Setter
	@Column(name="nombrecomun")
	private String nombrecomun;
	
	@Getter
	@Setter
	@Column(name="estatustaxonomico")
	private String estatustaxonomico;
	
	@Getter
	@Setter
	@Column(name="ecuador")
	private Boolean ecuador;
	
	@Getter
	@Setter
	@Column(name="endemica")
	private Boolean endemica;
	
	@Getter
	@Setter
	@Column(name="nativa")
	private Boolean nativa;
	
	@Getter
	@Setter
	@Column(name="exotica")
	private Boolean exotica;
	
	@Getter
	@Setter
	@Column(name="invasora")
	private Boolean invasora;
	
	@Getter
	@Setter
	@Column(name="domestica")
	private Boolean domestica;
	
	@Getter
	@Setter
	@Column(name="migratoria")
	private Boolean migratoria;
	
	@Getter
	@Setter
	@Column(name="areanatural")
	private String areanatural;
	
	@Getter
	@Setter
	@Column(name="provincia")
	private String provincia;

}
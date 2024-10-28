package ec.gob.ambiente.enlisy.model;

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

import ec.gob.ambiente.suia.domain.base.EntidadBase;
import lombok.Getter;
import lombok.Setter;


@NamedQueries({ @NamedQuery(name = ReportTemplate.OBTENER_PLANTILLA_POR_INFORME, query = "select p from ReportTemplate p where p.tipoDocumentoId = :p_tipoDocumentoId")})
@AttributeOverrides({
	@AttributeOverride(name = "estado", column = @Column(name = "rete_status"))
	})
@Entity
@Table(name = "report_template", schema = "public")
public class ReportTemplate extends EntidadBase {	

	private static final long serialVersionUID = 1525971341648586158L;

	private static final String PAQUETE = "ec.gob.ambiente.enlisy.model.";
	public static final String OBTENER_PLANTILLA_POR_INFORME = PAQUETE + "ReportTemplate.obtenerPlantillaPorInforme";
	
    @Id
    @Column(name = "rete_id")   
	@Getter @Setter
    private Integer reteId;

	@Getter @Setter
    @Column(name = "rete_html_template", length=5000)
    private String reteHtmlTemplate;

	@Getter @Setter
    @Column(name = "rete_process_name")
    private String reteProcessName;

	@Getter @Setter
    @Column(name = "rete_process_code")
    private String reteProcessCode;
	
	@Getter
	@Setter
	@Column(name = "doty_id", updatable = false, insertable = false)
	private Integer tipoDocumentoId;

	@Override
	public Integer getId() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setId(Integer id) {
		// TODO Auto-generated method stub
		
	}	

	
}
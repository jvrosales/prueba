package ec.gob.ambiente.vo;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import lombok.Getter;
import lombok.Setter;
import ec.gob.ambiente.enlisy.geneticresources.model.ProposedCollection;

public class AutorizacionARSFCVO {
	
	public AutorizacionARSFCVO(ProposedCollection proposedCollection)
	{
		proyecto=proposedCollection.getResearchApplication().getReapTitle();
		institucion_cientifica=proposedCollection.getResearchApplication().getOrganization().getOrgaNameOrganization();		
		resultados=proposedCollection.getPrcoExpectedResults();
		numero_tramite=proposedCollection.getPrcoCode()!=null?proposedCollection.getPrcoCode():proposedCollection.getResearchApplication().getReapCode();
			
		numero=getNumero(numero_tramite);
		fecha_actual=getFechaActual();
		fecha_informe_preliminar=getFechaInformePreliminar(proposedCollection);
		
	}
	
	
	private String getNumero(String numero_tramite) {
		
		numero_tramite=numero_tramite.replace("-MODIFICACION","");
		numero_tramite=numero_tramite.replace("-RENOVACION","");
		String[] numeroTramite=numero_tramite.split("-");
		String secuencia=numeroTramite[numeroTramite.length-1];
		String numero=""+Integer.valueOf(secuencia);
		return numero;
				
	}
	
	private String getFechaActual()
	{
		SimpleDateFormat dt1 = new SimpleDateFormat("yyyy-MM-dd");
		return dt1.format(new Date());
	}
	
	private String getFechaInformePreliminar(ProposedCollection propuesta)
	{		
		Calendar cal=Calendar.getInstance();
		cal.setTime(propuesta.getPrcoDateEnd());
		cal.add(Calendar.DATE,-15);	
		SimpleDateFormat format1 = new SimpleDateFormat("yyyy/MM/dd");
		String date1 = format1.format(cal.getTime());  
		return date1.substring(0,10);
	}
	
	//Campos seteados en el constructor
	@Getter
	private String proyecto,institucion_cientifica,resultados,numero_tramite,numero,fecha_actual,fecha_informe_preliminar;

	//Campos de un solo valor
	@Getter
	@Setter
	private String proponente,nombre_tecnico_responsable,director_nacional_biodiversidad;
	
	//Campos en tablas html
	@Getter
	@Setter
	private String duracion,componente,campo,centros_tenencia,contribucion,tecnicos_investigadores,ubicaciones,objetivos,equipos_materiales;
	
	//Solo aplica a Investigadores o Estudiantes
	@Getter
	@Setter
	private String especies,metodologia_laboratorio,metodologia_campo;
	
	//Solo aplica a Instituciones o Consultores
	@Getter
	@Setter
	private String metodologias;
	

	
}

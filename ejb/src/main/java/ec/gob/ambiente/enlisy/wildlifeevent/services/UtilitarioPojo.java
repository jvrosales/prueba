package ec.gob.ambiente.enlisy.wildlifeevent.services;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

import ec.gob.ambiente.enlisy.wildlifeevent.model.ReporteCampo;
import ec.gob.ambiente.enlisy.wildlifeevent.model.ReporteEntrada;

public class UtilitarioPojo {
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public static ReporteEntrada generarEntradaWsReporte(Object objetoDto) {
        ReporteEntrada reporteEntrada = new ReporteEntrada();
        List<ReporteCampo> camposResporte = new ArrayList();

        Method[] metodos = objetoDto.getClass().getDeclaredMethods();
        Field[] campos = objetoDto.getClass().getDeclaredFields();

        for (Method metodo : metodos) {
            try {
                if (metodo.getName().startsWith("get")) {
                    ReporteCampo reporteCampo = new ReporteCampo();
                    reporteCampo.setLlave(getNombreCampo(campos, metodo.getName().substring(3)));
                    reporteCampo.setValor((String) metodo.invoke(objetoDto));
                    camposResporte.add(reporteCampo);
                }
            } catch (Exception e) {
				// TODO: handle exception
			}
        }

        reporteEntrada.setListaCamposReemplazar(camposResporte);

        return reporteEntrada;
    }
	
	private static String getNombreCampo(Field[] campos, String nombreCampo) {
        for (Field campo : campos) {
            if (nombreCampo.toUpperCase().equals(campo.getName().toUpperCase())) {
                nombreCampo = campo.getName();
            }
        }

        return nombreCampo;
    }

}

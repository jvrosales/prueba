package ec.gob.ambiente.enlisy.wildlifeevent.model;

import java.io.Serializable;
import java.util.List;

import lombok.Getter;
import lombok.Setter;
public class ReporteEntrada implements Serializable{
    private static final long serialVersionUID = 4931337515931807789L;
    
    @Getter
    @Setter
    private String codigoReporte;
    @Getter
    @Setter
    private List<ReporteCampo> listaCamposReemplazar;
    @Getter
    @Setter
    private String tituloReporte;
    @Getter
    @Setter
    private String marcaDeAgua;
    @Getter
    @Setter
    private String orientacion;
    @Getter
    @Setter
    private String cargaCabecera;
    @Getter
    @Setter
    private String cargaPie;

    public ReporteEntrada() {
    }

}

package ec.gob.ambiente.enlisy.wildlifeevent.model;

import java.io.Serializable;
import java.util.List;

import lombok.Getter;
import lombok.Setter;

public class ReporteCampo implements Serializable {
    private static final long serialVersionUID = -1047572019919248297L;

    @Getter
    @Setter
    private String llave;
    @Getter
    @Setter
    private String valor;
    @Getter
    @Setter
    private List<ReporteCampoPartido> listaCampoPartido;

}

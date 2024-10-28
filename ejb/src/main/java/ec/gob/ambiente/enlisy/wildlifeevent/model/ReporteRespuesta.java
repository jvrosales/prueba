package ec.gob.ambiente.enlisy.wildlifeevent.model;

import java.io.Serializable;
import lombok.Getter;
import lombok.Setter;

public class ReporteRespuesta implements Serializable{
    private static final long serialVersionUID = -4332032466166830502L;
    
    @Getter
    @Setter
    private String codigo;
    @Getter
    @Setter
    private String cuerpo;
    @Getter
    @Setter
    private String mensaje;

}

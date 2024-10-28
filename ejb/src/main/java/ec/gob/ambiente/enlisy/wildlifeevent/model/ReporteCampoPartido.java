package ec.gob.ambiente.enlisy.wildlifeevent.model;

import java.io.Serializable;

import lombok.Getter;
import lombok.Setter;

public class ReporteCampoPartido implements Serializable{
    private static final long serialVersionUID = 7875415980108386254L;

    @Getter
    @Setter
    private String orden;
    @Getter
    @Setter
    private String trama;
}

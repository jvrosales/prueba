package ec.gob.ambiente.enlisy.wildlifeevent.model;

import java.io.Serializable;
import lombok.Getter;
import lombok.Setter;

public class ReporteConsulta implements Serializable {

	    private static final long serialVersionUID = -3546538123390116977L;

	    @Getter
	    @Setter
	    private String nombreReporte;

	    @Getter
	    @Setter
	    private String htmlReporte;

	    
	    public ReporteConsulta() {
			super();
		}


		public ReporteConsulta(String nombreReporte, String htmlReporte) {
	        this.nombreReporte = nombreReporte;
	        this.htmlReporte = htmlReporte;
	    }

}

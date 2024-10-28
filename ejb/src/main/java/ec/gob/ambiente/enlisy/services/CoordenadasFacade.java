package ec.gob.ambiente.enlisy.services;

import javax.ejb.Stateless;

import ec.gob.consulta.CapasResponse;
import ec.gob.consulta.ClienteCapas;
import ec.gob.consulta.ClienteCapasService;


@Stateless
public class CoordenadasFacade {
	
	public String consultarCapas(String user, String pass, Integer xCoor, Integer yCoor){
		ClienteCapasService service;
		try {
			service = new ClienteCapasService();
			ClienteCapas port = service.getClienteCapasPort();
			CapasResponse response = port.intersects(user, pass, xCoor, yCoor);			
			return response.getIntersectMesagge();			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "";
	}	
}

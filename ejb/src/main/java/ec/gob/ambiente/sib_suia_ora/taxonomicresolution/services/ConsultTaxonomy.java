package ec.gob.ambiente.sib_suia_ora.taxonomicresolution.services;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;

import org.apache.commons.lang.StringEscapeUtils;

import lombok.Getter;
import lombok.Setter;

public class ConsultTaxonomy {

	private String response;
    private final Integer timeout;
    
    @Getter
	@Setter
    private String path;
    
    
    /**
     * Constructor de la clase que recibe los parametros para la consulta
     *
     * @param req
     * @param direccion
     * @param to
     * @throws Exception
     */
    public ConsultTaxonomy(String req, String direccion, Integer to) throws Exception {
        URL url = null;
        String request = req;
        String dir = direccion;
        timeout = to;
        try {
            url = new URL(dir); 
            getResponse(request, prepareRequest(request, url));
        } catch (Exception e) {
            response = e.getMessage();
            System.out.println("Ocurrio un error: "+e.getMessage());
        }
    }

    /**
     * Metodo que prepara el contenido para la consulta al web service en donde
     * se agrega el request xml.
     *
     * @param req
     * @throws IOException
     */
    public HttpURLConnection prepareRequest(String req, URL url) throws IOException {
        HttpURLConnection rc = (HttpURLConnection) url.openConnection();
        rc.setRequestMethod("POST");
        rc.setDoOutput(true);
        rc.setDoInput(true);
        rc.setConnectTimeout(timeout);
        rc.setReadTimeout(timeout);
        rc.setRequestProperty("Content-Type", "text/xml; charset=utf-8");
        rc.setRequestProperty("Keep-Alive", "300");
        rc.setRequestProperty("Connection", "keep-alive");
        rc.setRequestProperty("Content-Length", Integer.toString(req.length()));
        rc.connect();
        return rc;
    }

    /**
     * En este metodo se obtiene el resultado del web service y ademas realiza
     * la conversion a un string para poder leer la respuesta.
     *
     * @param req
     * @throws IOException
     */
    /**
    public void getResponse(String req, HttpURLConnection rc) throws IOException {
        OutputStreamWriter out = new OutputStreamWriter(rc.getOutputStream());
        out.write(req, 0, req.length());
        out.flush();
        InputStreamReader read = new InputStreamReader(rc.getInputStream());
        StringBuffer sb = new StringBuffer();
        int ch = read.read();
        while (ch != -1) {
            sb.append((char) ch);
            ch = read.read();
        }
        response = sb.toString();
        read.close();
        rc.disconnect();
    }
    /**

	

    /**
     * En este metodo devuelve la respuesta del web service en un string
     *
     * @return String
     */
    public String getResponse() {
        if (this.response != null) {
            return this.response;
        } else {
            return "";
        }
    }
    
    @SuppressWarnings("unused")
	public void getResponse(String req, HttpURLConnection rc)  {
    	File responseFile;
		try {
			responseFile = File.createTempFile("SOAP", "xml");
		
        OutputStreamWriter out = new OutputStreamWriter(rc.getOutputStream(), StandardCharsets.UTF_8);
        out.write(req, 0, req.length());
        out.flush();
        
        //InputStreamReader read = new InputStreamReader(rc.getInputStream());
        BufferedReader stdInput = new BufferedReader(new InputStreamReader(
        		rc.getInputStream(), StandardCharsets.UTF_8));
        BufferedWriter writer = null;
        String s = null;
        try {
            writer = new BufferedWriter(new FileWriter(responseFile));
            int ch = stdInput.read();
            while (ch != -1) {
            	if((((char) ch) != '<')&&(((char) ch) != '>')&&(((char) ch) != '"'))
            	{
            		writer.write(StringEscapeUtils.escapeXml(Character.toString((char) ch)));
                    
            	}
            	else
            	{
            		writer.write((char) ch);
            	}
            	ch = stdInput.read();
            }
           

        } finally {
            if (writer != null) {
                writer.close();
            }
            rc.disconnect();
        }
        
        path=responseFile.getAbsolutePath();
        System.out.println("Temp file created: "+responseFile.getAbsolutePath());
        
       
		} catch (IOException  e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
}

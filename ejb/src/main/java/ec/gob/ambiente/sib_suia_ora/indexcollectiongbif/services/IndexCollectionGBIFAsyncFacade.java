package ec.gob.ambiente.sib_suia_ora.indexcollectiongbif.services;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.Field;
import java.net.HttpURLConnection;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Types;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Properties;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

import javax.ejb.Asynchronous;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

import org.apache.log4j.Logger;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import com.jayway.jsonpath.Configuration;
import com.jayway.jsonpath.JsonPath;
import com.jayway.jsonpath.Option;

import ec.gob.ambiente.enlisy.cetas.model.CollectionsWmc;
import ec.gob.ambiente.enlisy.dao.AbstractFacade;
import ec.gob.ambiente.enlisy.model.PatentRequest;
import ec.gob.ambiente.enlisy.model.User;
import ec.gob.ambiente.enlisy.speciescatalog.services.BiodiversityCatalogParameterFacade;
import ec.gob.ambiente.enlisy.wildlifeevent.model.BiodiversityGeneralCatalog;
import ec.gob.ambiente.enlisy.wildlifeevent.services.BiodiversityCatalogTypeFacade;
import ec.gob.ambiente.enlisy.wildlifeevent.services.BiodiversityGeneralCatalogFacade;
import ec.gob.ambiente.sib_suia_ora.indexcollectiongbif.model.DatasetIpt;
import ec.gob.ambiente.sib_suia_ora.taxonomicresolution.model.PropertyType;
import ec.gob.ambiente.sib_suia_ora.taxonomicresolution.model.ViewResources;
import lombok.Getter;
import lombok.Setter;

/**
 * Servicios para la consulta y manejo de los dataset
 * indezados desde GBIF, adicional recibiendo como
 * como parametro el DOI de una publicaciÃ³n especÃ­fica
 *
 * @author RolandoSoria
 *
 */
@Stateless
@TransactionManagement(value=TransactionManagementType.BEAN)
public class IndexCollectionGBIFAsyncFacade extends AbstractFacade<DatasetIpt, Integer> {

    public IndexCollectionGBIFAsyncFacade() {
   	 super(DatasetIpt.class, Integer.class);
    }
    
    static final String messagesFile = "messages.properties";
    static final String propertiesFile = "mae.properties";
    private static final String FROM = "Ministerio del Ambiente";
    private static final String CONTENT_TYPE = "text/html; charset=utf-8";
    private static final String TRANSPORT = "smtp";
    @SuppressWarnings("unused")
	private static final String SPLIT_SEPARATE = ";";
    
    private final Logger log = Logger.getLogger(this.getClass());
    
    @EJB
    private BiodiversityGeneralCatalogFacade bgCatalogFacade;
    
    @EJB
    private BiodiversityCatalogParameterFacade biodiversityCatalogParameterFacade;
    
    @EJB
    private BiodiversityCatalogTypeFacade bcTypeFacade;
    
    @EJB
    private DatasetIptFacade dataSetIPT;
    
    @EJB
    private IndexCollectionGBIFAsyncFacade indexCollFacade;
    
    @Getter
    @Setter
    private String paramDOI;
   	 
    @Getter
    @Setter
    private User usrRequestIndex;
    
    @Getter
    @Setter
    private Configuration conf;
    
    @Getter
    @Setter
    private List<BiodiversityGeneralCatalog> fieldsDS;
    
    @Getter
    @Setter
    private List<BiodiversityGeneralCatalog> fieldsColl;
    
    @Getter
    @Setter
    private BiodiversityGeneralCatalog bgInstCode;
    
    @Getter
    @Setter
    private long startProcess;
   	 
    @Getter
    @Setter
    private List<CollectionsWmc> listaInsertarActualizar;
    
    @Getter
    @Setter
    private String mensaje;
    
    @Getter
    @Setter
    private String strListFields;
    
    /**
     * Actualizar las colleciones con su respectiva patente y centro de manejo
     * @param paramDS Dataset con la patente y el centro de manejo asociar
     * @param user Usuario que realiza la peticiÃ³n
     * @param start Inicio el proceso
     * @return
     */
    /*public void setPatentWMC(DatasetIpt paramDS, User user, long start)
    {
   	 try {    
   		 Connection con = getConnection();
   		 //Actualiza la patente y el centro de manejo de los registros asociados al dataset
        	String qStr = "update cetas.collections_wmc set " +
 				   "paap_id = " + paramDS.getPatentApplication().getPaapId() + ", " +
 				   "wmce_id = " + paramDS.getPatentApplication().getWildlifeManagementCenter().getWmceId() + ", " +
 				   "coll_date_update = '" + new SimpleDateFormat("yyyy-MM-dd'T'hh:mm:ss").format(new Date()) + "'," +
 				   "coll_user_update = '" + usrRequestIndex.getUserName()+ "'" +
 				   "where dsipt_id = " + paramDS.getDsiptId();
   	 
        	PreparedStatement psDS = con.prepareStatement(qStr);
   		 psDS.execute();
   		 psDS.close();
   		 con.close();
   		 
   	 } catch (Exception e) {
   		 e.printStackTrace();   		 
   	 }
   	 long timeProcess = (System.currentTimeMillis() - start)/1000;
   	 mensaje += "<p>Se ha finalizado el " +
   			 new SimpleDateFormat("dd MMM yyyy \' a las \' hh:mm:ss",new Locale("es", "ES")).format(new Date()) +
   			 ", tiempo de duraciÃ³n " + timeProcess + " segundos</p>";
   	 sendEmail(user.getPeople().getEmail(), "Proceso de indexacion desde nodo nacional de GBIF", disenioEmail());
   	 System.out.println(biodiversityCatalogParameterFacade.findByCode("notificacion.indexgbif.end").getBccaValue() + " en " + timeProcess + " segundos");   		 
    }*/
    
    public void setPatentWMC(DatasetIpt paramDS, User user, long start)
    {
   	 try {    
   		 Connection con = getConnection();
   		 //Actualiza la patente y el centro de manejo de los registros asociados al dataset
        	String qStr = "update biodiversity_mcm.collections_request set " +
 				   "pain_id = " + paramDS.getPatentRequest().getPatentInformation() + ", " +
 				   "pare_id = " + paramDS.getPatentRequest().getId() + ", " +
 				   "coll_date_update = '" + new SimpleDateFormat("yyyy-MM-dd'T'hh:mm:ss").format(new Date()) + "'," +
 				   "coll_user_update = '" + usrRequestIndex.getUserName()+ "'" +
 				   "where dsipt_id = " + paramDS.getDsiptId();
   	 
        	PreparedStatement psDS = con.prepareStatement(qStr);
   		 psDS.execute();
   		 psDS.close();
   		 con.close();
   		 
   	 } catch (Exception e) {
   		 e.printStackTrace();   		 
   	 }
   	 long timeProcess = (System.currentTimeMillis() - start)/1000;
   	 mensaje += "<p>Se ha finalizado el " +
   			 new SimpleDateFormat("dd MMM yyyy \' a las \' hh:mm:ss",new Locale("es", "ES")).format(new Date()) +
   			 ", tiempo de duraciÃ³n " + timeProcess + " segundos</p>";
   	 sendEmail(user.getPeople().getEmail(), "Proceso de indexacion desde nodo nacional de GBIF", disenioEmail());
   	 System.out.println(biodiversityCatalogParameterFacade.findByCode("notificacion.indexgbif.end").getBccaValue() + " en " + timeProcess + " segundos");   		 
    }
    
    
    /**
     * Recupera la informaciÃ³n de las publicaciones realizadas
     * en el nodo nacional desde el API de GBIF en formato json
     **/    
    public JSONArray requestApiGbif() {
   	 try {
   		 //Se recupera el parametro de la url del servicio REST
   		 String urlJson = bgCatalogFacade.findByCode("ipt_ds_url").getBigcValue();
        	URL url = new URL(urlJson);
       	 
        	//Se realiza la conexiÃ³n al servicio API de GBIF
        	HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        	conn.setRequestMethod("GET");
        	conn.setRequestProperty("Accept", "application/json");
        	conn.setConnectTimeout(60000);
        	conn.setReadTimeout(60000);
        	conn.connect();
       	 
        	//Se recupera el stream que devuelve el servicio REST
        	BufferedReader br = new BufferedReader(new InputStreamReader(
                	(conn.getInputStream()),"UTF-8"));
   		 
        	//JSON parser del stream a un objeto JSON
        	JSONParser jsonParser = new JSONParser();
        	Object obj = jsonParser.parse(br);
        	JSONObject jsonRoot = (JSONObject) obj;
                  	 
        	conn.disconnect();
       	 
        	return (JSONArray) jsonRoot.get("results");
       	 
   	 }catch (Exception e) {
   		 e.printStackTrace();
   		 return null;
   	 }
    }
    
    /**
     * Inicia el proceso asyncrono indexador de las collecciones de historia natural
     * desde el nodo nacional de GBIF
     * @param doi Identificador unico de cada publicaciÃ³n
     * @param user Usuario que realiza el pedido de indexaciÃ³n
     * @param start Tiempo de inicio del proceso de indezacion
     * @param resultList Recibe un json con los datos de una especifica publicaciÃ³n
     * @return
     */    
    @Asynchronous
    public void getParamCatalog(String doi, User user, long start, JSONArray resultList, PatentRequest patentRequest) {
   	 conf = Configuration.defaultConfiguration().addOptions(Option.DEFAULT_PATH_LEAF_TO_NULL);
   	 mensaje = "<p>Proceso iniciado el " +
   			   new SimpleDateFormat("dd MMM yyyy \' a las \' hh:mm:ss",new Locale("es", "ES")).format(new Date()) +
   			   ", se han obtenido los siguientes resultados:</p>";
   	 paramDOI = doi;
   	 startProcess = start;
   	 usrRequestIndex = user;    
   	 if(resultList == null) {
   		 resultList = requestApiGbif();
   	 }
   	 
   	 getIPTDatasets(resultList, patentRequest);   	 
    }
    
    /**
     * Obtener la informaciÃ³n de los datasets publicados
     * en el nodo nacional desde el API de GBIF con el
     * consumo del servicio REST en formato JSON
     * @param resultList Recibe un json con los datos de una especifica publicaciÃ³n o de todas las publicaciones en el GBIF
     * @return
     */
    @SuppressWarnings("unchecked")
	private void getIPTDatasets(JSONArray resultList, PatentRequest patentRequest)
    {
   	 try {    
   		 //Iterate over results dataset array
        	Iterator<JSONObject> iteratorDS = resultList.iterator();
       	 
        	//Recupera los paramtros para la indexacion
       	 
        	//Campos del cada dataset
        	fieldsDS =  bgCatalogFacade.findByBict(bcTypeFacade.findByType("campos_ds_ipt"));
       	 
        	//Listado de campos a mapear en la tabla temporal collections_tmp
        	fieldsColl =  bgCatalogFacade.findByBict(bcTypeFacade.findByType("campos_coll_ipt"));
       	 
        	//Valor del institucion_code que se llena para todos los registros       	 
        	bgInstCode = bgCatalogFacade.findByCode("ipt_coll_institucion_code");
       	 
        	//Listado de los campos a ser almacenados, son los campos en la tabla collections_wmc
        	List<BiodiversityGeneralCatalog> lstFields =  bgCatalogFacade.findByBict(bcTypeFacade.findByType("lista_coll_ipt"));
        	strListFields = "";       	 
        	for (BiodiversityGeneralCatalog bgcItem : lstFields) {
       		 strListFields += (strListFields.length()>0?",":"") + bgcItem.getBigcDescription().toString();
   		 }       	 
       	 
        	//Si llega un doi que no sea el comodÃ­n all, busca por el item del json del doi en especÃ­fico
   		 while (iteratorDS.hasNext()) {
   			 JSONObject jsonItem = iteratorDS.next();
   			 loopResultsDataSetIPT(jsonItem, patentRequest);   		 
   		 }
   		 
   	 } catch (Exception e) {
   		 e.printStackTrace();   		 
   	 }
   	 long timeProcess = (System.currentTimeMillis() - startProcess)/1000;
   	 mensaje += "<p>Se ha finalizado el " +
   			 new SimpleDateFormat("dd MMM yyyy \' a las \' hh:mm:ss",new Locale("es", "ES")).format(new Date()) +
   			 ", tiempo de duraciÃ³n " + timeProcess + " segundos</p>";
   	 dataSetIPT.updateUseDOI(paramDOI, false, usrRequestIndex);
   	 sendEmail(usrRequestIndex.getPeople().getEmail(), "Proceso de indexacion desde nodo nacional de GBIF", disenioEmail());
   	 System.out.println(biodiversityCatalogParameterFacade.findByCode("notificacion.indexgbif.end").getBccaValue() + " en " + timeProcess + " segundos");   		 
    }
    
    /**
     * Recorrer la informaciÃ³n de un datasets si cumple con las codicionales
     * de tipo de dato parametrizado, si cumple se descarga la informacion almacenada en el IPT
     * @param jsonItem
     * @param fieldsDS
     * @return
     */
    private void loopResultsDataSetIPT(JSONObject jsonItem, PatentRequest patentRequest) /*throws Exception*/
	{
    	//Inicia el ciclo de dataset
    	String title = (String) jsonItem.get("title");    
    	System.out.println("Inicia dataset " + title);
   	 
   	try {
   		 String itemDOI = jsonItem.get("doi").toString();
   		 
   		 //Verifica si el tipo de dato del dataset esta dentro de los tipo parametrizados en el catalogo
   		 if(bgCatalogFacade.findByCode("ipt_ds_type").getBigcValue().contains(jsonItem.get("type").toString())) {
   			 DatasetIpt itemDS = dataSetIPT.findByDOI(itemDOI);
   		 
   			 //Verifica si existe el dataset en la base
   			 if(itemDS != null) {
   				 //Recuperar el parametro del campo de fecha a comparar para ver si hay actualizaciones
   				 SimpleDateFormat sdfAux = new SimpleDateFormat("yyyy-MM-dd'T'hh:mm:ss");
   				 Date dsModified = sdfAux.parse((String)
   						 JsonPath.read(jsonItem, bgCatalogFacade.findByDescription("Fecha version Dataset IPT").getBigcCode()));
   				 
   				 //Verifica si habido alguna modificaciÃ³n en el dataset con el que esta cargado en la base
   				 if (itemDS.getDsiptModified().compareTo(dsModified) == 0) {
   					 mensaje += "<p>La publicaciÃ³n de <b>" + title + "</b>, ya se encuentra en la base y no ha tenido modificaciones.</p>";
   					 System.out.println("La publicaciÃ³n de " + title + ", ya se encuentra en la base y no ha tenido modificaciones");
   					 return;
   				 }   				 
   			 }else {
   				 //Si no lo encuentra se hace el ingreso en la base
   				 itemDS = new DatasetIpt();
   			 }
   			 for(BiodiversityGeneralCatalog itemField : fieldsDS) {
   				 String nameField = itemField.getBigcValue().toString();
   				 String nameValue = itemField.getBigcCode().toString();
   				 
   				 if(JsonPath.using(conf).parse(jsonItem).read(nameValue) != null) {
   					 //Setea los valores en funciÃ³n del path recuperado de los parametros
   					 Field field = DatasetIpt.class.getDeclaredField(nameField);
   					 field.setAccessible(true);
   					 if (field.getType().isInstance(new Date())){   							                                         	 
   						 SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'hh:mm:ss");   							 
   						 field.set(itemDS,sdf.parse((String)JsonPath.read(jsonItem, nameValue)));
   					 }else {
   						 if(JsonPath.read(jsonItem, nameValue).getClass().isInstance(new net.minidev.json.JSONArray())) {
   							 field.set(itemDS,((net.minidev.json.JSONArray)JsonPath.read(jsonItem, nameValue)).get(0));
   						 }
   						 else {
   							 field.set(itemDS,JsonPath.read(jsonItem, nameValue));
   						 }   						 
   					 }    
   				 }   												 
   			 }
   			 //Guarda o actualiza el dataset
   			 mensaje += "<p>La publicaciÃ³n de <b>" + title + "</b>, con cÃ³digo DOI: <b>" + itemDOI + "</b></p><blockquote>" ;
   			 itemDS.setPatentRequest(patentRequest);
   			 itemDS = dataSetIPT.save(itemDS, usrRequestIndex);
   			 String nameFile = ((net.minidev.json.JSONArray)JsonPath.read(jsonItem, bgCatalogFacade.findByCode("ipt_url_dwc_archive").getBigcValue())).get(0).toString();
   			 unZip(nameFile, title, itemDS);
   			 mensaje += "</blockquote>";
   		 }else {
   			 mensaje += "<p>La publicaciÃ³n de <b>" + title + "</b>, no corresponde al tipo de datos parametrizado, no fue cargado a la base.</p>";
   			 System.out.println("Dataset " + title + ", no corresponde al tipo de datos parametrizado, no fue cargado a la base");
   		 }    
   	 } catch (Exception e) {
   		 log.error("Error al recuperar la informaciÃ³n del dataset, " +e.getMessage());
   		 mensaje += "Error al recuperar la informaciÃ³n del dataset.</blockquote>";
   		 //throw e;
   	 }  	 
	}
    
    /**
     * Para recuperar en memoria el archivo zip que contiene los archivos con los datos
     * de la publicaciÃ³n de un dataset descargado desde una url obtenida desde el api de GBIF
     * @param urlFileZip
     * @param title
     * @param itemDS
     * */    
    private void unZip(String urlFileZip, String title, DatasetIpt itemDS) throws Exception {
   	 PreparedStatement pstmtInsGrup = null;
   	 Connection con = getConnection();
   	 try {
   		 //Recupera los datos desde la url que tienen los datos del dataset en formato zip
   		 URL ficheroUrl = new URL(urlFileZip);
   		 ZipInputStream zis = new ZipInputStream(ficheroUrl.openStream());
        	ZipEntry ze = zis.getNextEntry();
        	String line = "";
        	long initProcessColl = System.currentTimeMillis();
        	BufferedReader bisEvent = null;
        	//Se recorre el archivo zip
        	while (ze != null)
        	{
       		 //System.out.println( "Archivo: " + ze.getName());
       		 String nameFile = ze.getName();       		 
   			 if(nameFile.equals(bgCatalogFacade.findByCode("ipt_ds_name_file_occur").getBigcValue()))
            	{
           		 BufferedReader bis = new BufferedReader(new InputStreamReader(zis,"UTF-8"));
                	line = bis.readLine();
                	long i = 0;
                	List<String> headFile = Arrays.asList(line.split("\t"));
               	 
                	//Se arma el query de insert de todos los registros del archivo en la tabla temporal
                	String qstr0 = "insert into biodiversity_mcm.collections_tmp(pare_id, pain_id, dsipt_id, coll_status_ipt, " +
               			 bgInstCode.getBigcValue() + ", " +
               			 "coll_user_create, coll_date_create, coll_status";
               	 
   				 //qstr1 para guardar los nombres de los campos a insertar
                	String qstr1 = "";     
               	 
                	//qstr2 para guardar los valores de los campos a insertar
                	String qstr2 = ") values (" +
               			 itemDS.getPatentRequest().getId() + ", " +
               			 itemDS.getPatentRequest().getPatentInformation().getId() + ", " +
               			 itemDS.getDsiptId() + ", " +
               			 "true, '" + bgInstCode.getBigcDescription() + "', '" + usrRequestIndex.getUserName() + "', '" +
               			 new SimpleDateFormat("yyyy-MM-dd'T'hh:mm:ss").format(new Date()) +
               			 "', true";
               	 
                	List<String> headFields = Arrays.asList(new String[headFile.size()]);
               	 
                	//Colocar los campos de la tabla en el orden que llega del archivo
                	for(BiodiversityGeneralCatalog itemField : fieldsColl) {
       				 String nameFieldFile = itemField.getBigcDescription().toString();
       				 String nameFieldTable = itemField.getBigcValue().toString();
       				 int indexField = headFile.indexOf(nameFieldFile);
       				 if(indexField>=0) {
       					 headFields.set(indexField, nameFieldTable);
       				 }
       			 }
               	 
                	//Armar el query de inserciÃ³n, campos y sus valores
                	for(int x=0; x<headFields.size(); x++){
               		 if(headFields.get(x)!= null) {
               			 if(x<headFields.size()){
   	            			 qstr1 += ", ";
   	            			 qstr2 += ", ";
   	            		 }
               			 qstr1 += headFields.get(x);
   	            		 qstr2 += "?";   	            		 
               		 }
                	}
               	 
                	qstr0 += qstr1 + qstr2 + ")";
	                //System.out.println("Consulta a insertar: " + qstr0);
               	 
                	line = bis.readLine();               	 
                	if (con != null) {
               		 //Para recuperar el tipo de dato de cada columna a insertar
               		 qstr1 = qstr1.substring(1);
               		 String q = "Select " + qstr1 + " from biodiversity_mcm.collections_tmp where 1=0";
               		 PreparedStatement ps = con.prepareStatement(q);
               		 ps.execute();
               		 ResultSetMetaData rs =  ps.getMetaData();
   					 
               		 pstmtInsGrup = con.prepareStatement(qstr0);
   					 
   					 // Recorrer la informaciÃ³n del archivo descargado desde el api de GBIF
              		      // para ir guardando cada unos de los registros del archivo occurrence.txt
              		      // en la tabla temporal collections_tmp, segun el mapeo de campos parametrizados.               		 
   	             	while (line != null)
   	             	{
		                	//line += ".";
   	            		 String[] dataArray = line.split("\t");
   	            		 //Insercion en proceso batch de las ocurrencias recuperadas
		                	
		                	int k = 1;
		                	for (int j = 0; j < headFields.size(); j++) {
		                		//System.out.println("Campo:" + headFields.get(j) + ": "+ dataArray[j]);
   	            			 if(headFields.get(j)!= null) {
		                			try {
			                			if(dataArray[j].equals("") || dataArray[j].equals(null)) {
		                					pstmtInsGrup.setNull(k, Types.NULL);
		                				}else {
		                					if(rs.getColumnType(k) == Types.DATE) {
		                						if (isValid((String) dataArray[j])) {
			            							SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");	
			                						pstmtInsGrup.setObject(k, sdf.parse((String) dataArray[j]), Types.DATE);
			            						}else {
			            							pstmtInsGrup.setNull(k, Types.NULL);
			            						}
		                					}else {
		                						pstmtInsGrup.setObject(k, dataArray[j].replaceAll(",", "."),rs.getColumnType(k));
		                					}	                						
		                				}
		                			} catch (Exception e) {
										// TODO: handle exception
		                				pstmtInsGrup.setNull(k, Types.NULL);
									}
   	            				 k++;
   	            			 }
   						 }
   	            		 pstmtInsGrup.addBatch();
   	            		 i++;
   	            		 line = bis.readLine();
   	             	}
   	             	pstmtInsGrup.executeBatch();
               		 ps.close();
               		 
	                	//mensaje += "se realizo con éxito la carga de " + i + " registros.<br>";
                    	System.out.println("Numero de registros: " + i);
                    	break;
   				 }
            	}             	 
            	ze = zis.getNextEntry();
        	}
        	zis.closeEntry();
        	zis.close();
       	 
        	zis = new ZipInputStream(ficheroUrl.openStream());
        	ze = zis.getNextEntry();
        	line = "";
       	 
        	//Se recorre el archivo zip
        	while (ze != null)
        	{
            	//System.out.println( "Archivo: " + ze.getName());
       		 String nameFile = ze.getName();
       		 if(nameFile.equals(bgCatalogFacade.findByCode("ipt_ds_name_file_event").getBigcValue())) {
       			 bisEvent = new BufferedReader(new InputStreamReader(zis,"UTF-8"));       	 
   	         	//Actualiza con los datos del event
   	         	line = bisEvent.readLine();
   	        	 
   	         	List<String> headFile = Arrays.asList(line.split("\t"));
   	        	 
   	         	List<String> headFields = Arrays.asList(new String[headFile.size()]);
   	        	 
   	         	//Colocar los campos de la tabla en el orden que llega del archivo
   	         	for(BiodiversityGeneralCatalog itemField : fieldsColl) {
   					 String nameFieldFile = itemField.getBigcDescription().toString();
   					 String nameFieldTable = itemField.getBigcValue().toString();
   					 int indexField = headFile.indexOf(nameFieldFile);
   					 if(indexField>=0) {
   						 headFields.set(indexField, nameFieldTable);
   					 }
   				 }
   	        	 
   	         	String strEventId = "";
   	         	//qstr1 para guardar los nombres de los campos a actualizar
   	         	String qstr1 = "";
   	        	 
   	         	//Actualiza la informaciÃ³n que tenga el mismo eventID
   				 String qUpEv = "update biodiversity_mcm.collections_tmp set ";   	 
   	      		 
   				 for(int x=0; x<headFields.size(); x++){
   	        		 if(headFields.get(x)!= null) {
   	        			 qstr1 += headFields.get(x);
   	        			 qUpEv += headFields.get(x) + " = ?";
   	        			 if(x<(headFields.size()-1)){
   	        				 qstr1 += ", ";
   	        				 qUpEv += ", ";
   		        		 }
   	        		 }
   				 }
   				 qstr1 = qstr1.substring(0,qstr1.length()-2);
   				 qUpEv = qUpEv.substring(0,qUpEv.length()-2) + " where coll_eventid = ?" ;
   			 
   	        	 
		            //System.out.println("Consulta a insertar: " + qUpEv);
   	        	 
   	         	line = bisEvent.readLine();    
   	        	 
   	         	if (con != null) {
   	        		 //Para recuperar el tipo de dato de cada columna a insertar
   	        		 String q = "Select " + qstr1 + " from biodiversity_mcm.collections_tmp where 1=0";
   	        		 PreparedStatement ps = con.prepareStatement(q);
   	        		 ps.execute();
   	        		 ResultSetMetaData rs =  ps.getMetaData();
   					 
   	        		 pstmtInsGrup = con.prepareStatement(qUpEv);
   					 
   					 // Recorrer la informaciÃ³n del archivo descargado desde el api de GBIF
   	       		      // para ir guardando cada unos de los registros del archivo event.txt
   	       		      // en la tabla temporal collections_tmp, segun el mapeo de campos parametrizados.               		 
   	             	while (line != null)
   	             	{
   	            		 String[] dataArray = line.split("\t");
   	            		 
   	            		 //Insercion en proceso batch de las ocurrencias recuperadas
   	            		 int k = 1;
   	            		 for (int j = 0; j < dataArray.length; j++) {
		                		//System.out.println("Campo:" + headFields.get(j) + ": "+ dataArray[j]);
   	            			 if(headFields.get(j)!= null) {
   	            				 if(headFields.get(j).equals("coll_eventid")) {
   	            					 strEventId = dataArray[j].toString();
   	            				 }
   	            				 if(dataArray[j].equals("") || dataArray[j].equals(null)) {
   	        						 pstmtInsGrup.setNull(k, Types.NULL);
   	        					 }else {
   	        						 if(rs.getColumnType(k) == Types.DATE) {
   	        							 if (isValid((String) dataArray[j])) {
   	        								 SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");    
   		        							 pstmtInsGrup.setObject(k, sdf.parse((String) dataArray[j]), Types.DATE);
   	        							 }else {
   	        								 pstmtInsGrup.setNull(k, Types.NULL);
   	        							 }   	        							 
   	        						 }else {
   	        							 pstmtInsGrup.setObject(k, dataArray[j].replaceAll(",", "."),rs.getColumnType(k));
   	        						 }               							 
   	        					 }
   	            				 k++;
   	            			 }
   						 }
   	            		 pstmtInsGrup.setObject(k,strEventId,Types.VARCHAR);
   	            		 pstmtInsGrup.addBatch();
   	            		 line = bisEvent.readLine();
   	             	}
   	             	pstmtInsGrup.executeBatch();
   	             	ps.close();
   	             	break;
   	         	}
       		 }
       		 ze = zis.getNextEntry();
        	}
        	zis.closeEntry();
        	zis.close();
       	 
   		 //Actualiza el campo gelo_id en relaciÃ³n al valor que llegue en el campo countryCode
   		 //Actualiza los campos de longitud y latitud en relacion al valor del campo verbatimCoordinateSystem
   		 String qStr = "update biodiversity_mcm.collections_tmp ct set " +
   				 "gelo_id = (select gelo_id from biodiversity.country_codes where cncod_iso_code = ct.coll_country_code), " +
   				 "coll_coordinate_x = case when lower(coll_coordinate_system) like '%utm%' then coll_tmp_decimal_longitude end, " +
   				 "coll_coordinate_y = case when lower(coll_coordinate_system) like '%utm%' then coll_tmp_decimal_latitude end, " +
   				 "coll_decimal_longitude = case when lower(coll_coordinate_system) like '%grados%' then coll_tmp_decimal_longitude end, " +
   				 "coll_decimal_latitude = case when lower(coll_coordinate_system) like '%grados%' then coll_tmp_decimal_latitude end, " +
   				 "coll_verbatim_longitude = case when coll_coordinate_system is null then coll_tmp_decimal_longitude end, " +
   				 "coll_verbatim_latitude = case when coll_coordinate_system is null then coll_tmp_decimal_latitude end";
   		 
        	PreparedStatement psDS = con.prepareStatement(qStr);
   		 psDS.execute();
   		 
   		 //Actualiza la columna coll_status_ipt a false de los registros que se han eliminado desde el IPT
        	qStr = "update biodiversity_mcm.collections_request set " +
 				   "coll_status_ipt = false, " +
 				   "coll_date_update = '" + new SimpleDateFormat("yyyy-MM-dd'T'hh:mm:ss").format(new Date()) + "'," +
 				   "coll_user_update = '" + usrRequestIndex.getUserName()+ "'" +
 				   "where coll_collection_number in (" +
 				   "select t1.coll_collection_number from biodiversity_mcm.collections_request t1 left join biodiversity_mcm.collections_tmp t2 on "+
 				   "t1.coll_collection_number = t2.coll_collection_number and t1.dsipt_id = t2.dsipt_id "+
   			   "where t2.coll_collection_number is null and t1.dsipt_id = " + itemDS.getDsiptId() + ")";
   	 
        	psDS = con.prepareStatement(qStr);
   		 psDS.execute();   		 
   		 
   		 //Devuelve cuantos registros se han eliminado desde el IPT
   		 qStr = "select count(*) num_reg_delete from biodiversity_mcm.collections_request t1 left join biodiversity_mcm.collections_tmp t2 " +
			  		"on t1.coll_collection_number = t2.coll_collection_number and t1.dsipt_id = t2.dsipt_id " +
			  		"where t1.dsipt_id = " + itemDS.getDsiptId() + " and t2.coll_collection_number is null";
   		 
   		 Statement stCount = con.createStatement();
        	ResultSet rsQuery = stCount.executeQuery(qStr);
        	rsQuery.next();
   		 mensaje += "Se han eliminado " + rsQuery.getString("num_reg_delete") + " registros desde el IPT,  se mantienen en base solo cambian su status.<br>";
   		 
   		 //Actualiza la informaciÃ³n que sea diferente
   		 qStr = "update biodiversity_mcm.collections_request t1 set " +
     				   "coll_status_ipt = true, " +
     				   "coll_date_update = '" + new SimpleDateFormat("yyyy-MM-dd'T'hh:mm:ss").format(new Date()) + "'," +
     				   "coll_user_update = '" + usrRequestIndex.getUserName()+ "',";
     		 
   		 String[] arrStrFields = strListFields.split(",");     
   		 
   		 for (int j = 0; j < arrStrFields.length; j++) {
   			 qStr += arrStrFields[j].trim() + "=" + "t2."+ arrStrFields[j].trim();
   			 if(j<arrStrFields.length-1){
   				 qStr += ", ";
       		 }
   		 }
   		 qStr += " from biodiversity_mcm.collections_tmp t2 where t1.coll_collection_number = t2.coll_collection_number " +
 					"and t1.dsipt_id = t2.dsipt_id and t1.dsipt_id = " + itemDS.getDsiptId();
   	 
        	psDS = con.prepareStatement(qStr);
   		 psDS.execute();
   		 
   		 //Devuelve cuantos registros se han actualizado desde el IPT
   		 qStr = "select count(*) num_reg_update from biodiversity_mcm.collections_request t1 inner join biodiversity_mcm.collections_tmp t2 "+
			  		"on t1.coll_collection_number = t2.coll_collection_number and t1.dsipt_id = t2.dsipt_id and t1.dsipt_id = " + itemDS.getDsiptId();
  	 
        	rsQuery = stCount.executeQuery(qStr);
        	rsQuery.next();
   		 mensaje += "Se han actualizado " + rsQuery.getString("num_reg_update") + " registros desde el IPT.<br>";
   		    			 
   		 //Devuelve cuantos registros se han insertado desde el IPT   		 
   		 qStr = "select count(*) num_reg_insert from biodiversity_mcm.collections_tmp t1 left join biodiversity_mcm.collections_request t2 on " +
   				"t1.coll_collection_number = t2.coll_collection_number and t1.dsipt_id = t2.dsipt_id " +
   				"where t1.dsipt_id = " + itemDS.getDsiptId() + " and t2.coll_collection_number is null";
   		 
  	 
        	rsQuery = stCount.executeQuery(qStr);
        	rsQuery.next();
   		 mensaje += "Se han insertado " + rsQuery.getString("num_reg_insert") + " registros desde el IPT.<br>";
   			    				 
   		 //Inserta la informaciÃ³n nueva que llega desde el IPT
   		 qStr = "insert into biodiversity_mcm.collections_request (pare_id, pain_id, dsipt_id, coll_status_ipt, " +
   				 "coll_user_create, coll_date_create, coll_status, coll_loading_method," + strListFields + ")(" +
   				 "select t1.pare_id, t1.pain_id, t1.dsipt_id, t1.coll_status_ipt, t1.coll_user_create, t1.coll_date_create, t1.coll_status, 3," +
   				 "t1." + strListFields.replace("," , ", t1.") + " from biodiversity_mcm.collections_tmp t1 left join biodiversity_mcm.collections_request t2 on " +
   				 "t1.coll_collection_number = t2.coll_collection_number and t1.dsipt_id = t2.dsipt_id " +
     				 "where t1.dsipt_id = " + itemDS.getDsiptId() + " and t2.coll_collection_number is null)";
     		 
   		 psDS = con.prepareStatement(qStr);
   		 psDS.execute();
   		 
   		 //Borrar los datos de la tabla temporal
   		 qStr = "delete from biodiversity_mcm.collections_tmp";
   		 psDS = con.prepareStatement(qStr);
   		 psDS.execute();
   		 
   		 //Se cierra las sentencias y la conexiÃ³n       				    				 
   		 psDS.close();               		 
        	con.close();
       	 
        	long timeProcessColl = ( System.currentTimeMillis() - initProcessColl) / 1000;
        	System.out.println("Se termino la carga de collections del dataset " + title + " en " + timeProcessColl + " segundos" );
       	 
    	} catch (IOException ex) {
   		 log.error("Error al recuperar la informaciÃ³n del archivo zip, " + ex.getMessage());
   		 mensaje += "Error al recuperar la informaciÃ³n del archivo zip, " + ex.getMessage() + ". ";
   		 ex.printStackTrace();
   		 throw ex;
    	} catch (SQLException e) {
   		 mensaje += "No se logro realizar la carga de informaciÃ³n, debido a " + e.getNextException() + ". ";
   		 System.out.println("Captura de NextException: " + e.getNextException());   		 
   		 e.printStackTrace();
   		 //Roll back del dataset si es nuevo
   		 String qStr = "delete from biodiversity_mcm.collections_tmp";
   		 PreparedStatement psDS = con.prepareStatement(qStr);
   		 psDS.execute();
   		 
   		 if(itemDS.getDsiptDateUpdate() == null) {
   			 String q = "delete from biodiversity_mcm.dataset_ipt where dsipt_id=" + itemDS.getDsiptId();
   			 PreparedStatement ps = con.prepareStatement(q);
   			 ps.execute();
   			 ps.close();
   		 }
   		 throw e;
   	 } catch (Exception e) {
   		 mensaje += "No se logro realizar la carga de informaciÃ³n, debido a " + e.getMessage() + ". ";
   		 System.out.println("Captura de NextException: " + e.getMessage());   		 
   		 e.printStackTrace();
   		 //Roll back del dataset si es nuevo
   		 String qStr = "delete from biodiversity_mcm.collections_tmp";
   		 PreparedStatement psDS = con.prepareStatement(qStr);
   		 psDS.execute();
   		 
   		 if(itemDS.getDsiptDateUpdate() == null) {
   			 String q = "delete from biodiversity_mcm.dataset_ipt where dsipt_id=" + itemDS.getDsiptId();
   			 PreparedStatement ps = con.prepareStatement(q);
   			 ps.execute();
   			 ps.close();
   		 }
   		 throw e;
   	 }
   	 finally {   		 
   		 try {
   			 if (pstmtInsGrup != null) {   				 
   				 pstmtInsGrup.close();   				 
   			 }
   			 if(con != null) {
   				 con.close();
   			 }
   			 
   		 } catch (SQLException e) {
				// TODO Auto-generated catch block
   			 e.printStackTrace();
   		 }
   	 }   	 
	}    
    
    /**
     * Obtener el pool de conexiones
     **/
    private Connection getConnection() {

   	 Connection connMY = null;
   	 try {

   		 Context ctx = new InitialContext();
   		 DataSource ds = (DataSource) ctx.lookup("java:jboss/datasources/SuiaDS");
   		 connMY = ds.getConnection();
   	 }

   	 catch (Exception e) {
   		 e.printStackTrace();
   	 }

   	 return connMY;

    }
   	 
    /**
     * Estructura del diseÃ±o del mail a ser enviado
     * @param mensaje
     * */    
    public String disenioEmail() {

   	 try {
   		 String header = biodiversityCatalogParameterFacade.findByCode("html_template.email.header.default").getBccaValue();
   		 String footer = biodiversityCatalogParameterFacade.findByCode("html_template.email.footer.default").getBccaValue();
   		 String content = biodiversityCatalogParameterFacade.findByCode("html_template.email.content.default").getBccaValue();
   		 String peopleMail = usrRequestIndex.getPeople().getPeopTitle() + " " + usrRequestIndex.getPeople().getPeopName();
   		 String disenio = header.replace("usuario", peopleMail) + content.replace("mensaje", mensaje) + footer;
   		 return disenio;

   	 } catch (Exception e) {
   		 e.printStackTrace();
   	 }
   	 return null;
    }
    
    @SuppressWarnings("deprecation")
	public static boolean sendEmail(String emailsDestino, String asunto, String mensaje) {
   	 try {
   		 Properties props = new Properties();
   		 String prop_fileUrl = ViewResources.getProperty("email.properties.file", PropertyType.PROPERTY);
   		 String prop_userKey = ViewResources.getProperty("email.key.user", PropertyType.PROPERTY);
   		 String prop_passwordKey = ViewResources.getProperty("email.key.password", PropertyType.PROPERTY);

   		 props.load(new FileInputStream(prop_fileUrl));
   		 String usuario = props.getProperty(prop_userKey);
   		 String password = props.getProperty(prop_passwordKey);

   		 Session session = Session.getInstance(props, null);

   		 MimeMessage message = new MimeMessage(session);
   		 message.addRecipient(Message.RecipientType.TO, new InternetAddress(emailsDestino));
   		 
   		 message.setSubject(asunto);
   		 message.setSentDate(new Date());
   		 message.setText(asunto);

   		 InternetAddress maeAddress = new InternetAddress(usuario);
   		 maeAddress.setPersonal(FROM);
   		 message.setFrom(maeAddress);

   		 message.setContent(mensaje, CONTENT_TYPE);
   		 Transport tr = session.getTransport(TRANSPORT);
   		 tr.connect(usuario, password);
   		 message.saveChanges();
   		 tr.sendMessage(message, message.getAllRecipients());
   		 tr.close();
   		 return true;
   	 } catch (MessagingException e) {
   		 e.printStackTrace();
   	 } catch (Throwable e) {
			// TODO Auto-generated catch block
   		 e.printStackTrace();
   	 }
   	 return false;
    }
    
    private boolean isValid(String dateStr) {
   	 SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
    	try {
        	sdf.parse(dateStr);
    	} catch (ParseException e) {
        	return false;
    	}
    	return true;
	}

}



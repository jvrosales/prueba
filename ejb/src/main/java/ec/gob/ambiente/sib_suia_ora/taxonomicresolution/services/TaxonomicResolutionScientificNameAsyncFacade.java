package ec.gob.ambiente.sib_suia_ora.taxonomicresolution.services;

import java.io.FileInputStream;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Properties;

import javax.ejb.Asynchronous;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.persistence.EntityManager;
import javax.persistence.Query;

import ec.gob.ambiente.enlisy.dao.AbstractFacade;
import ec.gob.ambiente.enlisy.speciescatalog.model.SpecieTaxa;
import ec.gob.ambiente.enlisy.speciescatalog.services.BiodiversityCatalogParameterFacade;
import ec.gob.ambiente.enlisy.wildlifeevent.services.BiodiversityGeneralCatalogFacade;
import ec.gob.ambiente.sib_suia_ora.taxonomicresolution.model.PropertyType;
import ec.gob.ambiente.sib_suia_ora.taxonomicresolution.model.ViewResources;

/**
 * Servicios para la consulta de la informacion taxonomica de un nombre o
 * nombres cientificos enviados como parametro en el servicio web de resolucion
 * taxonomica
 * 
 * @author CristinaFactos
 *
 */
@Stateless
public class TaxonomicResolutionScientificNameAsyncFacade extends AbstractFacade<SpecieTaxa, Integer> {

	public TaxonomicResolutionScientificNameAsyncFacade() {
		super(SpecieTaxa.class, Integer.class);
	}

	static final String messagesFile = "messages.properties";
	static final String propertiesFile = "mae.properties";
	private static final String FROM = "Ministerio del Ambiente";
	private static final String CONTENT_TYPE = "text/html; charset=utf-8";
	private static final String TRANSPORT = "smtp";
	private static final String SPLIT_SEPARATE = ";";

	/**
	 * Servicios parametros
	 */
	@EJB
	private BiodiversityCatalogParameterFacade biodiversityCatalogParameterFacade;

	@EJB
	private BiodiversityGeneralCatalogFacade catalogFacade;
	
	@Asynchronous
	public void getTaxonomicResolutionByScientificName(String nameSubmitted, Integer matchAccuracy, String onlyBestMach,
			String kingdom, String email) {
		String key = generateKey();
		sendEmail(email, "Servicio Web Resolución Taxonómica",
				disenioEmail(key, "notificacion.key.inicio.resolucion.taxonomica"));

		nameSubmitted = nameSubmitted.replace("\r\n", ";");
		String[] total = nameSubmitted.split(";");
		Integer n = total.length;
		try {
			if (n >= 2000 && (matchAccuracy <= 80) && onlyBestMach.equals("0")) {
				if (n >= 2000 && n <= 5000) {
					Integer longInt = (n + 1) / 5;

					String[] partOne = Arrays.copyOfRange(total, 0, longInt);
					String[] partTwo = Arrays.copyOfRange(total, longInt, (longInt + longInt));
					String[] partThree = Arrays.copyOfRange(total, (longInt + longInt), (longInt + longInt + longInt));
					String[] partFour = Arrays.copyOfRange(total, (longInt + longInt + longInt),
							(longInt + longInt + longInt + longInt));
					String[] partFive = Arrays.copyOfRange(total, (longInt + longInt + longInt + longInt), n);

					StringBuilder sBPartOne = new StringBuilder();
					for (String part : partOne) {
						if (part != null && !part.equals("")) {
							sBPartOne.append(part);
							sBPartOne.append(";");

						}
					}
					String url = catalogFacade.findByCode("url_sw_res_tax").getBigcDescription();

					String data = "<soapenv:Envelope xmlns:soapenv=\"http://schemas.xmlsoap.org/soap/envelope/\" xmlns:web=\"http://webservices.taxonomicresolution.sib_suia_ora.ambiente.gob.ec/\">"
							+ " <soapenv:Header/>" + " <soapenv:Body>" + " <web:taxonomicResolutionByScientificName>"
							+ "<nameSubmitted>" + sBPartOne + "</nameSubmitted>" + " <matchAccuracy>" + matchAccuracy
							+ "</matchAccuracy>" + " <onlyBestMatch>" + onlyBestMach + "</onlyBestMatch>" + " <kingdom>"
							+ kingdom + "</kingdom>" + " </web:taxonomicResolutionByScientificName>"
							+ " </soapenv:Body>" + "</soapenv:Envelope> ";
					System.out.println("Inicio consumo Soap 1: " + new Date());
					ConsultTaxonomy soapClient = new ConsultTaxonomy(data, url, 720000);
					System.out.println("Fin consumo Soap 1: " + new Date());
					String pathOne = soapClient.getPath();

					StringBuilder sBPartTwo = new StringBuilder();
					for (String part : partTwo) {
						if (part != null && !part.equals("")) {
							sBPartTwo.append(part);
							sBPartTwo.append(";");

						}
					}

					String dataTwo = "<soapenv:Envelope xmlns:soapenv=\"http://schemas.xmlsoap.org/soap/envelope/\" xmlns:web=\"http://webservices.taxonomicresolution.sib_suia_ora.ambiente.gob.ec/\">"
							+ " <soapenv:Header/>" + " <soapenv:Body>" + " <web:taxonomicResolutionByScientificName>"
							+ "<nameSubmitted>" + sBPartTwo + "</nameSubmitted>" + " <matchAccuracy>" + matchAccuracy
							+ "</matchAccuracy>" + " <onlyBestMatch>" + onlyBestMach + "</onlyBestMatch>" + " <kingdom>"
							+ kingdom + "</kingdom>" + " </web:taxonomicResolutionByScientificName>"
							+ " </soapenv:Body>" + "</soapenv:Envelope> ";
					System.out.println("Inicio consumo Soap 2: " + new Date());
					ConsultTaxonomy soapClientTwo = new ConsultTaxonomy(dataTwo, url, 720000);
					System.out.println("Fin consumo Soap 2: " + new Date());
					String pathTwo = soapClientTwo.getPath();

					StringBuilder sBPartThree = new StringBuilder();
					for (String part : partThree) {
						if (part != null && !part.equals("")) {
							sBPartThree.append(part);
							sBPartThree.append(";");

						}
					}

					String dataThree = "<soapenv:Envelope xmlns:soapenv=\"http://schemas.xmlsoap.org/soap/envelope/\" xmlns:web=\"http://webservices.taxonomicresolution.sib_suia_ora.ambiente.gob.ec/\">"
							+ " <soapenv:Header/>" + " <soapenv:Body>" + " <web:taxonomicResolutionByScientificName>"
							+ "<nameSubmitted>" + sBPartThree + "</nameSubmitted>" + " <matchAccuracy>" + matchAccuracy
							+ "</matchAccuracy>" + " <onlyBestMatch>" + onlyBestMach + "</onlyBestMatch>" + " <kingdom>"
							+ kingdom + "</kingdom>" + " </web:taxonomicResolutionByScientificName>"
							+ " </soapenv:Body>" + "</soapenv:Envelope> ";
					System.out.println("Inicio consumo Soap 3: " + new Date());
					ConsultTaxonomy soapClientThree = new ConsultTaxonomy(dataThree, url, 720000);
					System.out.println("Fin consumo Soap 3: " + new Date());
					String pathThree = soapClientThree.getPath();

					StringBuilder sBPartFour = new StringBuilder();
					for (String part : partFour) {
						if (part != null && !part.equals("")) {
							sBPartFour.append(part);
							sBPartFour.append(";");

						}
					}

					String dataFour = "<soapenv:Envelope xmlns:soapenv=\"http://schemas.xmlsoap.org/soap/envelope/\" xmlns:web=\"http://webservices.taxonomicresolution.sib_suia_ora.ambiente.gob.ec/\">"
							+ " <soapenv:Header/>" + " <soapenv:Body>" + " <web:taxonomicResolutionByScientificName>"
							+ "<nameSubmitted>" + sBPartFour + "</nameSubmitted>" + " <matchAccuracy>" + matchAccuracy
							+ "</matchAccuracy>" + " <onlyBestMatch>" + onlyBestMach + "</onlyBestMatch>" + " <kingdom>"
							+ kingdom + "</kingdom>" + " </web:taxonomicResolutionByScientificName>"
							+ " </soapenv:Body>" + "</soapenv:Envelope> ";
					System.out.println("Inicio consumo Soap 3: " + new Date());
					ConsultTaxonomy soapClientFour = new ConsultTaxonomy(dataFour, url, 720000);
					System.out.println("Fin consumo Soap 3: " + new Date());
					String pathFour = soapClientFour.getPath();

					StringBuilder sBPartFive = new StringBuilder();
					for (String part : partFive) {
						if (part != null && !part.equals("")) {
							sBPartFive.append(part);
							sBPartFive.append(";");

						}
					}

					String dataFive = "<soapenv:Envelope xmlns:soapenv=\"http://schemas.xmlsoap.org/soap/envelope/\" xmlns:web=\"http://webservices.taxonomicresolution.sib_suia_ora.ambiente.gob.ec/\">"
							+ " <soapenv:Header/>" + " <soapenv:Body>" + " <web:taxonomicResolutionByScientificName>"
							+ "<nameSubmitted>" + sBPartFive + "</nameSubmitted>" + " <matchAccuracy>" + matchAccuracy
							+ "</matchAccuracy>" + " <onlyBestMatch>" + onlyBestMach + "</onlyBestMatch>" + " <kingdom>"
							+ kingdom + "</kingdom>" + " </web:taxonomicResolutionByScientificName>"
							+ " </soapenv:Body>" + "</soapenv:Envelope> ";
					System.out.println("Inicio consumo Soap 4: " + new Date());
					ConsultTaxonomy soapClientFive = new ConsultTaxonomy(dataFive, url, 720000);
					System.out.println("Fin consumo Soap 4: " + new Date());
					String pathFive = soapClientFive.getPath();
					insertProcess(email, key,
							pathOne + ";" + pathTwo + ";" + pathThree + ";" + pathFour + ";" + pathFive, onlyBestMach,
							longInt);
					sendEmail(email, "Servicio Web Resolución Taxonómica",
							disenioEmail(key, "notificacion.key.resolucion.taxonomica"));
					updateProcess(key, email);
				} else {
					
					Integer longInt = (n + 1) / 10;

					String[] partOne = Arrays.copyOfRange(total, 0, longInt);
					String[] partTwo = Arrays.copyOfRange(total, longInt, (longInt + longInt));
					String[] partThree = Arrays.copyOfRange(total, (longInt + longInt), (longInt + longInt + longInt));
					String[] partFour = Arrays.copyOfRange(total, (longInt + longInt + longInt),
							(longInt + longInt + longInt + longInt));
					String[] partFive = Arrays.copyOfRange(total, (longInt + longInt + longInt+ longInt),
							(longInt + longInt + longInt + longInt+ longInt));
					String[] partSix = Arrays.copyOfRange(total, (longInt + longInt + longInt+ longInt+ longInt),
							(longInt + longInt + longInt + longInt+ longInt+ longInt));
					String[] partSeven = Arrays.copyOfRange(total, (longInt + longInt + longInt+ longInt+ longInt+ longInt),
							(longInt + longInt + longInt + longInt+ longInt+ longInt+ longInt));
					String[] partEigth = Arrays.copyOfRange(total, (longInt + longInt + longInt+ longInt+ longInt+ longInt+ longInt),
							(longInt + longInt + longInt + longInt+ longInt+ longInt+ longInt+ longInt));
					String[] partNine = Arrays.copyOfRange(total, (longInt + longInt + longInt+ longInt+ longInt+ longInt+ longInt+ longInt),
							(longInt + longInt + longInt + longInt+ longInt+ longInt+ longInt+ longInt+ longInt));
					String[] partTen = Arrays.copyOfRange(total, (longInt + longInt + longInt + longInt+ longInt+ longInt+ longInt+ longInt+ longInt), n);

					StringBuilder sBPartOne = new StringBuilder();
					for (String part : partOne) {
						if (part != null && !part.equals("")) {
							sBPartOne.append(part);
							sBPartOne.append(";");

						}
					}
					String url = catalogFacade.findByCode("url_sw_res_tax").getBigcDescription();

					String data = "<soapenv:Envelope xmlns:soapenv=\"http://schemas.xmlsoap.org/soap/envelope/\" xmlns:web=\"http://webservices.taxonomicresolution.sib_suia_ora.ambiente.gob.ec/\">"
							+ " <soapenv:Header/>" + " <soapenv:Body>" + " <web:taxonomicResolutionByScientificName>"
							+ "<nameSubmitted>" + sBPartOne + "</nameSubmitted>" + " <matchAccuracy>" + matchAccuracy
							+ "</matchAccuracy>" + " <onlyBestMatch>" + onlyBestMach + "</onlyBestMatch>" + " <kingdom>"
							+ kingdom + "</kingdom>" + " </web:taxonomicResolutionByScientificName>"
							+ " </soapenv:Body>" + "</soapenv:Envelope> ";
					System.out.println("Inicio consumo Soap 1: " + new Date());
					ConsultTaxonomy soapClient = new ConsultTaxonomy(data, url, 720000);
					System.out.println("Fin consumo Soap 1: " + new Date());
					String pathOne = soapClient.getPath();

					StringBuilder sBPartTwo = new StringBuilder();
					for (String part : partTwo) {
						if (part != null && !part.equals("")) {
							sBPartTwo.append(part);
							sBPartTwo.append(";");

						}
					}

					String dataTwo = "<soapenv:Envelope xmlns:soapenv=\"http://schemas.xmlsoap.org/soap/envelope/\" xmlns:web=\"http://webservices.taxonomicresolution.sib_suia_ora.ambiente.gob.ec/\">"
							+ " <soapenv:Header/>" + " <soapenv:Body>" + " <web:taxonomicResolutionByScientificName>"
							+ "<nameSubmitted>" + sBPartTwo + "</nameSubmitted>" + " <matchAccuracy>" + matchAccuracy
							+ "</matchAccuracy>" + " <onlyBestMatch>" + onlyBestMach + "</onlyBestMatch>" + " <kingdom>"
							+ kingdom + "</kingdom>" + " </web:taxonomicResolutionByScientificName>"
							+ " </soapenv:Body>" + "</soapenv:Envelope> ";
					System.out.println("Inicio consumo Soap 2: " + new Date());
					ConsultTaxonomy soapClientTwo = new ConsultTaxonomy(dataTwo, url, 720000);
					System.out.println("Fin consumo Soap 2: " + new Date());
					String pathTwo = soapClientTwo.getPath();

					StringBuilder sBPartThree = new StringBuilder();
					for (String part : partThree) {
						if (part != null && !part.equals("")) {
							sBPartThree.append(part);
							sBPartThree.append(";");

						}
					}

					String dataThree = "<soapenv:Envelope xmlns:soapenv=\"http://schemas.xmlsoap.org/soap/envelope/\" xmlns:web=\"http://webservices.taxonomicresolution.sib_suia_ora.ambiente.gob.ec/\">"
							+ " <soapenv:Header/>" + " <soapenv:Body>" + " <web:taxonomicResolutionByScientificName>"
							+ "<nameSubmitted>" + sBPartThree + "</nameSubmitted>" + " <matchAccuracy>" + matchAccuracy
							+ "</matchAccuracy>" + " <onlyBestMatch>" + onlyBestMach + "</onlyBestMatch>" + " <kingdom>"
							+ kingdom + "</kingdom>" + " </web:taxonomicResolutionByScientificName>"
							+ " </soapenv:Body>" + "</soapenv:Envelope> ";
					System.out.println("Inicio consumo Soap 3: " + new Date());
					ConsultTaxonomy soapClientThree = new ConsultTaxonomy(dataThree, url, 720000);
					System.out.println("Fin consumo Soap 3: " + new Date());
					String pathThree = soapClientThree.getPath();

					StringBuilder sBPartFour = new StringBuilder();
					for (String part : partFour) {
						if (part != null && !part.equals("")) {
							sBPartFour.append(part);
							sBPartFour.append(";");

						}
					}

					String dataFour = "<soapenv:Envelope xmlns:soapenv=\"http://schemas.xmlsoap.org/soap/envelope/\" xmlns:web=\"http://webservices.taxonomicresolution.sib_suia_ora.ambiente.gob.ec/\">"
							+ " <soapenv:Header/>" + " <soapenv:Body>" + " <web:taxonomicResolutionByScientificName>"
							+ "<nameSubmitted>" + sBPartFour + "</nameSubmitted>" + " <matchAccuracy>" + matchAccuracy
							+ "</matchAccuracy>" + " <onlyBestMatch>" + onlyBestMach + "</onlyBestMatch>" + " <kingdom>"
							+ kingdom + "</kingdom>" + " </web:taxonomicResolutionByScientificName>"
							+ " </soapenv:Body>" + "</soapenv:Envelope> ";
					System.out.println("Inicio consumo Soap 4: " + new Date());
					ConsultTaxonomy soapClientFour = new ConsultTaxonomy(dataFour, url, 720000);
					System.out.println("Fin consumo Soap 4: " + new Date());
					String pathFour = soapClientFour.getPath();

					StringBuilder sBPartFive = new StringBuilder();
					for (String part : partFive) {
						if (part != null && !part.equals("")) {
							sBPartFive.append(part);
							sBPartFive.append(";");

						}
					}

					String dataFive = "<soapenv:Envelope xmlns:soapenv=\"http://schemas.xmlsoap.org/soap/envelope/\" xmlns:web=\"http://webservices.taxonomicresolution.sib_suia_ora.ambiente.gob.ec/\">"
							+ " <soapenv:Header/>" + " <soapenv:Body>" + " <web:taxonomicResolutionByScientificName>"
							+ "<nameSubmitted>" + sBPartFive + "</nameSubmitted>" + " <matchAccuracy>" + matchAccuracy
							+ "</matchAccuracy>" + " <onlyBestMatch>" + onlyBestMach + "</onlyBestMatch>" + " <kingdom>"
							+ kingdom + "</kingdom>" + " </web:taxonomicResolutionByScientificName>"
							+ " </soapenv:Body>" + "</soapenv:Envelope> ";
					System.out.println("Inicio consumo Soap 5: " + new Date());
					ConsultTaxonomy soapClientFive = new ConsultTaxonomy(dataFive, url, 720000);
					System.out.println("Fin consumo Soap 5: " + new Date());
					String pathFive = soapClientFive.getPath();
					
					StringBuilder sBPartSix = new StringBuilder();
					for (String part : partSix) {
						if (part != null && !part.equals("")) {
							sBPartSix.append(part);
							sBPartSix.append(";");

						}
					}
					
					String dataSix = "<soapenv:Envelope xmlns:soapenv=\"http://schemas.xmlsoap.org/soap/envelope/\" xmlns:web=\"http://webservices.taxonomicresolution.sib_suia_ora.ambiente.gob.ec/\">"
							+ " <soapenv:Header/>" + " <soapenv:Body>" + " <web:taxonomicResolutionByScientificName>"
							+ "<nameSubmitted>" + sBPartSix + "</nameSubmitted>" + " <matchAccuracy>" + matchAccuracy
							+ "</matchAccuracy>" + " <onlyBestMatch>" + onlyBestMach + "</onlyBestMatch>" + " <kingdom>"
							+ kingdom + "</kingdom>" + " </web:taxonomicResolutionByScientificName>"
							+ " </soapenv:Body>" + "</soapenv:Envelope> ";
					System.out.println("Inicio consumo Soap 6: " + new Date());
					ConsultTaxonomy soapClientsix = new ConsultTaxonomy(dataSix, url, 720000);
					System.out.println("Fin consumo Soap 6: " + new Date());
					String pathSix = soapClientsix.getPath();
					
					StringBuilder sBPartSeven = new StringBuilder();
					for (String part : partSeven) {
						if (part != null && !part.equals("")) {
							sBPartSeven.append(part);
							sBPartSeven.append(";");

						}
					}
					
					String dataSeven = "<soapenv:Envelope xmlns:soapenv=\"http://schemas.xmlsoap.org/soap/envelope/\" xmlns:web=\"http://webservices.taxonomicresolution.sib_suia_ora.ambiente.gob.ec/\">"
							+ " <soapenv:Header/>" + " <soapenv:Body>" + " <web:taxonomicResolutionByScientificName>"
							+ "<nameSubmitted>" + sBPartSeven + "</nameSubmitted>" + " <matchAccuracy>" + matchAccuracy
							+ "</matchAccuracy>" + " <onlyBestMatch>" + onlyBestMach + "</onlyBestMatch>" + " <kingdom>"
							+ kingdom + "</kingdom>" + " </web:taxonomicResolutionByScientificName>"
							+ " </soapenv:Body>" + "</soapenv:Envelope> ";
					System.out.println("Inicio consumo Soap 7: " + new Date());
					ConsultTaxonomy soapClientSeven = new ConsultTaxonomy(dataSeven, url, 720000);
					System.out.println("Fin consumo Soap 7: " + new Date());
					String pathSeven = soapClientSeven.getPath();
					
					StringBuilder sBPartEigth = new StringBuilder();
					for (String part : partEigth) {
						if (part != null && !part.equals("")) {
							sBPartEigth.append(part);
							sBPartEigth.append(";");

						}
					}
					
					String dataEigth = "<soapenv:Envelope xmlns:soapenv=\"http://schemas.xmlsoap.org/soap/envelope/\" xmlns:web=\"http://webservices.taxonomicresolution.sib_suia_ora.ambiente.gob.ec/\">"
							+ " <soapenv:Header/>" + " <soapenv:Body>" + " <web:taxonomicResolutionByScientificName>"
							+ "<nameSubmitted>" + sBPartEigth + "</nameSubmitted>" + " <matchAccuracy>" + matchAccuracy
							+ "</matchAccuracy>" + " <onlyBestMatch>" + onlyBestMach + "</onlyBestMatch>" + " <kingdom>"
							+ kingdom + "</kingdom>" + " </web:taxonomicResolutionByScientificName>"
							+ " </soapenv:Body>" + "</soapenv:Envelope> ";
					System.out.println("Inicio consumo Soap 8: " + new Date());
					ConsultTaxonomy soapClientEigth = new ConsultTaxonomy(dataEigth, url, 720000);
					System.out.println("Fin consumo Soap 8: " + new Date());
					String pathEigth = soapClientEigth.getPath();
					
					StringBuilder sBPartNine = new StringBuilder();
					for (String part : partNine) {
						if (part != null && !part.equals("")) {
							sBPartNine.append(part);
							sBPartNine.append(";");

						}
					}
					
					String dataNine = "<soapenv:Envelope xmlns:soapenv=\"http://schemas.xmlsoap.org/soap/envelope/\" xmlns:web=\"http://webservices.taxonomicresolution.sib_suia_ora.ambiente.gob.ec/\">"
							+ " <soapenv:Header/>" + " <soapenv:Body>" + " <web:taxonomicResolutionByScientificName>"
							+ "<nameSubmitted>" + sBPartNine + "</nameSubmitted>" + " <matchAccuracy>" + matchAccuracy
							+ "</matchAccuracy>" + " <onlyBestMatch>" + onlyBestMach + "</onlyBestMatch>" + " <kingdom>"
							+ kingdom + "</kingdom>" + " </web:taxonomicResolutionByScientificName>"
							+ " </soapenv:Body>" + "</soapenv:Envelope> ";
					System.out.println("Inicio consumo Soap 9: " + new Date());
					ConsultTaxonomy soapClientNine = new ConsultTaxonomy(dataNine, url, 720000);
					System.out.println("Fin consumo Soap 9: " + new Date());
					String pathNine = soapClientNine.getPath();
					
					StringBuilder sBPartTen = new StringBuilder();
					for (String part : partTen) {
						if (part != null && !part.equals("")) {
							sBPartTen.append(part);
							sBPartTen.append(";");

						}
					}
					
					String dataTen = "<soapenv:Envelope xmlns:soapenv=\"http://schemas.xmlsoap.org/soap/envelope/\" xmlns:web=\"http://webservices.taxonomicresolution.sib_suia_ora.ambiente.gob.ec/\">"
							+ " <soapenv:Header/>" + " <soapenv:Body>" + " <web:taxonomicResolutionByScientificName>"
							+ "<nameSubmitted>" + sBPartTen + "</nameSubmitted>" + " <matchAccuracy>" + matchAccuracy
							+ "</matchAccuracy>" + " <onlyBestMatch>" + onlyBestMach + "</onlyBestMatch>" + " <kingdom>"
							+ kingdom + "</kingdom>" + " </web:taxonomicResolutionByScientificName>"
							+ " </soapenv:Body>" + "</soapenv:Envelope> ";
					System.out.println("Inicio consumo Soap 10: " + new Date());
					ConsultTaxonomy soapClientTen = new ConsultTaxonomy(dataTen, url, 720000);
					System.out.println("Fin consumo Soap 10: " + new Date());
					String pathTen = soapClientTen.getPath();
					
					
					insertProcess(email, key,
							pathOne + ";" + pathTwo + ";" + pathThree + ";" + pathFour + ";" + pathFive+";"+pathSix+";"+pathSeven+";"+pathEigth+";"+pathNine+";"+pathTen, onlyBestMach,
							longInt);
					sendEmail(email, "Servicio Web Resolución Taxonómica",
							disenioEmail(key, "notificacion.key.resolucion.taxonomica"));
					updateProcess(key, email);
				}

			} else {
				String url = catalogFacade.findByCode("url_sw_res_tax").getBigcDescription();

				String data = "<soapenv:Envelope xmlns:soapenv=\"http://schemas.xmlsoap.org/soap/envelope/\" xmlns:web=\"http://webservices.taxonomicresolution.sib_suia_ora.ambiente.gob.ec/\">"
						+ " <soapenv:Header/>" + " <soapenv:Body>" + " <web:taxonomicResolutionByScientificName>"
						+ "<nameSubmitted>" + nameSubmitted + "</nameSubmitted>" + " <matchAccuracy>" + matchAccuracy
						+ "</matchAccuracy>" + " <onlyBestMatch>" + onlyBestMach + "</onlyBestMatch>" + " <kingdom>"
						+ kingdom + "</kingdom>" + " </web:taxonomicResolutionByScientificName>" + " </soapenv:Body>"
						+ "</soapenv:Envelope> ";
				System.out.println("Inicio consumo SW: " + new Date());

				ConsultTaxonomy soapClient = new ConsultTaxonomy(data, url, 720000);

				System.out.println("Inicio fin SW: " + new Date());
				insertProcess(email, key, soapClient.getPath(), onlyBestMach, 0);
				sendEmail(email, "Servicio Web Resolución Taxonómica",
						disenioEmail(key, "notificacion.key.resolucion.taxonomica"));
				updateProcess(key, email);

			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * Metodo para insertar los datos del proceso
	 * 
	 * @param email
	 * @param key
	 */
	public void insertProcess(String email, String key, String path, String onlyBest, Integer size) {
		try {
			Query q;
			EntityManager en = super.getEntityManager();
			q = en.createNativeQuery(
					"INSERT INTO biodiversity.taxonomic_resolution_processes (trpr_key,trpr_email,trpr_status,trpr_date_create,trpr_status_process,trpr_object,tpr_only_best_mach,trpr_use_key,tpr_group_size) values (?1,?2,?3,?4,?5,?6,?7,?8,?9)");
			// "SELECT
			// spta_id,spta_scientific_gui,spta_taxon_rank_name,spta_scientific_name,spta_higher_classification,
			// tara_id from biodiversity.species_taxa where spta_hierarchy_code like ?1 ");
			q.setParameter(1, key);
			q.setParameter(2, email);
			q.setParameter(3, true);
			q.setParameter(4, new Date());
			q.setParameter(5, false);
			q.setParameter(6, path);
			q.setParameter(7, onlyBest.charAt(0));
			q.setParameter(8, false);
			q.setParameter(9, size);
			q.executeUpdate();
		} catch (Exception e) {

		}

	}

	/**
	 * Metodo para obtener el proceso por key
	 * 
	 * @param key
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public Object[] getprocessByKeyAndEmail(String key, String email) {
		Object[] res = null;
		try {

			List<Object[]> result;
			Query q;
			EntityManager en = super.getEntityManager();
			q = en.createNativeQuery(
					"select trpr_object,tpr_only_best_mach,tpr_group_size from biodiversity.taxonomic_resolution_processes where trpr_key = ?1 and trpr_email = ?2 and trpr_status=true and trpr_status_process=true and trpr_use_key=false");
			// "SELECT
			// spta_id,spta_scientific_gui,spta_taxon_rank_name,spta_scientific_name,spta_higher_classification,
			// tara_id from biodiversity.species_taxa where spta_hierarchy_code like ?1 ");
			q.setParameter(1, key);
			q.setParameter(2, email);
			result = q.getResultList();
			if (result != null && !result.isEmpty()) {
				res = (result.get(0));
			}
			return res;
		}

		catch (Exception e) {
			return res;
		}
	}

	/**
	 * Metodo para insertar el resultado
	 * 
	 * @param email
	 * @param key
	 */
	public void insertResult(Integer idProcess, String result) {
		try {
			Query q;
			EntityManager en = super.getEntityManager();
			q = en.createNativeQuery(
					"INSERT INTO biodiversity.taxonomic_resolution_results (trpr_id,trre_object,trre_status,trre_date_create) values (?1,?2,?3,?4)");
			q.setParameter(1, idProcess);
			q.setParameter(2, result);
			q.setParameter(3, true);
			q.setParameter(4, new Date());
			q.executeUpdate();
		} catch (Exception e) {

		}
	}

	/**
	 * Metodo para generar la key de la llamada al sw de resolucion taxonomica
	 * 
	 * @return
	 */
	public String generateKey() {
		StringBuilder key = new StringBuilder();
		String temp = "ABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890";
		Integer lengthK = 8;
		while (lengthK-- != 0) {
			int charact = (int) (Math.random() * temp.length());
			key.append(temp.charAt(charact));
		}
		return key.toString();
	}

	/**
	 * Metodo para actualizar el estado del proceso a true cuando haya finalizado
	 * 
	 * @param key
	 * @param email
	 */
	public void updateProcess(String key, String email) {

		try {

			Query q;
			EntityManager en = super.getEntityManager();
			q = en.createNativeQuery(
					"update biodiversity.taxonomic_resolution_processes set trpr_status_process=true where trpr_key = ?1 and trpr_email = ?2 and trpr_status=true");
			q.setParameter(1, key);
			q.setParameter(2, email);
			q.executeUpdate();
		}

		catch (Exception e) {

		}
	}

	@SuppressWarnings("deprecation")
	public static boolean sendEmail(List<String> emailsDestino, List<String> emailsDestinoCopia, String asunto,
			String mensaje) {
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
			for (String email : emailsDestino) {
				message.addRecipient(Message.RecipientType.TO, new InternetAddress(email));
			}
			if (emailsDestinoCopia != null) {
				for (String email : emailsDestinoCopia) {
					message.addRecipient(Message.RecipientType.CC, new InternetAddress(email));
				}
			}

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

	public String disenioEmail(String key, String code) {

		try {
			String header = biodiversityCatalogParameterFacade.findByCode("html_template.email.header.default")
					.getBccaValue();
			String footer = biodiversityCatalogParameterFacade.findByCode("html_template.email.footer.default")
					.getBccaValue();
			String content = biodiversityCatalogParameterFacade.findByCode("html_template.email.content.default")
					.getBccaValue();
			String mensaje = biodiversityCatalogParameterFacade.findByCode(code).getBccaValue();
			mensaje = mensaje.replace("key", key == null ? "" : key);

			String disenio = header.replace("usuario", "") + content.replace("mensaje", mensaje) + footer;
			return disenio;

		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * Enviar Correo a Un solo destino
	 * 
	 * @param emailDestino
	 *            String
	 * @param asunto
	 *            String
	 * @param mensaje
	 *            String
	 * @return boolean
	 */
	public static boolean sendEmail(String emailDestino, String asunto, String mensaje) {
		return sendEmail(emailDestino, null, asunto, mensaje);
	}

	/**
	 * Enviar Correo a Varios Destinos Separados por caracter
	 * 
	 * @param emailsDestino
	 *            String
	 * @param emailsDestinoCopia
	 *            String
	 * @param asunto
	 *            String
	 * @param mensaje
	 *            String
	 * @return boolean
	 */
	public static boolean sendEmail(String emailsDestino, String emailsDestinoCopia, String asunto, String mensaje) {
		List<String> emailsDestinoList = Arrays.asList(emailsDestino.split(SPLIT_SEPARATE));
		List<String> emailsDestinoCopiaList = null;
		if (emailsDestinoCopia != null && emailsDestinoCopia.length() > 0)
			emailsDestinoCopiaList = Arrays.asList(emailsDestinoCopia.split(SPLIT_SEPARATE));
		return sendEmail(emailsDestinoList, emailsDestinoCopiaList, asunto, mensaje);
	}

	/**
	 * Metodo para actualizar si la clave ya se uso
	 * 
	 * @param key
	 * @param email
	 */
	public void updateUseKey(String key, String email) {

		try {

			Query q;
			EntityManager en = super.getEntityManager();
			q = en.createNativeQuery(
					"update biodiversity.taxonomic_resolution_processes set trpr_use_key=true where trpr_key = ?1 and trpr_email = ?2 and trpr_status=true");
			q.setParameter(1, key);
			q.setParameter(2, email);
			q.executeUpdate();
		}

		catch (Exception e) {

		}
	}

}

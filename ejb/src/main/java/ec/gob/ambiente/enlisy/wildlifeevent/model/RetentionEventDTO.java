package ec.gob.ambiente.enlisy.wildlifeevent.model;

import java.io.Serializable;
import lombok.Getter;
import lombok.Setter;

public class RetentionEventDTO implements Serializable {

   private static final long serialVersionUID = -4122749847733010662L;

   @Getter
   @Setter
   private String html;
   @Getter
   @Setter
   private String numeroRegistro;
   @Getter
   @Setter
   private String fechaRegistro;
   @Getter
   @Setter
   private String usuarioRegistro;
   @Getter
   @Setter
   private String identificacionUsuario;
   
   @Getter
   @Setter
   private String fechaEvento;
   @Getter
   @Setter
   private String fuenteInformacion;
   
   @Getter
   @Setter
   private String nombreProvincia;
   @Getter
   @Setter
   private String nombreCanton;
   @Getter
   @Setter
   private String nombreParroquia;
   @Getter
   @Setter
   private String gedaLocation;
   @Getter
   @Setter
   private String gedaX;
   @Getter
   @Setter
   private String gedaY;
   @Getter
   @Setter
   private String gedaZone;
   @Getter
   @Setter
   private String gedaAltitude;
   @Getter
   @Setter
   private String gedaObservations;
   @Getter
   @Setter
   private String gedaInformerIdentification;
   @Getter
   @Setter
   private String gedaInformerName;
   @Getter
   @Setter
   private String gedaInformerPhone;
   @Getter
   @Setter
   private String gedaInformerEmail;
   @Getter
   @Setter
   private String pewiSupportCentralPlant;
   @Getter
   @Setter
   private String pewiJustification;
   @Getter
   @Setter
   private String pewiAcceptance;
   @Getter
   @Setter
   private String reteOffenderIdentification;
   @Getter
   @Setter
   private String reteOffenderName;
   @Getter
   @Setter
   private String natiDescription;
   @Getter
   @Setter
   private String reteOffenderAddress;
   @Getter
   @Setter
   private String reteOffenderPlate;
   @Getter
   @Setter
   private String reteNroEnrollment;
   @Getter
   @Setter
   private String reteNroLicense;
   @Getter
   @Setter
   private String reteOffenderTransportCoop;
   @Getter
   @Setter
   private String lstDestinoFinalInf;
   @Getter
   @Setter
   private String reteOffenderDestination;
   @Getter
   @Setter
   private String lstRutaMovilizacion;
   @Getter
   @Setter
   private String reteCustodianIdentification;
   @Getter
   @Setter
   private String reteCustodianName;
   @Getter
   @Setter
   private String lstDestinoFinalCust;
   @Getter
   @Setter
   private String reteCustodiaDestination;
   @Getter
   @Setter
   private String reteRecomenDireProvi;
   @Getter
   @Setter
   private String tablaEspecie;
   
   
   public RetentionEventDTO() {
	   super();
   }
   public RetentionEventDTO(String numeroRegistro, String fechaRegistro,String usuarioRegistro,String identificacionUsuario,String fechaEvento,String fuenteInformacion,String nombreProvincia,String nombreCanton,String nombreParroquia,String gedaLocation,String gedaX,String gedaY,String gedaZone,String gedaAltitude,String gedaObservations,String gedaInformerIdentification,String gedaInformerName,String gedaInformerPhone,String gedaInformerEmail,String pewiSupportCentralPlant,String pewiJustification,String pewiAcceptance,
		   String reteOffenderIdentification,String reteOffenderName,String natiDescription,String reteOffenderAddress,String reteOffenderPlate,String reteNroEnrollment,String reteNroLicense,String reteOffenderTransportCoop,String lstDestinoFinalInf,String reteOffenderDestination,String lstRutaMovilizacion,
		   String reteCustodianIdentification,String reteCustodianName,String lstDestinoFinalCust,String reteCustodiaDestination,String reteRecomenDireProvi,String tablaEspecie) {
	   this.numeroRegistro = numeroRegistro;
       this.fechaRegistro = fechaRegistro;
       this.usuarioRegistro=usuarioRegistro;
       this.identificacionUsuario=identificacionUsuario;
       this.fechaEvento=fechaEvento;
       this.fuenteInformacion=fuenteInformacion;
       this.nombreProvincia=nombreProvincia;
       this.nombreCanton=nombreCanton;
       this.nombreParroquia=nombreParroquia;
       this.gedaLocation=gedaLocation;
       this.gedaX=gedaX;
       this.gedaY=gedaY;
       this.gedaZone=gedaZone;
       this.gedaAltitude=gedaAltitude;
       this.gedaObservations=gedaObservations;
       this.gedaInformerIdentification=gedaInformerIdentification;
       this.gedaInformerName=gedaInformerName;
       this.gedaInformerPhone=gedaInformerPhone;
       this.gedaInformerEmail=gedaInformerEmail;
       this.pewiSupportCentralPlant=pewiSupportCentralPlant;
       this.pewiJustification=pewiJustification;
       this.pewiAcceptance=pewiAcceptance;
       this.reteOffenderIdentification=reteOffenderIdentification;
       this.reteOffenderName=reteOffenderName;
       this.natiDescription=natiDescription;
       this.reteOffenderAddress=reteOffenderAddress;
       this.reteOffenderPlate=reteOffenderPlate;
       this.reteNroEnrollment=reteNroEnrollment;
       this.reteNroLicense=reteNroLicense;
       this.reteOffenderTransportCoop=reteOffenderTransportCoop;
       this.lstDestinoFinalInf=lstDestinoFinalInf;
       this.reteOffenderDestination=reteOffenderDestination;
       this.lstRutaMovilizacion=lstRutaMovilizacion;
       this.reteCustodianIdentification=reteCustodianIdentification;
       this.reteCustodianName=reteCustodianName;
       this.lstDestinoFinalCust=lstDestinoFinalCust;
       this.reteCustodiaDestination=reteCustodiaDestination;
       this.reteRecomenDireProvi=reteRecomenDireProvi;
       this.tablaEspecie=tablaEspecie;
   }
}

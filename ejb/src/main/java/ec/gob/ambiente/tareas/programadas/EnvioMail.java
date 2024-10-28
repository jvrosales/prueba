/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.gob.ambiente.tareas.programadas;

import java.util.Date;

import javax.annotation.Resource;
import javax.ejb.ActivationConfigProperty;
import javax.ejb.MessageDriven;
import javax.jms.JMSException;
import javax.jms.MessageListener;
import javax.jms.ObjectMessage;
import javax.mail.Message;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import ec.gob.ambiente.vo.NotificacionesMails;

/**
 *
 * @author christian
 */
@MessageDriven(activationConfig = {
    @ActivationConfigProperty(propertyName = "destinationType", propertyValue = "javax.jms.Queue"),
    @ActivationConfigProperty(propertyName = "destination", propertyValue = "envioMail")})
public class EnvioMail implements MessageListener {

   private static final org.apache.log4j.Logger LOG = org.apache.log4j.Logger
            .getLogger(EnvioMail.class);

    @Resource(lookup = "java:jboss/mail/magma", type = javax.mail.Session.class)
    private Session javaMailSession;

    private void cargarMensaje(Message msg, final NotificacionesMails not) {
        try {
        	
            msg.setSubject(not.getAsunto());
            String desde = javaMailSession.getProperty("mail.smtp.user");
            msg.setFrom(new InternetAddress(desde, "Ministerio del Ambiente"));
            msg.setRecipient(Message.RecipientType.BCC, new InternetAddress(not.getEmail()));
            msg.setContent(not.getContenido(), not.getTipoMensaje());
            Transport.send(msg);
        } catch (Exception e) {
            not.setObservacion(e.getMessage());
            not.setFechaObservacion(new Date());
            LOG.error(e, e);
        }       
    }

    @Override
    public void onMessage(javax.jms.Message msg) {
        try {
            ObjectMessage ob = (ObjectMessage) msg;
            Message msg1 = new MimeMessage(javaMailSession);
            NotificacionesMails not = (NotificacionesMails) ob.getObject();
            cargarMensaje(msg1, not);
        } catch (RuntimeException e) {
            LOG.error(e, e);
        } catch (JMSException e) {
            LOG.error(e, e);
        }
    }
}

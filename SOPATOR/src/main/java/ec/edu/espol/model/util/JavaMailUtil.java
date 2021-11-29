/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.espol.model.util;
//
//import java.io.FileInputStream;
//import java.io.IOException;
//import java.io.InputStream;
//import java.util.Date;
//import java.util.Properties;
//import javax.mail.Message;
//import javax.mail.MessagingException;
//import javax.mail.NoSuchProviderException;
//import javax.mail.Session;
//import javax.mail.Transport;
//import javax.mail.internet.InternetAddress;
//import javax.mail.internet.MimeMessage;

/**
 *
 * @author User
 */
public class JavaMailUtil {
//    private final String path = "jm.properties";
//    private String host;
//    private String correoRemitente;
//    private String contrasenaRemitente;
//    private final String destinatario;
//    private final String asunto;
//    private final String mensaje;
//    public JavaMailUtil(String destino, String subject, String msg) {
//        try (InputStream inputStream = new FileInputStream(path)) {
//            Properties prop = new Properties();
//            prop.load(inputStream);
//            host = prop.getProperty("host");
//            correoRemitente = prop.getProperty("email");
//            contrasenaRemitente = prop.getProperty("pass");
//        } catch (IOException e) {
//            System.out.println(e.toString()); System.out.println(e.getMessage());
//            System.out.println("No se pudo crear el correo para enviar");
//        }
//        destinatario = destino;
//        asunto = subject;
//        mensaje = msg;        
//    }
//    public JavaMailUtil() {
//        try (InputStream inputStream = new FileInputStream(path)) {
//            Properties prop = new Properties();
//            prop.load(inputStream);
//            host = prop.getProperty("host");
//            correoRemitente = prop.getProperty("email");
//            contrasenaRemitente = prop.getProperty("pass");
//        } catch (IOException e) {
//            System.out.println(e.toString()); System.out.println(e.getMessage());
//            System.out.println("No se pudo crear el correo para enviar");
//        }
//        destinatario = "dsoria@espol.edu.ec";
//        asunto = "JavaMail";
//        mensaje = "Funcionó";        
//    }
//    private Properties getProperties() {
//        Properties props= System.getProperties();
//        props.put("mail.smtp.starttls.enable", "true");
//        props.put("mail.smtp.host", host);
//        props.put("mail.smtp.port", "587");
//        props.put("mail.smtp.auth", "true");
//        props.put("mail.smtp.starttls.required", "true");
//        props.put("mail.smtp.ssl.trust", "smtp.gmail.com");    
//        return props;
//    }
//    private void makeTransport(Session mailSession, Message msg) throws NoSuchProviderException, MessagingException {
//        Transport transport=mailSession.getTransport("smtp");
//        transport.connect(host, correoRemitente, contrasenaRemitente);
//        transport.sendMessage(msg, msg.getAllRecipients());
//        transport.close();
//    }
//    private void makeMessage(Message msg, InternetAddress[] address) throws MessagingException {
//        msg.setRecipients(Message.RecipientType.TO, address);
//        msg.setSubject(asunto); msg.setSentDate(new Date());
//        msg.setText(mensaje);
//    }
//    public boolean sendMail() {
//        try {
//            boolean sessionDebug = false;
//            //java.security.Security.addProvider(new com.sun.net.ssl.internal.ssl.Provider());
//            Session mailSession = Session.getDefaultInstance(getProperties(), null);
//            mailSession.setDebug(sessionDebug);
//            Message msg = new MimeMessage(mailSession);
//            msg.setFrom(new InternetAddress(correoRemitente));
//            InternetAddress[] address = {new InternetAddress(destinatario)};
//            makeMessage(msg, address);
//            makeTransport(mailSession, msg);
//            return true;
//        } catch (Exception ex) {
//            return false;
//        }
//    }
}

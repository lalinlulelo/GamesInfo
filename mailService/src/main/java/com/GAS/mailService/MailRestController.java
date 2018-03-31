package com.GAS.mailService;

import java.io.DataOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.Socket;
import java.security.Security;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Observable;
import java.util.Observer;
import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.sun.mail.smtp.SMTPTransport;


//controlador de api rest
@RestController
public class MailRestController {
	// adquiere como parametros el usuario, el correo y el servidor de correo
	@PostMapping(value="/mail")
	@ResponseStatus(HttpStatus.CREATED)
	public Mail getMail(@RequestBody Mail mail) {
		// notificamos por consola el email recibido
		System.out.println("Message received from web : " + mail);
		try {
			Security.addProvider(new com.sun.net.ssl.internal.ssl.Provider());
			final String SSL_FACTORY = "javax.net.ssl.SSLSocketFactory";

			// Get a Properties object
			Properties props = System.getProperties();
			props.setProperty("mail.smtps.host", "smtp.gmail.com");
			//props.setProperty("mail.smtp.socketFactory.class", SSL_FACTORY);
			props.setProperty("mail.smtp.socketFactory.fallback", "false");
			props.setProperty("mail.smtp.port", "465");
			props.setProperty("mail.smtp.socketFactory.port", "465");
			props.setProperty("mail.smtps.auth", "true");
			//props.put("mail.smtp.starttls.enable", "true");
			props.put("mail.smtps.quitwait", "false");

			Session session = Session.getInstance(props, null);
			
			// String que portará el mensaje a enviar
			final MimeMessage msg = new MimeMessage(session);

			// -- Set the FROM and TO fields --
			// emisor
			msg.setFrom(new InternetAddress("infogamesurjc@gmail.com"));
			// receptor
			msg.setRecipients(Message.RecipientType.TO, InternetAddress.parse(mail.getUserMail(), false));
			// mensaje del correo
			msg.setSubject("Welcome to GamesInfo!");
			msg.setText(
					"Hi " + mail.getUserName()
							+ "\n\n Thank you for colaborate on our web page. We hope you'll enjoy it as much as we enjoyed developing it." 
							+ "\n\n Please, you need to confirm by clicking on the link below."
							+ "\n\n Our best regards, Team GamesInfo"
					,"utf-8");
			// fecha de envío
			msg.setSentDate(new Date());
			// se inicia la conexión
			SMTPTransport t = (SMTPTransport) session.getTransport("smtps");
			// se inicia sesión en el correo
			t.connect("smtp.gmail.com", "infogamesurjc@gmail.com", "movimientoNaranja");
			// se añade el mensaje a enviar
			t.sendMessage(msg, msg.getAllRecipients());
			// se cierra conexión
			t.close();
			System.out.println("correo enviado con exito");
		} catch (MessagingException ex) {
			System.out.println(ex);
		}
		// se notifica el correcto envío
		return mail;
	}
}

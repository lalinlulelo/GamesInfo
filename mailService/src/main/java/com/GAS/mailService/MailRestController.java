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
import org.springframework.web.bind.annotation.RestController;

import com.sun.mail.smtp.SMTPTransport;


@RestController
public class MailRestController {
	
	@GetMapping(value = "/user/{user}/mail/{nameM}/{server}/{ext}")
	public ResponseEntity<String> getMail(@PathVariable String user, @PathVariable String nameM, @PathVariable String server, @PathVariable String ext) {

		System.out.println("Email received ="+ user + ": " + nameM + "@" + server + "." + ext);
		try {

			Security.addProvider(new com.sun.net.ssl.internal.ssl.Provider());
			final String SSL_FACTORY = "javax.net.ssl.SSLSocketFactory";
			System.out.println("paso 1");

			// Get a Properties object
			Properties props = System.getProperties();
			props.setProperty("mail.smtps.host", "smtp.gmail.com");
			props.setProperty("mail.smtp.socketFactory.class", SSL_FACTORY);
			props.setProperty("mail.smtp.socketFactory.fallback", "false");
			props.setProperty("mail.smtp.port", "465");
			props.setProperty("mail.smtp.socketFactory.port", "465");
			props.setProperty("mail.smtps.auth", "true");
			
			System.out.println("paso 2");

			props.put("mail.smtps.quitwait", "false");

			Session session = Session.getInstance(props, null);

			final MimeMessage msg = new MimeMessage(session);
			System.out.println("paso 4");

			// -- Set the FROM and TO fields --
			msg.setFrom(new InternetAddress("infogamesurjc@gmail.com"));
			msg.setRecipients(Message.RecipientType.TO, InternetAddress.parse(nameM + "@" + server + "." + ext, false));

			msg.setSubject("Welcome to GamesInfo");
			msg.setText(
					"Hi " + user
							+ "\\n\\nThankyou for colaborate on our web page. We hope you'll enjoy it as much as we enjoyed developing it. Please, we need you to confirm your account, "
							+ "by clicking on the link below\\n\\nOur best regards, Team GamesInfo",
					"utf-8");
			msg.setSentDate(new Date());
			System.out.println("paso 5");

			SMTPTransport t = (SMTPTransport) session.getTransport("smtps");

			t.connect("smtp.gmail.com", "infogamesurjc@gmail.com", "movimientoNaranja");
			System.out.println("paso 6");
			t.sendMessage(msg, msg.getAllRecipients());
			System.out.println("paso 7");
			t.close();

		} catch (MessagingException ex) {
			System.out.println(ex);
		}
		
		System.out.println("paso 8");
		return new ResponseEntity<>("Mail send to "+ user + ": " + nameM + "@" + server + "." + ext, HttpStatus.OK);

	}
}

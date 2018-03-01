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

import com.sun.mail.smtp.SMTPTransport;

public class client extends Thread implements Observer{
	private Socket socket;

	private ObjectInputStream entradaDatos;
	private DataOutputStream salidaDatos;

	public client(Socket socket) {
		this.socket = socket;
		try {
			entradaDatos = new ObjectInputStream(socket.getInputStream());
			salidaDatos = new DataOutputStream(socket.getOutputStream());
		} catch (IOException ex) {
			System.out.println("Fallo creada stream");
			ex.printStackTrace();
		}
	}

	@SuppressWarnings("restriction")
	public void run() {

		System.out.println("Cliente Conectado");
		List<String> mensajeRecibido = new ArrayList<>();
		boolean conectado = true;

		System.out.println("Holaaa");

		while (conectado) {
			try {
				mensajeRecibido = (List<String>) entradaDatos.readObject();

				if (mensajeRecibido != null) {

					salidaDatos.writeUTF("Datos recibidos con exito");
					System.out.println(mensajeRecibido.get(0));
					System.out.println(mensajeRecibido.get(1));

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
						System.out.println("paso 3");
						Session session = Session.getInstance(props, null);

						final MimeMessage msg = new MimeMessage(session);
						System.out.println("paso 4");
						// -- Set the FROM and TO fields --
						msg.setFrom(new InternetAddress("infogamesurjc@gmail.com"));
						msg.setRecipients(Message.RecipientType.TO,
								InternetAddress.parse(mensajeRecibido.get(1), false));

						msg.setSubject("Welcome to GamesInfo");
						msg.setText(
								"Hi " + mensajeRecibido.get(0)
										+ "\n\nThankyou for colaborate on our web page. We hope you'll enjoy it as much as we enjoyed developing it. Please, we need you to confirm your account, by clicking on the link below\n\nOur best regards, Team GamesInfo",
								"utf-8");
						msg.setSentDate(new Date());
						System.out.println("paso 5");
						SMTPTransport t = (SMTPTransport) session.getTransport("smtps");
						System.out.println("paso 6");
						t.connect("smtp.gmail.com", "infogamesurjc@gmail.com", "movimientoNaranja");
						System.out.println("paso 7");
						t.sendMessage(msg, msg.getAllRecipients());
						System.out.println("paso 8");
						t.close();
						conectado = false;
						try {
							entradaDatos.close();
							salidaDatos.close();
						} catch (IOException ex2) {
						}

					} catch (MessagingException ex) {
						System.out.println("Cliente Desconectado");
						conectado = false;
					}
				}

			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				System.out.println("Fallo creacion List");
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	public void update(Observable o, Object arg) {
		try {
			// Envia el mensaje al cliente
			salidaDatos.writeUTF("Datos recibidos con exitooooo");
		} catch (IOException ex) {
			System.out.println("Fallo update");
			ex.printStackTrace();
		}
	}
}

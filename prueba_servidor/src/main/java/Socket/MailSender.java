package Socket;

import java.io.DataInputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

public class MailSender {
	public static int mailSender(String name, String mail) {

		Socket socket;
		ObjectOutputStream salidaDatos;
		DataInputStream entradaDatos;
		System.out.println("ahi va el mensaje");
		try {

			socket = new Socket("127.0.0.1", 1234);
			salidaDatos = new ObjectOutputStream(socket.getOutputStream());
			entradaDatos = new DataInputStream(socket.getInputStream());
			System.out.println("tengo socket");
			List<String> ls = new ArrayList<>();

			ls.add(name);
			ls.add(mail);

			salidaDatos.writeObject(ls);

			System.out.println(entradaDatos.readUTF());

			salidaDatos.close();

			socket.close();

		} catch (IOException e) {

			e.printStackTrace();
			return -1;
		}

		return 0;
	}
}

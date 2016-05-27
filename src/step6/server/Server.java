package step6.server;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {

	public Server() {

		try {
			ServerSocket ss = new ServerSocket(8888);
			System.out.println("서버대기중 from Server");

			while (true) {
				Socket s = ss.accept();
				System.out.println(s.getInetAddress().getHostAddress() + " 클라이언트접속");

				ObjectInputStream ois = new ObjectInputStream(s.getInputStream());
				ObjectOutputStream oos = new ObjectOutputStream(s.getOutputStream());

				ServerThread st = new ServerThread(ois, oos);
				Thread t = new Thread(st); 
				t.start();
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	public static void main(String[] args) {
		new Server(); 
	}
}

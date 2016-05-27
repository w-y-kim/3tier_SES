package step6.client;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.ArrayList;

import step6.manager.Manager;
import step6.protocol.Command;
import step6.vo.Human;

public class SESClientManager implements Manager {

	private ObjectOutputStream oos;
	private ObjectInputStream ois;

	private Command cmd;
	
	
	
	public SESClientManager() {
		Socket socket; 
		try {
			socket = new Socket("localhost",8888);
			System.out.println("서버접속");
			oos = new ObjectOutputStream(socket.getOutputStream());
			ois = new ObjectInputStream(socket.getInputStream());
			System.out.println("스트림생성");
			//스트림인아웃은 각각의 매소드에서 실행시마다 수행 
		
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	@Override
	public boolean insertHuman(Human human) {
		boolean result = false; 
		cmd = new Command(Command.INSERT_HUMAN); 
		cmd.setArgs(human);
		try {
			oos.writeObject(cmd);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		System.out.println("클라 : insertHuman 실행요청");
		return result;
	}

	@Override
	public Human findHuman(String jumin) {
		Human result = null;
		cmd = new 
		System.out.println("클라 : findHuman 실행요청");
		return result;
	}

	@Override
	public boolean deleteHuman(String jumin) {
		boolean result = false; 
		System.out.println("클라 : deleteHuman 실행요청");
		return result;
	}

	@Override
	public void updateHuman(Human newData) {
		System.out.println("클라 : updateHuman 실행요청");
	}

	@Override
	public ArrayList<Human> getHumanList() {
		ArrayList<Human> result = new ArrayList<>(); 
		System.out.println("클라 : getHumanList 실행요청");
		return result;
	}

}

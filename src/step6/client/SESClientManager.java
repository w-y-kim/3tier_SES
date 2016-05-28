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
			result = (boolean) ois.readObject();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		
		System.out.println("클라 : insertHuman 실행요청");
		return result;
	}

	@Override
	public Human findHuman(String jumin) {
		Human result = null;
		cmd = new Command(Command.FIND_HUMAN); 
		cmd.setArgs(jumin);
		try {
			oos.writeObject(cmd);
			result = (Human) ois.readObject();
		} catch (IOException | ClassNotFoundException e) {
			e.printStackTrace();
		}
		System.out.println("클라 : findHuman 실행요청");
		return result;
	}

	@Override
	public boolean deleteHuman(String jumin) {
		boolean result = false; 
		cmd = new Command(Command.DELETE_HUMAN);
		cmd.setArgs(jumin);
		try {
			oos.writeObject(cmd);
			result = (boolean) ois.readObject();
		} catch (IOException | ClassNotFoundException e) {
			e.printStackTrace();
		}
		System.out.println("클라 : deleteHuman 실행요청");
		return result;
	}

	@Override
	public void updateHuman(Human newData) {
		cmd = new Command(Command.UPDATE_HUMAN);
		cmd.setArgs(newData);
		try {
			oos.writeObject(cmd);
			//void라 반환 값 없음 
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		System.out.println("클라 : updateHuman 실행요청");
	}

	@SuppressWarnings("unchecked")
	@Override
	public ArrayList<Human> getHumanList() {
		ArrayList<Human> result = new ArrayList<>(); 
		cmd = new Command(Command.GET_ALL_HUMAN);
		//para없음
		try {
			oos.writeObject(cmd);
			result = (ArrayList<Human>) ois.readObject();
		} catch (IOException | ClassNotFoundException e) {
			e.printStackTrace();
		}
		System.out.println("클라 : getHumanList 실행요청");
		return result;
	}

}

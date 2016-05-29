package step6.server;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

import step6.exception.DuplicateIDException;
import step6.exception.RecordNotFoundException;
import step6.protocol.Command;
import step6.vo.Human;

public class ServerThread implements Runnable {

	private ObjectInputStream ois;
	private ObjectOutputStream oos;
	private boolean exit;

	private ServerManager sm = new ServerManager();
	private Command cmd;

	public ServerThread(ObjectInputStream ois, ObjectOutputStream oos) {
		this.ois = ois;
		this.oos = oos;
	}

	@Override
	public void run() {
		while(!exit){
			try {
				cmd = (Command)ois.readObject();
				Object para = cmd.getArgs();
				switch (cmd.getCmdValue()) {
				case Command.FIND_HUMAN://찾기
					Human h = sm.findHuman((String)para); 
					oos.writeObject(h);
					System.out.println("서버 : findHuman 실행");
					break;
				case Command.INSERT_HUMAN://추가
					boolean insert_r = sm.insertHuman((Human) para);
					oos.writeObject(insert_r);
					System.out.println("서버 : insertHuman 실행");
					break;  
				case Command.DELETE_HUMAN://삭제
					boolean del_r = sm.deleteHuman((String) para);
					oos.writeObject(del_r);
					System.out.println("서버 : deleteHuman 실행");
					break;
				case Command.UPDATE_HUMAN://갱신
					sm.updateHuman((Human) para);
					//void라 스트림값 보낼 것이 없어요 
					System.out.println("서버 : updateHuman 실행");
					break;
				case Command.GET_ALL_HUMAN://전체
					ArrayList<Human> hList = sm.getHumanList();
					oos.writeObject(hList);
					System.out.println("서버 : getHumanList 실행");
					break;
				default: System.out.println("스레드에서 Command조건이 헛돌고 있어여");
					break;
				}
				
				
				
			} catch (ClassNotFoundException | IOException | RecordNotFoundException | DuplicateIDException e) {
				e.printStackTrace();
				exit = true;//IOException 시 종료 
			} 
		}
	}

}

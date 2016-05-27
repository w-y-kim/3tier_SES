package step6.server;

import java.util.ArrayList;

import step6.dao.Database;
import step6.exception.DuplicateIDException;
import step6.exception.RecordNotFoundException;
import step6.manager.Manager;
import step6.vo.Human;

public class ServerManager implements Manager {
	
	private Database db = new Database(); //db의 멤버들 모두 사용하자!
	private ArrayList<Human> hal = new ArrayList<>(); //쓸일 없는거 같은데? 
	
	@Override
	public boolean insertHuman(Human human) throws DuplicateIDException {
		boolean result = db.insertHuman(human); 
		return result;
	}

	@Override
	public Human findHuman(String jumin) throws RecordNotFoundException {
		Human result = db.findHuman(jumin);
		return result;
	}

	@Override
	public boolean deleteHuman(String jumin) throws RecordNotFoundException {
		boolean result = db.deleteHuman(jumin);
		return result;
	}

	@Override
	public void updateHuman(Human newData) throws RecordNotFoundException {
		db.updateHuman(newData);
	}

	@Override
	public ArrayList<Human> getHumanList() {
		ArrayList<Human> result = db.getHumanList();
		return result;
	}
}

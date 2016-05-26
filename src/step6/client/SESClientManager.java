package step6.client;

import java.util.ArrayList;

import step6.manager.Manager;
import step6.vo.Human;

public class SESClientManager implements Manager {

	@Override
	public boolean insertHuman(Human human) {
		return false;
	}

	@Override
	public Human findHuman(String jumin) {
		return null;
	}

	@Override
	public boolean deleteHuman(String jumin) {
		return false;
	}

	@Override
	public void updateHuman(Human newData) {
	}

	@Override
	public ArrayList<Human> getHumanList() {
		return null;
	}

}

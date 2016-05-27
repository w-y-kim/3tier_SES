package step6.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import step6.exception.DuplicateIDException;
import step6.exception.RecordNotFoundException;
import step6.manager.Manager;
import step6.vo.Human;
import step6.vo.Professor;
import step6.vo.Staff;
import step6.vo.Trainee;

public class Database implements Manager {

	private Human human;// 기본정보 받아와서 저장하는 공용자원

	/**
	 * Human 테이블에 동일한 주민번호 가진 사람이 있는지 검사하고 있다면 human 객체 생성
	 * 
	 * @param jumin
	 * @return 동일사람 있으면 true
	 */
	public boolean juminExist(String jumin) {
		boolean result = false;

		Connection con = ConnectionManager.getConnection();
		String sql = "SELECT * FROM HUMAN WHERE JUMIN = ?";

		try {
			PreparedStatement pstat = con.prepareStatement(sql);
			pstat.setString(1, jumin);
			ResultSet rs = pstat.executeQuery();

			if (rs.next()) {

				System.out.println("DB에서 해당 데이터 존재");
				// 주민번호를 제외한 컬럼의 정보를 가져와 객체 만들겠음
				String name = rs.getString("NAME");
				int age = rs.getInt("AGE");
				String type = rs.getString("TYPE");
				human = new Human(name, age, jumin, type);
				result = true;
			} else
				System.out.println("DB에 해당 데이터 없음");

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;

	}

	/**
	 * getCustomer와 동일한 역할
	 * 
	 * @param jumin
	 * @return
	 * @throws RecordNotFoundException
	 */
	public Human findHuman(String jumin) throws RecordNotFoundException {
		Human h = null;
		// TODO 조인을 안해도 되도록 테이블을 두번에 나눠 각자 부르는데 조인해서 부르면 더 효율적일듯
		// TODO 테이블을 2개 부르는 이유는 여기서는 각VO의 객체를 생성할 때 필요한 변수값 때문
		// TODO 변수값을 채워야지 VO가 만들어지고 GUI에서 결과값을 볼 수 있으니까...
		// TODO 서현이는 아마 여기서 Human테이블을 검색하는 쿼리를 따로 돌려서 거기서 변수값 뽑을듯
		if (!juminExist(jumin)) {// 존재 시 juminExist()가 Human 객체생성
			throw new RecordNotFoundException();
		} else {// 존재하는 경우만 다음 코드 수행
			Connection con = ConnectionManager.getConnection();
			h = this.human;// juminExist()메소드 수행 결과 저장한 human 정보(or 클래스변수초기값
			String sql = null;
			PreparedStatement pstat = null;
			ResultSet rs = null;
			String key = null;

			if (h instanceof Professor) {
				key = "교수";
			} else if (h instanceof Trainee) {
				key = "학생";
			} else if (h instanceof Staff) {
				key = "직원";
			}
			try {
				switch (key) {
				case "교수":
					sql = "SELECT * FROM PROFESSOR WHERE JUMIN = ?";
					pstat = con.prepareStatement(sql);
					pstat.setString(1, jumin);
					rs = pstat.executeQuery();

					if (rs.next()) {
						String major = rs.getString("major");
						h = new Professor(h.getName(), h.getAge(), jumin, major);
					}
					break;
				case "학생":
					sql = "SELECT * FROM TRAINEE WHERE JUMIN = ?";
					pstat = con.prepareStatement(sql);
					pstat.setString(1, jumin);
					rs = pstat.executeQuery();

					if (rs.next()) {
						String stdNo = rs.getString("stdNo");
						h = new Trainee(h.getName(), h.getAge(), jumin, stdNo);
					}
					break;
				case "직원":
					sql = "SELECT * FROM STAFF WHERE JUMIN = ?";
					pstat = con.prepareStatement(sql);
					pstat.setString(1, jumin);
					rs = pstat.executeQuery();

					if (rs.next()) {
						String field = rs.getString("field");
						h = new Staff(h.getName(), h.getAge(), jumin, field);
					}
					break;

				default:
					System.out.println("@FIXME 타입별 insert 시 일치하는 타입이 없음");
					break;
				}
			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				if (con != null)
					ConnectionManager.close(con);
			} // try-catch
		} // else

		return h;
	}

	@SuppressWarnings("resource")
	@Override
	public boolean insertHuman(Human human) throws DuplicateIDException {
		boolean result = false;
		if (juminExist(human.getJumin())) {// 존재 시 중복자료예외처리
			throw new DuplicateIDException();
		} else {// 존재안하는 경우만 다음 코드 수행
			Connection con = ConnectionManager.getConnection();
			String sql = null;
			String type = null;
			try {
				sql = "INSERT INTO HUMAN VALUES(?,?,?,?)";
				PreparedStatement pstat = con.prepareStatement(sql);
				pstat.setString(1, human.getName());
				pstat.setInt(2, human.getAge());
				pstat.setString(3, human.getJumin());
				if (human instanceof Professor) {
					type = "교수";
				} else if (human instanceof Trainee) {
					type = "학생";
				} else if (human instanceof Staff) {
					type = "직원";
				}
				pstat.setString(4, type);// instanceof로 객체별 플래그변수로 타입데이터 채움
				int row = pstat.executeUpdate();
				if (row == 1) {
					System.out.println("HUMAN테이블 레코드 insert");
				}
				if (human instanceof Professor) {
					Professor p = (Professor) human;
					sql = "INSERT INTO PROFESSOR VALUES(?,?)";
					pstat = con.prepareStatement(sql);
					pstat.setString(1, p.getJumin());
					pstat.setString(2, p.getMajor());
					row = pstat.executeUpdate();
					if (row == 1) {
						System.out.println("교수테이블 레코드 insert");
						result = true;
					}
				} else if (human instanceof Trainee) {
					Trainee t = (Trainee) human;
					sql = "INSERT INTO TRAINEE VALUES(?,?)";
					pstat = con.prepareStatement(sql);
					pstat.setString(1, t.getJumin());
					pstat.setString(2, t.getStdNo());
					row = pstat.executeUpdate();
					if (row == 1) {
						System.out.println("학생테이블 레코드 insert");
						result = true;
					}
				} else if (human instanceof Staff) {
					Staff s = (Staff) human;
					sql = "INSERT INTO STAFF VALUES(?,?)";
					pstat = con.prepareStatement(sql);
					pstat.setString(1, s.getJumin());
					pstat.setString(2, s.getField());
					row = pstat.executeUpdate();
					row = pstat.executeUpdate();
					if (row == 1) {
						System.out.println("직원테이블 레코드 insert");
						result = true;
					}

				} else {
					System.out.println("@FIXME 사람테이블 레코드만 만들어짐 ");
				}

			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				if (con != null)
					ConnectionManager.close(con);
			} // try-catch
		} // else
		return result;
	}

	@SuppressWarnings("resource")
	@Override
	public boolean deleteHuman(String jumin) throws RecordNotFoundException {
		boolean result = false;
		if (!juminExist(jumin)) {// 존재 시 juminExist()가 Human 객체생성
			throw new RecordNotFoundException();
		} else {
			Connection con = ConnectionManager.getConnection();
			Human h = this.human;// juminExist() 시
			String sql = null;
			PreparedStatement pstat = null;
			String key = null;

			if (h instanceof Professor) {
				key = "교수";
			} else if (h instanceof Trainee) {
				key = "학생";
			} else if (h instanceof Staff) {
				key = "직원";
			}

			try {
				sql = "DELETE FROM HUMAN WHERE JUMIN=?";
				pstat = con.prepareStatement(sql);
				pstat.setString(1, jumin);
				int row = pstat.executeUpdate();
				if (row == 1) {
					System.out.println("HUMAN테이블 레코드 delete");
					result = true;
				}

				switch (key) {
				case "교수":
					sql = "DELETE FROM PROFESSOR WHERE JUMIN=?";
					pstat = con.prepareStatement(sql);
					pstat.setString(1, jumin);
					row = pstat.executeUpdate();
					if (row == 1) {
						System.out.println("교수테이블 레코드 delete");
						result = true;
					}
					break;
				case "학생":
					sql = "DELETE FROM TRAINEE WHERE JUMIN=?";
					pstat = con.prepareStatement(sql);
					pstat.setString(1, jumin);
					row = pstat.executeUpdate();
					if (row == 1) {
						System.out.println("학생테이블 레코드 delete");
						result = true;
					}
					break;
				case "직원":
					sql = "DELETE FROM STAFF WHERE JUMIN=?";
					pstat = con.prepareStatement(sql);
					pstat.setString(1, jumin);
					row = pstat.executeUpdate();
					if (row == 1) {
						System.out.println("직원테이블 레코드 delete");
						result = true;
					}
					break;
				default:
					System.out.println("@FIXME 타입별 delete 시 일치하는 타입이 없음");
					break;
				}

			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				if (con != null)
					ConnectionManager.close(con);
			} // try-catch
		} // else

		return result;
	}

	@SuppressWarnings("resource")
	@Override
	public void updateHuman(Human newData) throws RecordNotFoundException {
		if (!juminExist(newData.getJumin())) {// 기존 레코드가 없으면 업데이트 못하는거니까
			throw new RecordNotFoundException();
		} else {
			Connection con = ConnectionManager.getConnection();
			String sql = null;
			PreparedStatement pstat = null;
			String type = null;

			if (newData instanceof Professor) {
				type = "교수";
			} else if (newData instanceof Trainee) {
				type = "학생";
			} else if (newData instanceof Staff) {
				type = "직원";
			}

			try {
				sql = "UPDATE HUMAN SET NAME=?,AGE=?,TYPE=? WHERE JUMIN=?";
				pstat = con.prepareStatement(sql);
				pstat.setString(1, newData.getName());
				pstat.setInt(2, newData.getAge());
				pstat.setString(3, type);
				pstat.setString(4, newData.getJumin());
				int row = pstat.executeUpdate();
				if (row == 1) {
					System.out.println("HUMAN테이블 레코드 update");
				}
				if (newData instanceof Professor) {
					Professor p = (Professor) newData;
					sql = "UPDATE PROFESSOR SET MAJOR=? WHERE JUMIN=?";
					pstat = con.prepareStatement(sql);
					pstat.setString(1, p.getMajor());
					pstat.setString(2, p.getJumin());
					row = pstat.executeUpdate();
					if (row == 1) {
						System.out.println("교수테이블 레코드 update");
					}
				} else if (newData instanceof Trainee) {
					Trainee t = (Trainee) newData;
					sql = "UPDATE PROFESSOR SET STDNO=? WHERE JUMIN=?";
					pstat = con.prepareStatement(sql);
					pstat.setString(1, t.getStdNo());
					pstat.setString(2, t.getJumin());
					row = pstat.executeUpdate();
					if (row == 1) {
						System.out.println("학생테이블 레코드 update");
					}
				} else if (newData instanceof Staff) {
					Staff s = (Staff) newData;
					sql = "UPDATE PROFESSOR SET FIELD=? WHERE JUMIN=?";
					pstat = con.prepareStatement(sql);
					pstat.setString(1, s.getField());
					pstat.setString(2, s.getJumin());
					row = pstat.executeUpdate();
					if (row == 1) {
						System.out.println("직원테이블 레코드 update");
					}
				}
			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				if (con != null)
					ConnectionManager.close(con);
			} // try-catch
		}
	}

	@Override
	public ArrayList<Human> getHumanList() {
		ArrayList<Human> result = null;// 초기값으로 null을 해주는 것보다 배열 생성이 낫지 않을까?
		result = new ArrayList<Human>();// 이렇게 하면 일단 호출부에서 널포인터는 안뜰 듯
		// 있으면 있는대로 없느면 없는대로 모두 가져올거니까 존재하는지 검사할 것도 없음, 바로SQL

		// SELECT에서 이렇게하면 객체를 각각 생성해서 다시 또 get해서 담아줘야하니 불편, 조인하자
		// String sql1 = "SELECET * FROM HUHAN";
		// String sql2 = "SELECET * FROM PROFESSOR";
		// String sql3 = "SELECET * FROM PROFESSOR";
		// String sql4 = "SELECET * FROM PROFESSOR";

		String sql = "SELECT h.jumin from Human";

		Connection con = ConnectionManager.getConnection();
		PreparedStatement pstat;
		try {
			pstat = con.prepareStatement(sql);
			ResultSet rs = pstat.executeQuery();

			//TODO find메소드를 이용하는 것이 효율이 떨어질거 같긴함, con을 또 만들어야하니
			while(rs.next()){//가져온 전체 주민등록번호 수만큼 반복 
			String jumin = rs.getString("h.jumin");
			Human typedHuman = this.findHuman(jumin);//find메소드에서 대신 객체를 구분해서 생성해준다 
			result.add(typedHuman);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (RecordNotFoundException e) {
			e.printStackTrace();
		}

		// String sql1 = "SELECT h.name, h.age, h,jumin, h.type, p.major from
		// human h natural join professor p";
		// String sql2 = "SELECT h.name, h.age, h,jumin, h.type, t.stdno from
		// human h natural join trainee t";
		// String sql3 = "SELECT h.name, h.age, h,jumin, h.type, s.field from
		// human h natural join staff s";
		// PreparedStatement pstat = null;
		// ResultSet rs = null;
		// try {
		// //TODO pstat 변수를 따로 만들지 않은 것을 후회하게 될까? CPU스케줄링?
		//
		// //교수
		// pstat = con.prepareStatement(sql1);
		// rs = pstat.executeQuery();
		// while(rs.next()){
		// String name = rs.getString("h.name");
		// int age = rs.getInt("h.age");
		// String jumin = rs.getString("h.jumin");
		// new
		// }
		//
		// //학생
		// pstat = con.prepareStatement(sql2);
		// pstat.executeQuery();
		//
		// //직원
		// pstat = con.prepareStatement(sql3);
		// pstat.executeQuery();
		//
		//
		// } catch (SQLException e) {
		// e.printStackTrace();
		// } finally {
		// if (con != null)
		// ConnectionManager.close(con);
		// } // try-catch
		//
		return result;
	}

}

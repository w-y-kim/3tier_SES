package step6.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import step6.exception.RecordNotFoundException;
import step6.vo.Human;
import step6.vo.Professor;
import step6.vo.Trainee;

public class Database {

	private Human human;// 기본정보 받아와서 저장하는 공용자원

	/**
	 * Human 테이블에 동일한 주민번호 가진 사람이 있는지 검사하고 있다면 human 객체 생성
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
	 * @param jumin
	 * @return
	 * @throws RecordNotFoundException
	 */
	public Human findHuman(String jumin) throws RecordNotFoundException {
		Human h = this.human;// juminExist()메소드 수행 결과 저장한 human 정보(or 클래스변수초기값
								// null);

		String sql = null;
		PreparedStatement pstat = null;
		ResultSet rs = null;

		if (!juminExist(jumin)) {
			throw new RecordNotFoundException();
		} else {
			Connection con = ConnectionManager.getConnection();

			try {
				switch (h.getType()) {
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
						h = new Professor(h.getName(), h.getAge(), jumin, field);
					}
					break;

				default:
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

}

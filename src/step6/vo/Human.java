package step6.vo;

import java.io.Serializable;

/**
 * <pre>
 * SES(Soft Engineer School) 관리 프로그램의 관리인원(Professor, Trainee, Staff) 클래스의 슈퍼 클래스
 * 관리인원 클래스들의 공통 속성인 이름, 나이, 주민번호를 멤버 변수로 갖는다.
 * </pre>
 * */
public class Human implements Serializable {

	private String name;  //관리인원 이름
	private int age;	  //관리인원 나이
	private String jumin; //관리인원 주민번호
	private String type; 
	
	/**
	 * 주어진 이름, 나이, 주민번호 정보를 가지고 새로운 Human 객체를 생성한다.
	 * @param name 구성원의 이름
	 * @param age 구성원의 나이
	 * @param jumin 구성원의 주민번호
	 * @param type 
	 * */
	public Human(String name, int age, String jumin, String type) {
		this.name = name;
		this.age = age;
		this.jumin = jumin;
	}

	//자식클래스에서 사용하기 위함(굳이 타입 안받아도 되니까)
	public Human(String name, int age, String jumin) {
		this.name = name;
		this.age = age;
		this.jumin = jumin;
	}
	
	/**
	 * 이름을 조회한다.
	 * @return Human 객체가 가지고 있는 이름
	 * */
	public String getName() {
		return name;
	}

	/**
	 * 새로운 이름으로 변경한다.
	 * @param name 변경하고자 하는 새로운 이름
	 * */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * 나이를 조회한다.
	 * @return Human 객체가 가지고 있는 나이
	 * */
	public int getAge() {
		return age;
	}

	/**
	 * 새로운 나이로 변경한다.
	 * @param age 변경하고자 하는 새로운 나이
	 * */
	public void setAge(int age) {
		this.age = age;
	}

	/**
	 * 주민번호를 조회한다.
	 * @return Human 객체가 가지고 있는 주민번호
	 * */
	public String getJumin() {
		return jumin;
	}

	/**
	 * 새로운 주민번호로 변경한다.
	 * @param jumin 변경하고자 하는 새로운 주민번호
	 * */
	public void setJumin(String jumin) {
		this.jumin = jumin;
	}
	
	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	/**
	 * Human 객체가 가지고 있는 이름, 나이, 주민번호 정보를 출력한다.
	 * */
	@Override
	public String toString(){
		return String.format("이름: %s, 나이: %d, 주민번호: %s", name, age, jumin);
	}
	
}

package step6.vo;

import java.io.Serializable;

/**
 * <pre>
 * SES(Soft Engineer School) 관리 프로그램의 관리인원 중 운영진에 대한 정보를 관리하는 클래스
 * Human 클래스를 상속하고 있으며, Staff 객체의 추가적인 정보인 부서를 멤버 변수로 갖는다.
 * </pre>
 * */
public class Staff extends Human implements Serializable  {
	private String field; //운영진의 부서

	/**
	 * 주어진 이름, 나이, 주민번호, 부서 정보를 가지고 새로운 Staff 객체를 생성한다.
	 * @param name 운영진의 이름
	 * @param age 운영진의 나이
	 * @param jumin 운영진의 주민번호
	 * @param field 운영진의 부서
	 * */
	public Staff(String name, int age, String jumin, String field) {
		super(name, age, jumin);
		this.field = field;
	}

	/**
	 * 부서를 조회한다.
	 * @return Staff 객체가 가지고 있는 부서
	 * */
	public String getField() {
		return field;
	}

	/**
	 * 새로운 부서로 변경한다.
	 * @param field 변경하고자 하는 새로운 부서
	 * */
	public void setField(String field) {
		this.field = field;
	}
	
	@Override
	/**
	 * Staff 객체가 가지고 있는 이름, 나이, 주민번호, 부서 정보를 출력한다.
	 * */
	public String toString() {
		return super.toString()+String.format(", 부서: %s%n", field);
	}
	
}

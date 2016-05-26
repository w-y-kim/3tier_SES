package step6.vo;

/**
 * <pre>
 * SES(Soft Engineer School) 관리 프로그램의 관리인원 중 연수생에 대한 정보를 관리하는 클래스
 * Human 클래스를 상속하고 있으며, Trainee 객체의 추가적인 정보인 학번을 멤버 변수로 갖는다.
 * </pre>
 * */
public class Trainee extends Human {
	private String stdNo; //연수생 학번

	/**
	 * 주어진 이름, 나이, 주민번호, 학번 정보를 가지고 새로운 Trainee 객체를 생성한다.
	 * @param name 연수생의 이름
	 * @param age 연수생의 나이
	 * @param jumin 연수생의 주민번호
	 * @param stdNo 연수생의 학번
	 * */
	public Trainee(String name, int age, String jumin, String stdNo) {
		super(name, age, jumin);
		this.stdNo = stdNo;
	}

	/**
	 * 학번을 조회한다.
	 * @return Trainee 객체가 가지고 있는 학번
	 * */
	public String getStdNo() {
		return stdNo;
	}

	/**
	 * 새로운 학번으로 변경한다.
	 * @param stdNo 변경하고자 하는 새로운 학번
	 * */
	public void setStdNo(String stdNo) {
		this.stdNo = stdNo;
	}

	@Override
	/**
	 * Trainee 객체가 가지고 있는 이름, 나이, 주민번호, 학번 정보를 출력한다.
	 * */
	public String toString() {
		return super.toString()+String.format(", 학번: %s%n", stdNo);
	}
	
	
}

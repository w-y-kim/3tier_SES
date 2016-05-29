package step6.client;

/**
 * <pre>
 * SES(Soft Engineer School) 관리 프로그램의 시작점
 * SESUI 클래스의 객체를 생성하여 프로그램을 시작한다.
 * </pre>
 * */
public class SESMain {

	/**
	 * Java-Stand alone Application Entry point
	 * */
	public static void main(String[] args) {
		new SESUI();
	}

}
//FIXME 
/*실행 시 문제점 
 *
 *1. insert  
 *	-운영진 입력 시 부서등록 안됨 -> 타입미스 
 *  -동일 jumin 등록 시도 시 핵멈춤
 *  	-DUIPLIICATED 예외처리는 되지만 결과가 서버에만 머물고 GUI는 계속 대기
 *  	-
 * 
 * 
 * */

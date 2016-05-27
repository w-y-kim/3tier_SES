package step6.protocol;

public class Command {

	private int cmdValue;//요청한 명령 
	private Object args;//서버메소드 파라미터로 전달할 인자값
	private Object result;//서버단 처리결과
	private int status;//예외처리결과 
	
	public static final int FIND_HUMAN = 10;
	public static final int INSERT_HUMAN = 30;
	public static final int DELETE_HUMAN = 50;
	public static final int UPDATE_HUMAN = 70;
	public static final int GET_ALL_HUMAN = 90;

	public static final int RECORED_NOT_FOUND = -10;
	public static final int DUPLICATE_ID = -30;
	public static final int INVALID_TRANSACTION = -50;
	
	public Command(int cmdValue){
		this.cmdValue = cmdValue;
	}

	public int getCmdValue() {
		return cmdValue;
	}

	public void setCmdValue(int cmdValue) {
		this.cmdValue = cmdValue;
	}

	public Object getArgs() {
		return args;
	}

	public void setArgs(Object args) {
		this.args = args;
	}

	public Object getResult() {
		return result;
	}

	public void setResult(Object result) {
		this.result = result;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}
	
	
}

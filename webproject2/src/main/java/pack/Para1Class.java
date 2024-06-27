package pack;

public class Para1Class {	// jsp로 호출될 클래스. Bean
	private String msg;
	
	public void setMsg(String msg) {
		this.msg =msg;
	}
	
	public String getMsg() {
		return msg + " 메세지 처리";
	}
}

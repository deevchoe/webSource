package pack;
/*
자바빈은 클래스이므로 기존의 자바 클래스를 작성하는 방법과 동일하다.
자바빈의 경우 데이터를 담을 프로퍼티(맴버변수)와 
데이터를 가져오거나 세팅하는 기능을 하는 메서드로 구성된다.
 */

public class ExamBean {		
	// 클라이언트로부터 전송되는 복수개의 값을 1개의 그룹으로 묶어 처리하는 클래스
	// 이러한 용도의 클래스를 FormBean이라고 부른다.
	
	private String name;
	private int kor;
	private int eng;
	private int mat;
	
	/* 데이터를 가져오거나(get), 세팅하는(set) 기능을 하는 메서드를 만든다. 
	- 데이터를 가져오는 경우 -> get메서드
	- 데이터를 세팅하는 경우 -> set메서드    
	*/
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getKor() {
		return kor;
	}
	public void setKor(int kor) {
		this.kor = kor;
	}
	public int getEng() {
		return eng;
	}
	public void setEng(int eng) {
		this.eng = eng;
	}
	public int getMat() {
		return mat;
	}
	public void setMat(int mat) {
		this.mat = mat;
	}
	
	
	
	

}

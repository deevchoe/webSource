package pack;
// DTO Class
public class ScoreDTO {
	private String no;
	private String name;
	private int kor;
	private int eng;
	
	public ScoreDTO(String no, String name, int kor, int eng) {
		this.no = no;
		this.name = name;
		this.kor = kor;
		this.eng = eng;
	}
	
	public String getNo() {
		return no;
	}
	
	public String getName() {
		return name;
	}
	
	public int getKor() {
		return kor;
	}
	
	public int getEng() {
		return eng;
	}
}

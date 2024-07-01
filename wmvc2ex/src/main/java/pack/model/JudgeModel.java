package pack.model;

public class JudgeModel {

	public String judge(String j) {
		String result = "";
		
		if(Integer.parseInt(j) % 2 == 0) {
			result = "짝수";
		}else {
			result = "홀수";
		}
		
		return result;
	}
}

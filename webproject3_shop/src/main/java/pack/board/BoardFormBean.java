package pack.board;

import java.time.LocalDate;

import lombok.Data;

@Data
public class BoardFormBean {
	private String name, pass, mail, title, cont, bip, bdate;
	private int num, readcnt, gnum, onum, nested;
	
	public void setBdate() {	// overloading
		LocalDate now = LocalDate.now();
		int year = now.getYear();
		int month = now.getMonthValue();
		int day = now.getDayOfMonth();
		
		this.bdate = year + "-" + month + "-" + day;
	}
}

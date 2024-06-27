package pack.board;

import lombok.Data;

@Data
public class BoardDTO {
	private String name, pass, mail, title, cont, bip, bdate;
	private int num, readcnt, gnum, onum, nested;
}

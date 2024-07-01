package pack.model;

import java.util.ArrayList;

public class MulModel {
	public ArrayList<String> mul(String m) {
		ArrayList<String> list = new ArrayList<String>();
		for(int i = 1; i <= 9; i++) {
			list.add(m + " X " + i + " = " + (Integer.parseInt(m) * i));
		}
		return list;
	}
}

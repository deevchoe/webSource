package kr.mvc.model;

import java.util.ArrayList;

// 여러 개의 DAO 클래스 관리가 목적
public class UserManager {
	private static UserManager manager = new UserManager();
	
	public static UserManager instance() {
		return manager;
	}
	
	private UserDaoModel getUserDaoModel() {
		return new UserDaoModel();
	}
	
	public boolean login(String user_id, String user_password) {
		UserDto dto = getUserDaoModel().findUser(user_id);
		if(dto == null) return false;
		
		if(dto.getPassword().equals(user_password)) {
			return true;
		}else {
			return false;
		}
	}
	
	public ArrayList<UserDto> getUserAll(){
		return getUserDaoModel().getUserDataAll();
	}
}

package kr.mvc.model;

import java.util.ArrayList;

import kr.mvc.controller.UserForm;

// 여러 개의 DAO 클래스 관리가 목적
public class UserManager {
	private static UserManager manager = new UserManager(); // 싱글톤
	
	public static UserManager instance() {
		return manager;
	}
	
	private UserDaoModel getUserDaoModel() {
		return new UserDaoModel(); // 원래 싱글톤으로 만들어줘야 함
	}
	
	/* 이렇게 여러 DaoModel 가져오는 메소드 만들 수 있어
	private JikwonDaoModel getJikwonDaoModel() {
		return new JikwonDaoModel(); // 원래 싱글톤으로 만들어줘야 함
	}
	
	private SangpumDaoModel getSangpumDaoModel() {
		return new SangpumDaoModel(); // 원래 싱글톤으로 만들어줘야 함
	}
	*/
	
	public boolean login(String user_id, String user_password) {
		UserDto dto = getUserDaoModel().findUser(user_id);
		
		if(dto == null) return false;
		
		if(dto.getPassword().equals(user_password)) return true;
		else return false;
	}
	
	public ArrayList<UserDto> getUserAll(){
		return getUserDaoModel().getUserDataAll();
	}
	
	public int insert(UserForm userForm) {
		return getUserDaoModel().insertData(userForm);
	}
	
	public UserDto findUser(String userid) {
		return getUserDaoModel().findUser(userid);
	}
	
	public boolean update(UserForm userForm) {
		return (getUserDaoModel().updateData(userForm) == 1) ? true : false;
	}
	
	public boolean delete(String userid) {
		return (getUserDaoModel().deleteData(userid) == 1) ? true : false;
	}
}

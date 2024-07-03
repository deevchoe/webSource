package kr.mvc.model;

import java.util.ArrayList;
import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import kr.mvc.controller.UserForm;

// Data Access Object의 약자로, 실제로 DB에 접근하는 객체
// Controller 클래스의 요청을 받아 DB 연동 처리를 담당
public class UserDaoModel {
	// SQLMapper에서 session 읽어오기
	private SqlSessionFactory factory = SqlMapConfig.getSqlSession();
	
	public UserDto findUser(String userid) {
		UserDto dto = null;
		SqlSession session = factory.openSession();
		try {
			dto = session.selectOne("findUser", userid);
		} catch (Exception e) {
			System.out.println("findUser err: " + e);
		} finally {
			session.close();
		}
		return dto;
	}
	
	public ArrayList<UserDto> getUserDataAll(){
		List<UserDto> list = null;
		SqlSession session = factory.openSession();
		try {
			list = session.selectList("selectDataAll");
		} catch (Exception e) {
			System.out.println("geUserAll err: " + e);
		} finally {
			session.close();
		}
		return (ArrayList<UserDto>)list; 
	}
	
	public int insertData(UserForm userForm) {
		int result = 0;
		SqlSession session = factory.openSession();
		try {
			result = session.insert("insertData", userForm);
			session.commit();
		} catch (Exception e) {
			System.out.println("insertData err: " + e);
			session.rollback();
		} finally {
			session.close();
		}
		return result;
	}
	
	public int updateData(UserForm userForm) {
		int result = 0;
		SqlSession session = factory.openSession();
		try {
			result = session.insert("updateData", userForm);
			session.commit();
		} catch (Exception e) {
			System.out.println("updateData err: " + e);
			session.rollback();
		} finally {
			session.close();
		}
		return result;
	}
	
	public int deleteData(String userid) {
		int result = 0;
		SqlSession session = factory.openSession();
		try {
			result = session.delete("deleteData", userid);
			session.commit();
		} catch (Exception e) {
			System.out.println("deleteData err: " + e);
			session.rollback();
		} finally {
			session.close();
		}
		return result;
	}
}

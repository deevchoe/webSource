package pack.business;

import java.sql.SQLException;
import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import pack.business.DataDto;
import pack.mybatis.SqlMapConfig;

public class ProcessDao implements ProcessInter{
	private SqlSessionFactory factory = SqlMapConfig.getSqlSession();
	
	@Override
	public List<DataDto> selectDataAll() {
		SqlSession sqlSession = factory.openSession();
		List<DataDto> list = null;
		
		try {
			list = sqlSession.selectList("selectDataAll");
		} catch (Exception e) {
			System.out.println("selectDataAll err : " + e);
		} finally {
			if(sqlSession != null) sqlSession.close();
		}
		return list;
	}
	
	@Override
	public DataDto selectPart(String id) {
		SqlSession sqlSession = factory.openSession();
		DataDto dto = null;
		
		try {
			dto = sqlSession.selectOne("selectPart", id);
		} catch (Exception e) {
			System.out.println("selectPart err : " + e);
		} finally {
			if(sqlSession != null) sqlSession.close();
		}
		
		return dto;
	} 
	
	@Override
	public boolean insertData(DataFormBean form) {
		boolean b = false;
		SqlSession sqlSession = factory.openSession();
		
		try {
			if(sqlSession.insert("insertData", form) > 0) b = true; // insert 한 값이 0보다 크다면 성공한거자나
			sqlSession.commit();
		} catch (Exception e) {
			System.out.println("insertData err : " + e);
			sqlSession.rollback();
		} finally {
			if(sqlSession != null) sqlSession.close();
		}
		
		return b;
	}
	
	@Override
	public boolean updateData(DataFormBean form) {
		boolean b = false;
		SqlSession sqlSession = factory.openSession();
		
		try {
			// 비밀번호 비교 후 수정 여부 판단
			DataDto dto = selectPart(form.getId());
			
			if(dto.getPasswd().equals(form.getPasswd())) { // db에 있는 비밀번호.equals클라이언트가 입력한 비밀번호
				// 수정 처리
				if(sqlSession.update("updateData",form) > 0) {
					b = true;
					sqlSession.commit();
				}
			}
		} catch (Exception e) {
			System.out.println("updateData err : " + e);
			sqlSession.rollback();
		} finally {
			if(sqlSession != null) sqlSession.close();
		}
		
		return b;
	}
	
	@Override
	public boolean deleteData(String id) {
		boolean b = false;
		SqlSession sqlSession = factory.openSession();
		
		try {
			int cou = sqlSession.delete("deleteData", id);
			if(cou > 0) b = true;
			sqlSession.commit();
		} catch (Exception e) {
			System.out.println("deleteData err : " + e);
			sqlSession.rollback();
		} finally {
			if(sqlSession != null) sqlSession.close();
		}
		
		return b;
	}
}
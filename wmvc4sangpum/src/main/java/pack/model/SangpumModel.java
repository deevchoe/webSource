package pack.model;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

public class SangpumModel {
	private SqlSessionFactory factory = SqlMapConfig.getSqlSession();
	
	public List<SangpumDto> selectDataAll(){	// 메소드의 이름
		List<SangpumDto> list = null;
		
		SqlSession session = factory.openSession();
		list = session.selectList("selectDataAll");	// id의 이름
		session.close();
		
		return list;
	}
}
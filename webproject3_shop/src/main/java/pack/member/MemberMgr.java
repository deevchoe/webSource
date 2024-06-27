package pack.member;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

public class MemberMgr {
	private Connection conn;
	private PreparedStatement pstmt;
	private ResultSet rs;
	private DataSource ds;
	
	public MemberMgr() {
		try {
			Context context = new InitialContext();
			ds = (DataSource) context.lookup("java:comp/env/jdbc_maria");
		} catch (Exception e) {
			System.out.println("db 연결 실패 : " + e.getMessage());
		}
	}
	
	// 동 이름으로 우편번호 검색
	public ArrayList<ZipcodeDTO> zipcodeRead(String dongName){
		ArrayList<ZipcodeDTO> list = new ArrayList<ZipcodeDTO>();
		try {
			conn = ds.getConnection();
			String sql = "select * from ziptab where area3 like ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, dongName + "%");
			rs = pstmt.executeQuery();
			while(rs.next()) {
				ZipcodeDTO dto = new ZipcodeDTO();
				dto.setZipcode(rs.getString("zipcode"));
				dto.setArea1(rs.getString("area1"));
				dto.setArea2(rs.getString("area2"));
				dto.setArea3(rs.getString("area3"));
				list.add(dto);
			}
		} catch (Exception e) {
			System.out.println("zipcodeRead err: " + e);
		} finally {
			try {
				if (rs != null) rs.close();
				if (pstmt != null) pstmt.close();
				if (conn != null) conn.close();
			} catch (Exception e2) {
				// TODO: handle exception
			}
		}
		return list;
	}
	
	public boolean idCheckProcess(String id) {
		boolean b = false;
		try {
			conn = ds.getConnection();
			String sql = "select id from member where id = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, id);
			rs = pstmt.executeQuery();
			b = rs.next(); // 있으면 true, 없으면 false
		} catch (Exception e) {
			System.out.println("idCheckProcess err: " + e);
		} finally {
			try {
				if (rs != null) rs.close();
				if (pstmt != null) pstmt.close();
				if (conn != null) conn.close();
			} catch (Exception e2) {
				// TODO: handle exception
			}
		}
		return b;
	}
	
	public boolean memberInsert(MemberBean mbean) {
		boolean b = false;
		try {
			conn = ds.getConnection();
			String sql = "insert into member values(?,?,?,?,?,?,?,?)";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, mbean.getId());
			pstmt.setString(2, mbean.getPasswd());
			pstmt.setString(3, mbean.getName());
			pstmt.setString(4, mbean.getEmail());
			pstmt.setString(5, mbean.getPhone());
			pstmt.setString(6, mbean.getZipcode());
			pstmt.setString(7, mbean.getAddress());
			pstmt.setString(8, mbean.getJob());
			if(pstmt.executeUpdate() == 1) b = true;
		} catch (Exception e) {
			System.out.println("memberInsert err: " + e);
		} finally {
			try {
				if (rs != null) rs.close();
				if (pstmt != null) pstmt.close();
				if (conn != null) conn.close();
			} catch (Exception e2) {
				// TODO: handle exception
			}
		}
		return b;
	}
	
	public boolean loginCheck(String id, String passwd) {
		boolean b = false;
		
		try {
			conn = ds.getConnection();
			String sql = "select * from member where id=? and passwd=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, id);
			pstmt.setString(2, passwd);
			rs = pstmt.executeQuery();
			b = rs.next();
		} catch (Exception e) {
			System.out.println("memberInsert err: " + e);
		} finally {
			try {
				if (rs != null) rs.close();
				if (pstmt != null) pstmt.close();
				if (conn != null) conn.close();
			} catch (Exception e2) {
				// TODO: handle exception
			}
		}
		return b;	
	}
	
	public MemberBean getMember(String id) {
		MemberBean bean = null;
		try {
			conn = ds.getConnection();
			String sql = "select * from member where id=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, id);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				bean = new MemberBean();
				bean.setId(rs.getString("id"));
				bean.setPasswd(rs.getString("passwd"));
				bean.setName(rs.getString("name"));
				bean.setEmail(rs.getString("email"));
				bean.setPhone(rs.getString("phone"));
				bean.setZipcode(rs.getString("zipcode"));
				bean.setAddress(rs.getString("address"));
				bean.setJob(rs.getString("job"));
			}
		} catch (Exception e) {
			System.out.println("getMember err: " + e);
		} finally {
			try {
				if (rs != null) rs.close();
				if (pstmt != null) pstmt.close();
				if (conn != null) conn.close();
			} catch (Exception e2) {
				// TODO: handle exception
			}
		}
		return bean;
	}
	
	public boolean memberUpdate(MemberBean memberBean, String id) {	// 멤버 업데이트를 성공할 수도 있고 실패할 수도 있으니까 boolean으로 받아
		boolean b = false;
		
		try {
			conn = ds.getConnection();
			String sql = "update member set passwd=?,name=?,email=?,phone=?,zipcode=?,address=?,job=? where id=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, memberBean.getPasswd());
			pstmt.setString(2, memberBean.getName());
			pstmt.setString(3, memberBean.getEmail());
			pstmt.setString(4, memberBean.getPhone());
			pstmt.setString(5, memberBean.getZipcode());
			pstmt.setString(6, memberBean.getAddress());
			pstmt.setString(7, memberBean.getJob());
			pstmt.setString(8, id);
			if(pstmt.executeUpdate() > 0) b = true;
		} catch (Exception e) {
			System.out.println("memberUpdate err: " + e);
		} finally {
			try {
				if (rs != null) rs.close();
				if (pstmt != null) pstmt.close();
				if (conn != null) conn.close();
			} catch (Exception e2) {
				// TODO: handle exception
			}
		}
		return b;
	}
	
	// 관리자 로그인용
	public boolean adminLoginCheck(String adminid, String adminpasswd) {
		boolean b = false;
		
		try {
			conn = ds.getConnection();
			String sql = "select * from admin where admin_id=? and admin_passwd=?";	// 동시만족해야징
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, adminid);
			pstmt.setString(2, adminpasswd);
			rs = pstmt.executeQuery();
			b = rs.next();
		} catch (Exception e) {
			System.out.println("adminLoginCheck err: " + e);
		} finally {
			try {
				if (rs != null) rs.close();
				if (pstmt != null) pstmt.close();
				if (conn != null) conn.close();
			} catch (Exception e2) {
				// TODO: handle exception
			}
		}
		
		return b;
	}
	
	// 관리자가 전체 회원자료 읽기
	public ArrayList<MemberBean> getMemberAll(){	// 원래 MemberDTO 써야돼ㅐ
		ArrayList<MemberBean> list = new ArrayList<MemberBean>();
		
		try {
			conn = ds.getConnection();
			String sql = "select * from member order by id asc";
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			while(rs.next()) {	// 자료가 있는동안 반복해
				MemberBean dto = new MemberBean();
				dto.setId(rs.getString("id"));
				dto.setName(rs.getString("name"));
				dto.setPasswd(rs.getString("passwd"));				
				dto.setEmail(rs.getString("email"));				
				dto.setPhone(rs.getString("phone"));	
				// 관리자가 고객의 정보를 일부만 일단 보는거야
				list.add(dto);
			}
		} catch (Exception e) {
			System.out.println("getMemberAll err: " + e);
		} finally {
			try {
				if (rs != null) rs.close();
				if (pstmt != null) pstmt.close();
				if (conn != null) conn.close();
			} catch (Exception e2) {
				// TODO: handle exception
			}
		}
		
		return list;
	}
	
}

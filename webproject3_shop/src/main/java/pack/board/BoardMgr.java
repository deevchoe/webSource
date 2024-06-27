package pack.board;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

public class BoardMgr {
	private Connection conn;
	private PreparedStatement pstmt;
	private ResultSet rs;
	private DataSource ds;
	
	private int recTot;	// 전체 레코드 수
	private int pList = 5; // 페이지 당 출력 행 수
	private int pageSu;	// 전체 페이지 수
	
	public BoardMgr() {
		try {
			Context context = new InitialContext();
			ds = (DataSource)context.lookup("java:comp/env/jdbc_maria");
		} catch (Exception e) {
			System.out.println("db 연결 실패 : " + e);
		}
	}
	
	//public ArrayList<BoardDTO> getDataAll(int page){
	public ArrayList<BoardDTO> getDataAll(int page, String stype, String sword){
		ArrayList<BoardDTO> list = new ArrayList<BoardDTO>(); 	// 게시글 목록을 담을 ArrayList 생성.
		
		String sql = "select * from board"; // 전체 게시글을 가져오는 sql문
		try {
			conn = ds.getConnection();	// 데이터베이스와 연결
			
			if(sword == null) {		// 검색이 없는 경우
				sql += " order by gnum desc, onum asc";		// 전체 게시글을 가져올 때 정렬 순서 지정
				pstmt = conn.prepareStatement(sql);		// sql문을 실행할 PreparedStatement 생성
			}else {	// 검색이 있는 경우
				sql += " where " + stype + " like ?";	// 검색어를 포함하는 sql문 생성      stype : 검색 유형. 제목인지 작성자인지 
				sql += " order by gnum desc, onum asc";	// 검색 결과의 정렬 순서 지정.
				pstmt = conn.prepareStatement(sql);		// sql문을 실행할 PreparedStatement 생성
				pstmt.setString(1, "%" + sword + "%");
				// %는 SQL의 like 연산자에서 사용되는 와일드카드 문자.
				// like 연산자는 주어진 패턴과 일치하는 문자열을 검색할 때 사용
				/*
				 검색어가 "apple"인 경우:

					%apple%은 "apple", "pineapple", "applesauce" 등과 일치
					apple%는 "apple", "applesauce"와만 일치
					%apple은 "apple", "pineapple"와만 일치
				 */
			}
			
			rs = pstmt.executeQuery(); // sql문을 실행하고 결과를 rs에 저장
			
			// paging 처리 : 특정 페이지의 게시글로 이동하기 위해 ResultSet의 레코드 포인터를 이동시킨다.
			for(int i = 0; i < (page - 1) * pList; i++) {
				rs.next();	// 레코드 포인터를 다음 레코드로 이동   포인터의 위치 :  0, 9, 19 ...
			}
			
			int k = 0;
			while(rs.next() && k < pList) {	// ResultSet에서 다음 레코드를 읽어와서 처리. 한 페이지에 최대 pList 개의 게시글이 표시된다.
				BoardDTO dto = new BoardDTO();	// 각 게시글의 정보를 담을 BoardDTO 객체 생성
				// ResultSet에서 현재 레코드의 정보를 읽어와서 BoardTO 객체에 설정한다.
				dto.setNum(rs.getInt("num"));
				dto.setName(rs.getString("name"));
				dto.setTitle(rs.getString("title"));
				dto.setBdate(rs.getString("bdate"));
				dto.setReadcnt(rs.getInt("readcnt"));
				dto.setNested(rs.getInt("nested"));
				list.add(dto);	// BoardDTO 객체를 ArrayList에 추가한다.
				k++;
			}
		} catch (Exception e) {
			System.out.println("getDataAll err : " + e);
		} finally {
			try {
				if(rs != null) rs.close();
				if(pstmt != null) pstmt.close();
				if(conn != null) conn.close();
			} catch (Exception e2) {
				// TODO: handle exception
			}
		}
		
		return list;	// 처리된 게시글 목록을 반환한다.
	}
	
	public int currentMaxNum() { // board 테이블의 최대 번호 반납
		String sql = "select max(num) from board";
		int num = 0;
		try {
			conn = ds.getConnection();
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			if(rs.next()) num = rs.getInt(1);
		} catch (Exception e) {
			System.out.println("currentMaxNum err : " + e);
		} finally {
			try {
				if(rs != null) rs.close();
				if(pstmt != null) pstmt.close();
				if(conn != null) conn.close();
			} catch (Exception e2) {
				// TODO: handle exception
			}
		}
		
		return num;
	}
	
	// 이게 저장이다ㅏ...
	public void saveData(BoardFormBean bean) {	// board insert
		String sql = "insert into board values(?,?,?,?,?,?,?,?,?,?,?,?)";
		
		try {
			conn = ds.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, bean.getNum());
			pstmt.setString(2, bean.getName());
			pstmt.setString(3, bean.getPass());
			pstmt.setString(4, bean.getMail());
			pstmt.setString(5, bean.getTitle());
			pstmt.setString(6, bean.getCont());
			pstmt.setString(7, bean.getBip());
			pstmt.setString(8, bean.getBdate());
			pstmt.setInt(9, 0); 	// readcnt는 0 줘야해ㅐ 그래야 누적돼. 초기치가 0
			pstmt.setInt(10, bean.getGnum());
			pstmt.setInt(11, 0);	// onum
			pstmt.setInt(12, 0);	// nested
			
			pstmt.executeUpdate();
		} catch (Exception e) {
			System.out.println("saveData err : " + e);
		} finally {
			try {
				if(rs != null) rs.close();
				if(pstmt != null) pstmt.close();
				if(conn != null) conn.close();
			} catch (Exception e2) {
				// TODO: handle exception
			}
		}
	}
	
	public void totalList() {	// 전체 레코드 수 구하기
		String sql = "select count(*) from board";
		try {
			conn = ds.getConnection();
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			rs.next();
			recTot = rs.getInt(1);
			System.out.println("전체 레코드 수 : " + recTot);
		} catch (Exception e) {
			System.out.println("totalList err : " + e);
		} finally {
			try {
				if(rs != null) rs.close();
				if(pstmt != null) pstmt.close();
				if(conn != null) conn.close();
			} catch (Exception e2) {
				// TODO: handle exception
			}
		}
	}
	
	public int getPageSu() {	// 전체 페이지 수 반환
		pageSu = recTot / pList;
		if(recTot % pList > 0) pageSu++;
		return pageSu;
	}
	
	public void updateReadcnt(String num) {		// 조회수 증가
		String sql = "update board set readcnt=readcnt + 1 where num=?";
		try {
			conn = ds.getConnection();	// conn으로 연결
			pstmt = conn.prepareStatement(sql);	// pstmt로 sql문 처리
			pstmt.setString(1, num);	// num이 넘어오니까 ?에 num
			pstmt.executeUpdate();
		} catch (Exception e) {
			System.out.println("updateReadcnt err : " + e);
		} finally {
			try {
				if(rs != null) rs.close();
				if(pstmt != null) pstmt.close();
				if(conn != null) conn.close();
			} catch (Exception e2) {
				// TODO: handle exception
			}
		}
	}
	
	public BoardDTO getData(String num) {
		String sql = "select * from board where num=?";
		BoardDTO dto = null;
		
		try {
			conn = ds.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, num);
			rs = pstmt.executeQuery();
			// 읽어왔어.
			
			if(rs.next()) {	// 있을 수도 있고 없을 수도 있어 rs.next()가 있니?자료가 있니? 그러면 dto = new ~ 인스턴스해서 보고싶은거 불러와
				dto = new BoardDTO();
				dto.setName(rs.getString("name"));
				dto.setPass(rs.getString("pass"));
				dto.setMail(rs.getString("mail"));
				dto.setTitle(rs.getString("title"));
				dto.setCont(rs.getString("cont"));
				dto.setBip(rs.getString("bip"));
				dto.setBdate(rs.getString("bdate"));
				dto.setReadcnt(rs.getInt("readcnt"));
				//dto.setNested(rs.getInt("nested"));  들여쓰기는 댓글에서 볼거니깐 없어도돼	
			}
		} catch (Exception e) {
			System.out.println("getData err : " + e);
		} finally {
			try {
				if(rs != null) rs.close();
				if(pstmt != null) pstmt.close();
				if(conn != null) conn.close();
			} catch (Exception e2) {
				// TODO: handle exception
			}
		}
		
		return dto;
	}
	
	public BoardDTO getReplyData(String num) {	// 댓글용
		String sql = "select * from board where num=?";
		BoardDTO dto = null;
		
		try {
			conn = ds.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, num);
			rs = pstmt.executeQuery();
			// 읽어왔어.
			
			if(rs.next()) {	// 있을 수도 있고 없을 수도 있어 rs.next()가 있니?자료가 있니? 그러면 dto = new ~ 인스턴스해서 보고싶은거 불러와
				dto = new BoardDTO();
				dto.setTitle(rs.getString("title"));	// 원글의 제목
				dto.setGnum(rs.getInt("gnum"));			// 원글의 gnum
				dto.setOnum(rs.getInt("onum"));			// 원글의 onum
				dto.setNested(rs.getInt("nested"));		// 들여쓰기
			}
		} catch (Exception e) {
			System.out.println("getReplyData err : " + e);
		} finally {
			try {
				if(rs != null) rs.close();
				if(pstmt != null) pstmt.close();
				if(conn != null) conn.close();
			} catch (Exception e2) {
				// TODO: handle exception
			}
		}
		
		return dto;
	}
	
	public void updateOnum(int gnum, int onum) {	// 댓글용 : onum 갱신
		// 같은 그룹의 레코드는 모두 작업에 참여 - 같은 그룹의 onum 값 갱신
		// 댓글의 onum은 이미 db에 있는 onum보다 크거나 같은 값을 변경함
		String sql = "update board set onum = onum + 1 where onum >= ? and gnum = ?";
		
		try {
			conn = ds.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, onum);
			pstmt.setInt(2, gnum);
			pstmt.executeUpdate();
		} catch (Exception e) {
			System.out.println("updateOnum err : " + e);
		} finally {
			try {
				if(rs != null) rs.close();
				if(pstmt != null) pstmt.close();
				if(conn != null) conn.close();
			} catch (Exception e2) {
				// TODO: handle exception
			}
		}		
	}
	
	public void replySave(BoardFormBean bean) {		// 댓글용 - 저장
		String sql = "insert into board values(?,?,?,?,?,?,?,?,?,?,?,?)";
		
		try {
			conn = ds.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, bean.getNum());
			pstmt.setString(2, bean.getName());
			pstmt.setString(3, bean.getPass());
			pstmt.setString(4, bean.getMail());
			pstmt.setString(5, bean.getTitle());
			pstmt.setString(6, bean.getCont());
			pstmt.setString(7, bean.getBip());
			pstmt.setString(8, bean.getBdate());
			pstmt.setInt(9, 0); 	// readcnt는 0 줘야해ㅐ 그래야 누적돼. 초기치가 0
			pstmt.setInt(10, bean.getGnum());
			
			pstmt.setInt(11, bean.getOnum());	// onum
			pstmt.setInt(12, bean.getNested());	// nested
			
			pstmt.executeUpdate();
		} catch (Exception e) {
			System.out.println("replySave err : " + e);
		} finally {
			try {
				if(rs != null) rs.close();
				if(pstmt != null) pstmt.close();
				if(conn != null) conn.close();
			} catch (Exception e2) {
				// TODO: handle exception
			}
		}
	}
	
	public boolean checkPass(int num, String user_pass) {	// 글수정에서 비번 비교용
		boolean b = false;
		
		String sql = "select pass from board where num=?";
		try {
			conn = ds.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, num);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				if(user_pass.equals(rs.getString("pass"))) {	// user_pass : 사용자가 입력한 비밀번호 pass : db 속 패스워드
					b = true;
				}
			}
		} catch (Exception e) {
			System.out.println("checkPass err : " + e);
		} finally {
			try {
				if(rs != null) rs.close();
				if(pstmt != null) pstmt.close();
				if(conn != null) conn.close();
			} catch (Exception e2) {
				// TODO: handle exception
			}
		}
		
		return b;
	}
	
	public void saveEdit(BoardFormBean bean) {
		String sql = "update board set name=?, mail=?, title=?, cont=? where num=?";
		
		try {
			conn = ds.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, bean.getName());
			pstmt.setString(2, bean.getMail());
			pstmt.setString(3, bean.getTitle());
			pstmt.setString(4, bean.getCont());
			pstmt.setInt(5, bean.getNum());
			pstmt.executeUpdate();
		} catch (Exception e) {
			System.out.println("saveEdit err : " + e);
		} finally {
			try {
				if(rs != null) rs.close();
				if(pstmt != null) pstmt.close();
				if(conn != null) conn.close();
			} catch (Exception e2) {
				// TODO: handle exception
			}
		}
	}
	
	public void delData(String num) {
		String sql = "delete from board where num=?";
		
		try {
			conn = ds.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, num);
			pstmt.executeUpdate();
		} catch (Exception e) {
			System.out.println("saveEdit err : " + e);
		} finally {
			try {
				if(rs != null) rs.close();
				if(pstmt != null) pstmt.close();
				if(conn != null) conn.close();
			} catch (Exception e2) {
				// TODO: handle exception
			}
		}
	}
}

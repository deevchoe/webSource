package pack;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

public class ConnClass2 {
	private Connection conn;
	private PreparedStatement pstmt;
	private ResultSet rs;
	public static int max = 0;
	public static int min = 2147483647;
	
	public ConnClass2() {
		try {
			Class.forName("org.mariadb.jdbc.Driver");
		} catch (Exception e) {
			System.out.println("ConnClass err : " + e);
		}
	}
	
	public ArrayList<JikwonDTO> getData(String buser){
		ArrayList<JikwonDTO> list = new ArrayList<JikwonDTO>();
		
		try {
			String url="jdbc:mariadb://localhost:3306/test";
			conn = DriverManager.getConnection(url, "root", "9112");
			String sql = "select jikwon_no, jikwon_name, jikwon_jik, jikwon_gen, jikwon_pay from jikwon" +
						" where buser_num in (select buser_no from buser where buser_name = ?)";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, buser);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				JikwonDTO dto = new JikwonDTO();
				dto.setNo(rs.getString("jikwon_no"));
				dto.setName(rs.getString("jikwon_name"));
				dto.setJik(rs.getString("jikwon_jik"));
				dto.setGen(rs.getString("jikwon_gen"));
				list.add(dto);
				if(max < rs.getInt("jikwon_pay")) {
					max = rs.getInt("jikwon_pay");
				}
				if(min > rs.getInt("jikwon_pay")) {
					min = rs.getInt("jikwon_pay");
				}
			}
		} catch (Exception e) {
			System.out.println("getDataAll err : " + e);
		}finally {
			try {
				if(rs != null) rs.close();
				if(pstmt != null) pstmt.close();
				if(conn != null) conn.close();
			} catch (Exception e2) {
				// TODO: handle exception
			}
		}
		return list;
	}
}

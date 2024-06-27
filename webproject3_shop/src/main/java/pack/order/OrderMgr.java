package pack.order;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

public class OrderMgr {
	private Connection conn;
	private PreparedStatement pstmt;
	private ResultSet rs;
	private DataSource ds;
	
	public OrderMgr() {
		try {
			Context context = new InitialContext();
			ds = (DataSource) context.lookup("java:comp/env/jdbc_maria");
		} catch (Exception e) {
			System.out.println("OrderMgr db 연결 실패 : " + e.getMessage());
		}
		
	}
	
	public ArrayList<OrderBean> getOrder(String id){
		ArrayList<OrderBean> list = new ArrayList<OrderBean>();
		
		try {
			conn = ds.getConnection();
			String sql = "select * from shop_order where id = ? order by no desc";	// 자기가 주문한 상품을 최근꺼 먼저 봄
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, id);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				OrderBean bean = new OrderBean();
				bean.setNo(rs.getString("no"));
				bean.setProduct_no(rs.getString("product_no"));
				bean.setQuantity(rs.getString("quantity"));
				bean.setSdate(rs.getString("sdate"));
				bean.setState(rs.getString("state"));
				bean.setId(rs.getString("id"));
				list.add(bean);
			}
		} catch (Exception e) {
			System.out.println("getOrder err: " + e);
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
	
	public void insertOrder(OrderBean bean) {
		try {
			conn = ds.getConnection();
			String sql = "insert into shop_order(product_no, quantity, sdate, state, id) values(?,?,now(),?,?)";	// oracle은 sysdate mariadb와 mysql은 now()
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, bean.getProduct_no());
			pstmt.setString(2, bean.getQuantity());
			pstmt.setString(3, "1");	// ?의 갯수다. 그래서 3번이 주문상태야ㅏㅏㅏ 1:접수, 2:입금확인, 3:배송준비 4:배송중, 5:처리결과 state???  sdate
			pstmt.setString(4, bean.getId());
			pstmt.executeUpdate();
		} catch (Exception e) {
			System.out.println("insertOrder err: " + e);
		} finally {
			try {
				if (rs != null) rs.close();
				if (pstmt != null) pstmt.close();
				if (conn != null) conn.close();
			} catch (Exception e2) {
				// TODO: handle exception
			}
		}
		
	}
	
	public ArrayList<OrderBean> getOrderAll(){
		ArrayList<OrderBean> list = new ArrayList<OrderBean>();
		
		try {
			conn = ds.getConnection();
			String sql = "select * from shop_order order by no desc";	// 자기가 주문한 상품을 최근꺼 먼저 봄
			
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				OrderBean bean = new OrderBean();
				bean.setNo(rs.getString("no"));
				bean.setProduct_no(rs.getString("product_no"));
				bean.setQuantity(rs.getString("quantity"));
				bean.setSdate(rs.getString("sdate"));
				bean.setState(rs.getString("state"));
				bean.setId(rs.getString("id"));
				list.add(bean);
			}
		} catch (Exception e) {
			System.out.println("getOrderAll err: " + e);
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
	
	
	public OrderBean getOrderDetail(String no){	// 상품 하나만 읽어올거야
		OrderBean bean = null;
		
		try {
			conn = ds.getConnection();
			String sql = "select * from shop_order where no = ?";	// 자기가 주문한 상품을 최근꺼 먼저 봄
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, no);
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				bean = new OrderBean();
				bean.setNo(rs.getString("no"));
				bean.setProduct_no(rs.getString("product_no"));
				bean.setQuantity(rs.getString("quantity"));
				bean.setSdate(rs.getString("sdate"));
				bean.setState(rs.getString("state"));
				bean.setId(rs.getString("id"));
			}
		} catch (Exception e) {
			System.out.println("getOrderDetail err: " + e);
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
	
	public boolean updateOrder(String no, String state) {
		boolean b = false;
		
		try {
			conn = ds.getConnection();
			
			String sql = "update shop_order set state=? where no=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, state);
			pstmt.setString(2, no);
			if(pstmt.executeUpdate() > 0) b= true;
		} catch (Exception e) {
			System.out.println("updateOrder err: " + e);
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
	
	public boolean deleteOrder(String no) {
		boolean b = false;
		
		try {
			conn = ds.getConnection();
			
			String sql = "delete from shop_order where no=?";	// 고객의 의사 없이는 지울 수 없어ㅓㅓ 고객이 요청했다 가정.
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, no);
			if(pstmt.executeUpdate() > 0) b= true;
		} catch (Exception e) {
			System.out.println("deleteOrder err: " + e);
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
}
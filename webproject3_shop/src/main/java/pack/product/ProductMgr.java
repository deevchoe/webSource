package pack.product;

import java.io.File;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.servlet.http.HttpServletRequest;
import javax.sql.DataSource;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

//import com.oreilly.servlet.MultipartRequest;
//import com.oreilly.servlet.multipart.DefaultFileRenamePolicy

import pack.order.OrderBean;

public class ProductMgr {
	private Connection conn;
	private PreparedStatement pstmt;
	private ResultSet rs;
	private DataSource ds;
	
	public ProductMgr() {
		try {
			Context context = new InitialContext();
			ds = (DataSource) context.lookup("java:comp/env/jdbc_maria");
		} catch (Exception e) {
			System.out.println("db 연결 실패 : " + e.getMessage());
		}
	}
	
	public ArrayList<ProductDTO> getproductAll(){
		ArrayList<ProductDTO> list = new ArrayList<ProductDTO>();
		
		try {
			conn = ds.getConnection();
			String sql = "select * from shop_product order by no desc";
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				ProductDTO dto = new ProductDTO();
				dto.setNo(rs.getInt("no"));
				dto.setName(rs.getString("name"));
				dto.setPrice(rs.getString("price"));
				dto.setDetail(rs.getString("detail"));
				dto.setSdate(rs.getString("sdate"));
				dto.setStock(rs.getString("stock"));
				dto.setImage(rs.getString("image"));
				list.add(dto);
			}
		} catch (Exception e) {
			System.out.println("getproductAll err: " + e);
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
	/*
	public boolean insertProduct(HttpServletRequest request) {	// request가 넘어와 request가 무슨 타입이야? HttpServletRequest 타입
		boolean b = false;
		
		try {
			// 업로드할 이미지 경로(이때 경로는 절대경로여야 한다. => 여기선 upload 폴더)
			String uploadDir = "D:\\Study\\Academy\\Acorn\\websource\\wproject6_shop\\src\\main\\webapp\\upload";	// 이클립스에서 대상 폴더 우클릭 Properties에 있는 경로 복붙
			MultipartRequest multi = new MultipartRequest(request, uploadDir, 	// cos.jar가 있어야 가능
							5 * 1024 * 1024, "UTF-8", new DefaultFileRenamePolicy()); 	// multipartrequest 생성자에 넣어주는거 5mb로 할래! 맨뒤는 정책. 그대로 두면됨
			//System.out.println(multi.getParameter("name"));
			//System.out.println(multi.getParameter("price"));
			conn = ds.getConnection();
			String sql = "insert into shop_product(name,price,detail,sdate,stock,image)" +	// no는 자동증가니까 제외
							"values(?,?,?,now(),?,?)";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, multi.getParameter("name"));
			pstmt.setString(2, multi.getParameter("price"));
			pstmt.setString(3, multi.getParameter("detail"));
			pstmt.setString(4, multi.getParameter("stock"));
			
			if(multi.getFilesystemName("image") == null) {
				// 상품 등록 시 이미지를 선택하지 않은 경우
				pstmt.setString(5, multi.getParameter("ready.gif")); 	// 이미지
			}else {
				pstmt.setString(5, multi.getFilesystemName("image")); 	// 이미지				
			}
			if(pstmt.executeUpdate() > 0) b = true;
		} catch (Exception e) {
			System.out.println("insertProduct err: " + e);
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
	*/
	public boolean insertProduct(HttpServletRequest request) {
        boolean b = false;
        
        try {
            // 업로드할 이미지 경로 : upload 폴더(절대 경로)
            String uploadDir = "C:/work/jsou/wproject3_shop/src/main/webapp/upload";
            File uploadDirFile = new File(uploadDir);
            if (!uploadDirFile.exists()) {
                uploadDirFile.mkdirs();
            }
            
            // DiskFileItemFactory: 파일 아이템을 생성하기 위해 사용. 임시 파일의 저장소를 관리함.
            DiskFileItemFactory factory = new DiskFileItemFactory();
            //ServletFileUpload: HTTP 요청에서 파일을 처리함.
            ServletFileUpload upload = new ServletFileUpload(factory);
            upload.setSizeMax(5 * 1024 * 1024); // 파일 크기 제한 (5MB)
            
            // parseRequest: HTTP 요청을 파싱하여 폼 필드와 파일 필드를 분리한다.
            // FileItem: 폼 필드와 파일 필드를 나타내는 객체
            List<FileItem> formItems = upload.parseRequest(request);

            String name = null;
            String price = null;
            String detail = null;
            String stock = null;
            String image = null;

            for (FileItem item : formItems) {
            	// item.isFormField(): 현재 필드가 폼 필드인지 파일 필드인지 검사
                if (item.isFormField()) {
                    // 일반 필드
                    String fieldName = item.getFieldName();
                    String fieldValue = item.getString("UTF-8");

                    if ("name".equals(fieldName)) {
                        name = fieldValue;
                    } else if ("price".equals(fieldName)) {
                        price = fieldValue;
                    } else if ("detail".equals(fieldName)) {
                        detail = fieldValue;
                    } else if ("stock".equals(fieldName)) {
                        stock = fieldValue;
                    }
                } else {
                    // 파일 필드
                    String fileName = new File(item.getName()).getName();
                    // File.separator는 운영 체제에 독립적인 파일 경로 구분자를 제공하기 위해 사용된다. 
                    // os에 따라 파일 경로 구분자가 다르기 때문에 이를 사용하면 코드의 이식성을 높일 수 있다.
                    String filePath = uploadDir + File.separator + fileName;
                    File storeFile = new File(filePath);
                    item.write(storeFile);  // 파일 필드를 지정된 위치에 저장
                    image = fileName;
                }
            }

            if (image == null) {
                image = "ready.gif";
            }

            conn = ds.getConnection();
            String sql = "insert into shop_product(name,price,detail,sdate,stock,image)" +
                         "values(?,?,?,now(),?,?)";
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, name);
            pstmt.setString(2, price);
            pstmt.setString(3, detail);
            pstmt.setString(4, stock);
            pstmt.setString(5, image);

            if (pstmt.executeUpdate() > 0) b = true; 
        } catch (FileUploadException e) {
            System.out.println("insertProduct FileUploadException : " + e);
        } catch (Exception e) {
            System.out.println("insertProduct Exception : " + e);
        } finally {
            try {
                if (rs != null) rs.close();
                if (pstmt != null) pstmt.close();
                if (conn != null) conn.close();
            } catch (Exception e2) {
                System.out.println("insertProduct close Exception : " + e2);
            }
        }

        return b;
    }

	
	
	public ProductDTO getProduct(String no) {
		ProductDTO dto = null;
		
		try {
			conn = ds.getConnection();
			String sql = "select * from shop_product where no=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, no);
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				dto = new ProductDTO();
				dto.setNo(rs.getInt("no"));
				dto.setName(rs.getString("name"));
				dto.setPrice(rs.getString("price"));
				dto.setDetail(rs.getString("detail"));
				dto.setSdate(rs.getString("sdate"));
				dto.setStock(rs.getString("stock"));
				dto.setImage(rs.getString("image"));
			}
		} catch (Exception e) {
			System.out.println("getProduct err: " + e);
		} finally {
			try {
				if (rs != null) rs.close();
				if (pstmt != null) pstmt.close();
				if (conn != null) conn.close();
			} catch (Exception e2) {
				// TODO: handle exception
			}
		}
		
		return dto;
	}
	
	/*
	public boolean updateProduct(HttpServletRequest request) {	// 회원이 수정하겠다고 입력한 모든게 여기로 넘어온다
		boolean b = false;
		
		try {
			// 업로드할 이미지 경로(이때 경로는 절대경로여야 한다. => 여기선 upload 폴더)
			String uploadDir = "D:\\Study\\Academy\\Acorn\\websource\\wproject6_shop\\src\\main\\webapp\\upload";	// 이클립스에서 대상 폴더 우클릭 Properties에 있는 경로 복붙
			MultipartRequest multi = new MultipartRequest(request, uploadDir, 	// cos.jar가 있어야 가능
							5 * 1024 * 1024, "UTF-8", new DefaultFileRenamePolicy()); 	// multipartrequest 생성자에 넣어주는거 5mb로 할래! 맨뒤는 정책. 그대로 두면됨
			//System.out.println(multi.getParameter("name"));
			//System.out.println(multi.getParameter("price"));
			conn = ds.getConnection();

			if(multi.getFilesystemName("image") == null) {
				String sql = "update shop_product set name=?,price=?,detail=?,stock=? where no=?";
				pstmt = conn.prepareStatement(sql);
				pstmt.setString(1, multi.getParameter("name"));
				pstmt.setString(2, multi.getParameter("price"));
				pstmt.setString(3, multi.getParameter("detail"));
				pstmt.setString(4, multi.getParameter("stock"));
				pstmt.setString(5, multi.getParameter("no"));
			}else {
				String sql = "update shop_product set name=?,price=?,detail=?,stock=?,image=? where no=?";
				pstmt = conn.prepareStatement(sql);
				pstmt.setString(1, multi.getParameter("name"));
				pstmt.setString(2, multi.getParameter("price"));
				pstmt.setString(3, multi.getParameter("detail"));
				pstmt.setString(4, multi.getParameter("stock"));
				pstmt.setString(5, multi.getFilesystemName("image")); 	// 이미지				
				pstmt.setString(6, multi.getParameter("no"));
			}
			if(pstmt.executeUpdate() > 0) b = true;
		} catch (Exception e) {
			System.out.println("updateProduct err: " + e);
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
	} */
	
    public boolean updateProduct(HttpServletRequest request) {
        boolean b = false;
        
        try {
            // 업로드할 이미지 경로 : upload 폴더(절대 경로)
            String uploadDir = "C:/work/jsou/wproject3_shop/src/main/webapp/upload";
            File uploadDirFile = new File(uploadDir);
            if (!uploadDirFile.exists()) {
                uploadDirFile.mkdirs();
            }

            DiskFileItemFactory factory = new DiskFileItemFactory();
            ServletFileUpload upload = new ServletFileUpload(factory);
            upload.setSizeMax(5 * 1024 * 1024); // 파일 크기 제한 (5MB)

            List<FileItem> formItems = upload.parseRequest(request);

            String name = null;
            String price = null;
            String detail = null;
            String stock = null;
            String no = null;
            String image = null;

            for (FileItem item : formItems) {
                if (item.isFormField()) {
                    // 일반 필드
                    String fieldName = item.getFieldName();
                    String fieldValue = item.getString("UTF-8");

                    if ("name".equals(fieldName)) {
                        name = fieldValue;
                    } else if ("price".equals(fieldName)) {
                        price = fieldValue;
                    } else if ("detail".equals(fieldName)) {
                        detail = fieldValue;
                    } else if ("stock".equals(fieldName)) {
                        stock = fieldValue;
                    } else if ("no".equals(fieldName)) {
                        no = fieldValue;
                    }
                } else {
                    // 파일 필드
                    String fileName = new File(item.getName()).getName();
                    // UUID: UUID.randomUUID().toString()을 사용하여 파일 이름에 고유한 식별자를 추가. 
                    // 동일한 이름의 파일이 업로드 폴더에 이미 존재하는 경우에는 새로운 이름으로 파일 저장 가능
                    // 원래 파일 이름 앞에 UUID를 추가하여 고유한 파일 이름을 생성한다.
                    String uniqueFileName = UUID.randomUUID().toString() + "_" + fileName;
                    
                    // 파일 경로를 설정할 때 고유한 파일 이름을 사용한다.
                    String filePath = uploadDir + File.separator + uniqueFileName;
                    File storeFile = new File(filePath);
                    item.write(storeFile);
                    image = uniqueFileName;
                }
            }

            conn = ds.getConnection();

            if (image == null) {
                String sql = "update shop_product set name=?,price=?,detail=?,stock=? where no=?";
                pstmt = conn.prepareStatement(sql);
                pstmt.setString(1, name);
                pstmt.setString(2, price);
                pstmt.setString(3, detail);
                pstmt.setString(4, stock);
                pstmt.setString(5, no);
            } else {
                String sql = "update shop_product set name=?,price=?,detail=?,stock=?,image=? where no=?";
                pstmt = conn.prepareStatement(sql);
                pstmt.setString(1, name);
                pstmt.setString(2, price);
                pstmt.setString(3, detail);
                pstmt.setString(4, stock);
                pstmt.setString(5, image);
                pstmt.setString(6, no);
            }

            if (pstmt.executeUpdate() > 0) b = true; 
        } catch (FileUploadException e) {
            System.out.println("updateProduct FileUploadException : " + e);
        } catch (Exception e) {
            System.out.println("updateProduct Exception : " + e);
        } finally {
            try {
                if (rs != null) rs.close();
                if (pstmt != null) pstmt.close();
                if (conn != null) conn.close();
            } catch (Exception e2) {
                System.out.println("updateProduct close Exception : " + e2);
            }
        }

        return b;
    }
	
	public boolean deleteProduct(String no) {	// delete는 사실 이렇게 간단히 끝내면 안된다. 꼼꼼히 체크해서 삭제해야 함
		boolean b = false;
		
		try {
			conn = ds.getConnection();
			String sql = "delete from shop_product where no=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, no);
			if(pstmt.executeUpdate() > 0) b = true;
		} catch (Exception e) {
			System.out.println("deleteProduct err: " + e);
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
	
	// 고객이 상품 주문 시 주문 수만큼 재고량 빼기
	public void reduceProduct(OrderBean bean) {
		try {
			conn = ds.getConnection();
			String sql = "update shop_product set stock=(stock - ?) where no=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, bean.getQuantity());
			pstmt.setString(2, bean.getProduct_no());
			pstmt.executeUpdate();
		} catch (Exception e) {
			System.out.println("reduceProduct err: " + e);
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
	
	
}
